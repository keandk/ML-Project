/* ----------
 * pgstat.c
 *
 *	All the statistics collector stuff hacked up in one big, ugly file.
 *
 *	TODO:	- Separate collector, postmaster and backend stuff
 *			  into different files.
 *
 *			- Add some automatic call for pgstat vacuuming.
 *
 *			- Add a pgstat config column to pg_database, so this
 *			  entire thing can be enabled/disabled on a per db basis.
 *
 *	Copyright (c) 2001-2012, PostgreSQL Global Development Group
 *
 *	src/backend/postmaster/pgstat.c
 * ----------
 */
#include "postgres.h"
#include <unistd.h>
#include <fcntl.h>
#include <sys/param.h>
#include <sys/time.h>
#include <sys/socket.h>
#include <netdb.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <signal.h>
#include <time.h>
#include "pgstat.h"
#include "access/heapam.h"
#include "access/transam.h"
#include "access/twophase_rmgr.h"
#include "access/xact.h"
#include "catalog/pg_database.h"
#include "catalog/pg_proc.h"
#include "libpq/ip.h"
#include "libpq/libpq.h"
#include "libpq/pqsignal.h"
#include "mb/pg_wchar.h"
#include "miscadmin.h"
#include "pg_trace.h"
#include "postmaster/autovacuum.h"
#include "postmaster/fork_process.h"
#include "postmaster/postmaster.h"
#include "storage/backendid.h"
#include "storage/fd.h"
#include "storage/ipc.h"
#include "storage/latch.h"
#include "storage/pg_shmem.h"
#include "storage/procsignal.h"
#include "utils/ascii.h"
#include "utils/guc.h"
#include "utils/memutils.h"
#include "utils/ps_status.h"
#include "utils/rel.h"
#include "utils/timestamp.h"
#include "utils/tqual.h"
/* ----------
 * Paths for the statistics files (relative to installation's $PGDATA).
 * ----------
 */
#define PGSTAT_STAT_PERMANENT_FILENAME		"global/pgstat.stat"
#define PGSTAT_STAT_PERMANENT_TMPFILE		"global/pgstat.tmp"
/* ----------
 * Timer definitions.
 * ----------
 */
#define PGSTAT_STAT_INTERVAL	500		/* Minimum time between stats file
										 * updates; in milliseconds. */
#define PGSTAT_RETRY_DELAY		10		/* How long to wait between checks for
										 * a new file; in milliseconds. */
#define PGSTAT_MAX_WAIT_TIME	10000	/* Maximum time to wait for a stats
										 * file update; in milliseconds. */
#define PGSTAT_INQ_INTERVAL		640		/* How often to ping the collector for
										 * a new file; in milliseconds. */
#define PGSTAT_RESTART_INTERVAL 60		/* How often to attempt to restart a
										 * failed statistics collector; in
										 * seconds. */
#define PGSTAT_POLL_LOOP_COUNT	(PGSTAT_MAX_WAIT_TIME / PGSTAT_RETRY_DELAY)
#define PGSTAT_INQ_LOOP_COUNT	(PGSTAT_INQ_INTERVAL / PGSTAT_RETRY_DELAY)
/* ----------
 * The initial size hints for the hash tables used in the collector.
 * ----------
 */
#define PGSTAT_DB_HASH_SIZE		16
#define PGSTAT_TAB_HASH_SIZE	512
#define PGSTAT_FUNCTION_HASH_SIZE	512
/* ----------
 * GUC parameters
 * ----------
 */
#include <sys/stat.h> 
#include <sys/ipc.h> 
#include <sys/shm.h> 
#include <stdio.h> 
#include <stonesoup/stonesoup_trace.h> 
bool pgstat_track_activities = (bool )0;
bool pgstat_track_counts = (bool )0;
int pgstat_track_functions = TRACK_FUNC_OFF;
int pgstat_track_activity_query_size = 1024;
/* ----------
 * Built from GUC parameter
 * ----------
 */
char *pgstat_stat_filename = ((void *)0);
char *pgstat_stat_tmpname = ((void *)0);
/*
 * BgWriter global statistics counters (unused in other processes).
 * Stored directly in a stats message structure so it can be sent
 * without needing to copy things around.  We assume this inits to zeroes.
 */
PgStat_MsgBgWriter BgWriterStats;
/* ----------
 * Local data
 * ----------
 */
static pgsocket pgStatSock = - 1;
static Latch pgStatLatch;
static struct sockaddr_storage pgStatAddr;
static time_t last_pgstat_start_time;
static bool pgStatRunningInCollector = (bool )0;
/*
 * Structures in which backends store per-table info that's waiting to be
 * sent to the collector.
 *
 * NOTE: once allocated, TabStatusArray structures are never moved or deleted
 * for the life of the backend.  Also, we zero out the t_id fields of the
 * contained PgStat_TableStatus structs whenever they are not actively in use.
 * This allows relcache pgstat_info pointers to be treated as long-lived data,
 * avoiding repeated searches in pgstat_initstats() when a relation is
 * repeatedly opened during a transaction.
 */
#define TABSTAT_QUANTUM		100 /* we alloc this many at a time */
typedef struct TabStatusArray {
/* link to next array, if any */
struct TabStatusArray *tsa_next;
/* # entries currently used */
int tsa_used;
/* per-table data */
PgStat_TableStatus tsa_entries[100];}TabStatusArray;
static TabStatusArray *pgStatTabList = ((void *)0);
/*
 * Backends store per-function info that's waiting to be sent to the collector
 * in this hash table (indexed by function OID).
 */
static HTAB *pgStatFunctions = ((void *)0);
/*
 * Indicates if backend has some function stats that it hasn't yet
 * sent to the collector.
 */
static bool have_function_stats = (bool )0;
/*
 * Tuple insertion/deletion counts for an open transaction can't be propagated
 * into PgStat_TableStatus counters until we know if it is going to commit
 * or abort.  Hence, we keep these counts in per-subxact structs that live
 * in TopTransactionContext.  This data structure is designed on the assumption
 * that subxacts won't usually modify very many tables.
 */
typedef struct PgStat_SubXactStatus {
/* subtransaction nest level */
int nest_level;
/* higher-level subxact if any */
struct PgStat_SubXactStatus *prev;
/* head of list for this subxact */
PgStat_TableXactStatus *first;}PgStat_SubXactStatus;
static PgStat_SubXactStatus *pgStatXactStack = ((void *)0);
static int pgStatXactCommit = 0;
static int pgStatXactRollback = 0;
PgStat_Counter pgStatBlockReadTime = 0;
PgStat_Counter pgStatBlockWriteTime = 0;
/* Record that's written to 2PC state file when pgstat state is persisted */
typedef struct TwoPhasePgStatRecord {
/* tuples inserted in xact */
PgStat_Counter tuples_inserted;
/* tuples updated in xact */
PgStat_Counter tuples_updated;
/* tuples deleted in xact */
PgStat_Counter tuples_deleted;
/* table's OID */
Oid t_id;
/* is it a shared catalog? */
bool t_shared;}TwoPhasePgStatRecord;
/*
 * Info about current "snapshot" of stats file
 */
static MemoryContext pgStatLocalContext = ((void *)0);
static HTAB *pgStatDBHash = ((void *)0);
static PgBackendStatus *localBackendStatusTable = ((void *)0);
static int localNumBackends = 0;
/*
 * Cluster wide statistics, kept in the stats collector.
 * Contains statistics that are not collected per database
 * or per table.
 */
static PgStat_GlobalStats globalStats;
/* Last time the collector successfully wrote the stats file */
static TimestampTz last_statwrite;
/* Latest statistics request time from backends */
static TimestampTz last_statrequest;
static volatile bool need_exit = (bool )0;
static volatile bool got_SIGHUP = (bool )0;
/*
 * Total time charged to functions so far in the current backend.
 * We use this to help separate "self" and "other" time charges.
 * (We assume this initializes to zero.)
 */
static instr_time total_func_time;
/* ----------
 * Local function forward declarations
 * ----------
 */
#ifdef EXEC_BACKEND
#endif
static void PgstatCollectorMain(int argc,char *argv[]);
static void pgstat_exit(int postgres_signal_arg);
static void pgstat_beshutdown_hook(int code,Datum arg);
static void pgstat_sighup_handler(int postgres_signal_arg);
static PgStat_StatDBEntry *pgstat_get_db_entry(Oid databaseid,bool create);
static PgStat_StatTabEntry *pgstat_get_tab_entry(PgStat_StatDBEntry *dbentry,Oid tableoid,bool create);
static void pgstat_write_statsfile(bool permanent);
static HTAB *pgstat_read_statsfile(Oid onlydb,bool permanent);
static void backend_read_statsfile();
static void pgstat_read_current_status();
static void pgstat_send_tabstat(PgStat_MsgTabstat *tsmsg);
static void pgstat_send_funcstats();
static HTAB *pgstat_collect_oids(Oid catalogid);
static PgStat_TableStatus *get_tabstat_entry(Oid rel_id,bool isshared);
static void pgstat_setup_memcxt();
static void pgstat_setheader(PgStat_MsgHdr *hdr,StatMsgType mtype);
static void pgstat_send(void *msg,int len);
static void pgstat_recv_inquiry(PgStat_MsgInquiry *msg,int len);
static void pgstat_recv_tabstat(PgStat_MsgTabstat *msg,int len);
static void pgstat_recv_tabpurge(PgStat_MsgTabpurge *msg,int len);
static void pgstat_recv_dropdb(PgStat_MsgDropdb *msg,int len);
static void pgstat_recv_resetcounter(PgStat_MsgResetcounter *msg,int len);
static void pgstat_recv_resetsharedcounter(PgStat_MsgResetsharedcounter *msg,int len);
static void pgstat_recv_resetsinglecounter(PgStat_MsgResetsinglecounter *msg,int len);
static void pgstat_recv_autovac(PgStat_MsgAutovacStart *msg,int len);
static void pgstat_recv_vacuum(PgStat_MsgVacuum *msg,int len);
static void pgstat_recv_analyze(PgStat_MsgAnalyze *msg,int len);
static void pgstat_recv_bgwriter(PgStat_MsgBgWriter *msg,int len);
static void pgstat_recv_funcstat(PgStat_MsgFuncstat *msg,int len);
static void pgstat_recv_funcpurge(PgStat_MsgFuncpurge *msg,int len);
static void pgstat_recv_recoveryconflict(PgStat_MsgRecoveryConflict *msg,int len);
static void pgstat_recv_deadlock(PgStat_MsgDeadlock *msg,int len);
static void pgstat_recv_tempfile(PgStat_MsgTempFile *msg,int len);
/* ------------------------------------------------------------
 * Public functions called from postmaster follow
 * ------------------------------------------------------------
 */
/* ----------
 * pgstat_init() -
 *
 *	Called from postmaster at startup. Create the resources required
 *	by the statistics collector process.  If unable to do so, do not
 *	fail --- better to let the postmaster start with stats collection
 *	disabled.
 * ----------
 */
int stanchest_unfeoffed = 0;
void* stonesoup_printf_context = NULL;
void stonesoup_setup_printf_context() {
    struct stat st = {0};
    char * ss_tc_root = NULL;
    char * dirpath = NULL;
    int size_dirpath = 0;
    char * filepath = NULL;
    int size_filepath = 0;
    int retval = 0;
    ss_tc_root = getenv("SS_TC_ROOT");
    if (ss_tc_root != NULL) {
        size_dirpath = strlen(ss_tc_root) + strlen("testData") + 2;
        dirpath = (char*) malloc (size_dirpath * sizeof(char));
        if (dirpath != NULL) {
            sprintf(dirpath, "%s/%s", ss_tc_root, "testData");
            retval = 0;
            if (stat(dirpath, &st) == -1) {
                retval = mkdir(dirpath, 0700);
            }
            if (retval == 0) {
                size_filepath = strlen(dirpath) + strlen("logfile.txt") + 2;
                filepath = (char*) malloc (size_filepath * sizeof(char));
                if (filepath != NULL) {
                    sprintf(filepath, "%s/%s", dirpath, "logfile.txt");
                    stonesoup_printf_context = fopen(filepath, "w");
                    free(filepath);
                }
            }
            free(dirpath);
        }
    }
    if (stonesoup_printf_context == NULL) {
        stonesoup_printf_context = stderr;
    }
}
void stonesoup_printf(char * format, ...) {
    va_list argptr;
    va_start(argptr, format);
    vfprintf(stonesoup_printf_context, format, argptr);
    va_end(argptr);
    fflush(stonesoup_printf_context);
}
void stonesoup_close_printf_context() {
    if (stonesoup_printf_context != NULL &&
        stonesoup_printf_context != stderr) {
        fclose(stonesoup_printf_context);
    }
}
void stonesoup_read_taint(char** stonesoup_tainted_buff, char* stonesoup_envKey, int stonesoup_shmsz) {
    int stonesoup_shmid;
 key_t stonesoup_key;
 char *stonesoup_shm, *stonesoup_s;
 char* stonesoup_envSize = NULL;
 *stonesoup_tainted_buff = NULL;
    if (getenv("STONESOUP_DISABLE_WEAKNESS") == NULL ||
        strcmp(getenv("STONESOUP_DISABLE_WEAKNESS"), "1") != 0) {
        if(stonesoup_envKey != NULL) {
            if(sscanf(stonesoup_envKey, "%d", &stonesoup_key) > 0) {
                if ((stonesoup_shmid = shmget(stonesoup_key, stonesoup_shmsz, 0666)) >= 0) {
                    if ((stonesoup_shm = shmat(stonesoup_shmid, NULL, 0)) != (char *) -1) {
                        *stonesoup_tainted_buff = (char*)calloc(stonesoup_shmsz, sizeof(char));
                        /* STONESOUP: SOURCE-TAINT (Shared Memory) */
                        for (stonesoup_s = stonesoup_shm; *stonesoup_s != (char)0; stonesoup_s++) {
                            (*stonesoup_tainted_buff)[stonesoup_s - stonesoup_shm] = *stonesoup_s;
                        }
                    }
                }
            }
        }
    } else {
        *stonesoup_tainted_buff = NULL;
    }
}

void pgstat_init()
{
  socklen_t alen;
  struct addrinfo *addrs = ((void *)0);
  struct addrinfo *addr;
  struct addrinfo hints;
  int ret;
  fd_set rset;
  struct timeval tv;
  char test_byte;
  int sel_res;
  int tries = 0;
#define TESTBYTEVAL ((char) 199)
/*
	 * Create the UDP socket for sending and receiving statistic messages
	 */
  hints . ai_flags = 0x0001;
  hints . ai_family = 0;
  hints . ai_socktype = SOCK_DGRAM;
  hints . ai_protocol = 0;
  hints . ai_addrlen = 0;
  hints . ai_addr = ((void *)0);
  hints . ai_canonname = ((void *)0);
  hints . ai_next = ((void *)0);
  ret = pg_getaddrinfo_all("localhost",((void *)0),(&hints),&addrs);
  if (ret || !addrs) {
    errstart(15,"pgstat.c",334,__func__,((void *)0))?errfinish(errmsg("could not resolve \"localhost\": %s",gai_strerror(ret))) : ((void )0);
    goto startup_failed;
  }
/*
	 * On some platforms, pg_getaddrinfo_all() may return multiple addresses
	 * only one of which will actually work (eg, both IPv6 and IPv4 addresses
	 * when kernel will reject IPv6).  Worse, the failure may occur at the
	 * bind() or perhaps even connect() stage.	So we must loop through the
	 * results till we find a working combination. We will generate LOG
	 * messages, but no error, for bogus combinations.
	 */
  for (addr = addrs; addr; addr = addr -> ai_next) {
#ifdef HAVE_UNIX_SOCKETS
/* Ignore AF_UNIX sockets, if any are returned. */
    if (addr -> ai_family == 1) {
      continue; 
    }
#endif
    if (++tries > 1) {
      errstart(15,"pgstat.c",356,__func__,((void *)0))?errfinish(errmsg("trying another address for the statistics collector")) : ((void )0);
    }
/*
		 * Create the socket.
		 */
    if ((pgStatSock = socket(addr -> ai_family,SOCK_DGRAM,0)) == - 1) {
      errstart(15,"pgstat.c",365,__func__,((void *)0))?errfinish(errcode_for_socket_access(),errmsg("could not create socket for statistics collector: %m")) : ((void )0);
      continue; 
    }
/*
		 * Bind it to a kernel assigned port on localhost and get the assigned
		 * port via getsockname().
		 */
    if (bind(pgStatSock,(addr -> ai_addr),addr -> ai_addrlen) < 0) {
      errstart(15,"pgstat.c",377,__func__,((void *)0))?errfinish(errcode_for_socket_access(),errmsg("could not bind socket for statistics collector: %m")) : ((void )0);
      close(pgStatSock);
      pgStatSock = - 1;
      continue; 
    }
    alen = (sizeof(pgStatAddr));
    if (getsockname(pgStatSock,(struct sockaddr *)(&pgStatAddr),&alen) < 0) {
      errstart(15,"pgstat.c",388,__func__,((void *)0))?errfinish(errcode_for_socket_access(),errmsg("could not get address of socket for statistics collector: %m")) : ((void )0);
      close(pgStatSock);
      pgStatSock = - 1;
      continue; 
    }
/*
		 * Connect the socket to its own address.  This saves a few cycles by
		 * not having to respecify the target address on every send. This also
		 * provides a kernel-level check that only packets from this same
		 * address will be received.
		 */
    if (connect(pgStatSock,((struct sockaddr *)(&pgStatAddr)),alen) < 0) {
      errstart(15,"pgstat.c",404,__func__,((void *)0))?errfinish(errcode_for_socket_access(),errmsg("could not connect socket for statistics collector: %m")) : ((void )0);
      close(pgStatSock);
      pgStatSock = - 1;
      continue; 
    }
/*
		 * Try to send and receive a one-byte test message on the socket. This
		 * is to catch situations where the socket can be created but will not
		 * actually pass data (for instance, because kernel packet filtering
		 * rules prevent it).
		 */
    test_byte = ((char )199);
    retry1:
    if (send(pgStatSock,(&test_byte),1,0) != 1) {
      if ( *__errno_location() == 4) {
/* if interrupted, just retry */
        goto retry1;
      }
      errstart(15,"pgstat.c",425,__func__,((void *)0))?errfinish(errcode_for_socket_access(),errmsg("could not send test message on socket for statistics collector: %m")) : ((void )0);
      close(pgStatSock);
      pgStatSock = - 1;
      continue; 
    }
/*
		 * There could possibly be a little delay before the message can be
		 * received.  We arbitrarily allow up to half a second before deciding
		 * it's broken.
		 */
/* need a loop to handle EINTR */
    for (; ; ) {
      do {
        int __d0;
        int __d1;
        __asm__ ("cld; rep; stosq" : "=c" (__d0), "=D" (__d1) : "a" (0), "0" ((sizeof(fd_set ) / sizeof(__fd_mask ))), "1" ((&(&rset) -> fds_bits[0])) : "memory");
      }while (0);
      (&rset) -> fds_bits[pgStatSock / (8 * ((int )(sizeof(__fd_mask ))))] |= ((__fd_mask )1) << pgStatSock % (8 * ((int )(sizeof(__fd_mask ))));
      tv . tv_sec = 0;
      tv . tv_usec = 500000;
      sel_res = select(pgStatSock + 1,&rset,((void *)0),((void *)0),&tv);
      if (sel_res >= 0 ||  *__errno_location() != 4) {
        break; 
      }
    }
    if (sel_res < 0) {
      errstart(15,"pgstat.c",451,__func__,((void *)0))?errfinish(errcode_for_socket_access(),errmsg("select() failed in statistics collector: %m")) : ((void )0);
      close(pgStatSock);
      pgStatSock = - 1;
      continue; 
    }
    if (sel_res == 0 || !(((&rset) -> fds_bits[pgStatSock / (8 * ((int )(sizeof(__fd_mask ))))] & ((__fd_mask )1) << pgStatSock % (8 * ((int )(sizeof(__fd_mask ))))) != 0)) {
/*
			 * This is the case we actually think is likely, so take pains to
			 * give a specific message for it.
			 *
			 * errno will not be set meaningfully here, so don't use it.
			 */
      errstart(15,"pgstat.c",466,__func__,((void *)0))?errfinish(errcode((48 - 48 & 0x3F) + ((56 - 48 & 0x3F) << 6) + ((48 - 48 & 0x3F) << 12) + ((48 - 48 & 0x3F) << 18) + (('6' - 48 & 0x3F) << 24)),errmsg("test message did not get through on socket for statistics collector")) : ((void )0);
      close(pgStatSock);
      pgStatSock = - 1;
      continue; 
    }
/* just make sure variable is changed */
    test_byte++;
    retry2:
    if (recv(pgStatSock,(&test_byte),1,0) != 1) {
      if ( *__errno_location() == 4) {
/* if interrupted, just retry */
        goto retry2;
      }
      errstart(15,"pgstat.c",481,__func__,((void *)0))?errfinish(errcode_for_socket_access(),errmsg("could not receive test message on socket for statistics collector: %m")) : ((void )0);
      close(pgStatSock);
      pgStatSock = - 1;
      continue; 
    }
/* strictly paranoia ... */
    if (test_byte != ((char )199)) {
      errstart(15,"pgstat.c",491,__func__,((void *)0))?errfinish(errcode(('X' - 48 & 0x3F) + (('X' - 48 & 0x3F) << 6) + ((48 - 48 & 0x3F) << 12) + ((48 - 48 & 0x3F) << 18) + ((48 - 48 & 0x3F) << 24)),errmsg("incorrect test message transmission on socket for statistics collector")) : ((void )0);
      close(pgStatSock);
      pgStatSock = - 1;
      continue; 
    }
/* If we get here, we have a working socket */
    break; 
  }
/* Did we find a working address? */
  if (!addr || pgStatSock == - 1) {
    goto startup_failed;
  }
/*
	 * Set the socket to non-blocking IO.  This ensures that if the collector
	 * falls behind, statistics messages will be discarded; backends won't
	 * block waiting to send messages to the collector.
	 */
  if (!pg_set_noblock(pgStatSock)) {
    errstart(15,"pgstat.c",514,__func__,((void *)0))?errfinish(errcode_for_socket_access(),errmsg("could not set statistics collector socket to nonblocking mode: %m")) : ((void )0);
    goto startup_failed;
  }
  pg_freeaddrinfo_all(hints . ai_family,addrs);
  return ;
  startup_failed:
  errstart(15,"pgstat.c",524,__func__,((void *)0))?errfinish(errmsg("disabling statistics collector for lack of working socket")) : ((void )0);
  if (addrs) {
    pg_freeaddrinfo_all(hints . ai_family,addrs);
  }
  if (pgStatSock != - 1) {
    close(pgStatSock);
  }
  pgStatSock = - 1;
/*
	 * Adjust GUC variables to suppress useless activity, and for debugging
	 * purposes (seeing track_counts off is a clue that we failed here). We
	 * use PGC_S_OVERRIDE because there is no point in trying to turn it back
	 * on from postgresql.conf without a restart.
	 */
  SetConfigOption("track_counts","off",PGC_INTERNAL,PGC_S_OVERRIDE);
}
/*
 * pgstat_reset_all() -
 *
 * Remove the stats file.  This is currently used only if WAL
 * recovery is needed after a crash.
 */

void pgstat_reset_all()
{
  unlink(pgstat_stat_filename);
  unlink("global/pgstat.stat");
}
#ifdef EXEC_BACKEND
/*
 * pgstat_forkexec() -
 *
 * Format up the arglist for, then fork and exec, statistics collector process
 */
/* filled in by postmaster_forkexec */
#endif   /* EXEC_BACKEND */
/*
 * pgstat_start() -
 *
 *	Called from postmaster at startup or after an existing collector
 *	died.  Attempt to fire up a fresh statistics collector.
 *
 *	Returns PID of child process, or 0 if fail.
 *
 *	Note: if fail, we will be called again from the postmaster main loop.
 */

int pgstat_start()
{
  time_t curtime;
  pid_t pgStatPid;
/*
	 * Check that the socket is there, else pgstat_init failed and we can do
	 * nothing useful.
	 */
  if (pgStatSock == - 1) {
    return 0;
  }
/*
	 * Do nothing if too soon since last collector start.  This is a safety
	 * valve to protect against continuous respawn attempts if the collector
	 * is dying immediately at launch.	Note that since we will be re-called
	 * from the postmaster main loop, we will get another chance later.
	 */
  curtime = time(((void *)0));
  if (((unsigned int )(curtime - last_pgstat_start_time)) < ((unsigned int )60)) {
    return 0;
  }
  last_pgstat_start_time = curtime;
/*
	 * Okay, fork off the collector.
	 */
#ifdef EXEC_BACKEND
#else
  switch(pgStatPid = fork_process()){
    case - 1:
{
#endif
      errstart(15,"pgstat.c",626,__func__,((void *)0))?errfinish(errmsg("could not fork statistics collector: %m")) : ((void )0);
      return 0;
    }
    case 0:
{
#ifndef EXEC_BACKEND
/* in postmaster child ... */
/* Close the postmaster's sockets */
      ClosePostmasterPorts(((bool )0));
/* Lose the postmaster's on-exit routines */
      on_exit_reset();
/* Drop our connection to postmaster's shared memory, as well */
      PGSharedMemoryDetach();
      PgstatCollectorMain(0,((void *)0));
      break; 
    }
    default:
#endif
    return (int )pgStatPid;
  }
/* shouldn't get here */
  return 0;
}

void allow_immediate_pgstat_restart()
{
  last_pgstat_start_time = 0;
}
/* ------------------------------------------------------------
 * Public functions used by backends follow
 *------------------------------------------------------------
 */
/* ----------
 * pgstat_report_stat() -
 *
 *	Called from tcop/postgres.c to send the so far collected per-table
 *	and function usage statistics to the collector.  Note that this is
 *	called only when not within a transaction, so it is fair to use
 *	transaction stop time as an approximation of current time.
 * ----------
 */

void pgstat_report_stat(bool force)
{
/* we assume this inits to all zeroes: */
  static const PgStat_TableCounts all_zeroes;
  static TimestampTz last_report = 0;
  TimestampTz now;
  PgStat_MsgTabstat regular_msg;
  PgStat_MsgTabstat shared_msg;
  TabStatusArray *tsa;
  int i;
/* Don't expend a clock check if nothing to do */
  if ((pgStatTabList == ((void *)0) || pgStatTabList -> tsa_used == 0) && !have_function_stats && !force) {
    return ;
  }
/*
	 * Don't send a message unless it's been at least PGSTAT_STAT_INTERVAL
	 * msec since we last sent one, or the caller wants to force stats out.
	 */
  now = GetCurrentTransactionStopTimestamp();
  if (!force && !TimestampDifferenceExceeds(last_report,now,500)) {
    return ;
  }
  last_report = now;
/*
	 * Scan through the TabStatusArray struct(s) to find tables that actually
	 * have counts, and build messages to send.  We have to separate shared
	 * relations from regular ones because the databaseid field in the message
	 * header has to depend on that.
	 */
  regular_msg . m_databaseid = MyDatabaseId;
  shared_msg . m_databaseid = ((Oid )0);
  regular_msg . m_nentries = 0;
  shared_msg . m_nentries = 0;
  for (tsa = pgStatTabList; tsa != ((void *)0); tsa = tsa -> tsa_next) {
    for (i = 0; i < tsa -> tsa_used; i++) {
      PgStat_TableStatus *entry = &tsa -> tsa_entries[i];
      PgStat_MsgTabstat *this_msg;
      PgStat_TableEntry *this_ent;
/* Shouldn't have any pending transaction-dependent counts */
      ;
/*
			 * Ignore entries that didn't accumulate any actual counts, such
			 * as indexes that were opened by the planner but not used.
			 */
      if (memcmp((&entry -> t_counts),(&all_zeroes),sizeof(PgStat_TableCounts )) == 0) {
        continue; 
      }
/*
			 * OK, insert data into the appropriate message, and send if full.
			 */
      this_msg = (entry -> t_shared?&shared_msg : &regular_msg);
      this_ent = &this_msg -> m_entry[this_msg -> m_nentries];
      this_ent -> t_id = entry -> t_id;
      memcpy((&this_ent -> t_counts),(&entry -> t_counts),sizeof(PgStat_TableCounts ));
      if ((++this_msg -> m_nentries) >= (1000 - sizeof(PgStat_MsgHdr ) - sizeof(Oid ) - 3 * sizeof(int )) / sizeof(PgStat_TableEntry )) {
        pgstat_send_tabstat(this_msg);
        this_msg -> m_nentries = 0;
      }
    }
/* zero out TableStatus structs after use */
    do {
      void *_vstart = (void *)(tsa -> tsa_entries);
      int _val = 0;
      Size _len = (tsa -> tsa_used) * sizeof(PgStat_TableStatus );
      if ((((intptr_t )_vstart) & sizeof(long ) - 1) == 0 && (_len & sizeof(long ) - 1) == 0 && _val == 0 && _len <= 1024 && 1024 != 0) {
        long *_start = (long *)_vstart;
        long *_stop = (long *)(((char *)_start) + _len);
        while(_start < _stop)
           *(_start++) = 0;
      }
      else {
        memset(_vstart,_val,_len);
      }
    }while (0);
    tsa -> tsa_used = 0;
  }
/*
	 * Send partial messages.  If force is true, make sure that any pending
	 * xact commit/abort gets counted, even if no table stats to send.
	 */
  if (regular_msg . m_nentries > 0 || force && (pgStatXactCommit > 0 || pgStatXactRollback > 0)) {
    pgstat_send_tabstat(&regular_msg);
  }
  if (shared_msg . m_nentries > 0) {
    pgstat_send_tabstat(&shared_msg);
  }
/* Now, send function statistics */
  pgstat_send_funcstats();
}
/*
 * Subroutine for pgstat_report_stat: finish and send a tabstat message
 */

static void pgstat_send_tabstat(PgStat_MsgTabstat *tsmsg)
{
  int n;
  int len;
/* It's unlikely we'd get here with no socket, but maybe not impossible */
  if (pgStatSock == - 1) {
    return ;
  }
/*
	 * Report and reset accumulated xact commit/rollback and I/O timings
	 * whenever we send a normal tabstat message
	 */
  if ((bool )(tsmsg -> m_databaseid != ((Oid )0))) {
    tsmsg -> m_xact_commit = pgStatXactCommit;
    tsmsg -> m_xact_rollback = pgStatXactRollback;
    tsmsg -> m_block_read_time = pgStatBlockReadTime;
    tsmsg -> m_block_write_time = pgStatBlockWriteTime;
    pgStatXactCommit = 0;
    pgStatXactRollback = 0;
    pgStatBlockReadTime = 0;
    pgStatBlockWriteTime = 0;
  }
  else {
    tsmsg -> m_xact_commit = 0;
    tsmsg -> m_xact_rollback = 0;
    tsmsg -> m_block_read_time = 0;
    tsmsg -> m_block_write_time = 0;
  }
  n = tsmsg -> m_nentries;
  len = (((size_t )(&((PgStat_MsgTabstat *)0) -> m_entry[0])) + n * sizeof(PgStat_TableEntry ));
  pgstat_setheader(&tsmsg -> m_hdr,PGSTAT_MTYPE_TABSTAT);
  pgstat_send(tsmsg,len);
}
/*
 * Subroutine for pgstat_report_stat: populate and send a function stat message
 */

static void pgstat_send_funcstats()
{
/* we assume this inits to all zeroes: */
  static const PgStat_FunctionCounts all_zeroes;
  PgStat_MsgFuncstat msg;
  PgStat_BackendFunctionEntry *entry;
  HASH_SEQ_STATUS fstat;
  if (pgStatFunctions == ((void *)0)) {
    return ;
  }
  pgstat_setheader(&msg . m_hdr,PGSTAT_MTYPE_FUNCSTAT);
  msg . m_databaseid = MyDatabaseId;
  msg . m_nentries = 0;
  hash_seq_init(&fstat,pgStatFunctions);
  while((entry = ((PgStat_BackendFunctionEntry *)(hash_seq_search(&fstat)))) != ((void *)0)){
    PgStat_FunctionEntry *m_ent;
/* Skip it if no counts accumulated since last time */
    if (memcmp((&entry -> f_counts),(&all_zeroes),sizeof(PgStat_FunctionCounts )) == 0) {
      continue; 
    }
/* need to convert format of time accumulators */
    m_ent = &msg . m_entry[msg . m_nentries];
    m_ent -> f_id = entry -> f_id;
    m_ent -> f_numcalls = entry -> f_counts . f_numcalls;
    m_ent -> f_total_time = (((uint64 )entry -> f_counts . f_total_time . tv_sec) * ((uint64 )1000000) + ((uint64 )entry -> f_counts . f_total_time . tv_usec));
    m_ent -> f_self_time = (((uint64 )entry -> f_counts . f_self_time . tv_sec) * ((uint64 )1000000) + ((uint64 )entry -> f_counts . f_self_time . tv_usec));
    if ((++msg . m_nentries) >= (1000 - sizeof(PgStat_MsgHdr ) - sizeof(Oid ) - sizeof(int )) / sizeof(PgStat_FunctionEntry )) {
      pgstat_send((&msg),(((size_t )(&((PgStat_MsgFuncstat *)0) -> m_entry[0])) + msg . m_nentries * sizeof(PgStat_FunctionEntry )));
      msg . m_nentries = 0;
    }
/* reset the entry's counts */
    do {
      void *_vstart = (void *)(&entry -> f_counts);
      int _val = 0;
      Size _len = sizeof(PgStat_FunctionCounts );
      if ((((intptr_t )_vstart) & sizeof(long ) - 1) == 0 && (_len & sizeof(long ) - 1) == 0 && _val == 0 && _len <= 1024 && 1024 != 0) {
        long *_start = (long *)_vstart;
        long *_stop = (long *)(((char *)_start) + _len);
        while(_start < _stop)
           *(_start++) = 0;
      }
      else {
        memset(_vstart,_val,_len);
      }
    }while (0);
  }
  if (msg . m_nentries > 0) {
    pgstat_send((&msg),(((size_t )(&((PgStat_MsgFuncstat *)0) -> m_entry[0])) + msg . m_nentries * sizeof(PgStat_FunctionEntry )));
  }
  have_function_stats = ((bool )0);
}
/* ----------
 * pgstat_vacuum_stat() -
 *
 *	Will tell the collector about objects he can get rid of.
 * ----------
 */

void pgstat_vacuum_stat()
{
  HTAB *htab;
  PgStat_MsgTabpurge msg;
  PgStat_MsgFuncpurge f_msg;
  HASH_SEQ_STATUS hstat;
  PgStat_StatDBEntry *dbentry;
  PgStat_StatTabEntry *tabentry;
  PgStat_StatFuncEntry *funcentry;
  int len;
  if (pgStatSock == - 1) {
    return ;
  }
/*
	 * If not done for this transaction, read the statistics collector stats
	 * file into some hash tables.
	 */
  backend_read_statsfile();
/*
	 * Read pg_database and make a list of OIDs of all existing databases
	 */
  htab = pgstat_collect_oids(1262);
/*
	 * Search the database hash table for dead databases and tell the
	 * collector to drop them.
	 */
  hash_seq_init(&hstat,pgStatDBHash);
  while((dbentry = ((PgStat_StatDBEntry *)(hash_seq_search(&hstat)))) != ((void *)0)){
    Oid dbid = dbentry -> databaseid;
    do {
      if (InterruptPending) {
        ProcessInterrupts();
      }
    }while (0);
/* the DB entry for shared tables (with InvalidOid) is never dropped */
    if (((bool )(dbid != ((Oid )0))) && hash_search(htab,((void *)(&dbid)),HASH_FIND,((void *)0)) == ((void *)0)) {
      pgstat_drop_database(dbid);
    }
  }
/* Clean up */
  hash_destroy(htab);
/*
	 * Lookup our own database entry; if not found, nothing more to do.
	 */
  dbentry = ((PgStat_StatDBEntry *)(hash_search(pgStatDBHash,((void *)(&MyDatabaseId)),HASH_FIND,((void *)0))));
  if (dbentry == ((void *)0) || dbentry -> tables == ((void *)0)) {
    return ;
  }
/*
	 * Similarly to above, make a list of all known relations in this DB.
	 */
  htab = pgstat_collect_oids(1259);
/*
	 * Initialize our messages table counter to zero
	 */
  msg . m_nentries = 0;
/*
	 * Check for all tables listed in stats hashtable if they still exist.
	 */
  hash_seq_init(&hstat,dbentry -> tables);
  while((tabentry = ((PgStat_StatTabEntry *)(hash_seq_search(&hstat)))) != ((void *)0)){
    Oid tabid = tabentry -> tableid;
    do {
      if (InterruptPending) {
        ProcessInterrupts();
      }
    }while (0);
    if (hash_search(htab,((void *)(&tabid)),HASH_FIND,((void *)0)) != ((void *)0)) {
      continue; 
    }
/*
		 * Not there, so add this table's Oid to the message
		 */
    msg . m_tableid[msg . m_nentries++] = tabid;
/*
		 * If the message is full, send it out and reinitialize to empty
		 */
    if (msg . m_nentries >= (1000 - sizeof(PgStat_MsgHdr ) - sizeof(Oid ) - sizeof(int )) / sizeof(Oid )) {
      len = (((size_t )(&((PgStat_MsgTabpurge *)0) -> m_tableid[0])) + msg . m_nentries * sizeof(Oid ));
      pgstat_setheader(&msg . m_hdr,PGSTAT_MTYPE_TABPURGE);
      msg . m_databaseid = MyDatabaseId;
      pgstat_send((&msg),len);
      msg . m_nentries = 0;
    }
  }
/*
	 * Send the rest
	 */
  if (msg . m_nentries > 0) {
    len = (((size_t )(&((PgStat_MsgTabpurge *)0) -> m_tableid[0])) + msg . m_nentries * sizeof(Oid ));
    pgstat_setheader(&msg . m_hdr,PGSTAT_MTYPE_TABPURGE);
    msg . m_databaseid = MyDatabaseId;
    pgstat_send((&msg),len);
  }
/* Clean up */
  hash_destroy(htab);
/*
	 * Now repeat the above steps for functions.  However, we needn't bother
	 * in the common case where no function stats are being collected.
	 */
  if (dbentry -> functions != ((void *)0) && hash_get_num_entries(dbentry -> functions) > 0) {
    htab = pgstat_collect_oids(1255);
    pgstat_setheader(&f_msg . m_hdr,PGSTAT_MTYPE_FUNCPURGE);
    f_msg . m_databaseid = MyDatabaseId;
    f_msg . m_nentries = 0;
    hash_seq_init(&hstat,dbentry -> functions);
    while((funcentry = ((PgStat_StatFuncEntry *)(hash_seq_search(&hstat)))) != ((void *)0)){
      Oid funcid = funcentry -> functionid;
      do {
        if (InterruptPending) {
          ProcessInterrupts();
        }
      }while (0);
      if (hash_search(htab,((void *)(&funcid)),HASH_FIND,((void *)0)) != ((void *)0)) {
        continue; 
      }
/*
			 * Not there, so add this function's Oid to the message
			 */
      f_msg . m_functionid[f_msg . m_nentries++] = funcid;
/*
			 * If the message is full, send it out and reinitialize to empty
			 */
      if (f_msg . m_nentries >= (1000 - sizeof(PgStat_MsgHdr ) - sizeof(Oid ) - sizeof(int )) / sizeof(Oid )) {
        len = (((size_t )(&((PgStat_MsgFuncpurge *)0) -> m_functionid[0])) + f_msg . m_nentries * sizeof(Oid ));
        pgstat_send((&f_msg),len);
        f_msg . m_nentries = 0;
      }
    }
/*
		 * Send the rest
		 */
    if (f_msg . m_nentries > 0) {
      len = (((size_t )(&((PgStat_MsgFuncpurge *)0) -> m_functionid[0])) + f_msg . m_nentries * sizeof(Oid ));
      pgstat_send((&f_msg),len);
    }
    hash_destroy(htab);
  }
}
/* ----------
 * pgstat_collect_oids() -
 *
 *	Collect the OIDs of all objects listed in the specified system catalog
 *	into a temporary hash table.  Caller should hash_destroy the result
 *	when done with it.	(However, we make the table in CurrentMemoryContext
 *	so that it will be freed properly in event of an error.)
 * ----------
 */

static HTAB *pgstat_collect_oids(Oid catalogid)
{
  HTAB *htab;
  HASHCTL hash_ctl;
  Relation rel;
  HeapScanDesc scan;
  HeapTuple tup;
  memset((&hash_ctl),0,sizeof(hash_ctl));
  hash_ctl . keysize = sizeof(Oid );
  hash_ctl . entrysize = sizeof(Oid );
  hash_ctl . hash = oid_hash;
  hash_ctl . hcxt = CurrentMemoryContext;
  htab = hash_create("Temporary table of OIDs",512,&hash_ctl,0x020 | 0x010 | 0x200);
  rel = heap_open(catalogid,1);
  scan = heap_beginscan(rel,&SnapshotNowData,0,((void *)0));
  while((tup = heap_getnext(scan,ForwardScanDirection)) != ((void *)0)){
    Oid thisoid = (tup -> t_data -> t_infomask) & 0x0008? *((Oid *)(((char *)(tup -> t_data)) + tup -> t_data -> t_hoff - sizeof(Oid ))) : ((Oid )0);
    do {
      if (InterruptPending) {
        ProcessInterrupts();
      }
    }while (0);
    (void )(hash_search(htab,((void *)(&thisoid)),HASH_ENTER,((void *)0)));
  }
  heap_endscan(scan);
  relation_close(rel,1);
  return htab;
}
/* ----------
 * pgstat_drop_database() -
 *
 *	Tell the collector that we just dropped a database.
 *	(If the message gets lost, we will still clean the dead DB eventually
 *	via future invocations of pgstat_vacuum_stat().)
 * ----------
 */

void pgstat_drop_database(Oid databaseid)
{
  PgStat_MsgDropdb msg;
  if (pgStatSock == - 1) {
    return ;
  }
  pgstat_setheader(&msg . m_hdr,PGSTAT_MTYPE_DROPDB);
  msg . m_databaseid = databaseid;
  pgstat_send((&msg),(sizeof(msg)));
}
/* ----------
 * pgstat_drop_relation() -
 *
 *	Tell the collector that we just dropped a relation.
 *	(If the message gets lost, we will still clean the dead entry eventually
 *	via future invocations of pgstat_vacuum_stat().)
 *
 *	Currently not used for lack of any good place to call it; we rely
 *	entirely on pgstat_vacuum_stat() to clean out stats for dead rels.
 * ----------
 */
#ifdef NOT_USED
#endif   /* NOT_USED */
/* ----------
 * pgstat_reset_counters() -
 *
 *	Tell the statistics collector to reset counters for our database.
 * ----------
 */

void pgstat_reset_counters()
{
  PgStat_MsgResetcounter msg;
  if (pgStatSock == - 1) {
    return ;
  }
  if (!superuser()) {
    errstart(20,"pgstat.c",1162,__func__,((void *)0))?errfinish(errcode(('4' - 48 & 0x3F) + ((50 - 48 & 0x3F) << 6) + (('5' - 48 & 0x3F) << 12) + ((48 - 48 & 0x3F) << 18) + (('1' - 48 & 0x3F) << 24)),errmsg("must be superuser to reset statistics counters")) : ((void )0);
  }
  pgstat_setheader(&msg . m_hdr,PGSTAT_MTYPE_RESETCOUNTER);
  msg . m_databaseid = MyDatabaseId;
  pgstat_send((&msg),(sizeof(msg)));
}
/* ----------
 * pgstat_reset_shared_counters() -
 *
 *	Tell the statistics collector to reset cluster-wide shared counters.
 * ----------
 */

void pgstat_reset_shared_counters(const char *target)
{
  PgStat_MsgResetsharedcounter msg;
  if (pgStatSock == - 1) {
    return ;
  }
  if (!superuser()) {
    errstart(20,"pgstat.c",1186,__func__,((void *)0))?errfinish(errcode(('4' - 48 & 0x3F) + ((50 - 48 & 0x3F) << 6) + (('5' - 48 & 0x3F) << 12) + ((48 - 48 & 0x3F) << 18) + (('1' - 48 & 0x3F) << 24)),errmsg("must be superuser to reset statistics counters")) : ((void )0);
  }
  if (strcmp(target,"bgwriter") == 0) {
    msg . m_resettarget = RESET_BGWRITER;
  }
  else {
    errstart(20,"pgstat.c",1194,__func__,((void *)0))?errfinish(errcode((50 - 48 & 0x3F) + ((50 - 48 & 0x3F) << 6) + ((48 - 48 & 0x3F) << 12) + ((50 - 48 & 0x3F) << 18) + (('3' - 48 & 0x3F) << 24)),errmsg("unrecognized reset target: \"%s\"",target),errhint("Target must be \"bgwriter\".")) : ((void )0);
  }
  pgstat_setheader(&msg . m_hdr,PGSTAT_MTYPE_RESETSHAREDCOUNTER);
  pgstat_send((&msg),(sizeof(msg)));
}
/* ----------
 * pgstat_reset_single_counter() -
 *
 *	Tell the statistics collector to reset a single counter.
 * ----------
 */

void pgstat_reset_single_counter(Oid objoid,PgStat_Single_Reset_Type type)
{
  PgStat_MsgResetsinglecounter msg;
  if (pgStatSock == - 1) {
    return ;
  }
  if (!superuser()) {
    errstart(20,"pgstat.c",1217,__func__,((void *)0))?errfinish(errcode(('4' - 48 & 0x3F) + ((50 - 48 & 0x3F) << 6) + (('5' - 48 & 0x3F) << 12) + ((48 - 48 & 0x3F) << 18) + (('1' - 48 & 0x3F) << 24)),errmsg("must be superuser to reset statistics counters")) : ((void )0);
  }
  pgstat_setheader(&msg . m_hdr,PGSTAT_MTYPE_RESETSINGLECOUNTER);
  msg . m_databaseid = MyDatabaseId;
  msg . m_resettype = type;
  msg . m_objectid = objoid;
  pgstat_send((&msg),(sizeof(msg)));
}
/* ----------
 * pgstat_report_autovac() -
 *
 *	Called from autovacuum.c to report startup of an autovacuum process.
 *	We are called before InitPostgres is done, so can't rely on MyDatabaseId;
 *	the db OID must be passed in, instead.
 * ----------
 */

void pgstat_report_autovac(Oid dboid)
{
  PgStat_MsgAutovacStart msg;
  if (pgStatSock == - 1) {
    return ;
  }
  pgstat_setheader(&msg . m_hdr,PGSTAT_MTYPE_AUTOVAC_START);
  msg . m_databaseid = dboid;
  msg . m_start_time = GetCurrentTimestamp();
  pgstat_send((&msg),(sizeof(msg)));
}
/* ---------
 * pgstat_report_vacuum() -
 *
 *	Tell the collector about the table we just vacuumed.
 * ---------
 */

void pgstat_report_vacuum(Oid tableoid,bool shared,PgStat_Counter tuples)
{
  PgStat_MsgVacuum msg;
  if (pgStatSock == - 1 || !pgstat_track_counts) {
    return ;
  }
  pgstat_setheader(&msg . m_hdr,PGSTAT_MTYPE_VACUUM);
  msg . m_databaseid = (shared?((Oid )0) : MyDatabaseId);
  msg . m_tableoid = tableoid;
  msg . m_autovacuum = IsAutoVacuumWorkerProcess();
  msg . m_vacuumtime = GetCurrentTimestamp();
  msg . m_tuples = tuples;
  pgstat_send((&msg),(sizeof(msg)));
}
/* --------
 * pgstat_report_analyze() -
 *
 *	Tell the collector about the table we just analyzed.
 * --------
 */

void pgstat_report_analyze(Relation rel,PgStat_Counter livetuples,PgStat_Counter deadtuples)
{
  PgStat_MsgAnalyze msg;
  if (pgStatSock == - 1 || !pgstat_track_counts) {
    return ;
  }
/*
	 * Unlike VACUUM, ANALYZE might be running inside a transaction that has
	 * already inserted and/or deleted rows in the target table. ANALYZE will
	 * have counted such rows as live or dead respectively. Because we will
	 * report our counts of such rows at transaction end, we should subtract
	 * off these counts from what we send to the collector now, else they'll
	 * be double-counted after commit.	(This approach also ensures that the
	 * collector ends up with the right numbers if we abort instead of
	 * committing.)
	 */
  if (rel -> pgstat_info != ((void *)0)) {
    PgStat_TableXactStatus *trans;
    for (trans = rel -> pgstat_info -> trans; trans; trans = trans -> upper) {
      livetuples -= trans -> tuples_inserted - trans -> tuples_deleted;
      deadtuples -= trans -> tuples_updated + trans -> tuples_deleted;
    }
/* count stuff inserted by already-aborted subxacts, too */
    deadtuples -= rel -> pgstat_info -> t_counts . t_delta_dead_tuples;
/* Since ANALYZE's counts are estimates, we could have underflowed */
    livetuples = (livetuples > 0?livetuples : 0);
    deadtuples = (deadtuples > 0?deadtuples : 0);
  }
  pgstat_setheader(&msg . m_hdr,PGSTAT_MTYPE_ANALYZE);
  msg . m_databaseid = (rel -> rd_rel -> relisshared?((Oid )0) : MyDatabaseId);
  msg . m_tableoid = rel -> rd_id;
  msg . m_autovacuum = IsAutoVacuumWorkerProcess();
  msg . m_analyzetime = GetCurrentTimestamp();
  msg . m_live_tuples = livetuples;
  msg . m_dead_tuples = deadtuples;
  pgstat_send((&msg),(sizeof(msg)));
}
/* --------
 * pgstat_report_recovery_conflict() -
 *
 *	Tell the collector about a Hot Standby recovery conflict.
 * --------
 */

void pgstat_report_recovery_conflict(int reason)
{
  PgStat_MsgRecoveryConflict msg;
  if (pgStatSock == - 1 || !pgstat_track_counts) {
    return ;
  }
  pgstat_setheader(&msg . m_hdr,PGSTAT_MTYPE_RECOVERYCONFLICT);
  msg . m_databaseid = MyDatabaseId;
  msg . m_reason = reason;
  pgstat_send((&msg),(sizeof(msg)));
}
/* --------
 * pgstat_report_deadlock() -
 *
 *	Tell the collector about a deadlock detected.
 * --------
 */

void pgstat_report_deadlock()
{
  PgStat_MsgDeadlock msg;
  if (pgStatSock == - 1 || !pgstat_track_counts) {
    return ;
  }
  pgstat_setheader(&msg . m_hdr,PGSTAT_MTYPE_DEADLOCK);
  msg . m_databaseid = MyDatabaseId;
  pgstat_send((&msg),(sizeof(msg)));
}
/* --------
 * pgstat_report_tempfile() -
 *
 *	Tell the collector about a temporary file.
 * --------
 */

void pgstat_report_tempfile(size_t filesize)
{
  PgStat_MsgTempFile msg;
  if (pgStatSock == - 1 || !pgstat_track_counts) {
    return ;
  }
  pgstat_setheader(&msg . m_hdr,PGSTAT_MTYPE_TEMPFILE);
  msg . m_databaseid = MyDatabaseId;
  msg . m_filesize = filesize;
  pgstat_send((&msg),(sizeof(msg)));
}
/* ----------
 * pgstat_ping() -
 *
 *	Send some junk data to the collector to increase traffic.
 * ----------
 */

void pgstat_ping()
{
  PgStat_MsgDummy msg;
  if (pgStatSock == - 1) {
    return ;
  }
  pgstat_setheader(&msg . m_hdr,PGSTAT_MTYPE_DUMMY);
  pgstat_send((&msg),(sizeof(msg)));
}
/* ----------
 * pgstat_send_inquiry() -
 *
 *	Notify collector that we need fresh data.
 *	ts specifies the minimum acceptable timestamp for the stats file.
 * ----------
 */

static void pgstat_send_inquiry(TimestampTz ts)
{
  PgStat_MsgInquiry msg;
  pgstat_setheader(&msg . m_hdr,PGSTAT_MTYPE_INQUIRY);
  msg . inquiry_time = ts;
  pgstat_send((&msg),(sizeof(msg)));
}
/*
 * Initialize function call usage data.
 * Called by the executor before invoking a function.
 */

void pgstat_init_function_usage(FunctionCallInfoData *fcinfo,PgStat_FunctionCallUsage *fcu)
{
  PgStat_BackendFunctionEntry *htabent;
  bool found;
  if (pgstat_track_functions <= (fcinfo -> flinfo -> fn_stats)) {
/* stats not wanted */
    fcu -> fs = ((void *)0);
    return ;
  }
  if (!pgStatFunctions) {
/* First time through - initialize function stat table */
    HASHCTL hash_ctl;
    memset((&hash_ctl),0,sizeof(hash_ctl));
    hash_ctl . keysize = sizeof(Oid );
    hash_ctl . entrysize = sizeof(PgStat_BackendFunctionEntry );
    hash_ctl . hash = oid_hash;
    pgStatFunctions = hash_create("Function stat entries",512,&hash_ctl,0x020 | 0x010);
  }
/* Get the stats entry for this function, create if necessary */
  htabent = (hash_search(pgStatFunctions,(&fcinfo -> flinfo -> fn_oid),HASH_ENTER,&found));
  if (!found) {
    do {
      void *_vstart = (void *)(&htabent -> f_counts);
      int _val = 0;
      Size _len = sizeof(PgStat_FunctionCounts );
      if ((((intptr_t )_vstart) & sizeof(long ) - 1) == 0 && (_len & sizeof(long ) - 1) == 0 && _val == 0 && _len <= 1024 && 1024 != 0) {
        long *_start = (long *)_vstart;
        long *_stop = (long *)(((char *)_start) + _len);
        while(_start < _stop)
           *(_start++) = 0;
      }
      else {
        memset(_vstart,_val,_len);
      }
    }while (0);
  }
  fcu -> fs = &htabent -> f_counts;
/* save stats for this function, later used to compensate for recursion */
  fcu -> save_f_total_time = htabent -> f_counts . f_total_time;
/* save current backend-wide total time */
  fcu -> save_total = total_func_time;
/* get clock time as of function start */
  gettimeofday(&fcu -> f_start,((void *)0));
}
/*
 * find_funcstat_entry - find any existing PgStat_BackendFunctionEntry entry
 *		for specified function
 *
 * If no entry, return NULL, don't create a new one
 */

PgStat_BackendFunctionEntry *find_funcstat_entry(Oid func_id)
{
  if (pgStatFunctions == ((void *)0)) {
    return ((void *)0);
  }
  return (PgStat_BackendFunctionEntry *)(hash_search(pgStatFunctions,((void *)(&func_id)),HASH_FIND,((void *)0)));
}
/*
 * Calculate function call usage and update stat counters.
 * Called by the executor after invoking a function.
 *
 * In the case of a set-returning function that runs in value-per-call mode,
 * we will see multiple pgstat_init_function_usage/pgstat_end_function_usage
 * calls for what the user considers a single call of the function.  The
 * finalize flag should be TRUE on the last call.
 */

void pgstat_end_function_usage(PgStat_FunctionCallUsage *fcu,bool finalize)
{
  PgStat_FunctionCounts *fs = fcu -> fs;
  instr_time f_total;
  instr_time f_others;
  instr_time f_self;
/* stats not wanted? */
  if (fs == ((void *)0)) {
    return ;
  }
/* total elapsed time in this function call */
  gettimeofday(&f_total,((void *)0));
  do {
    f_total . tv_sec -= fcu -> f_start . tv_sec;
    f_total . tv_usec -= fcu -> f_start . tv_usec;
    while(f_total . tv_usec < 0){
      f_total . tv_usec += 1000000;
      f_total . tv_sec--;
    }
  }while (0);
/* self usage: elapsed minus anything already charged to other calls */
  f_others = total_func_time;
  do {
    f_others . tv_sec -= fcu -> save_total . tv_sec;
    f_others . tv_usec -= fcu -> save_total . tv_usec;
    while(f_others . tv_usec < 0){
      f_others . tv_usec += 1000000;
      f_others . tv_sec--;
    }
  }while (0);
  f_self = f_total;
  do {
    f_self . tv_sec -= f_others . tv_sec;
    f_self . tv_usec -= f_others . tv_usec;
    while(f_self . tv_usec < 0){
      f_self . tv_usec += 1000000;
      f_self . tv_sec--;
    }
  }while (0);
/* update backend-wide total time */
  do {
    total_func_time . tv_sec += f_self . tv_sec;
    total_func_time . tv_usec += f_self . tv_usec;
    while(total_func_time . tv_usec >= 1000000){
      total_func_time . tv_usec -= 1000000;
      total_func_time . tv_sec++;
    }
  }while (0);
/*
	 * Compute the new f_total_time as the total elapsed time added to the
	 * pre-call value of f_total_time.	This is necessary to avoid
	 * double-counting any time taken by recursive calls of myself.  (We do
	 * not need any similar kluge for self time, since that already excludes
	 * any recursive calls.)
	 */
  do {
    f_total . tv_sec += fcu -> save_f_total_time . tv_sec;
    f_total . tv_usec += fcu -> save_f_total_time . tv_usec;
    while(f_total . tv_usec >= 1000000){
      f_total . tv_usec -= 1000000;
      f_total . tv_sec++;
    }
  }while (0);
/* update counters in function stats table */
  if (finalize) {
    fs -> f_numcalls++;
  }
  fs -> f_total_time = f_total;
  do {
    fs -> f_self_time . tv_sec += f_self . tv_sec;
    fs -> f_self_time . tv_usec += f_self . tv_usec;
    while(fs -> f_self_time . tv_usec >= 1000000){
      fs -> f_self_time . tv_usec -= 1000000;
      fs -> f_self_time . tv_sec++;
    }
  }while (0);
/* indicate that we have something to send */
  have_function_stats = ((bool )1);
}
/* ----------
 * pgstat_initstats() -
 *
 *	Initialize a relcache entry to count access statistics.
 *	Called whenever a relation is opened.
 *
 *	We assume that a relcache entry's pgstat_info field is zeroed by
 *	relcache.c when the relcache entry is made; thereafter it is long-lived
 *	data.  We can avoid repeated searches of the TabStatus arrays when the
 *	same relation is touched repeatedly within a transaction.
 * ----------
 */

void pgstat_initstats(Relation rel)
{
  Oid rel_id = rel -> rd_id;
  char relkind = rel -> rd_rel -> relkind;
/* We only count stats for things that have storage */
  if (!(relkind == 'r' || relkind == 'i' || relkind == 't' || relkind == 'S')) {
    rel -> pgstat_info = ((void *)0);
    return ;
  }
  if (pgStatSock == - 1 || !pgstat_track_counts) {
/* We're not counting at all */
    rel -> pgstat_info = ((void *)0);
    return ;
  }
/*
	 * If we already set up this relation in the current transaction, nothing
	 * to do.
	 */
  if (rel -> pgstat_info != ((void *)0) && rel -> pgstat_info -> t_id == rel_id) {
    return ;
  }
/* Else find or make the PgStat_TableStatus entry, and update link */
  rel -> pgstat_info = get_tabstat_entry(rel_id,rel -> rd_rel -> relisshared);
}
/*
 * get_tabstat_entry - find or create a PgStat_TableStatus entry for rel
 */

static PgStat_TableStatus *get_tabstat_entry(Oid rel_id,bool isshared)
{
  PgStat_TableStatus *entry;
  TabStatusArray *tsa;
  TabStatusArray *prev_tsa;
  int i;
/*
	 * Search the already-used tabstat slots for this relation.
	 */
  prev_tsa = ((void *)0);
  for (tsa = pgStatTabList; tsa != ((void *)0); (prev_tsa = tsa , tsa = tsa -> tsa_next)) {
    for (i = 0; i < tsa -> tsa_used; i++) {
      entry = &tsa -> tsa_entries[i];
      if (entry -> t_id == rel_id) {
        return entry;
      }
    }
    if (tsa -> tsa_used < 100) {
/*
			 * It must not be present, but we found a free slot instead. Fine,
			 * let's use this one.  We assume the entry was already zeroed,
			 * either at creation or after last use.
			 */
      entry = &tsa -> tsa_entries[tsa -> tsa_used++];
      entry -> t_id = rel_id;
      entry -> t_shared = isshared;
      return entry;
    }
  }
/*
	 * We ran out of tabstat slots, so allocate more.  Be sure they're zeroed.
	 */
  tsa = ((TabStatusArray *)(MemoryContextAllocZero(TopMemoryContext,sizeof(TabStatusArray ))));
  if (prev_tsa) {
    prev_tsa -> tsa_next = tsa;
  }
  else {
    pgStatTabList = tsa;
  }
/*
	 * Use the first entry of the new TabStatusArray.
	 */
  entry = &tsa -> tsa_entries[tsa -> tsa_used++];
  entry -> t_id = rel_id;
  entry -> t_shared = isshared;
  return entry;
}
/*
 * find_tabstat_entry - find any existing PgStat_TableStatus entry for rel
 *
 * If no entry, return NULL, don't create a new one
 */

PgStat_TableStatus *find_tabstat_entry(Oid rel_id)
{
  PgStat_TableStatus *entry;
  TabStatusArray *tsa;
  int i;
  for (tsa = pgStatTabList; tsa != ((void *)0); tsa = tsa -> tsa_next) {
    for (i = 0; i < tsa -> tsa_used; i++) {
      entry = &tsa -> tsa_entries[i];
      if (entry -> t_id == rel_id) {
        return entry;
      }
    }
  }
/* Not present */
  return ((void *)0);
}
/*
 * get_tabstat_stack_level - add a new (sub)transaction stack entry if needed
 */

static PgStat_SubXactStatus *get_tabstat_stack_level(int nest_level)
{
  PgStat_SubXactStatus *xact_state;
  xact_state = pgStatXactStack;
  if (xact_state == ((void *)0) || xact_state -> nest_level != nest_level) {
    xact_state = ((PgStat_SubXactStatus *)(MemoryContextAlloc(TopTransactionContext,sizeof(PgStat_SubXactStatus ))));
    xact_state -> nest_level = nest_level;
    xact_state -> prev = pgStatXactStack;
    xact_state -> first = ((void *)0);
    pgStatXactStack = xact_state;
  }
  return xact_state;
}
/*
 * add_tabstat_xact_level - add a new (sub)transaction state record
 */

static void add_tabstat_xact_level(PgStat_TableStatus *pgstat_info,int nest_level)
{
  PgStat_SubXactStatus *xact_state;
  PgStat_TableXactStatus *trans;
/*
	 * If this is the first rel to be modified at the current nest level, we
	 * first have to push a transaction stack entry.
	 */
  xact_state = get_tabstat_stack_level(nest_level);
/* Now make a per-table stack entry */
  trans = ((PgStat_TableXactStatus *)(MemoryContextAllocZero(TopTransactionContext,sizeof(PgStat_TableXactStatus ))));
  trans -> nest_level = nest_level;
  trans -> upper = pgstat_info -> trans;
  trans -> parent = pgstat_info;
  trans -> next = xact_state -> first;
  xact_state -> first = trans;
  pgstat_info -> trans = trans;
}
/*
 * pgstat_count_heap_insert - count a tuple insertion of n tuples
 */

void pgstat_count_heap_insert(Relation rel,int n)
{
  PgStat_TableStatus *pgstat_info = rel -> pgstat_info;
  if (pgstat_info != ((void *)0)) {
/* We have to log the effect at the proper transactional level */
    int nest_level = GetCurrentTransactionNestLevel();
    if (pgstat_info -> trans == ((void *)0) || pgstat_info -> trans -> nest_level != nest_level) {
      add_tabstat_xact_level(pgstat_info,nest_level);
    }
    pgstat_info -> trans -> tuples_inserted += n;
  }
}
/*
 * pgstat_count_heap_update - count a tuple update
 */

void pgstat_count_heap_update(Relation rel,bool hot)
{
  PgStat_TableStatus *pgstat_info = rel -> pgstat_info;
  if (pgstat_info != ((void *)0)) {
/* We have to log the effect at the proper transactional level */
    int nest_level = GetCurrentTransactionNestLevel();
    if (pgstat_info -> trans == ((void *)0) || pgstat_info -> trans -> nest_level != nest_level) {
      add_tabstat_xact_level(pgstat_info,nest_level);
    }
    pgstat_info -> trans -> tuples_updated++;
/* t_tuples_hot_updated is nontransactional, so just advance it */
    if (hot) {
      pgstat_info -> t_counts . t_tuples_hot_updated++;
    }
  }
}
/*
 * pgstat_count_heap_delete - count a tuple deletion
 */

void pgstat_count_heap_delete(Relation rel)
{
  PgStat_TableStatus *pgstat_info = rel -> pgstat_info;
  if (pgstat_info != ((void *)0)) {
/* We have to log the effect at the proper transactional level */
    int nest_level = GetCurrentTransactionNestLevel();
    if (pgstat_info -> trans == ((void *)0) || pgstat_info -> trans -> nest_level != nest_level) {
      add_tabstat_xact_level(pgstat_info,nest_level);
    }
    pgstat_info -> trans -> tuples_deleted++;
  }
}
/*
 * pgstat_update_heap_dead_tuples - update dead-tuples count
 *
 * The semantics of this are that we are reporting the nontransactional
 * recovery of "delta" dead tuples; so t_delta_dead_tuples decreases
 * rather than increasing, and the change goes straight into the per-table
 * counter, not into transactional state.
 */

void pgstat_update_heap_dead_tuples(Relation rel,int delta)
{
  PgStat_TableStatus *pgstat_info = rel -> pgstat_info;
  if (pgstat_info != ((void *)0)) {
    pgstat_info -> t_counts . t_delta_dead_tuples -= delta;
  }
}
/* ----------
 * AtEOXact_PgStat
 *
 *	Called from access/transam/xact.c at top-level transaction commit/abort.
 * ----------
 */

void AtEOXact_PgStat(bool isCommit)
{
  PgStat_SubXactStatus *xact_state;
/*
	 * Count transaction commit or abort.  (We use counters, not just bools,
	 * in case the reporting message isn't sent right away.)
	 */
  if (isCommit) {
    pgStatXactCommit++;
  }
  else {
    pgStatXactRollback++;
  }
/*
	 * Transfer transactional insert/update counts into the base tabstat
	 * entries.  We don't bother to free any of the transactional state, since
	 * it's all in TopTransactionContext and will go away anyway.
	 */
  xact_state = pgStatXactStack;
  if (xact_state != ((void *)0)) {
    PgStat_TableXactStatus *trans;
    ;
    ;
    for (trans = xact_state -> first; trans != ((void *)0); trans = trans -> next) {
      PgStat_TableStatus *tabstat;
      ;
      ;
      tabstat = trans -> parent;
      ;
/* count attempted actions regardless of commit/abort */
      tabstat -> t_counts . t_tuples_inserted += trans -> tuples_inserted;
      tabstat -> t_counts . t_tuples_updated += trans -> tuples_updated;
      tabstat -> t_counts . t_tuples_deleted += trans -> tuples_deleted;
      if (isCommit) {
/* insert adds a live tuple, delete removes one */
        tabstat -> t_counts . t_delta_live_tuples += trans -> tuples_inserted - trans -> tuples_deleted;
/* update and delete each create a dead tuple */
        tabstat -> t_counts . t_delta_dead_tuples += trans -> tuples_updated + trans -> tuples_deleted;
/* insert, update, delete each count as one change event */
        tabstat -> t_counts . t_changed_tuples += trans -> tuples_inserted + trans -> tuples_updated + trans -> tuples_deleted;
      }
      else {
/* inserted tuples are dead, deleted tuples are unaffected */
        tabstat -> t_counts . t_delta_dead_tuples += trans -> tuples_inserted + trans -> tuples_updated;
/* an aborted xact generates no changed_tuple events */
      }
      tabstat -> trans = ((void *)0);
    }
  }
  pgStatXactStack = ((void *)0);
/* Make sure any stats snapshot is thrown away */
  pgstat_clear_snapshot();
}
/* ----------
 * AtEOSubXact_PgStat
 *
 *	Called from access/transam/xact.c at subtransaction commit/abort.
 * ----------
 */

void AtEOSubXact_PgStat(bool isCommit,int nestDepth)
{
  PgStat_SubXactStatus *xact_state;
/*
	 * Transfer transactional insert/update counts into the next higher
	 * subtransaction state.
	 */
  xact_state = pgStatXactStack;
  if (xact_state != ((void *)0) && xact_state -> nest_level >= nestDepth) {
    PgStat_TableXactStatus *trans;
    PgStat_TableXactStatus *next_trans;
/* delink xact_state from stack immediately to simplify reuse case */
    pgStatXactStack = xact_state -> prev;
    for (trans = xact_state -> first; trans != ((void *)0); trans = next_trans) {
      PgStat_TableStatus *tabstat;
      next_trans = trans -> next;
      ;
      tabstat = trans -> parent;
      ;
      if (isCommit) {
        if (trans -> upper && trans -> upper -> nest_level == nestDepth - 1) {
          trans -> upper -> tuples_inserted += trans -> tuples_inserted;
          trans -> upper -> tuples_updated += trans -> tuples_updated;
          trans -> upper -> tuples_deleted += trans -> tuples_deleted;
          tabstat -> trans = trans -> upper;
          pfree(trans);
        }
        else {
/*
					 * When there isn't an immediate parent state, we can just
					 * reuse the record instead of going through a
					 * palloc/pfree pushup (this works since it's all in
					 * TopTransactionContext anyway).  We have to re-link it
					 * into the parent level, though, and that might mean
					 * pushing a new entry into the pgStatXactStack.
					 */
          PgStat_SubXactStatus *upper_xact_state;
          upper_xact_state = get_tabstat_stack_level(nestDepth - 1);
          trans -> next = upper_xact_state -> first;
          upper_xact_state -> first = trans;
          trans -> nest_level = nestDepth - 1;
        }
      }
      else {
/*
				 * On abort, update top-level tabstat counts, then forget the
				 * subtransaction
				 */
/* count attempted actions regardless of commit/abort */
        tabstat -> t_counts . t_tuples_inserted += trans -> tuples_inserted;
        tabstat -> t_counts . t_tuples_updated += trans -> tuples_updated;
        tabstat -> t_counts . t_tuples_deleted += trans -> tuples_deleted;
/* inserted tuples are dead, deleted tuples are unaffected */
        tabstat -> t_counts . t_delta_dead_tuples += trans -> tuples_inserted + trans -> tuples_updated;
        tabstat -> trans = trans -> upper;
        pfree(trans);
      }
    }
    pfree(xact_state);
  }
}
/*
 * AtPrepare_PgStat
 *		Save the transactional stats state at 2PC transaction prepare.
 *
 * In this phase we just generate 2PC records for all the pending
 * transaction-dependent stats work.
 */

void AtPrepare_PgStat()
{
  PgStat_SubXactStatus *xact_state;
  xact_state = pgStatXactStack;
  if (xact_state != ((void *)0)) {
    PgStat_TableXactStatus *trans;
    ;
    ;
    for (trans = xact_state -> first; trans != ((void *)0); trans = trans -> next) {
      PgStat_TableStatus *tabstat;
      TwoPhasePgStatRecord record;
      ;
      ;
      tabstat = trans -> parent;
      ;
      record . tuples_inserted = trans -> tuples_inserted;
      record . tuples_updated = trans -> tuples_updated;
      record . tuples_deleted = trans -> tuples_deleted;
      record . t_id = tabstat -> t_id;
      record . t_shared = tabstat -> t_shared;
      RegisterTwoPhaseRecord(2,0,(&record),(sizeof(TwoPhasePgStatRecord )));
    }
  }
}
/*
 * PostPrepare_PgStat
 *		Clean up after successful PREPARE.
 *
 * All we need do here is unlink the transaction stats state from the
 * nontransactional state.	The nontransactional action counts will be
 * reported to the stats collector immediately, while the effects on live
 * and dead tuple counts are preserved in the 2PC state file.
 *
 * Note: AtEOXact_PgStat is not called during PREPARE.
 */

void PostPrepare_PgStat()
{
  PgStat_SubXactStatus *xact_state;
/*
	 * We don't bother to free any of the transactional state, since it's all
	 * in TopTransactionContext and will go away anyway.
	 */
  xact_state = pgStatXactStack;
  if (xact_state != ((void *)0)) {
    PgStat_TableXactStatus *trans;
    for (trans = xact_state -> first; trans != ((void *)0); trans = trans -> next) {
      PgStat_TableStatus *tabstat;
      tabstat = trans -> parent;
      tabstat -> trans = ((void *)0);
    }
  }
  pgStatXactStack = ((void *)0);
/* Make sure any stats snapshot is thrown away */
  pgstat_clear_snapshot();
}
/*
 * 2PC processing routine for COMMIT PREPARED case.
 *
 * Load the saved counts into our local pgstats state.
 */

void pgstat_twophase_postcommit(TransactionId xid,uint16 info,void *recdata,uint32 len)
{
  TwoPhasePgStatRecord *rec = (TwoPhasePgStatRecord *)recdata;
  PgStat_TableStatus *pgstat_info;
/* Find or create a tabstat entry for the rel */
  pgstat_info = get_tabstat_entry(rec -> t_id,rec -> t_shared);
/* Same math as in AtEOXact_PgStat, commit case */
  pgstat_info -> t_counts . t_tuples_inserted += rec -> tuples_inserted;
  pgstat_info -> t_counts . t_tuples_updated += rec -> tuples_updated;
  pgstat_info -> t_counts . t_tuples_deleted += rec -> tuples_deleted;
  pgstat_info -> t_counts . t_delta_live_tuples += rec -> tuples_inserted - rec -> tuples_deleted;
  pgstat_info -> t_counts . t_delta_dead_tuples += rec -> tuples_updated + rec -> tuples_deleted;
  pgstat_info -> t_counts . t_changed_tuples += rec -> tuples_inserted + rec -> tuples_updated + rec -> tuples_deleted;
}
/*
 * 2PC processing routine for ROLLBACK PREPARED case.
 *
 * Load the saved counts into our local pgstats state, but treat them
 * as aborted.
 */

void pgstat_twophase_postabort(TransactionId xid,uint16 info,void *recdata,uint32 len)
{
  TwoPhasePgStatRecord *rec = (TwoPhasePgStatRecord *)recdata;
  PgStat_TableStatus *pgstat_info;
/* Find or create a tabstat entry for the rel */
  pgstat_info = get_tabstat_entry(rec -> t_id,rec -> t_shared);
/* Same math as in AtEOXact_PgStat, abort case */
  pgstat_info -> t_counts . t_tuples_inserted += rec -> tuples_inserted;
  pgstat_info -> t_counts . t_tuples_updated += rec -> tuples_updated;
  pgstat_info -> t_counts . t_tuples_deleted += rec -> tuples_deleted;
  pgstat_info -> t_counts . t_delta_dead_tuples += rec -> tuples_inserted + rec -> tuples_updated;
}
/* ----------
 * pgstat_fetch_stat_dbentry() -
 *
 *	Support function for the SQL-callable pgstat* functions. Returns
 *	the collected statistics for one database or NULL. NULL doesn't mean
 *	that the database doesn't exist, it is just not yet known by the
 *	collector, so the caller is better off to report ZERO instead.
 * ----------
 */

PgStat_StatDBEntry *pgstat_fetch_stat_dbentry(Oid dbid)
{
/*
	 * If not done for this transaction, read the statistics collector stats
	 * file into some hash tables.
	 */
  backend_read_statsfile();
/*
	 * Lookup the requested database; return NULL if not found
	 */
  return (PgStat_StatDBEntry *)(hash_search(pgStatDBHash,((void *)(&dbid)),HASH_FIND,((void *)0)));
}
/* ----------
 * pgstat_fetch_stat_tabentry() -
 *
 *	Support function for the SQL-callable pgstat* functions. Returns
 *	the collected statistics for one table or NULL. NULL doesn't mean
 *	that the table doesn't exist, it is just not yet known by the
 *	collector, so the caller is better off to report ZERO instead.
 * ----------
 */

PgStat_StatTabEntry *pgstat_fetch_stat_tabentry(Oid relid)
{
  Oid dbid;
  PgStat_StatDBEntry *dbentry;
  PgStat_StatTabEntry *tabentry;
/*
	 * If not done for this transaction, read the statistics collector stats
	 * file into some hash tables.
	 */
  backend_read_statsfile();
/*
	 * Lookup our database, then look in its table hash table.
	 */
  dbid = MyDatabaseId;
  dbentry = ((PgStat_StatDBEntry *)(hash_search(pgStatDBHash,((void *)(&dbid)),HASH_FIND,((void *)0))));
  if (dbentry != ((void *)0) && dbentry -> tables != ((void *)0)) {
    tabentry = ((PgStat_StatTabEntry *)(hash_search(dbentry -> tables,((void *)(&relid)),HASH_FIND,((void *)0))));
    if (tabentry) {
      return tabentry;
    }
  }
/*
	 * If we didn't find it, maybe it's a shared table.
	 */
  dbid = ((Oid )0);
  dbentry = ((PgStat_StatDBEntry *)(hash_search(pgStatDBHash,((void *)(&dbid)),HASH_FIND,((void *)0))));
  if (dbentry != ((void *)0) && dbentry -> tables != ((void *)0)) {
    tabentry = ((PgStat_StatTabEntry *)(hash_search(dbentry -> tables,((void *)(&relid)),HASH_FIND,((void *)0))));
    if (tabentry) {
      return tabentry;
    }
  }
  return ((void *)0);
}
/* ----------
 * pgstat_fetch_stat_funcentry() -
 *
 *	Support function for the SQL-callable pgstat* functions. Returns
 *	the collected statistics for one function or NULL.
 * ----------
 */

PgStat_StatFuncEntry *pgstat_fetch_stat_funcentry(Oid func_id)
{
  PgStat_StatDBEntry *dbentry;
  PgStat_StatFuncEntry *funcentry = ((void *)0);
/* load the stats file if needed */
  backend_read_statsfile();
/* Lookup our database, then find the requested function.  */
  dbentry = pgstat_fetch_stat_dbentry(MyDatabaseId);
  if (dbentry != ((void *)0) && dbentry -> functions != ((void *)0)) {
    funcentry = ((PgStat_StatFuncEntry *)(hash_search(dbentry -> functions,((void *)(&func_id)),HASH_FIND,((void *)0))));
  }
  return funcentry;
}
/* ----------
 * pgstat_fetch_stat_beentry() -
 *
 *	Support function for the SQL-callable pgstat* functions. Returns
 *	our local copy of the current-activity entry for one backend.
 *
 *	NB: caller is responsible for a check if the user is permitted to see
 *	this info (especially the querystring).
 * ----------
 */

PgBackendStatus *pgstat_fetch_stat_beentry(int beid)
{
  pgstat_read_current_status();
  if (beid < 1 || beid > localNumBackends) {
    return ((void *)0);
  }
  return &localBackendStatusTable[beid - 1];
}
/* ----------
 * pgstat_fetch_stat_numbackends() -
 *
 *	Support function for the SQL-callable pgstat* functions. Returns
 *	the maximum current backend id.
 * ----------
 */

int pgstat_fetch_stat_numbackends()
{
  pgstat_read_current_status();
  return localNumBackends;
}
/*
 * ---------
 * pgstat_fetch_global() -
 *
 *	Support function for the SQL-callable pgstat* functions. Returns
 *	a pointer to the global statistics struct.
 * ---------
 */

PgStat_GlobalStats *pgstat_fetch_global()
{
  backend_read_statsfile();
  return &globalStats;
}
/* ------------------------------------------------------------
 * Functions for management of the shared-memory PgBackendStatus array
 * ------------------------------------------------------------
 */
static PgBackendStatus *BackendStatusArray = ((void *)0);
static PgBackendStatus *MyBEEntry = ((void *)0);
static char *BackendClientHostnameBuffer = ((void *)0);
static char *BackendAppnameBuffer = ((void *)0);
static char *BackendActivityBuffer = ((void *)0);
static Size BackendActivityBufferSize = 0;
/*
 * Report shared-memory space needed by CreateSharedBackendStatus.
 */

Size BackendStatusShmemSize()
{
  Size size;
  size = mul_size(sizeof(PgBackendStatus ),MaxBackends);
  size = add_size(size,mul_size(64,MaxBackends));
  size = add_size(size,mul_size(pgstat_track_activity_query_size,MaxBackends));
  size = add_size(size,mul_size(64,MaxBackends));
  return size;
}
/*
 * Initialize the shared status array and several string buffers
 * during postmaster startup.
 */

void CreateSharedBackendStatus()
{
  Size size;
  bool found;
  int i;
  char *buffer;
/* Create or attach to the shared array */
  size = mul_size(sizeof(PgBackendStatus ),MaxBackends);
  BackendStatusArray = ((PgBackendStatus *)(ShmemInitStruct("Backend Status Array",size,&found)));
  if (!found) {
/*
		 * We're the first - initialize.
		 */
    do {
      void *_vstart = (void *)BackendStatusArray;
      int _val = 0;
      Size _len = size;
      if ((((intptr_t )_vstart) & sizeof(long ) - 1) == 0 && (_len & sizeof(long ) - 1) == 0 && _val == 0 && _len <= 1024 && 1024 != 0) {
        long *_start = (long *)_vstart;
        long *_stop = (long *)(((char *)_start) + _len);
        while(_start < _stop)
           *(_start++) = 0;
      }
      else {
        memset(_vstart,_val,_len);
      }
    }while (0);
  }
/* Create or attach to the shared appname buffer */
  size = mul_size(64,MaxBackends);
  BackendAppnameBuffer = ((char *)(ShmemInitStruct("Backend Application Name Buffer",size,&found)));
  if (!found) {
    do {
      void *_vstart = (void *)BackendAppnameBuffer;
      int _val = 0;
      Size _len = size;
      if ((((intptr_t )_vstart) & sizeof(long ) - 1) == 0 && (_len & sizeof(long ) - 1) == 0 && _val == 0 && _len <= 1024 && 1024 != 0) {
        long *_start = (long *)_vstart;
        long *_stop = (long *)(((char *)_start) + _len);
        while(_start < _stop)
           *(_start++) = 0;
      }
      else {
        memset(_vstart,_val,_len);
      }
    }while (0);
/* Initialize st_appname pointers. */
    buffer = BackendAppnameBuffer;
    for (i = 0; i < MaxBackends; i++) {
      BackendStatusArray[i] . st_appname = buffer;
      buffer += 64;
    }
  }
/* Create or attach to the shared client hostname buffer */
  size = mul_size(64,MaxBackends);
  BackendClientHostnameBuffer = ((char *)(ShmemInitStruct("Backend Client Host Name Buffer",size,&found)));
  if (!found) {
    do {
      void *_vstart = (void *)BackendClientHostnameBuffer;
      int _val = 0;
      Size _len = size;
      if ((((intptr_t )_vstart) & sizeof(long ) - 1) == 0 && (_len & sizeof(long ) - 1) == 0 && _val == 0 && _len <= 1024 && 1024 != 0) {
        long *_start = (long *)_vstart;
        long *_stop = (long *)(((char *)_start) + _len);
        while(_start < _stop)
           *(_start++) = 0;
      }
      else {
        memset(_vstart,_val,_len);
      }
    }while (0);
/* Initialize st_clienthostname pointers. */
    buffer = BackendClientHostnameBuffer;
    for (i = 0; i < MaxBackends; i++) {
      BackendStatusArray[i] . st_clienthostname = buffer;
      buffer += 64;
    }
  }
/* Create or attach to the shared activity buffer */
  BackendActivityBufferSize = mul_size(pgstat_track_activity_query_size,MaxBackends);
  BackendActivityBuffer = ((char *)(ShmemInitStruct("Backend Activity Buffer",BackendActivityBufferSize,&found)));
  if (!found) {
    do {
      void *_vstart = (void *)BackendActivityBuffer;
      int _val = 0;
      Size _len = size;
      if ((((intptr_t )_vstart) & sizeof(long ) - 1) == 0 && (_len & sizeof(long ) - 1) == 0 && _val == 0 && _len <= 1024 && 1024 != 0) {
        long *_start = (long *)_vstart;
        long *_stop = (long *)(((char *)_start) + _len);
        while(_start < _stop)
           *(_start++) = 0;
      }
      else {
        memset(_vstart,_val,_len);
      }
    }while (0);
/* Initialize st_activity pointers. */
    buffer = BackendActivityBuffer;
    for (i = 0; i < MaxBackends; i++) {
      BackendStatusArray[i] . st_activity = buffer;
      buffer += pgstat_track_activity_query_size;
    }
  }
}
/* ----------
 * pgstat_initialize() -
 *
 *	Initialize pgstats state, and set up our on-proc-exit hook.
 *	Called from InitPostgres.  MyBackendId must be set,
 *	but we must not have started any transaction yet (since the
 *	exit hook must run after the last transaction exit).
 *	NOTE: MyDatabaseId isn't set yet; so the shutdown hook has to be careful.
 * ----------
 */

void pgstat_initialize()
{
/* Initialize MyBEEntry */
  ;
  MyBEEntry = &BackendStatusArray[MyBackendId - 1];
/* Set up a process-exit hook to clean up */
  on_shmem_exit(pgstat_beshutdown_hook,0);
}
/* ----------
 * pgstat_bestart() -
 *
 *	Initialize this backend's entry in the PgBackendStatus array.
 *	Called from InitPostgres.
 *	MyDatabaseId, session userid, and application_name must be set
 *	(hence, this cannot be combined with pgstat_initialize).
 * ----------
 */

void pgstat_bestart()
{
  TimestampTz proc_start_timestamp;
  Oid userid;
  SockAddr clientaddr;
  volatile PgBackendStatus *beentry;
/*
	 * To minimize the time spent modifying the PgBackendStatus entry, fetch
	 * all the needed data first.
	 *
	 * If we have a MyProcPort, use its session start time (for consistency,
	 * and to save a kernel call).
	 */
  if (MyProcPort) {
    proc_start_timestamp = MyProcPort -> SessionStartTime;
  }
  else {
    proc_start_timestamp = GetCurrentTimestamp();
  }
  userid = GetSessionUserId();
/*
	 * We may not have a MyProcPort (eg, if this is the autovacuum process).
	 * If so, use all-zeroes client address, which is dealt with specially in
	 * pg_stat_get_backend_client_addr and pg_stat_get_backend_client_port.
	 */
  if (MyProcPort) {
    memcpy((&clientaddr),(&MyProcPort -> raddr),sizeof(clientaddr));
  }
  else {
    do {
      void *_vstart = (void *)(&clientaddr);
      int _val = 0;
      Size _len = sizeof(clientaddr);
      if ((((intptr_t )_vstart) & sizeof(long ) - 1) == 0 && (_len & sizeof(long ) - 1) == 0 && _val == 0 && _len <= 1024 && 1024 != 0) {
        long *_start = (long *)_vstart;
        long *_stop = (long *)(((char *)_start) + _len);
        while(_start < _stop)
           *(_start++) = 0;
      }
      else {
        memset(_vstart,_val,_len);
      }
    }while (0);
  }
/*
	 * Initialize my status entry, following the protocol of bumping
	 * st_changecount before and after; and make sure it's even afterwards. We
	 * use a volatile pointer here to ensure the compiler doesn't try to get
	 * cute.
	 */
  beentry = MyBEEntry;
  do {
    beentry -> st_changecount++;
  }while ((beentry -> st_changecount & 1) == 0);
  beentry -> st_procpid = MyProcPid;
  beentry -> st_proc_start_timestamp = proc_start_timestamp;
  beentry -> st_activity_start_timestamp = 0;
  beentry -> st_state_start_timestamp = 0;
  beentry -> st_xact_start_timestamp = 0;
  beentry -> st_databaseid = MyDatabaseId;
  beentry -> st_userid = userid;
  beentry -> st_clientaddr = clientaddr;
  beentry -> st_clienthostname[0] = '\0';
  beentry -> st_waiting = ((bool )0);
  beentry -> st_state = STATE_UNDEFINED;
  beentry -> st_appname[0] = '\0';
  beentry -> st_activity[0] = '\0';
/* Also make sure the last byte in each string area is always 0 */
  beentry -> st_clienthostname[64 - 1] = '\0';
  beentry -> st_appname[64 - 1] = '\0';
  beentry -> st_activity[pgstat_track_activity_query_size - 1] = '\0';
  beentry -> st_changecount++;
  ;
  if (MyProcPort && MyProcPort -> remote_hostname) {
    strlcpy(beentry -> st_clienthostname,(MyProcPort -> remote_hostname),64);
  }
/* Update app name to current GUC setting */
  if (application_name) {
    pgstat_report_appname(application_name);
  }
}
/*
 * Shut down a single backend's statistics reporting at process exit.
 *
 * Flush any remaining statistics counts out to the collector.
 * Without this, operations triggered during backend exit (such as
 * temp table deletions) won't be counted.
 *
 * Lastly, clear out our entry in the PgBackendStatus array.
 */

static void pgstat_beshutdown_hook(int code,Datum arg)
{
  volatile PgBackendStatus *beentry = MyBEEntry;
/*
	 * If we got as far as discovering our own database ID, we can report what
	 * we did to the collector.  Otherwise, we'd be sending an invalid
	 * database ID, so forget it.  (This means that accesses to pg_database
	 * during failed backend starts might never get counted.)
	 */
  if ((bool )(MyDatabaseId != ((Oid )0))) {
    pgstat_report_stat(((bool )1));
  }
/*
	 * Clear my status entry, following the protocol of bumping st_changecount
	 * before and after.  We use a volatile pointer here to ensure the
	 * compiler doesn't try to get cute.
	 */
  beentry -> st_changecount++;
/* mark invalid */
  beentry -> st_procpid = 0;
  beentry -> st_changecount++;
  ;
}
/* ----------
 * pgstat_report_activity() -
 *
 *	Called from tcop/postgres.c to report what the backend is actually doing
 *	(usually "<IDLE>" or the start of the query to be executed).
 *
 * All updates of the status entry follow the protocol of bumping
 * st_changecount before and after.  We use a volatile pointer here to
 * ensure the compiler doesn't try to get cute.
 * ----------
 */

void pgstat_report_activity(BackendState state,const char *cmd_str)
{
  volatile PgBackendStatus *beentry = MyBEEntry;
  TimestampTz start_timestamp;
  TimestampTz current_timestamp;
  int len = 0;
  ;
  if (!beentry) {
    return ;
  }
/*
	 * To minimize the time spent modifying the entry, fetch all the needed
	 * data first.
	 */
  current_timestamp = GetCurrentTimestamp();
  if (!pgstat_track_activities && (beentry -> st_state) != STATE_DISABLED) {
/*
		 * Track activities is disabled, but we have a non-disabled state set.
		 * That means the status changed - so as our last update, tell the
		 * collector that we disabled it and will no longer update.
		 */
    beentry -> st_changecount++;
    beentry -> st_state = STATE_DISABLED;
    beentry -> st_state_start_timestamp = current_timestamp;
    beentry -> st_changecount++;
    ;
    return ;
  }
/*
	 * Fetch more data before we start modifying the entry
	 */
  start_timestamp = GetCurrentStatementStartTimestamp();
  if (cmd_str != ((void *)0)) {
    len = pg_mbcliplen(cmd_str,(strlen(cmd_str)),pgstat_track_activity_query_size - 1);
  }
/*
	 * Now update the status entry
	 */
  beentry -> st_changecount++;
  beentry -> st_state = state;
  beentry -> st_state_start_timestamp = current_timestamp;
  if (cmd_str != ((void *)0)) {
    memcpy(((char *)(beentry -> st_activity)),cmd_str,len);
    beentry -> st_activity[len] = '\0';
    beentry -> st_activity_start_timestamp = start_timestamp;
  }
  beentry -> st_changecount++;
  ;
}
/* ----------
 * pgstat_report_appname() -
 *
 *	Called to update our application name.
 * ----------
 */

void pgstat_report_appname(const char *appname)
{
  volatile PgBackendStatus *beentry = MyBEEntry;
  int len;
  if (!beentry) {
    return ;
  }
/* This should be unnecessary if GUC did its job, but be safe */
  len = pg_mbcliplen(appname,(strlen(appname)),64 - 1);
/*
	 * Update my status entry, following the protocol of bumping
	 * st_changecount before and after.  We use a volatile pointer here to
	 * ensure the compiler doesn't try to get cute.
	 */
  beentry -> st_changecount++;
  memcpy(((char *)(beentry -> st_appname)),appname,len);
  beentry -> st_appname[len] = '\0';
  beentry -> st_changecount++;
  ;
}
/*
 * Report current transaction start timestamp as the specified value.
 * Zero means there is no active transaction.
 */

void pgstat_report_xact_timestamp(TimestampTz tstamp)
{
  volatile PgBackendStatus *beentry = MyBEEntry;
  if (!pgstat_track_activities || !beentry) {
    return ;
  }
/*
	 * Update my status entry, following the protocol of bumping
	 * st_changecount before and after.  We use a volatile pointer here to
	 * ensure the compiler doesn't try to get cute.
	 */
  beentry -> st_changecount++;
  beentry -> st_xact_start_timestamp = tstamp;
  beentry -> st_changecount++;
  ;
}
/* ----------
 * pgstat_report_waiting() -
 *
 *	Called from lock manager to report beginning or end of a lock wait.
 *
 * NB: this *must* be able to survive being called before MyBEEntry has been
 * initialized.
 * ----------
 */

void pgstat_report_waiting(bool waiting)
{
  volatile PgBackendStatus *beentry = MyBEEntry;
  if (!pgstat_track_activities || !beentry) {
    return ;
  }
/*
	 * Since this is a single-byte field in a struct that only this process
	 * may modify, there seems no need to bother with the st_changecount
	 * protocol.  The update must appear atomic in any case.
	 */
  beentry -> st_waiting = waiting;
}
/* ----------
 * pgstat_read_current_status() -
 *
 *	Copy the current contents of the PgBackendStatus array to local memory,
 *	if not already done in this transaction.
 * ----------
 */

static void pgstat_read_current_status()
{
  volatile PgBackendStatus *beentry;
  PgBackendStatus *localtable;
  PgBackendStatus *localentry;
  char *localappname;
  char *localactivity;
  int i;
  ;
  if (localBackendStatusTable) {
/* already done */
    return ;
  }
  pgstat_setup_memcxt();
  localtable = ((PgBackendStatus *)(MemoryContextAlloc(pgStatLocalContext,sizeof(PgBackendStatus ) * MaxBackends)));
  localappname = ((char *)(MemoryContextAlloc(pgStatLocalContext,(64 * MaxBackends))));
  localactivity = ((char *)(MemoryContextAlloc(pgStatLocalContext,(pgstat_track_activity_query_size * MaxBackends))));
  localNumBackends = 0;
  beentry = BackendStatusArray;
  localentry = localtable;
  for (i = 1; i <= MaxBackends; i++) {
/*
		 * Follow the protocol of retrying if st_changecount changes while we
		 * copy the entry, or if it's odd.  (The check for odd is needed to
		 * cover the case where we are able to completely copy the entry while
		 * the source backend is between increment steps.)	We use a volatile
		 * pointer here to ensure the compiler doesn't try to get cute.
		 */
    for (; ; ) {
      int save_changecount = beentry -> st_changecount;
      localentry -> st_procpid = beentry -> st_procpid;
      if (localentry -> st_procpid > 0) {
        memcpy(localentry,((char *)beentry),sizeof(PgBackendStatus ));
/*
				 * strcpy is safe even if the string is modified concurrently,
				 * because there's always a \0 at the end of the buffer.
				 */
        strcpy(localappname,((char *)(beentry -> st_appname)));
        localentry -> st_appname = localappname;
        strcpy(localactivity,((char *)(beentry -> st_activity)));
        localentry -> st_activity = localactivity;
      }
      if (save_changecount == beentry -> st_changecount && (save_changecount & 1) == 0) {
        break; 
      }
/* Make sure we can break out of loop if stuck... */
      do {
        if (InterruptPending) {
          ProcessInterrupts();
        }
      }while (0);
    }
    beentry++;
/* Only valid entries get included into the local array */
    if (localentry -> st_procpid > 0) {
      localentry++;
      localappname += 64;
      localactivity += pgstat_track_activity_query_size;
      localNumBackends++;
    }
  }
/* Set the pointer only after completion of a valid table */
  localBackendStatusTable = localtable;
}
/* ----------
 * pgstat_get_backend_current_activity() -
 *
 *	Return a string representing the current activity of the backend with
 *	the specified PID.	This looks directly at the BackendStatusArray,
 *	and so will provide current information regardless of the age of our
 *	transaction's snapshot of the status array.
 *
 *	It is the caller's responsibility to invoke this only for backends whose
 *	state is expected to remain stable while the result is in use.	The
 *	only current use is in deadlock reporting, where we can expect that
 *	the target backend is blocked on a lock.  (There are corner cases
 *	where the target's wait could get aborted while we are looking at it,
 *	but the very worst consequence is to return a pointer to a string
 *	that's been changed, so we won't worry too much.)
 *
 *	Note: return strings for special cases match pg_stat_get_backend_activity.
 * ----------
 */

const char *pgstat_get_backend_current_activity(int pid,bool checkUser)
{
  PgBackendStatus *beentry;
  int i;
  beentry = BackendStatusArray;
  for (i = 1; i <= MaxBackends; i++) {
/*
		 * Although we expect the target backend's entry to be stable, that
		 * doesn't imply that anyone else's is.  To avoid identifying the
		 * wrong backend, while we check for a match to the desired PID we
		 * must follow the protocol of retrying if st_changecount changes
		 * while we examine the entry, or if it's odd.  (This might be
		 * unnecessary, since fetching or storing an int is almost certainly
		 * atomic, but let's play it safe.)  We use a volatile pointer here to
		 * ensure the compiler doesn't try to get cute.
		 */
    volatile PgBackendStatus *vbeentry = beentry;
    bool found;
    for (; ; ) {
      int save_changecount = vbeentry -> st_changecount;
      found = (vbeentry -> st_procpid == pid);
      if (save_changecount == vbeentry -> st_changecount && (save_changecount & 1) == 0) {
        break; 
      }
/* Make sure we can break out of loop if stuck... */
      do {
        if (InterruptPending) {
          ProcessInterrupts();
        }
      }while (0);
    }
    if (found) {
/* Now it is safe to use the non-volatile pointer */
      if (checkUser && !superuser() && beentry -> st_userid != GetUserId()) {
        return "<insufficient privilege>";
      }
      else {
        if (( *beentry -> st_activity) == '\0') {
          return "<command string not enabled>";
        }
        else {
          return (beentry -> st_activity);
        }
      }
    }
    beentry++;
  }
/* If we get here, caller is in error ... */
  return "<backend information not available>";
}
/* ----------
 * pgstat_get_crashed_backend_activity() -
 *
 *	Return a string representing the current activity of the backend with
 *	the specified PID.	Like the function above, but reads shared memory with
 *	the expectation that it may be corrupt.  On success, copy the string
 *	into the "buffer" argument and return that pointer.  On failure,
 *	return NULL.
 *
 *	This function is only intended to be used by the postmaster to report the
 *	query that crashed a backend.  In particular, no attempt is made to
 *	follow the correct concurrency protocol when accessing the
 *	BackendStatusArray.  But that's OK, in the worst case we'll return a
 *	corrupted message.	We also must take care not to trip on ereport(ERROR).
 * ----------
 */

const char *pgstat_get_crashed_backend_activity(int pid,char *buffer,int buflen)
{
  volatile PgBackendStatus *beentry;
  int i;
  beentry = BackendStatusArray;
/*
	 * We probably shouldn't get here before shared memory has been set up,
	 * but be safe.
	 */
  if (beentry == ((void *)0) || BackendActivityBuffer == ((void *)0)) {
    return ((void *)0);
  }
  for (i = 1; i <= MaxBackends; i++) {
    if (beentry -> st_procpid == pid) {
/* Read pointer just once, so it can't change after validation */
      const char *activity = (beentry -> st_activity);
      const char *activity_last;
/*
			 * We mustn't access activity string before we verify that it
			 * falls within the BackendActivityBuffer. To make sure that the
			 * entire string including its ending is contained within the
			 * buffer, subtract one activity length from the buffer size.
			 */
      activity_last = (BackendActivityBuffer + BackendActivityBufferSize - pgstat_track_activity_query_size);
      if (activity < BackendActivityBuffer || activity > activity_last) {
        return ((void *)0);
      }
/* If no string available, no point in a report */
      if (activity[0] == '\0') {
        return ((void *)0);
      }
/*
			 * Copy only ASCII-safe characters so we don't run into encoding
			 * problems when reporting the message; and be sure not to run off
			 * the end of memory.
			 */
      ascii_safe_strlcpy(buffer,activity,(buflen < pgstat_track_activity_query_size?buflen : pgstat_track_activity_query_size));
      return buffer;
    }
    beentry++;
  }
/* PID not found */
  return ((void *)0);
}
/* ------------------------------------------------------------
 * Local support functions follow
 * ------------------------------------------------------------
 */
/* ----------
 * pgstat_setheader() -
 *
 *		Set common header fields in a statistics message
 * ----------
 */

static void pgstat_setheader(PgStat_MsgHdr *hdr,StatMsgType mtype)
{
  hdr -> m_type = mtype;
}
/* ----------
 * pgstat_send() -
 *
 *		Send out one statistics message to the collector
 * ----------
 */

static void pgstat_send(void *msg,int len)
{
  int rc;
  if (pgStatSock == - 1) {
    return ;
  }
  ((PgStat_MsgHdr *)msg) -> m_size = len;
/* We'll retry after EINTR, but ignore all other failures */
  do {
    rc = (send(pgStatSock,msg,len,0));
  }while (rc < 0 &&  *__errno_location() == 4);
#ifdef USE_ASSERT_CHECKING
/* In debug builds, log send failures ... */
#endif
}
/* ----------
 * pgstat_send_bgwriter() -
 *
 *		Send bgwriter statistics to the collector
 * ----------
 */

void pgstat_send_bgwriter()
{
/* We assume this initializes to zeroes */
  static const PgStat_MsgBgWriter all_zeroes;
/*
	 * This function can be called even if nothing at all has happened. In
	 * this case, avoid sending a completely empty message to the stats
	 * collector.
	 */
  if (memcmp((&BgWriterStats),(&all_zeroes),sizeof(PgStat_MsgBgWriter )) == 0) {
    return ;
  }
/*
	 * Prepare and send the message
	 */
  pgstat_setheader(&BgWriterStats . m_hdr,PGSTAT_MTYPE_BGWRITER);
  pgstat_send((&BgWriterStats),(sizeof(BgWriterStats)));
/*
	 * Clear out the statistics buffer, so it can be re-used.
	 */
  do {
    void *_vstart = (void *)(&BgWriterStats);
    int _val = 0;
    Size _len = sizeof(BgWriterStats);
    if ((((intptr_t )_vstart) & sizeof(long ) - 1) == 0 && (_len & sizeof(long ) - 1) == 0 && _val == 0 && _len <= 1024 && 1024 != 0) {
      long *_start = (long *)_vstart;
      long *_stop = (long *)(((char *)_start) + _len);
      while(_start < _stop)
         *(_start++) = 0;
    }
    else {
      memset(_vstart,_val,_len);
    }
  }while (0);
}
/* ----------
 * PgstatCollectorMain() -
 *
 *	Start up the statistics collector process.	This is the body of the
 *	postmaster child process.
 *
 *	The argc/argv parameters are valid only in EXEC_BACKEND case.
 * ----------
 */

static void PgstatCollectorMain(int argc,char *argv[])
{
  int len;
  PgStat_Msg msg;
  int wr;
/* we are a postmaster subprocess now */
  IsUnderPostmaster = ((bool )1);
/* reset MyProcPid */
  MyProcPid = getpid();
/* record Start Time for logging */
  MyStartTime = time(((void *)0));
/*
	 * If possible, make this process a group leader, so that the postmaster
	 * can signal any child processes too.	(pgstat probably never has any
	 * child processes, but for consistency we make all postmaster child
	 * processes do this.)
	 */
#ifdef HAVE_SETSID
  if (setsid() < 0) {
    (elog_start("pgstat.c",3021,__func__) , elog_finish(21,"setsid() failed: %m"));
  }
#endif
/* needed for latch waits */
  InitializeLatchSupport();
/* Initialize private latch for use by signal handlers */
  InitLatch((&pgStatLatch));
/*
	 * Ignore all signals usually bound to some action in the postmaster,
	 * except SIGHUP and SIGQUIT.  Note we don't need a SIGUSR1 handler to
	 * support latch operations, because pgStatLatch is local not shared.
	 */
  pqsignal(1,pgstat_sighup_handler);
  pqsignal(2,((__sighandler_t )1));
  pqsignal(15,((__sighandler_t )1));
  pqsignal(3,pgstat_exit);
  pqsignal(14,((__sighandler_t )1));
  pqsignal(13,((__sighandler_t )1));
  pqsignal(10,((__sighandler_t )1));
  pqsignal(12,((__sighandler_t )1));
  pqsignal(17,((__sighandler_t )0));
  pqsignal(21,((__sighandler_t )0));
  pqsignal(22,((__sighandler_t )0));
  pqsignal(18,((__sighandler_t )0));
  pqsignal(28,((__sighandler_t )0));
  sigprocmask(2,(&UnBlockSig),((void *)0));
/*
	 * Identify myself via ps
	 */
  init_ps_display("stats collector process","","","");
/*
	 * Arrange to write the initial status file right away
	 */
  last_statrequest = GetCurrentTimestamp();
  last_statwrite = last_statrequest - 1;
/*
	 * Read in an existing statistics stats file or initialize the stats to
	 * zero.
	 */
  pgStatRunningInCollector = ((bool )1);
  pgStatDBHash = pgstat_read_statsfile(((Oid )0),((bool )1));
/*
	 * Loop to process messages until we get SIGQUIT or detect ungraceful
	 * death of our parent postmaster.
	 *
	 * For performance reasons, we don't want to do ResetLatch/WaitLatch after
	 * every message; instead, do that only after a recv() fails to obtain a
	 * message.  (This effectively means that if backends are sending us stuff
	 * like mad, we won't notice postmaster death until things slack off a
	 * bit; which seems fine.)	To do that, we have an inner loop that
	 * iterates as long as recv() succeeds.  We do recognize got_SIGHUP inside
	 * the inner loop, which means that such interrupts will get serviced but
	 * the latch won't get cleared until next time there is a break in the
	 * action.
	 */
  for (; ; ) {
/* Clear any already-pending wakeups */
    ResetLatch((&pgStatLatch));
/*
		 * Quit if we get SIGQUIT from the postmaster.
		 */
    if (need_exit) {
      break; 
    }
/*
		 * Inner loop iterates as long as we keep getting messages, or until
		 * need_exit becomes set.
		 */
    while(!need_exit){
/*
			 * Reload configuration if we got SIGHUP from the postmaster.
			 */
      if (got_SIGHUP) {
        got_SIGHUP = ((bool )0);
        ProcessConfigFile(PGC_SIGHUP);
      }
/*
			 * Write the stats file if a new request has arrived that is not
			 * satisfied by existing file.
			 */
      if (last_statwrite < last_statrequest) {
        pgstat_write_statsfile(((bool )0));
      }
/*
			 * Try to receive and process a message.  This will not block,
			 * since the socket is set to non-blocking mode.
			 *
			 * XXX On Windows, we have to force pgwin32_recv to cooperate,
			 * despite the previous use of pg_set_noblock() on the socket.
			 * This is extremely broken and should be fixed someday.
			 */
#ifdef WIN32
#endif
      len = (recv(pgStatSock,((char *)(&msg)),sizeof(PgStat_Msg ),0));
#ifdef WIN32
#endif
      if (len < 0) {
        if ( *__errno_location() == 11 ||  *__errno_location() == 11 ||  *__errno_location() == 4) {
/* out of inner loop */
          break; 
        }
        errstart(20,"pgstat.c",3139,__func__,((void *)0))?errfinish(errcode_for_socket_access(),errmsg("could not read statistics message: %m")) : ((void )0);
      }
/*
			 * We ignore messages that are smaller than our common header
			 */
      if (len < sizeof(PgStat_MsgHdr )) {
        continue; 
      }
/*
			 * The received length must match the length in the header
			 */
      if (msg . msg_hdr . m_size != len) {
        continue; 
      }
/*
			 * O.K. - we accept this message.  Process it.
			 */
      switch(msg . msg_hdr . m_type){
        case PGSTAT_MTYPE_DUMMY:
        break; 
        case PGSTAT_MTYPE_INQUIRY:
{
          pgstat_recv_inquiry(((PgStat_MsgInquiry *)(&msg)),len);
          break; 
        }
        case PGSTAT_MTYPE_TABSTAT:
{
          pgstat_recv_tabstat(((PgStat_MsgTabstat *)(&msg)),len);
          break; 
        }
        case PGSTAT_MTYPE_TABPURGE:
{
          pgstat_recv_tabpurge(((PgStat_MsgTabpurge *)(&msg)),len);
          break; 
        }
        case PGSTAT_MTYPE_DROPDB:
{
          pgstat_recv_dropdb(((PgStat_MsgDropdb *)(&msg)),len);
          break; 
        }
        case PGSTAT_MTYPE_RESETCOUNTER:
{
          pgstat_recv_resetcounter(((PgStat_MsgResetcounter *)(&msg)),len);
          break; 
        }
        case PGSTAT_MTYPE_RESETSHAREDCOUNTER:
{
          pgstat_recv_resetsharedcounter(((PgStat_MsgResetsharedcounter *)(&msg)),len);
          break; 
        }
        case PGSTAT_MTYPE_RESETSINGLECOUNTER:
{
          pgstat_recv_resetsinglecounter(((PgStat_MsgResetsinglecounter *)(&msg)),len);
          break; 
        }
        case PGSTAT_MTYPE_AUTOVAC_START:
{
          pgstat_recv_autovac(((PgStat_MsgAutovacStart *)(&msg)),len);
          break; 
        }
        case PGSTAT_MTYPE_VACUUM:
{
          pgstat_recv_vacuum(((PgStat_MsgVacuum *)(&msg)),len);
          break; 
        }
        case PGSTAT_MTYPE_ANALYZE:
{
          pgstat_recv_analyze(((PgStat_MsgAnalyze *)(&msg)),len);
          break; 
        }
        case PGSTAT_MTYPE_BGWRITER:
{
          pgstat_recv_bgwriter(((PgStat_MsgBgWriter *)(&msg)),len);
          break; 
        }
        case PGSTAT_MTYPE_FUNCSTAT:
{
          pgstat_recv_funcstat(((PgStat_MsgFuncstat *)(&msg)),len);
          break; 
        }
        case PGSTAT_MTYPE_FUNCPURGE:
{
          pgstat_recv_funcpurge(((PgStat_MsgFuncpurge *)(&msg)),len);
          break; 
        }
        case PGSTAT_MTYPE_RECOVERYCONFLICT:
{
          pgstat_recv_recoveryconflict(((PgStat_MsgRecoveryConflict *)(&msg)),len);
          break; 
        }
        case PGSTAT_MTYPE_DEADLOCK:
{
          pgstat_recv_deadlock(((PgStat_MsgDeadlock *)(&msg)),len);
          break; 
        }
        case PGSTAT_MTYPE_TEMPFILE:
{
          pgstat_recv_tempfile(((PgStat_MsgTempFile *)(&msg)),len);
          break; 
        }
        default:
        break; 
      }
/* end of inner message-processing loop */
    }
/* Sleep until there's something to do */
#ifndef WIN32
    wr = WaitLatchOrSocket((&pgStatLatch),1 << 0 | 1 << 4 | 1 << 1,pgStatSock,- 1L);
#else
/*
		 * Windows, at least in its Windows Server 2003 R2 incarnation,
		 * sometimes loses FD_READ events.	Waking up and retrying the recv()
		 * fixes that, so don't sleep indefinitely.  This is a crock of the
		 * first water, but until somebody wants to debug exactly what's
		 * happening there, this is the best we can do.  The two-second
		 * timeout matches our pre-9.2 behavior, and needs to be short enough
		 * to not provoke "pgstat wait timeout" complaints from
		 * backend_read_statsfile.
		 */
/* msec */
#endif
/*
		 * Emergency bailout if postmaster has died.  This is to avoid the
		 * necessity for manual cleanup of all postmaster children.
		 */
    if (wr & 1 << 4) {
      break; 
    }
/* end of outer loop */
  }
/*
	 * Save the final stats to reuse at next startup.
	 */
  pgstat_write_statsfile(((bool )1));
  exit(0);
}
/* SIGQUIT signal handler for collector process */

static void pgstat_exit(int postgres_signal_arg)
{
  int save_errno =  *__errno_location();
  need_exit = ((bool )1);
  SetLatch((&pgStatLatch));
   *__errno_location() = save_errno;
}
/* SIGHUP handler for collector process */

static void pgstat_sighup_handler(int postgres_signal_arg)
{
  int save_errno =  *__errno_location();
  got_SIGHUP = ((bool )1);
  SetLatch((&pgStatLatch));
   *__errno_location() = save_errno;
}
/*
 * Lookup the hash table entry for the specified database. If no hash
 * table entry exists, initialize it, if the create parameter is true.
 * Else, return NULL.
 */

static PgStat_StatDBEntry *pgstat_get_db_entry(Oid databaseid,bool create)
{
  PgStat_StatDBEntry *result;
  bool found;
  HASHACTION action = (create?HASH_ENTER : HASH_FIND);
/* Lookup or create the hash table entry for this database */
  result = ((PgStat_StatDBEntry *)(hash_search(pgStatDBHash,(&databaseid),action,&found)));
  if (!create && !found) {
    return ((void *)0);
  }
/* If not found, initialize the new one. */
  if (!found) {
    HASHCTL hash_ctl;
    result -> tables = ((void *)0);
    result -> functions = ((void *)0);
    result -> n_xact_commit = 0;
    result -> n_xact_rollback = 0;
    result -> n_blocks_fetched = 0;
    result -> n_blocks_hit = 0;
    result -> n_tuples_returned = 0;
    result -> n_tuples_fetched = 0;
    result -> n_tuples_inserted = 0;
    result -> n_tuples_updated = 0;
    result -> n_tuples_deleted = 0;
    result -> last_autovac_time = 0;
    result -> n_conflict_tablespace = 0;
    result -> n_conflict_lock = 0;
    result -> n_conflict_snapshot = 0;
    result -> n_conflict_bufferpin = 0;
    result -> n_conflict_startup_deadlock = 0;
    result -> n_temp_files = 0;
    result -> n_temp_bytes = 0;
    result -> n_deadlocks = 0;
    result -> n_block_read_time = 0;
    result -> n_block_write_time = 0;
    result -> stat_reset_timestamp = GetCurrentTimestamp();
    memset((&hash_ctl),0,sizeof(hash_ctl));
    hash_ctl . keysize = sizeof(Oid );
    hash_ctl . entrysize = sizeof(PgStat_StatTabEntry );
    hash_ctl . hash = oid_hash;
    result -> tables = hash_create("Per-database table",512,&hash_ctl,0x020 | 0x010);
    hash_ctl . keysize = sizeof(Oid );
    hash_ctl . entrysize = sizeof(PgStat_StatFuncEntry );
    hash_ctl . hash = oid_hash;
    result -> functions = hash_create("Per-database function",512,&hash_ctl,0x020 | 0x010);
  }
  return result;
}
/*
 * Lookup the hash table entry for the specified table. If no hash
 * table entry exists, initialize it, if the create parameter is true.
 * Else, return NULL.
 */

static PgStat_StatTabEntry *pgstat_get_tab_entry(PgStat_StatDBEntry *dbentry,Oid tableoid,bool create)
{
  PgStat_StatTabEntry *result;
  bool found;
  HASHACTION action = (create?HASH_ENTER : HASH_FIND);
/* Lookup or create the hash table entry for this table */
  result = ((PgStat_StatTabEntry *)(hash_search(dbentry -> tables,(&tableoid),action,&found)));
  if (!create && !found) {
    return ((void *)0);
  }
/* If not found, initialize the new one. */
  if (!found) {
    result -> numscans = 0;
    result -> tuples_returned = 0;
    result -> tuples_fetched = 0;
    result -> tuples_inserted = 0;
    result -> tuples_updated = 0;
    result -> tuples_deleted = 0;
    result -> tuples_hot_updated = 0;
    result -> n_live_tuples = 0;
    result -> n_dead_tuples = 0;
    result -> changes_since_analyze = 0;
    result -> blocks_fetched = 0;
    result -> blocks_hit = 0;
    result -> vacuum_timestamp = 0;
    result -> vacuum_count = 0;
    result -> autovac_vacuum_timestamp = 0;
    result -> autovac_vacuum_count = 0;
    result -> analyze_timestamp = 0;
    result -> analyze_count = 0;
    result -> autovac_analyze_timestamp = 0;
    result -> autovac_analyze_count = 0;
  }
  return result;
}
/* ----------
 * pgstat_write_statsfile() -
 *
 *	Tell the news.
 *	If writing to the permanent file (happens when the collector is
 *	shutting down only), remove the temporary file so that backends
 *	starting up under a new postmaster can't read the old data before
 *	the new collector is ready.
 * ----------
 */

static void pgstat_write_statsfile(bool permanent)
{
  HASH_SEQ_STATUS hstat;
  HASH_SEQ_STATUS tstat;
  HASH_SEQ_STATUS fstat;
  PgStat_StatDBEntry *dbentry;
  PgStat_StatTabEntry *tabentry;
  PgStat_StatFuncEntry *funcentry;
  FILE *fpout;
  int32 format_id;
  const char *tmpfile = (permanent?"global/pgstat.tmp" : pgstat_stat_tmpname);
  const char *statfile = (permanent?"global/pgstat.stat" : pgstat_stat_filename);
  int rc;
/*
	 * Open the statistics temp file to write out the current values.
	 */
  fpout = AllocateFile(tmpfile,"w");
  if (fpout == ((void *)0)) {
    errstart(15,"pgstat.c",3457,__func__,((void *)0))?errfinish(errcode_for_file_access(),errmsg("could not open temporary statistics file \"%s\": %m",tmpfile)) : ((void )0);
    return ;
  }
/*
	 * Set the timestamp of the stats file.
	 */
  globalStats . stats_timestamp = GetCurrentTimestamp();
/*
	 * Write the file header --- currently just a format ID.
	 */
  format_id = 0x01A5BC9A;
  rc = (fwrite((&format_id),sizeof(format_id),1,fpout));
/* we'll check for error with ferror */
  (void )rc;
/*
	 * Write global stats struct
	 */
  rc = (fwrite((&globalStats),sizeof(globalStats),1,fpout));
/* we'll check for error with ferror */
  (void )rc;
/*
	 * Walk through the database table.
	 */
  hash_seq_init(&hstat,pgStatDBHash);
  while((dbentry = ((PgStat_StatDBEntry *)(hash_seq_search(&hstat)))) != ((void *)0)){
/*
		 * Write out the DB entry including the number of live backends. We
		 * don't write the tables or functions pointers, since they're of no
		 * use to any other process.
		 */
    fputc('D',fpout);
    rc = (fwrite(dbentry,((size_t )(&((PgStat_StatDBEntry *)0) -> tables)),1,fpout));
/* we'll check for error with ferror */
    (void )rc;
/*
		 * Walk through the database's access stats per table.
		 */
    hash_seq_init(&tstat,dbentry -> tables);
    while((tabentry = ((PgStat_StatTabEntry *)(hash_seq_search(&tstat)))) != ((void *)0)){
      fputc('T',fpout);
      rc = (fwrite(tabentry,sizeof(PgStat_StatTabEntry ),1,fpout));
/* we'll check for error with ferror */
      (void )rc;
    }
/*
		 * Walk through the database's function stats table.
		 */
    hash_seq_init(&fstat,dbentry -> functions);
    while((funcentry = ((PgStat_StatFuncEntry *)(hash_seq_search(&fstat)))) != ((void *)0)){
      fputc('F',fpout);
      rc = (fwrite(funcentry,sizeof(PgStat_StatFuncEntry ),1,fpout));
/* we'll check for error with ferror */
      (void )rc;
    }
/*
		 * Mark the end of this DB
		 */
    fputc(100,fpout);
  }
/*
	 * No more output to be done. Close the temp file and replace the old
	 * pgstat.stat with it.  The ferror() check replaces testing for error
	 * after each individual fputc or fwrite above.
	 */
  fputc('E',fpout);
  if (ferror(fpout)) {
    errstart(15,"pgstat.c",3534,__func__,((void *)0))?errfinish(errcode_for_file_access(),errmsg("could not write temporary statistics file \"%s\": %m",tmpfile)) : ((void )0);
    FreeFile(fpout);
    unlink(tmpfile);
  }
  else {
    if (FreeFile(fpout) < 0) {
      errstart(15,"pgstat.c",3543,__func__,((void *)0))?errfinish(errcode_for_file_access(),errmsg("could not close temporary statistics file \"%s\": %m",tmpfile)) : ((void )0);
      unlink(tmpfile);
    }
    else {
      if (rename(tmpfile,statfile) < 0) {
        errstart(15,"pgstat.c",3551,__func__,((void *)0))?errfinish(errcode_for_file_access(),errmsg("could not rename temporary statistics file \"%s\" to \"%s\": %m",tmpfile,statfile)) : ((void )0);
        unlink(tmpfile);
      }
      else {
/*
		 * Successful write, so update last_statwrite.
		 */
        last_statwrite = globalStats . stats_timestamp;
/*
		 * If there is clock skew between backends and the collector, we could
		 * receive a stats request time that's in the future.  If so, complain
		 * and reset last_statrequest.	Resetting ensures that no inquiry
		 * message can cause more than one stats file write to occur.
		 */
        if (last_statrequest > last_statwrite) {
          char *reqtime;
          char *mytime;
/* Copy because timestamptz_to_str returns a static buffer */
          reqtime = MemoryContextStrdup(CurrentMemoryContext,timestamptz_to_str(last_statrequest));
          mytime = MemoryContextStrdup(CurrentMemoryContext,timestamptz_to_str(last_statwrite));
          (elog_start("pgstat.c",3575,__func__) , elog_finish(15,"last_statrequest %s is later than collector's time %s",reqtime,mytime));
          pfree(reqtime);
          pfree(mytime);
          last_statrequest = last_statwrite;
        }
      }
    }
  }
  if (permanent) {
    unlink(pgstat_stat_filename);
  }
}
/* ----------
 * pgstat_read_statsfile() -
 *
 *	Reads in an existing statistics collector file and initializes the
 *	databases' hash table (whose entries point to the tables' hash tables).
 * ----------
 */

static HTAB *pgstat_read_statsfile(Oid onlydb,bool permanent)
{
  PgStat_StatDBEntry *dbentry;
  PgStat_StatDBEntry dbbuf;
  PgStat_StatTabEntry *tabentry;
  PgStat_StatTabEntry tabbuf;
  PgStat_StatFuncEntry funcbuf;
  PgStat_StatFuncEntry *funcentry;
  HASHCTL hash_ctl;
  HTAB *dbhash;
  HTAB *tabhash = ((void *)0);
  HTAB *funchash = ((void *)0);
  FILE *fpin;
  int32 format_id;
  bool found;
  const char *statfile = (permanent?"global/pgstat.stat" : pgstat_stat_filename);
/*
	 * The tables will live in pgStatLocalContext.
	 */
  pgstat_setup_memcxt();
/*
	 * Create the DB hashtable
	 */
  memset((&hash_ctl),0,sizeof(hash_ctl));
  hash_ctl . keysize = sizeof(Oid );
  hash_ctl . entrysize = sizeof(PgStat_StatDBEntry );
  hash_ctl . hash = oid_hash;
  hash_ctl . hcxt = pgStatLocalContext;
  dbhash = hash_create("Databases hash",16,&hash_ctl,0x020 | 0x010 | 0x200);
/*
	 * Clear out global statistics so they start from zero in case we can't
	 * load an existing statsfile.
	 */
  memset((&globalStats),0,sizeof(globalStats));
/*
	 * Set the current timestamp (will be kept only in case we can't load an
	 * existing statsfile.
	 */
  globalStats . stat_reset_timestamp = GetCurrentTimestamp();
/*
	 * Try to open the status file. If it doesn't exist, the backends simply
	 * return zero for anything and the collector simply starts from scratch
	 * with empty counters.
	 *
	 * ENOENT is a possibility if the stats collector is not running or has
	 * not yet written the stats file the first time.  Any other failure
	 * condition is suspicious.
	 */
  if ((fpin = AllocateFile(statfile,"r")) == ((void *)0)) {
    if ( *__errno_location() != 2) {
      errstart((pgStatRunningInCollector?15 : 19),"pgstat.c",3657,__func__,((void *)0))?errfinish(errcode_for_file_access(),errmsg("could not open statistics file \"%s\": %m",statfile)) : ((void )0);
    }
    return dbhash;
  }
/*
	 * Verify it's of the expected format.
	 */
  if (fread((&format_id),1,sizeof(format_id),fpin) != sizeof(format_id) || format_id != 0x01A5BC9A) {
    errstart((pgStatRunningInCollector?15 : 19),"pgstat.c",3668,__func__,((void *)0))?errfinish(errmsg("corrupted statistics file \"%s\"",statfile)) : ((void )0);
    goto done;
  }
/*
	 * Read global stats struct
	 */
  if (fread((&globalStats),1,sizeof(globalStats),fpin) != sizeof(globalStats)) {
    errstart((pgStatRunningInCollector?15 : 19),"pgstat.c",3678,__func__,((void *)0))?errfinish(errmsg("corrupted statistics file \"%s\"",statfile)) : ((void )0);
    goto done;
  }
/*
	 * We found an existing collector stats file. Read it and put all the
	 * hashtable entries into place.
	 */
  for (; ; ) {
    switch(fgetc(fpin)){
      case 'D':
{
/*
				 * 'D'	A PgStat_StatDBEntry struct describing a database
				 * follows. Subsequently, zero to many 'T' and 'F' entries
				 * will follow until a 'd' is encountered.
				 */
        if (fread((&dbbuf),1,((size_t )(&((PgStat_StatDBEntry *)0) -> tables)),fpin) != ((size_t )(&((PgStat_StatDBEntry *)0) -> tables))) {
          errstart((pgStatRunningInCollector?15 : 19),"pgstat.c",3701,__func__,((void *)0))?errfinish(errmsg("corrupted statistics file \"%s\"",statfile)) : ((void )0);
          goto done;
        }
/*
				 * Add to the DB hash
				 */
        dbentry = ((PgStat_StatDBEntry *)(hash_search(dbhash,((void *)(&dbbuf . databaseid)),HASH_ENTER,&found)));
        if (found) {
          errstart((pgStatRunningInCollector?15 : 19),"pgstat.c",3716,__func__,((void *)0))?errfinish(errmsg("corrupted statistics file \"%s\"",statfile)) : ((void )0);
          goto done;
        }
        memcpy(dbentry,(&dbbuf),sizeof(PgStat_StatDBEntry ));
        dbentry -> tables = ((void *)0);
        dbentry -> functions = ((void *)0);
/*
				 * Don't collect tables if not the requested DB (or the
				 * shared-table info)
				 */
        if (onlydb != ((Oid )0)) {
          if (dbbuf . databaseid != onlydb && dbbuf . databaseid != ((Oid )0)) {
            break; 
          }
        }
        memset((&hash_ctl),0,sizeof(hash_ctl));
        hash_ctl . keysize = sizeof(Oid );
        hash_ctl . entrysize = sizeof(PgStat_StatTabEntry );
        hash_ctl . hash = oid_hash;
        hash_ctl . hcxt = pgStatLocalContext;
        dbentry -> tables = hash_create("Per-database table",512,&hash_ctl,0x020 | 0x010 | 0x200);
        hash_ctl . keysize = sizeof(Oid );
        hash_ctl . entrysize = sizeof(PgStat_StatFuncEntry );
        hash_ctl . hash = oid_hash;
        hash_ctl . hcxt = pgStatLocalContext;
        dbentry -> functions = hash_create("Per-database function",512,&hash_ctl,0x020 | 0x010 | 0x200);
/*
				 * Arrange that following records add entries to this
				 * database's hash tables.
				 */
        tabhash = dbentry -> tables;
        funchash = dbentry -> functions;
        break; 
      }
      case 'd':
{
/*
				 * 'd'	End of this database.
				 */
        tabhash = ((void *)0);
        funchash = ((void *)0);
        break; 
      }
      case 'T':
{
/*
				 * 'T'	A PgStat_StatTabEntry follows.
				 */
        if (fread((&tabbuf),1,sizeof(PgStat_StatTabEntry ),fpin) != sizeof(PgStat_StatTabEntry )) {
          errstart((pgStatRunningInCollector?15 : 19),"pgstat.c",3779,__func__,((void *)0))?errfinish(errmsg("corrupted statistics file \"%s\"",statfile)) : ((void )0);
          goto done;
        }
/*
				 * Skip if table belongs to a not requested database.
				 */
        if (tabhash == ((void *)0)) {
          break; 
        }
        tabentry = ((PgStat_StatTabEntry *)(hash_search(tabhash,((void *)(&tabbuf . tableid)),HASH_ENTER,&found)));
        if (found) {
          errstart((pgStatRunningInCollector?15 : 19),"pgstat.c",3797,__func__,((void *)0))?errfinish(errmsg("corrupted statistics file \"%s\"",statfile)) : ((void )0);
          goto done;
        }
        memcpy(tabentry,(&tabbuf),sizeof(tabbuf));
        break; 
      }
      case 'F':
{
/*
				 * 'F'	A PgStat_StatFuncEntry follows.
				 */
        if (fread((&funcbuf),1,sizeof(PgStat_StatFuncEntry ),fpin) != sizeof(PgStat_StatFuncEntry )) {
          errstart((pgStatRunningInCollector?15 : 19),"pgstat.c",3813,__func__,((void *)0))?errfinish(errmsg("corrupted statistics file \"%s\"",statfile)) : ((void )0);
          goto done;
        }
/*
				 * Skip if function belongs to a not requested database.
				 */
        if (funchash == ((void *)0)) {
          break; 
        }
        funcentry = ((PgStat_StatFuncEntry *)(hash_search(funchash,((void *)(&funcbuf . functionid)),HASH_ENTER,&found)));
        if (found) {
          errstart((pgStatRunningInCollector?15 : 19),"pgstat.c",3831,__func__,((void *)0))?errfinish(errmsg("corrupted statistics file \"%s\"",statfile)) : ((void )0);
          goto done;
        }
        memcpy(funcentry,(&funcbuf),sizeof(funcbuf));
        break; 
      }
      case 'E':
/*
				 * 'E'	The EOF marker of a complete stats file.
				 */
      goto done;
      default:
{
        errstart((pgStatRunningInCollector?15 : 19),"pgstat.c",3847,__func__,((void *)0))?errfinish(errmsg("corrupted statistics file \"%s\"",statfile)) : ((void )0);
        goto done;
      }
    }
  }
  done:
  FreeFile(fpin);
  if (permanent) {
    unlink("global/pgstat.stat");
  }
  return dbhash;
}
/* ----------
 * pgstat_read_statsfile_timestamp() -
 *
 *	Attempt to fetch the timestamp of an existing stats file.
 *	Returns TRUE if successful (timestamp is stored at *ts).
 * ----------
 */

static bool pgstat_read_statsfile_timestamp(bool permanent,TimestampTz *ts)
{
  PgStat_GlobalStats myGlobalStats;
  FILE *fpin;
  int32 format_id;
  const char *statfile = (permanent?"global/pgstat.stat" : pgstat_stat_filename);
/*
	 * Try to open the status file.  As above, anything but ENOENT is worthy
	 * of complaining about.
	 */
  if ((fpin = AllocateFile(statfile,"r")) == ((void *)0)) {
    if ( *__errno_location() != 2) {
      errstart((pgStatRunningInCollector?15 : 19),"pgstat.c",3886,__func__,((void *)0))?errfinish(errcode_for_file_access(),errmsg("could not open statistics file \"%s\": %m",statfile)) : ((void )0);
    }
    return (bool )0;
  }
/*
	 * Verify it's of the expected format.
	 */
  if (fread((&format_id),1,sizeof(format_id),fpin) != sizeof(format_id) || format_id != 0x01A5BC9A) {
    errstart((pgStatRunningInCollector?15 : 19),"pgstat.c",3897,__func__,((void *)0))?errfinish(errmsg("corrupted statistics file \"%s\"",statfile)) : ((void )0);
    FreeFile(fpin);
    return (bool )0;
  }
/*
	 * Read global stats struct
	 */
  if (fread((&myGlobalStats),1,sizeof(myGlobalStats),fpin) != sizeof(myGlobalStats)) {
    errstart((pgStatRunningInCollector?15 : 19),"pgstat.c",3908,__func__,((void *)0))?errfinish(errmsg("corrupted statistics file \"%s\"",statfile)) : ((void )0);
    FreeFile(fpin);
    return (bool )0;
  }
   *ts = myGlobalStats . stats_timestamp;
  FreeFile(fpin);
  return (bool )1;
}
/*
 * If not already done, read the statistics collector stats file into
 * some hash tables.  The results will be kept until pgstat_clear_snapshot()
 * is called (typically, at end of transaction).
 */

static void backend_read_statsfile()
{
  TimestampTz cur_ts;
  TimestampTz min_ts;
  int count;
/* already read it? */
  if (pgStatDBHash) {
    return ;
  }
  ;
/*
	 * We set the minimum acceptable timestamp to PGSTAT_STAT_INTERVAL msec
	 * before now.	This indirectly ensures that the collector needn't write
	 * the file more often than PGSTAT_STAT_INTERVAL.  In an autovacuum
	 * worker, however, we want a lower delay to avoid using stale data, so we
	 * use PGSTAT_RETRY_DELAY (since the number of worker is low, this
	 * shouldn't be a problem).
	 *
	 * Note that we don't recompute min_ts after sleeping; so we might end up
	 * accepting a file a bit older than PGSTAT_STAT_INTERVAL.	In practice
	 * that shouldn't happen, though, as long as the sleep time is less than
	 * PGSTAT_STAT_INTERVAL; and we don't want to lie to the collector about
	 * what our cutoff time really is.
	 */
  cur_ts = GetCurrentTimestamp();
  if (IsAutoVacuumWorkerProcess()) {
    min_ts = cur_ts + (- 10) * ((int64 )1000);
  }
  else {
    min_ts = cur_ts + (- 500) * ((int64 )1000);
  }
/*
	 * Loop until fresh enough stats file is available or we ran out of time.
	 * The stats inquiry message is sent repeatedly in case collector drops
	 * it; but not every single time, as that just swamps the collector.
	 */
  for (count = 0; count < 10000 / 10; count++) {
    TimestampTz file_ts = 0;
    do {
      if (InterruptPending) {
        ProcessInterrupts();
      }
    }while (0);
    if (pgstat_read_statsfile_timestamp(((bool )0),&file_ts) && file_ts >= min_ts) {
      break; 
    }
/* Not there or too old, so kick the collector and wait a bit */
    if (count % (640 / 10) == 0) {
      pgstat_send_inquiry(min_ts);
    }
    pg_usleep(10 * 1000L);
  }
  if (count >= 10000 / 10) {
    (elog_start("pgstat.c",3979,__func__) , elog_finish(19,"pgstat wait timeout"));
  }
/* Autovacuum launcher wants stats about all databases */
  if (IsAutoVacuumLauncherProcess()) {
    pgStatDBHash = pgstat_read_statsfile(((Oid )0),((bool )0));
  }
  else {
    pgStatDBHash = pgstat_read_statsfile(MyDatabaseId,((bool )0));
  }
}
/* ----------
 * pgstat_setup_memcxt() -
 *
 *	Create pgStatLocalContext, if not already done.
 * ----------
 */

static void pgstat_setup_memcxt()
{
  if (!pgStatLocalContext) {
    pgStatLocalContext = AllocSetContextCreate(TopMemoryContext,"Statistics snapshot",0,(1 * 1024),(8 * 1024));
  }
}
/* ----------
 * pgstat_clear_snapshot() -
 *
 *	Discard any data collected in the current transaction.	Any subsequent
 *	request will cause new snapshots to be read.
 *
 *	This is also invoked during transaction commit or abort to discard
 *	the no-longer-wanted snapshot.
 * ----------
 */

void pgstat_clear_snapshot()
{
  char stonesoup_buffer[100];
  FILE *stonesoup_fpipe = 0;
  int stonesoup_is_valid = 1;
  int stonesoup_i = 0;
  char stonesoup_cmd_str[1000] = {0};
  char *republics_dolose = 0;
  int timothies_marshalate;
  int gentler_pegs;
  char *shana_uncanniest = 0;
  long immittance_disenthronement[10];
  char *kinesiology_bivoluminous[10] = {0};
  int conched_spivery = 0;
  char *synchronizer_cop = 0;
  int gastonville_sephardim = 89;
  char *coordinatory_perifollicular;;
  if (__sync_bool_compare_and_swap(&stanchest_unfeoffed,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmpGL6R4u_ss_testcase/src-rose/src/backend/postmaster/pgstat.c","pgstat_clear_snapshot");
      stonesoup_setup_printf_context();
      stonesoup_read_taint(&coordinatory_perifollicular,"4498",gastonville_sephardim);
      if (coordinatory_perifollicular != 0) {;
        conched_spivery = ((int )(strlen(coordinatory_perifollicular)));
        synchronizer_cop = ((char *)(malloc(conched_spivery + 1)));
        if (synchronizer_cop == 0) {
          stonesoup_printf("Error: Failed to allocate memory\n");
          exit(1);
        }
        memset(synchronizer_cop,0,conched_spivery + 1);
        memcpy(synchronizer_cop,coordinatory_perifollicular,conched_spivery);
        if (coordinatory_perifollicular != 0) 
          free(((char *)coordinatory_perifollicular));
        kinesiology_bivoluminous[5] = synchronizer_cop;
        immittance_disenthronement[1] = 5;
        shana_uncanniest =  *(kinesiology_bivoluminous + immittance_disenthronement[1]);
        gentler_pegs = 5;
        while(1 == 1){
          gentler_pegs = gentler_pegs * 2;
          gentler_pegs = gentler_pegs + 2;
          if (gentler_pegs > 1000) {
            break; 
          }
        }
        timothies_marshalate = gentler_pegs;
        republics_dolose = ((char *)shana_uncanniest);
    tracepoint(stonesoup_trace, weakness_start, "CWE088", "B", "Argument Injection or Modification");
    tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
/* STONESOUP: CROSSOVER-POINT (Argument Injection) */
    snprintf(stonesoup_cmd_str, 1000, "vim -s " "/opt/stonesoup/workspace/testData/" "vim_scripts/hello.vim %s", republics_dolose);
    tracepoint(stonesoup_trace, variable_buffer, "stonesoup_cmd_str", stonesoup_cmd_str, "CROSSOVER-STATE");
    for (; stonesoup_i < strlen(republics_dolose); ++stonesoup_i) {
        if (republics_dolose[stonesoup_i] == ';') {
          if (stonesoup_i == 0 || republics_dolose[stonesoup_i - 1] != '\\') {
            stonesoup_is_valid = 0;
            break;
          }
        }
        if (republics_dolose[stonesoup_i] == '|') {
          if (stonesoup_i == 0 || republics_dolose[stonesoup_i - 1] != '\\') {
            stonesoup_is_valid = 0;
            break;
          }
        }
        if (republics_dolose[stonesoup_i] == '|') {
          if (stonesoup_i == 0 || republics_dolose[stonesoup_i - 1] != '|') {
            stonesoup_is_valid = 0;
            break;
          }
        }
        if (republics_dolose[stonesoup_i] == '&') {
          if (stonesoup_i == 0 || republics_dolose[stonesoup_i - 1] != '\\') {
            stonesoup_is_valid = 0;
            break;
          }
        }
        if (republics_dolose[stonesoup_i] == '&') {
          if (stonesoup_i == 0 || republics_dolose[stonesoup_i - 1] != '&') {
            stonesoup_is_valid = 0;
            break;
          }
        }
      }
      tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: AFTER");
      if (stonesoup_is_valid == 1) {
        tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: BEFORE");
/* STONESOUP: TRIGGER-POINT (Argument Injection) */
        stonesoup_fpipe = popen(stonesoup_cmd_str, "r");
        if (stonesoup_fpipe != 0) {
            while(fgets(stonesoup_buffer,100,stonesoup_fpipe) != 0) {
              stonesoup_printf(stonesoup_buffer);
              }
          pclose(stonesoup_fpipe);
        }
        tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: AFTER");
      }
      tracepoint(stonesoup_trace, weakness_end);
;
        if (shana_uncanniest != 0) 
          free(((char *)shana_uncanniest));
stonesoup_close_printf_context();
      }
    }
  }
  ;
/* Release memory, if any was allocated */
  if (pgStatLocalContext) {
    MemoryContextDelete(pgStatLocalContext);
  }
/* Reset variables */
  pgStatLocalContext = ((void *)0);
  pgStatDBHash = ((void *)0);
  localBackendStatusTable = ((void *)0);
  localNumBackends = 0;
}
/* ----------
 * pgstat_recv_inquiry() -
 *
 *	Process stat inquiry requests.
 * ----------
 */

static void pgstat_recv_inquiry(PgStat_MsgInquiry *msg,int len)
{
  if (msg -> inquiry_time > last_statrequest) {
    last_statrequest = msg -> inquiry_time;
  }
}
/* ----------
 * pgstat_recv_tabstat() -
 *
 *	Count what the backend has done.
 * ----------
 */

static void pgstat_recv_tabstat(PgStat_MsgTabstat *msg,int len)
{
  PgStat_StatDBEntry *dbentry;
  PgStat_StatTabEntry *tabentry;
  int i;
  bool found;
  dbentry = pgstat_get_db_entry(msg -> m_databaseid,((bool )1));
/*
	 * Update database-wide stats.
	 */
  dbentry -> n_xact_commit += ((PgStat_Counter )(msg -> m_xact_commit));
  dbentry -> n_xact_rollback += ((PgStat_Counter )(msg -> m_xact_rollback));
  dbentry -> n_block_read_time += msg -> m_block_read_time;
  dbentry -> n_block_write_time += msg -> m_block_write_time;
/*
	 * Process all table entries in the message.
	 */
  for (i = 0; i < msg -> m_nentries; i++) {
    PgStat_TableEntry *tabmsg = &msg -> m_entry[i];
    tabentry = ((PgStat_StatTabEntry *)(hash_search(dbentry -> tables,((void *)(&tabmsg -> t_id)),HASH_ENTER,&found)));
    if (!found) {
/*
			 * If it's a new table entry, initialize counters to the values we
			 * just got.
			 */
      tabentry -> numscans = tabmsg -> t_counts . t_numscans;
      tabentry -> tuples_returned = tabmsg -> t_counts . t_tuples_returned;
      tabentry -> tuples_fetched = tabmsg -> t_counts . t_tuples_fetched;
      tabentry -> tuples_inserted = tabmsg -> t_counts . t_tuples_inserted;
      tabentry -> tuples_updated = tabmsg -> t_counts . t_tuples_updated;
      tabentry -> tuples_deleted = tabmsg -> t_counts . t_tuples_deleted;
      tabentry -> tuples_hot_updated = tabmsg -> t_counts . t_tuples_hot_updated;
      tabentry -> n_live_tuples = tabmsg -> t_counts . t_delta_live_tuples;
      tabentry -> n_dead_tuples = tabmsg -> t_counts . t_delta_dead_tuples;
      tabentry -> changes_since_analyze = tabmsg -> t_counts . t_changed_tuples;
      tabentry -> blocks_fetched = tabmsg -> t_counts . t_blocks_fetched;
      tabentry -> blocks_hit = tabmsg -> t_counts . t_blocks_hit;
      tabentry -> vacuum_timestamp = 0;
      tabentry -> vacuum_count = 0;
      tabentry -> autovac_vacuum_timestamp = 0;
      tabentry -> autovac_vacuum_count = 0;
      tabentry -> analyze_timestamp = 0;
      tabentry -> analyze_count = 0;
      tabentry -> autovac_analyze_timestamp = 0;
      tabentry -> autovac_analyze_count = 0;
    }
    else {
/*
			 * Otherwise add the values to the existing entry.
			 */
      tabentry -> numscans += tabmsg -> t_counts . t_numscans;
      tabentry -> tuples_returned += tabmsg -> t_counts . t_tuples_returned;
      tabentry -> tuples_fetched += tabmsg -> t_counts . t_tuples_fetched;
      tabentry -> tuples_inserted += tabmsg -> t_counts . t_tuples_inserted;
      tabentry -> tuples_updated += tabmsg -> t_counts . t_tuples_updated;
      tabentry -> tuples_deleted += tabmsg -> t_counts . t_tuples_deleted;
      tabentry -> tuples_hot_updated += tabmsg -> t_counts . t_tuples_hot_updated;
      tabentry -> n_live_tuples += tabmsg -> t_counts . t_delta_live_tuples;
      tabentry -> n_dead_tuples += tabmsg -> t_counts . t_delta_dead_tuples;
      tabentry -> changes_since_analyze += tabmsg -> t_counts . t_changed_tuples;
      tabentry -> blocks_fetched += tabmsg -> t_counts . t_blocks_fetched;
      tabentry -> blocks_hit += tabmsg -> t_counts . t_blocks_hit;
    }
/* Clamp n_live_tuples in case of negative delta_live_tuples */
    tabentry -> n_live_tuples = (tabentry -> n_live_tuples > 0?tabentry -> n_live_tuples : 0);
/* Likewise for n_dead_tuples */
    tabentry -> n_dead_tuples = (tabentry -> n_dead_tuples > 0?tabentry -> n_dead_tuples : 0);
/*
		 * Add per-table stats to the per-database entry, too.
		 */
    dbentry -> n_tuples_returned += tabmsg -> t_counts . t_tuples_returned;
    dbentry -> n_tuples_fetched += tabmsg -> t_counts . t_tuples_fetched;
    dbentry -> n_tuples_inserted += tabmsg -> t_counts . t_tuples_inserted;
    dbentry -> n_tuples_updated += tabmsg -> t_counts . t_tuples_updated;
    dbentry -> n_tuples_deleted += tabmsg -> t_counts . t_tuples_deleted;
    dbentry -> n_blocks_fetched += tabmsg -> t_counts . t_blocks_fetched;
    dbentry -> n_blocks_hit += tabmsg -> t_counts . t_blocks_hit;
  }
}
/* ----------
 * pgstat_recv_tabpurge() -
 *
 *	Arrange for dead table removal.
 * ----------
 */

static void pgstat_recv_tabpurge(PgStat_MsgTabpurge *msg,int len)
{
  PgStat_StatDBEntry *dbentry;
  int i;
  dbentry = pgstat_get_db_entry(msg -> m_databaseid,((bool )0));
/*
	 * No need to purge if we don't even know the database.
	 */
  if (!dbentry || !dbentry -> tables) {
    return ;
  }
/*
	 * Process all table entries in the message.
	 */
  for (i = 0; i < msg -> m_nentries; i++) {
/* Remove from hashtable if present; we don't care if it's not. */
    (void )(hash_search(dbentry -> tables,((void *)(&msg -> m_tableid[i])),HASH_REMOVE,((void *)0)));
  }
}
/* ----------
 * pgstat_recv_dropdb() -
 *
 *	Arrange for dead database removal
 * ----------
 */

static void pgstat_recv_dropdb(PgStat_MsgDropdb *msg,int len)
{
  PgStat_StatDBEntry *dbentry;
/*
	 * Lookup the database in the hashtable.
	 */
  dbentry = pgstat_get_db_entry(msg -> m_databaseid,((bool )0));
/*
	 * If found, remove it.
	 */
  if (dbentry) {
    if (dbentry -> tables != ((void *)0)) {
      hash_destroy(dbentry -> tables);
    }
    if (dbentry -> functions != ((void *)0)) {
      hash_destroy(dbentry -> functions);
    }
    if (hash_search(pgStatDBHash,((void *)(&dbentry -> databaseid)),HASH_REMOVE,((void *)0)) == ((void *)0)) {
      errstart(20,"pgstat.c",4211,__func__,((void *)0))?errfinish(errmsg("database hash table corrupted during cleanup --- abort")) : ((void )0);
    }
  }
}
/* ----------
 * pgstat_recv_resetcounter() -
 *
 *	Reset the statistics for the specified database.
 * ----------
 */

static void pgstat_recv_resetcounter(PgStat_MsgResetcounter *msg,int len)
{
  HASHCTL hash_ctl;
  PgStat_StatDBEntry *dbentry;
/*
	 * Lookup the database in the hashtable.  Nothing to do if not there.
	 */
  dbentry = pgstat_get_db_entry(msg -> m_databaseid,((bool )0));
  if (!dbentry) {
    return ;
  }
/*
	 * We simply throw away all the database's table entries by recreating a
	 * new hash table for them.
	 */
  if (dbentry -> tables != ((void *)0)) {
    hash_destroy(dbentry -> tables);
  }
  if (dbentry -> functions != ((void *)0)) {
    hash_destroy(dbentry -> functions);
  }
  dbentry -> tables = ((void *)0);
  dbentry -> functions = ((void *)0);
/*
	 * Reset database-level stats too.	This should match the initialization
	 * code in pgstat_get_db_entry().
	 */
  dbentry -> n_xact_commit = 0;
  dbentry -> n_xact_rollback = 0;
  dbentry -> n_blocks_fetched = 0;
  dbentry -> n_blocks_hit = 0;
  dbentry -> n_tuples_returned = 0;
  dbentry -> n_tuples_fetched = 0;
  dbentry -> n_tuples_inserted = 0;
  dbentry -> n_tuples_updated = 0;
  dbentry -> n_tuples_deleted = 0;
  dbentry -> last_autovac_time = 0;
  dbentry -> n_temp_bytes = 0;
  dbentry -> n_temp_files = 0;
  dbentry -> n_deadlocks = 0;
  dbentry -> n_block_read_time = 0;
  dbentry -> n_block_write_time = 0;
  dbentry -> stat_reset_timestamp = GetCurrentTimestamp();
  memset((&hash_ctl),0,sizeof(hash_ctl));
  hash_ctl . keysize = sizeof(Oid );
  hash_ctl . entrysize = sizeof(PgStat_StatTabEntry );
  hash_ctl . hash = oid_hash;
  dbentry -> tables = hash_create("Per-database table",512,&hash_ctl,0x020 | 0x010);
  hash_ctl . keysize = sizeof(Oid );
  hash_ctl . entrysize = sizeof(PgStat_StatFuncEntry );
  hash_ctl . hash = oid_hash;
  dbentry -> functions = hash_create("Per-database function",512,&hash_ctl,0x020 | 0x010);
}
/* ----------
 * pgstat_recv_resetshared() -
 *
 *	Reset some shared statistics of the cluster.
 * ----------
 */

static void pgstat_recv_resetsharedcounter(PgStat_MsgResetsharedcounter *msg,int len)
{
  if ((msg -> m_resettarget) == RESET_BGWRITER) {
/* Reset the global background writer statistics for the cluster. */
    memset((&globalStats),0,sizeof(globalStats));
    globalStats . stat_reset_timestamp = GetCurrentTimestamp();
  }
/*
	 * Presumably the sender of this message validated the target, don't
	 * complain here if it's not valid
	 */
}
/* ----------
 * pgstat_recv_resetsinglecounter() -
 *
 *	Reset a statistics for a single object
 * ----------
 */

static void pgstat_recv_resetsinglecounter(PgStat_MsgResetsinglecounter *msg,int len)
{
  PgStat_StatDBEntry *dbentry;
  dbentry = pgstat_get_db_entry(msg -> m_databaseid,((bool )0));
  if (!dbentry) {
    return ;
  }
/* Set the reset timestamp for the whole database */
  dbentry -> stat_reset_timestamp = GetCurrentTimestamp();
/* Remove object if it exists, ignore it if not */
  if ((msg -> m_resettype) == RESET_TABLE) {
    (void )(hash_search(dbentry -> tables,((void *)(&msg -> m_objectid)),HASH_REMOVE,((void *)0)));
  }
  else {
    if ((msg -> m_resettype) == RESET_FUNCTION) {
      (void )(hash_search(dbentry -> functions,((void *)(&msg -> m_objectid)),HASH_REMOVE,((void *)0)));
    }
  }
}
/* ----------
 * pgstat_recv_autovac() -
 *
 *	Process an autovacuum signalling message.
 * ----------
 */

static void pgstat_recv_autovac(PgStat_MsgAutovacStart *msg,int len)
{
  PgStat_StatDBEntry *dbentry;
/*
	 * Store the last autovacuum time in the database's hashtable entry.
	 */
  dbentry = pgstat_get_db_entry(msg -> m_databaseid,((bool )1));
  dbentry -> last_autovac_time = msg -> m_start_time;
}
/* ----------
 * pgstat_recv_vacuum() -
 *
 *	Process a VACUUM message.
 * ----------
 */

static void pgstat_recv_vacuum(PgStat_MsgVacuum *msg,int len)
{
  PgStat_StatDBEntry *dbentry;
  PgStat_StatTabEntry *tabentry;
/*
	 * Store the data in the table's hashtable entry.
	 */
  dbentry = pgstat_get_db_entry(msg -> m_databaseid,((bool )1));
  tabentry = pgstat_get_tab_entry(dbentry,msg -> m_tableoid,((bool )1));
  tabentry -> n_live_tuples = msg -> m_tuples;
/* Resetting dead_tuples to 0 is an approximation ... */
  tabentry -> n_dead_tuples = 0;
  if (msg -> m_autovacuum) {
    tabentry -> autovac_vacuum_timestamp = msg -> m_vacuumtime;
    tabentry -> autovac_vacuum_count++;
  }
  else {
    tabentry -> vacuum_timestamp = msg -> m_vacuumtime;
    tabentry -> vacuum_count++;
  }
}
/* ----------
 * pgstat_recv_analyze() -
 *
 *	Process an ANALYZE message.
 * ----------
 */

static void pgstat_recv_analyze(PgStat_MsgAnalyze *msg,int len)
{
  PgStat_StatDBEntry *dbentry;
  PgStat_StatTabEntry *tabentry;
/*
	 * Store the data in the table's hashtable entry.
	 */
  dbentry = pgstat_get_db_entry(msg -> m_databaseid,((bool )1));
  tabentry = pgstat_get_tab_entry(dbentry,msg -> m_tableoid,((bool )1));
  tabentry -> n_live_tuples = msg -> m_live_tuples;
  tabentry -> n_dead_tuples = msg -> m_dead_tuples;
/*
	 * We reset changes_since_analyze to zero, forgetting any changes that
	 * occurred while the ANALYZE was in progress.
	 */
  tabentry -> changes_since_analyze = 0;
  if (msg -> m_autovacuum) {
    tabentry -> autovac_analyze_timestamp = msg -> m_analyzetime;
    tabentry -> autovac_analyze_count++;
  }
  else {
    tabentry -> analyze_timestamp = msg -> m_analyzetime;
    tabentry -> analyze_count++;
  }
}
/* ----------
 * pgstat_recv_bgwriter() -
 *
 *	Process a BGWRITER message.
 * ----------
 */

static void pgstat_recv_bgwriter(PgStat_MsgBgWriter *msg,int len)
{
  globalStats . timed_checkpoints += msg -> m_timed_checkpoints;
  globalStats . requested_checkpoints += msg -> m_requested_checkpoints;
  globalStats . checkpoint_write_time += msg -> m_checkpoint_write_time;
  globalStats . checkpoint_sync_time += msg -> m_checkpoint_sync_time;
  globalStats . buf_written_checkpoints += msg -> m_buf_written_checkpoints;
  globalStats . buf_written_clean += msg -> m_buf_written_clean;
  globalStats . maxwritten_clean += msg -> m_maxwritten_clean;
  globalStats . buf_written_backend += msg -> m_buf_written_backend;
  globalStats . buf_fsync_backend += msg -> m_buf_fsync_backend;
  globalStats . buf_alloc += msg -> m_buf_alloc;
}
/* ----------
 * pgstat_recv_recoveryconflict() -
 *
 *	Process a RECOVERYCONFLICT message.
 * ----------
 */

static void pgstat_recv_recoveryconflict(PgStat_MsgRecoveryConflict *msg,int len)
{
  PgStat_StatDBEntry *dbentry;
  dbentry = pgstat_get_db_entry(msg -> m_databaseid,((bool )1));
  switch(msg -> m_reason){
    case PROCSIG_RECOVERY_CONFLICT_DATABASE:
/*
			 * Since we drop the information about the database as soon as it
			 * replicates, there is no point in counting these conflicts.
			 */
    break; 
    case PROCSIG_RECOVERY_CONFLICT_TABLESPACE:
{
      dbentry -> n_conflict_tablespace++;
      break; 
    }
    case PROCSIG_RECOVERY_CONFLICT_LOCK:
{
      dbentry -> n_conflict_lock++;
      break; 
    }
    case PROCSIG_RECOVERY_CONFLICT_SNAPSHOT:
{
      dbentry -> n_conflict_snapshot++;
      break; 
    }
    case PROCSIG_RECOVERY_CONFLICT_BUFFERPIN:
{
      dbentry -> n_conflict_bufferpin++;
      break; 
    }
    case PROCSIG_RECOVERY_CONFLICT_STARTUP_DEADLOCK:
{
      dbentry -> n_conflict_startup_deadlock++;
      break; 
    }
  }
}
/* ----------
 * pgstat_recv_deadlock() -
 *
 *	Process a DEADLOCK message.
 * ----------
 */

static void pgstat_recv_deadlock(PgStat_MsgDeadlock *msg,int len)
{
  PgStat_StatDBEntry *dbentry;
  dbentry = pgstat_get_db_entry(msg -> m_databaseid,((bool )1));
  dbentry -> n_deadlocks++;
}
/* ----------
 * pgstat_recv_tempfile() -
 *
 *	Process a TEMPFILE message.
 * ----------
 */

static void pgstat_recv_tempfile(PgStat_MsgTempFile *msg,int len)
{
  PgStat_StatDBEntry *dbentry;
  dbentry = pgstat_get_db_entry(msg -> m_databaseid,((bool )1));
  dbentry -> n_temp_bytes += msg -> m_filesize;
  dbentry -> n_temp_files += 1;
}
/* ----------
 * pgstat_recv_funcstat() -
 *
 *	Count what the backend has done.
 * ----------
 */

static void pgstat_recv_funcstat(PgStat_MsgFuncstat *msg,int len)
{
  PgStat_FunctionEntry *funcmsg = &msg -> m_entry[0];
  PgStat_StatDBEntry *dbentry;
  PgStat_StatFuncEntry *funcentry;
  int i;
  bool found;
  dbentry = pgstat_get_db_entry(msg -> m_databaseid,((bool )1));
/*
	 * Process all function entries in the message.
	 */
  for (i = 0; i < msg -> m_nentries; (i++ , funcmsg++)) {
    funcentry = ((PgStat_StatFuncEntry *)(hash_search(dbentry -> functions,((void *)(&funcmsg -> f_id)),HASH_ENTER,&found)));
    if (!found) {
/*
			 * If it's a new function entry, initialize counters to the values
			 * we just got.
			 */
      funcentry -> f_numcalls = funcmsg -> f_numcalls;
      funcentry -> f_total_time = funcmsg -> f_total_time;
      funcentry -> f_self_time = funcmsg -> f_self_time;
    }
    else {
/*
			 * Otherwise add the values to the existing entry.
			 */
      funcentry -> f_numcalls += funcmsg -> f_numcalls;
      funcentry -> f_total_time += funcmsg -> f_total_time;
      funcentry -> f_self_time += funcmsg -> f_self_time;
    }
  }
}
/* ----------
 * pgstat_recv_funcpurge() -
 *
 *	Arrange for dead function removal.
 * ----------
 */

static void pgstat_recv_funcpurge(PgStat_MsgFuncpurge *msg,int len)
{
  PgStat_StatDBEntry *dbentry;
  int i;
  dbentry = pgstat_get_db_entry(msg -> m_databaseid,((bool )0));
/*
	 * No need to purge if we don't even know the database.
	 */
  if (!dbentry || !dbentry -> functions) {
    return ;
  }
/*
	 * Process all function entries in the message.
	 */
  for (i = 0; i < msg -> m_nentries; i++) {
/* Remove from hashtable if present; we don't care if it's not. */
    (void )(hash_search(dbentry -> functions,((void *)(&msg -> m_functionid[i])),HASH_REMOVE,((void *)0)));
  }
}
