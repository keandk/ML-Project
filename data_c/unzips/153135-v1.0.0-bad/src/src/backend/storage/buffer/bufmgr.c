/*-------------------------------------------------------------------------
 *
 * bufmgr.c
 *	  buffer manager interface routines
 *
 * Portions Copyright (c) 1996-2012, PostgreSQL Global Development Group
 * Portions Copyright (c) 1994, Regents of the University of California
 *
 *
 * IDENTIFICATION
 *	  src/backend/storage/buffer/bufmgr.c
 *
 *-------------------------------------------------------------------------
 */
/*
 * Principal entry points:
 *
 * ReadBuffer() -- find or create a buffer holding the requested page,
 *		and pin it so that no one can destroy it while this process
 *		is using it.
 *
 * ReleaseBuffer() -- unpin a buffer
 *
 * MarkBufferDirty() -- mark a pinned buffer's contents as "dirty".
 *		The disk write is delayed until buffer replacement or checkpoint.
 *
 * See also these files:
 *		freelist.c -- chooses victim for buffer replacement
 *		buf_table.c -- manages the buffer lookup table
 */
#include "postgres.h"
#include <sys/file.h>
#include <unistd.h>
#include "catalog/catalog.h"
#include "executor/instrument.h"
#include "miscadmin.h"
#include "pg_trace.h"
#include "pgstat.h"
#include "postmaster/bgwriter.h"
#include "storage/buf_internals.h"
#include "storage/bufmgr.h"
#include "storage/ipc.h"
#include "storage/proc.h"
#include "storage/smgr.h"
#include "storage/standby.h"
#include "utils/rel.h"
#include "utils/resowner.h"
#include "utils/timestamp.h"
/* Note: these two macros only work on shared buffers, not local ones! */
#define BufHdrGetBlock(bufHdr)	((Block) (BufferBlocks + ((Size) (bufHdr)->buf_id) * BLCKSZ))
#define BufferGetLSN(bufHdr)	(PageGetLSN(BufHdrGetBlock(bufHdr)))
/* Note: this macro only works on local buffers, not shared ones! */
#define LocalBufHdrGetBlock(bufHdr) \
	LocalBufferBlockPointers[-((bufHdr)->buf_id + 2)]
/* Bits in SyncOneBuffer's return value */
#define BUF_WRITTEN				0x01
#define BUF_REUSABLE			0x02
/* GUC variables */
#include <sys/stat.h> 
#include <stonesoup/stonesoup_trace.h> 
bool zero_damaged_pages = (bool )0;
int bgwriter_lru_maxpages = 100;
double bgwriter_lru_multiplier = 2.0;
bool track_io_timing = (bool )0;
/*
 * How many buffers PrefetchBuffer callers should try to stay ahead of their
 * ReadBuffer calls by.  This is maintained by the assign hook for
 * effective_io_concurrency.  Zero means "never prefetch".
 */
int target_prefetch_pages = 0;
/* local state for StartBufferIO and related functions */
static volatile BufferDesc *InProgressBuf = ((void *)0);
static bool IsForInput;
/* local state for LockBufferForCleanup */
static volatile BufferDesc *PinCountWaitBuf = ((void *)0);
static Buffer ReadBuffer_common(SMgrRelation smgr,char relpersistence,ForkNumber forkNum,BlockNumber blockNum,ReadBufferMode mode,BufferAccessStrategy strategy,bool *hit);
static bool PinBuffer(volatile BufferDesc *buf,BufferAccessStrategy strategy);
static void PinBuffer_Locked(volatile BufferDesc *buf);
static void UnpinBuffer(volatile BufferDesc *buf,bool fixOwner);
static void BufferSync(int flags);
static int SyncOneBuffer(int buf_id,bool skip_recently_used);
static void WaitIO(volatile BufferDesc *buf);
static bool StartBufferIO(volatile BufferDesc *buf,bool forInput);
static void TerminateBufferIO(volatile BufferDesc *buf,bool clear_dirty,int set_flag_bits);
static void shared_buffer_write_error_callback(void *arg);
static void local_buffer_write_error_callback(void *arg);
static volatile BufferDesc *BufferAlloc(SMgrRelation smgr,char relpersistence,ForkNumber forkNum,BlockNumber blockNum,BufferAccessStrategy strategy,bool *foundPtr);
static void FlushBuffer(volatile BufferDesc *buf,SMgrRelation reln);
static void AtProcExit_Buffers(int code,Datum arg);
/*
 * PrefetchBuffer -- initiate asynchronous read of a block of a relation
 *
 * This is named by analogy to ReadBuffer but doesn't actually allocate a
 * buffer.	Instead it tries to ensure that a future ReadBuffer for the given
 * block will not be delayed by the I/O.  Prefetching is optional.
 * No-op if prefetching isn't compiled in.
 */
int misleadingness_outgush = 0;
int stonesoup_global_variable;

union kinematically_cambers 
{
  char *haythorn_vishnuite;
  double oradea_collery;
  char *theotokos_astrobiologists;
  char nondecadence_uncontracted;
  int monogoneutic_serfishly;
}
;
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
void woolworking_truncature(union kinematically_cambers **********cochleary_trotyl);
int stonesoup_contains_char(char *str_param,char c_param)
{
  tracepoint(stonesoup_trace, trace_location, "/tmp/tmpLT86py_ss_testcase/src-rose/src/backend/storage/buffer/bufmgr.c", "stonesoup_contains_char");
  int function_found;
  function_found = 0;
  tracepoint(stonesoup_trace, variable_address, "str_param", str_param, "INITIAL-STATE");
  tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
/* STONESOUP: CROSSOVER-POINT (Free Not At Start Of Buffer) */
  while( *str_param != 0){
    if ( *str_param == c_param) {
      function_found = 1;
      break;
    }
    str_param = str_param + 1;
  }
  tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: AFTER");
  tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: BEFORE");
  tracepoint(stonesoup_trace, variable_address, "str_param", str_param, "TRIGGER-STATE");
/* STONESOUP: TRIGGER-POINT (Free Not At Start Of Buffer) */
  free(str_param);
  tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: AFTER");
  return function_found;
}
int stonesoup_toupper(int c)
{
  if (c >= 97 && c <= 122) {
    return c - 32;
  }
  return c;
}

void PrefetchBuffer(Relation reln,ForkNumber forkNum,BlockNumber blockNum)
{
#ifdef USE_PREFETCH
  ;
  ;
/* Open it at the smgr level if not already done */
  do {
    if (reln -> rd_smgr == ((void *)0)) {
      smgrsetowner(&reln -> rd_smgr,smgropen(reln -> rd_node,reln -> rd_backend));
    }
  }while (0);
  if ((reln -> rd_rel -> relpersistence) == 't') {
/* see comments in ReadBufferExtended */
    if ((reln -> rd_rel -> relpersistence) == 't' && !reln -> rd_islocaltemp) {
      errstart(20,"bufmgr.c",136,__func__,((void *)0))?errfinish(errcode((48 - 48 & 0x3F) + ((65 - 48 & 0x3F) << 6) + ((48 - 48 & 0x3F) << 12) + ((48 - 48 & 0x3F) << 18) + ((48 - 48 & 0x3F) << 24)),errmsg("cannot access temporary tables of other sessions")) : ((void )0);
    }
/* pass it off to localbuf.c */
    LocalPrefetchBuffer(reln -> rd_smgr,forkNum,blockNum);
  }
  else {
/* identity of requested block */
    BufferTag newTag;
/* hash value for newTag */
    uint32 newHash;
/* buffer partition lock for it */
    LWLockId newPartitionLock;
    int buf_id;
/* create a tag so we can lookup the buffer */
    ((newTag . rnode = reln -> rd_smgr -> smgr_rnode . node , newTag . forkNum = forkNum) , newTag . blockNum = blockNum);
/* determine its hash code and partition lock ID */
    newHash = BufTableHashCode(&newTag);
    newPartitionLock = ((LWLockId )(FirstBufMappingLock + newHash % 16));
/* see if the block is in the buffer pool already */
    LWLockAcquire(newPartitionLock,LW_SHARED);
    buf_id = BufTableLookup(&newTag,newHash);
    LWLockRelease(newPartitionLock);
/* If not in buffers, initiate prefetch */
    if (buf_id < 0) {
      smgrprefetch(reln -> rd_smgr,forkNum,blockNum);
    }
/*
		 * If the block *is* in buffers, we do nothing.  This is not really
		 * ideal: the block might be just about to be evicted, which would be
		 * stupid since we know we are going to need it soon.  But the only
		 * easy answer is to bump the usage_count, which does not seem like a
		 * great solution: when the caller does ultimately touch the block,
		 * usage_count would get bumped again, resulting in too much
		 * favoritism for blocks that are involved in a prefetch sequence. A
		 * real fix would involve some additional per-buffer state, and it's
		 * not clear that there's enough of a problem to justify that.
		 */
  }
#endif   /* USE_PREFETCH */
}
/*
 * ReadBuffer -- a shorthand for ReadBufferExtended, for reading from main
 *		fork with RBM_NORMAL mode and default strategy.
 */

Buffer ReadBuffer(Relation reln,BlockNumber blockNum)
{
  return ReadBufferExtended(reln,MAIN_FORKNUM,blockNum,RBM_NORMAL,((void *)0));
}
/*
 * ReadBufferExtended -- returns a buffer containing the requested
 *		block of the requested relation.  If the blknum
 *		requested is P_NEW, extend the relation file and
 *		allocate a new block.  (Caller is responsible for
 *		ensuring that only one backend tries to extend a
 *		relation at the same time!)
 *
 * Returns: the buffer number for the buffer containing
 *		the block read.  The returned buffer has been pinned.
 *		Does not return on error --- elog's instead.
 *
 * Assume when this function is called, that reln has been opened already.
 *
 * In RBM_NORMAL mode, the page is read from disk, and the page header is
 * validated. An error is thrown if the page header is not valid.
 *
 * RBM_ZERO_ON_ERROR is like the normal mode, but if the page header is not
 * valid, the page is zeroed instead of throwing an error. This is intended
 * for non-critical data, where the caller is prepared to repair errors.
 *
 * In RBM_ZERO mode, if the page isn't in buffer cache already, it's filled
 * with zeros instead of reading it from disk.	Useful when the caller is
 * going to fill the page from scratch, since this saves I/O and avoids
 * unnecessary failure if the page-on-disk has corrupt page headers.
 * Caution: do not use this mode to read a page that is beyond the relation's
 * current physical EOF; that is likely to cause problems in md.c when
 * the page is modified and written out. P_NEW is OK, though.
 *
 * If strategy is not NULL, a nondefault buffer access strategy is used.
 * See buffer/README for details.
 */

Buffer ReadBufferExtended(Relation reln,ForkNumber forkNum,BlockNumber blockNum,ReadBufferMode mode,BufferAccessStrategy strategy)
{
  bool hit;
  Buffer buf;
/* Open it at the smgr level if not already done */
  do {
    if (reln -> rd_smgr == ((void *)0)) {
      smgrsetowner(&reln -> rd_smgr,smgropen(reln -> rd_node,reln -> rd_backend));
    }
  }while (0);
/*
	 * Reject attempts to read non-local temporary relations; we would be
	 * likely to get wrong data since we have no visibility into the owning
	 * session's local buffers.
	 */
  if ((reln -> rd_rel -> relpersistence) == 't' && !reln -> rd_islocaltemp) {
    errstart(20,"bufmgr.c",241,__func__,((void *)0))?errfinish(errcode((48 - 48 & 0x3F) + ((65 - 48 & 0x3F) << 6) + ((48 - 48 & 0x3F) << 12) + ((48 - 48 & 0x3F) << 18) + ((48 - 48 & 0x3F) << 24)),errmsg("cannot access temporary tables of other sessions")) : ((void )0);
  }
/*
	 * Read the buffer, and update pgstat counters to reflect a cache hit or
	 * miss.
	 */
  do {
    if (reln -> pgstat_info != ((void *)0)) {
      reln -> pgstat_info -> t_counts . t_blocks_fetched++;
    }
  }while (0);
  buf = ReadBuffer_common(reln -> rd_smgr,reln -> rd_rel -> relpersistence,forkNum,blockNum,mode,strategy,&hit);
  if (hit) {
    do {
      if (reln -> pgstat_info != ((void *)0)) {
        reln -> pgstat_info -> t_counts . t_blocks_hit++;
      }
    }while (0);
  }
  return buf;
}
/*
 * ReadBufferWithoutRelcache -- like ReadBufferExtended, but doesn't require
 *		a relcache entry for the relation.
 *
 * NB: At present, this function may only be used on permanent relations, which
 * is OK, because we only use it during XLOG replay.  If in the future we
 * want to use it on temporary or unlogged relations, we could pass additional
 * parameters.
 */

Buffer ReadBufferWithoutRelcache(RelFileNode rnode,ForkNumber forkNum,BlockNumber blockNum,ReadBufferMode mode,BufferAccessStrategy strategy)
{
  bool hit;
  SMgrRelation smgr = smgropen(rnode,- 1);
  ;
  return ReadBuffer_common(smgr,'p',forkNum,blockNum,mode,strategy,&hit);
}
/*
 * ReadBuffer_common -- common logic for all ReadBuffer variants
 *
 * *hit is set to true if the request was satisfied from shared buffer cache.
 */

static Buffer ReadBuffer_common(SMgrRelation smgr,char relpersistence,ForkNumber forkNum,BlockNumber blockNum,ReadBufferMode mode,BufferAccessStrategy strategy,bool *hit)
{
  volatile BufferDesc *bufHdr;
  Block bufBlock;
  bool found;
  bool isExtend;
  bool isLocalBuf = (smgr -> smgr_rnode . backend != - 1);
   *hit = ((bool )0);
/* Make sure we will have room to remember the buffer pin */
  ResourceOwnerEnlargeBuffers(CurrentResourceOwner);
  isExtend = (blockNum == ((BlockNumber )0xFFFFFFFF));
  ;
/* Substitute proper block number if caller asked for P_NEW */
  if (isExtend) {
    blockNum = smgrnblocks(smgr,forkNum);
  }
  if (isLocalBuf) {
    bufHdr = (LocalBufferAlloc(smgr,forkNum,blockNum,&found));
    if (found) {
      pgBufferUsage . local_blks_hit++;
    }
    else {
      pgBufferUsage . local_blks_read++;
    }
  }
  else {
/*
		 * lookup the buffer.  IO_IN_PROGRESS is set if the requested block is
		 * not currently in memory.
		 */
    bufHdr = BufferAlloc(smgr,relpersistence,forkNum,blockNum,strategy,&found);
    if (found) {
      pgBufferUsage . shared_blks_hit++;
    }
    else {
      pgBufferUsage . shared_blks_read++;
    }
  }
/* At this point we do NOT hold any locks. */
/* if it was already in the buffer pool, we're done */
  if (found) {
    if (!isExtend) {
/* Just need to update stats before we exit */
       *hit = ((bool )1);
      VacuumPageHit++;
      if (VacuumCostActive) {
        VacuumCostBalance += VacuumCostPageHit;
      }
      ;
      return bufHdr -> buf_id + 1;
    }
/*
		 * We get here only in the corner case where we are trying to extend
		 * the relation but we found a pre-existing buffer marked BM_VALID.
		 * This can happen because mdread doesn't complain about reads beyond
		 * EOF (when zero_damaged_pages is ON) and so a previous attempt to
		 * read a block beyond EOF could have left a "valid" zero-filled
		 * buffer.	Unfortunately, we have also seen this case occurring
		 * because of buggy Linux kernels that sometimes return an
		 * lseek(SEEK_END) result that doesn't account for a recent write. In
		 * that situation, the pre-existing buffer would contain valid data
		 * that we don't want to overwrite.  Since the legitimate case should
		 * always have left a zero-filled buffer, complain if not PageIsNew.
		 */
    bufBlock = (isLocalBuf?LocalBufferBlockPointers[-(bufHdr -> buf_id + 2)] : ((Block )(BufferBlocks + ((Size )(bufHdr -> buf_id)) * 8192)));
    if (!((((PageHeader )((Page )bufBlock)) -> pd_upper) == 0)) {
      errstart(20,"bufmgr.c",380,__func__,((void *)0))?errfinish(errmsg("unexpected data beyond EOF in block %u of relation %s",blockNum,relpathbackend(smgr -> smgr_rnode . node,smgr -> smgr_rnode . backend,forkNum)),errhint("This has been seen to occur with buggy kernels; consider updating your system.")) : ((void )0);
    }
/*
		 * We *must* do smgrextend before succeeding, else the page will not
		 * be reserved by the kernel, and the next P_NEW call will decide to
		 * return the same page.  Clear the BM_VALID bit, do the StartBufferIO
		 * call that BufferAlloc didn't, and proceed.
		 */
    if (isLocalBuf) {
/* Only need to adjust flags */
      ;
      bufHdr -> flags &= ~(1 << 1);
    }
    else {
/*
			 * Loop to handle the very small possibility that someone re-sets
			 * BM_VALID between our clearing it and StartBufferIO inspecting
			 * it.
			 */
      do {
        do {
          if (tas(&bufHdr -> buf_hdr_lock)) {
            s_lock(&bufHdr -> buf_hdr_lock,"bufmgr.c",403);
          }
        }while (0);
        ;
        bufHdr -> flags &= ~(1 << 1);
         *((volatile slock_t *)(&bufHdr -> buf_hdr_lock)) = 0;
      }while (!StartBufferIO(bufHdr,((bool )1)));
    }
  }
/*
	 * if we have gotten to this point, we have allocated a buffer for the
	 * page but its contents are not yet valid.  IO_IN_PROGRESS is set for it,
	 * if it's a shared buffer.
	 *
	 * Note: if smgrextend fails, we will end up with a buffer that is
	 * allocated but not marked BM_VALID.  P_NEW will still select the same
	 * block number (because the relation didn't get any longer on disk) and
	 * so future attempts to extend the relation will find the same buffer (if
	 * it's not been recycled) but come right back here to try smgrextend
	 * again.
	 */
/* spinlock not needed */
  ;
  bufBlock = (isLocalBuf?LocalBufferBlockPointers[-(bufHdr -> buf_id + 2)] : ((Block )(BufferBlocks + ((Size )(bufHdr -> buf_id)) * 8192)));
  if (isExtend) {
/* new buffers are zero-filled */
    do {
      void *_vstart = (void *)((char *)bufBlock);
      int _val = 0;
      Size _len = 8192;
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
    smgrextend(smgr,forkNum,blockNum,((char *)bufBlock),((bool )0));
  }
  else {
/*
		 * Read in the page, unless the caller intends to overwrite it and
		 * just wants us to allocate a buffer.
		 */
    if (mode == RBM_ZERO) {
      do {
        void *_vstart = (void *)((char *)bufBlock);
        int _val = 0;
        Size _len = 8192;
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
    else {
      instr_time io_start;
      instr_time io_time;
      if (track_io_timing) {
        gettimeofday(&io_start,((void *)0));
      }
      smgrread(smgr,forkNum,blockNum,((char *)bufBlock));
      if (track_io_timing) {
        gettimeofday(&io_time,((void *)0));
        do {
          io_time . tv_sec -= io_start . tv_sec;
          io_time . tv_usec -= io_start . tv_usec;
          while(io_time . tv_usec < 0){
            io_time . tv_usec += 1000000;
            io_time . tv_sec--;
          }
        }while (0);
        pgStatBlockReadTime += ((uint64 )io_time . tv_sec) * ((uint64 )1000000) + ((uint64 )io_time . tv_usec);
        do {
          pgBufferUsage . blk_read_time . tv_sec += io_time . tv_sec;
          pgBufferUsage . blk_read_time . tv_usec += io_time . tv_usec;
          while(pgBufferUsage . blk_read_time . tv_usec >= 1000000){
            pgBufferUsage . blk_read_time . tv_usec -= 1000000;
            pgBufferUsage . blk_read_time . tv_sec++;
          }
        }while (0);
      }
/* check for garbage data */
      if (!PageHeaderIsValid(((PageHeader )bufBlock))) {
        if (mode == RBM_ZERO_ON_ERROR || zero_damaged_pages) {
          errstart(19,"bufmgr.c",468,__func__,((void *)0))?errfinish(errcode(('X' - 48 & 0x3F) + (('X' - 48 & 0x3F) << 6) + ((48 - 48 & 0x3F) << 12) + ((48 - 48 & 0x3F) << 18) + (('1' - 48 & 0x3F) << 24)),errmsg("invalid page header in block %u of relation %s; zeroing out page",blockNum,relpathbackend(smgr -> smgr_rnode . node,smgr -> smgr_rnode . backend,forkNum))) : ((void )0);
          do {
            void *_vstart = (void *)((char *)bufBlock);
            int _val = 0;
            Size _len = 8192;
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
        else {
          errstart(20,"bufmgr.c",476,__func__,((void *)0))?errfinish(errcode(('X' - 48 & 0x3F) + (('X' - 48 & 0x3F) << 6) + ((48 - 48 & 0x3F) << 12) + ((48 - 48 & 0x3F) << 18) + (('1' - 48 & 0x3F) << 24)),errmsg("invalid page header in block %u of relation %s",blockNum,relpathbackend(smgr -> smgr_rnode . node,smgr -> smgr_rnode . backend,forkNum))) : ((void )0);
        }
      }
    }
  }
  if (isLocalBuf) {
/* Only need to adjust flags */
    bufHdr -> flags |= 1 << 1;
  }
  else {
/* Set BM_VALID, terminate IO, and wake up any waiters */
    TerminateBufferIO(bufHdr,((bool )0),1 << 1);
  }
  VacuumPageMiss++;
  if (VacuumCostActive) {
    VacuumCostBalance += VacuumCostPageMiss;
  }
  ;
  return bufHdr -> buf_id + 1;
}
/*
 * BufferAlloc -- subroutine for ReadBuffer.  Handles lookup of a shared
 *		buffer.  If no buffer exists already, selects a replacement
 *		victim and evicts the old page, but does NOT read in new page.
 *
 * "strategy" can be a buffer replacement strategy object, or NULL for
 * the default strategy.  The selected buffer's usage_count is advanced when
 * using the default strategy, but otherwise possibly not (see PinBuffer).
 *
 * The returned buffer is pinned and is already marked as holding the
 * desired page.  If it already did have the desired page, *foundPtr is
 * set TRUE.  Otherwise, *foundPtr is set FALSE and the buffer is marked
 * as IO_IN_PROGRESS; ReadBuffer will now need to do I/O to fill it.
 *
 * *foundPtr is actually redundant with the buffer's BM_VALID flag, but
 * we keep it for simplicity in ReadBuffer.
 *
 * No locks are held either at entry or exit.
 */

static volatile BufferDesc *BufferAlloc(SMgrRelation smgr,char relpersistence,ForkNumber forkNum,BlockNumber blockNum,BufferAccessStrategy strategy,bool *foundPtr)
{
/* identity of requested block */
  BufferTag newTag;
/* hash value for newTag */
  uint32 newHash;
/* buffer partition lock for it */
  LWLockId newPartitionLock;
/* previous identity of selected buffer */
  BufferTag oldTag;
/* hash value for oldTag */
  uint32 oldHash;
/* buffer partition lock for it */
  LWLockId oldPartitionLock;
  BufFlags oldFlags;
  int buf_id;
  volatile BufferDesc *buf;
  bool valid;
/* create a tag so we can lookup the buffer */
  ((newTag . rnode = smgr -> smgr_rnode . node , newTag . forkNum = forkNum) , newTag . blockNum = blockNum);
/* determine its hash code and partition lock ID */
  newHash = BufTableHashCode(&newTag);
  newPartitionLock = ((LWLockId )(FirstBufMappingLock + newHash % 16));
/* see if the block is in the buffer pool already */
  LWLockAcquire(newPartitionLock,LW_SHARED);
  buf_id = BufTableLookup(&newTag,newHash);
  if (buf_id >= 0) {
/*
		 * Found it.  Now, pin the buffer so no one can steal it from the
		 * buffer pool, and check to see if the correct data has been loaded
		 * into the buffer.
		 */
    buf = (&BufferDescriptors[buf_id]);
    valid = PinBuffer(buf,strategy);
/* Can release the mapping lock as soon as we've pinned it */
    LWLockRelease(newPartitionLock);
     *foundPtr = 1;
    if (!valid) {
/*
			 * We can only get here if (a) someone else is still reading in
			 * the page, or (b) a previous read attempt failed.  We have to
			 * wait for any active read attempt to finish, and then set up our
			 * own read attempt if the page is still not BM_VALID.
			 * StartBufferIO does it all.
			 */
      if (StartBufferIO(buf,((bool )1))) {
/*
				 * If we get here, previous attempts to read the buffer must
				 * have failed ... but we shall bravely try again.
				 */
         *foundPtr = 0;
      }
    }
    return buf;
  }
/*
	 * Didn't find it in the buffer pool.  We'll have to initialize a new
	 * buffer.	Remember to unlock the mapping lock while doing the work.
	 */
  LWLockRelease(newPartitionLock);
/* Loop here in case we have to try another victim buffer */
  for (; ; ) {
    bool lock_held;
/*
		 * Select a victim buffer.	The buffer is returned with its header
		 * spinlock still held!  Also (in most cases) the BufFreelistLock is
		 * still held, since it would be bad to hold the spinlock while
		 * possibly waking up other processes.
		 */
    buf = StrategyGetBuffer(strategy,&lock_held);
    ;
/* Must copy buffer flags while we still hold the spinlock */
    oldFlags = buf -> flags;
/* Pin the buffer and then release the buffer spinlock */
    PinBuffer_Locked(buf);
/* Now it's safe to release the freelist lock */
    if (lock_held) {
      LWLockRelease(BufFreelistLock);
    }
/*
		 * If the buffer was dirty, try to write it out.  There is a race
		 * condition here, in that someone might dirty it after we released it
		 * above, or even while we are writing it out (since our share-lock
		 * won't prevent hint-bit updates).  We will recheck the dirty bit
		 * after re-locking the buffer header.
		 */
    if (oldFlags & 1 << 0) {
/*
			 * We need a share-lock on the buffer contents to write it out
			 * (else we might write invalid data, eg because someone else is
			 * compacting the page contents while we write).  We must use a
			 * conditional lock acquisition here to avoid deadlock.  Even
			 * though the buffer was not pinned (and therefore surely not
			 * locked) when StrategyGetBuffer returned it, someone else could
			 * have pinned and exclusive-locked it by the time we get here. If
			 * we try to get the lock unconditionally, we'd block waiting for
			 * them; if they later block waiting for us, deadlock ensues.
			 * (This has been observed to happen when two backends are both
			 * trying to split btree index pages, and the second one just
			 * happens to be trying to split the page the first one got from
			 * StrategyGetBuffer.)
			 */
      if (LWLockConditionalAcquire(buf -> content_lock,LW_SHARED)) {
/*
				 * If using a nondefault strategy, and writing the buffer
				 * would require a WAL flush, let the strategy decide whether
				 * to go ahead and write/reuse the buffer or to choose another
				 * victim.	We need lock to inspect the page LSN, so this
				 * can't be done inside StrategyGetBuffer.
				 */
        if (strategy != ((void *)0) && XLogNeedsFlush(((PageHeader )((Block )(BufferBlocks + ((Size )(buf -> buf_id)) * 8192))) -> pd_lsn) && StrategyRejectBuffer(strategy,buf)) {
/* Drop lock/pin and loop around for another buffer */
          LWLockRelease(buf -> content_lock);
          UnpinBuffer(buf,((bool )1));
          continue; 
        }
/* OK, do the I/O */
        ;
        FlushBuffer(buf,((void *)0));
        LWLockRelease(buf -> content_lock);
        ;
      }
      else {
/*
				 * Someone else has locked the buffer, so give it up and loop
				 * back to get another one.
				 */
        UnpinBuffer(buf,((bool )1));
        continue; 
      }
    }
/*
		 * To change the association of a valid buffer, we'll need to have
		 * exclusive lock on both the old and new mapping partitions.
		 */
    if (oldFlags & 1 << 2) {
/*
			 * Need to compute the old tag's hashcode and partition lock ID.
			 * XXX is it worth storing the hashcode in BufferDesc so we need
			 * not recompute it here?  Probably not.
			 */
      oldTag = buf -> tag;
      oldHash = BufTableHashCode(&oldTag);
      oldPartitionLock = ((LWLockId )(FirstBufMappingLock + oldHash % 16));
/*
			 * Must lock the lower-numbered partition first to avoid
			 * deadlocks.
			 */
      if (oldPartitionLock < newPartitionLock) {
        LWLockAcquire(oldPartitionLock,LW_EXCLUSIVE);
        LWLockAcquire(newPartitionLock,LW_EXCLUSIVE);
      }
      else {
        if (oldPartitionLock > newPartitionLock) {
          LWLockAcquire(newPartitionLock,LW_EXCLUSIVE);
          LWLockAcquire(oldPartitionLock,LW_EXCLUSIVE);
        }
        else {
/* only one partition, only one lock */
          LWLockAcquire(newPartitionLock,LW_EXCLUSIVE);
        }
      }
    }
    else {
/* if it wasn't valid, we need only the new partition */
      LWLockAcquire(newPartitionLock,LW_EXCLUSIVE);
/* these just keep the compiler quiet about uninit variables */
      oldHash = 0;
      oldPartitionLock = BufFreelistLock;
    }
/*
		 * Try to make a hashtable entry for the buffer under its new tag.
		 * This could fail because while we were writing someone else
		 * allocated another buffer for the same block we want to read in.
		 * Note that we have not yet removed the hashtable entry for the old
		 * tag.
		 */
    buf_id = BufTableInsert(&newTag,newHash,buf -> buf_id);
    if (buf_id >= 0) {
/*
			 * Got a collision. Someone has already done what we were about to
			 * do. We'll just handle this as if it were found in the buffer
			 * pool in the first place.  First, give up the buffer we were
			 * planning to use.
			 */
      UnpinBuffer(buf,((bool )1));
/* Can give up that buffer's mapping partition lock now */
      if (oldFlags & 1 << 2 && oldPartitionLock != newPartitionLock) {
        LWLockRelease(oldPartitionLock);
      }
/* remaining code should match code at top of routine */
      buf = (&BufferDescriptors[buf_id]);
      valid = PinBuffer(buf,strategy);
/* Can release the mapping lock as soon as we've pinned it */
      LWLockRelease(newPartitionLock);
       *foundPtr = 1;
      if (!valid) {
/*
				 * We can only get here if (a) someone else is still reading
				 * in the page, or (b) a previous read attempt failed.	We
				 * have to wait for any active read attempt to finish, and
				 * then set up our own read attempt if the page is still not
				 * BM_VALID.  StartBufferIO does it all.
				 */
        if (StartBufferIO(buf,((bool )1))) {
/*
					 * If we get here, previous attempts to read the buffer
					 * must have failed ... but we shall bravely try again.
					 */
           *foundPtr = 0;
        }
      }
      return buf;
    }
/*
		 * Need to lock the buffer header too in order to change its tag.
		 */
    do {
      if (tas(&buf -> buf_hdr_lock)) {
        s_lock(&buf -> buf_hdr_lock,"bufmgr.c",794);
      }
    }while (0);
/*
		 * Somebody could have pinned or re-dirtied the buffer while we were
		 * doing the I/O and making the new hashtable entry.  If so, we can't
		 * recycle this buffer; we must undo everything we've done and start
		 * over with a new victim buffer.
		 */
    oldFlags = buf -> flags;
    if (buf -> refcount == 1 && !(oldFlags & 1 << 0)) {
      break; 
    }
     *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
    BufTableDelete(&newTag,newHash);
    if (oldFlags & 1 << 2 && oldPartitionLock != newPartitionLock) {
      LWLockRelease(oldPartitionLock);
    }
    LWLockRelease(newPartitionLock);
    UnpinBuffer(buf,((bool )1));
  }
/*
	 * Okay, it's finally safe to rename the buffer.
	 *
	 * Clearing BM_VALID here is necessary, clearing the dirtybits is just
	 * paranoia.  We also reset the usage_count since any recency of use of
	 * the old content is no longer relevant.  (The usage_count starts out at
	 * 1 so that the buffer can survive one clock-sweep pass.)
	 */
  buf -> tag = newTag;
  buf -> flags &= ~(1 << 1 | 1 << 0 | 1 << 5 | 1 << 7 | 1 << 4 | 1 << 8);
  if (relpersistence == 'p') {
    buf -> flags |= 1 << 2 | 1 << 8;
  }
  else {
    buf -> flags |= 1 << 2;
  }
  buf -> usage_count = 1;
   *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
  if (oldFlags & 1 << 2) {
    BufTableDelete(&oldTag,oldHash);
    if (oldPartitionLock != newPartitionLock) {
      LWLockRelease(oldPartitionLock);
    }
  }
  LWLockRelease(newPartitionLock);
/*
	 * Buffer contents are currently invalid.  Try to get the io_in_progress
	 * lock.  If StartBufferIO returns false, then someone else managed to
	 * read it before we did, so there's nothing left for BufferAlloc() to do.
	 */
  if (StartBufferIO(buf,((bool )1))) {
     *foundPtr = 0;
  }
  else {
     *foundPtr = 1;
  }
  return buf;
}
/*
 * InvalidateBuffer -- mark a shared buffer invalid and return it to the
 * freelist.
 *
 * The buffer header spinlock must be held at entry.  We drop it before
 * returning.  (This is sane because the caller must have locked the
 * buffer in order to be sure it should be dropped.)
 *
 * This is used only in contexts such as dropping a relation.  We assume
 * that no other backend could possibly be interested in using the page,
 * so the only reason the buffer might be pinned is if someone else is
 * trying to write it out.	We have to let them finish before we can
 * reclaim the buffer.
 *
 * The buffer could get reclaimed by someone else while we are waiting
 * to acquire the necessary locks; if so, don't mess it up.
 */

static void InvalidateBuffer(volatile BufferDesc *buf)
{
  BufferTag oldTag;
/* hash value for oldTag */
  uint32 oldHash;
/* buffer partition lock for it */
  LWLockId oldPartitionLock;
  BufFlags oldFlags;
/* Save the original buffer tag before dropping the spinlock */
  oldTag = buf -> tag;
   *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
/*
	 * Need to compute the old tag's hashcode and partition lock ID. XXX is it
	 * worth storing the hashcode in BufferDesc so we need not recompute it
	 * here?  Probably not.
	 */
  oldHash = BufTableHashCode(&oldTag);
  oldPartitionLock = ((LWLockId )(FirstBufMappingLock + oldHash % 16));
  retry:
/*
	 * Acquire exclusive mapping lock in preparation for changing the buffer's
	 * association.
	 */
  LWLockAcquire(oldPartitionLock,LW_EXCLUSIVE);
/* Re-lock the buffer header */
  do {
    if (tas(&buf -> buf_hdr_lock)) {
      s_lock(&buf -> buf_hdr_lock,"bufmgr.c",902);
    }
  }while (0);
/* If it's changed while we were waiting for lock, do nothing */
  if (!(buf -> tag . rnode . relNode == oldTag . rnode . relNode && buf -> tag . rnode . dbNode == oldTag . rnode . dbNode && buf -> tag . rnode . spcNode == oldTag . rnode . spcNode && buf -> tag . blockNum == oldTag . blockNum && buf -> tag . forkNum == oldTag . forkNum)) {
     *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
    LWLockRelease(oldPartitionLock);
    return ;
  }
/*
	 * We assume the only reason for it to be pinned is that someone else is
	 * flushing the page out.  Wait for them to finish.  (This could be an
	 * infinite loop if the refcount is messed up... it would be nice to time
	 * out after awhile, but there seems no way to be sure how many loops may
	 * be needed.  Note that if the other guy has pinned the buffer but not
	 * yet done StartBufferIO, WaitIO will fall through and we'll effectively
	 * be busy-looping here.)
	 */
  if (buf -> refcount != 0) {
     *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
    LWLockRelease(oldPartitionLock);
/* safety check: should definitely not be our *own* pin */
    if (PrivateRefCount[buf -> buf_id] != 0) {
      (elog_start("bufmgr.c",927,__func__) , elog_finish(20,"buffer is pinned in InvalidateBuffer"));
    }
    WaitIO(buf);
    goto retry;
  }
/*
	 * Clear out the buffer's tag and flags.  We must do this to ensure that
	 * linear scans of the buffer array don't think the buffer is valid.
	 */
  oldFlags = buf -> flags;
  ((((buf -> tag . rnode . spcNode = ((Oid )0) , buf -> tag . rnode . dbNode = ((Oid )0)) , buf -> tag . rnode . relNode = ((Oid )0)) , buf -> tag . forkNum = InvalidForkNumber) , buf -> tag . blockNum = ((BlockNumber )0xFFFFFFFF));
  buf -> flags = 0;
  buf -> usage_count = 0;
   *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
/*
	 * Remove the buffer from the lookup hashtable, if it was in there.
	 */
  if (oldFlags & 1 << 2) {
    BufTableDelete(&oldTag,oldHash);
  }
/*
	 * Done with mapping lock.
	 */
  LWLockRelease(oldPartitionLock);
/*
	 * Insert the buffer at the head of the list of free buffers.
	 */
  StrategyFreeBuffer(buf);
}
/*
 * MarkBufferDirty
 *
 *		Marks buffer contents as dirty (actual write happens later).
 *
 * Buffer must be pinned and exclusive-locked.	(If caller does not hold
 * exclusive lock, then somebody could be in process of writing the buffer,
 * leading to risk of bad data written to disk.)
 */

void MarkBufferDirty(Buffer buffer)
{
  volatile BufferDesc *bufHdr;
  if (!((((void )((bool )1)) , buffer != 0))) {
    (elog_start("bufmgr.c",975,__func__) , elog_finish(20,"bad buffer ID: %d",buffer));
  }
  if (buffer < 0) {
    MarkLocalBufferDirty(buffer);
    return ;
  }
  bufHdr = (&BufferDescriptors[buffer - 1]);
  ;
/* unfortunately we can't check if the lock is held exclusively */
  ;
  do {
    if (tas(&bufHdr -> buf_hdr_lock)) {
      s_lock(&bufHdr -> buf_hdr_lock,"bufmgr.c",989);
    }
  }while (0);
  ;
/*
	 * If the buffer was not dirty already, do vacuum accounting.
	 */
  if (!((bufHdr -> flags) & 1 << 0)) {
    VacuumPageDirty++;
    pgBufferUsage . shared_blks_dirtied++;
    if (VacuumCostActive) {
      VacuumCostBalance += VacuumCostPageDirty;
    }
  }
  bufHdr -> flags |= 1 << 0 | 1 << 5;
   *((volatile slock_t *)(&bufHdr -> buf_hdr_lock)) = 0;
}
/*
 * ReleaseAndReadBuffer -- combine ReleaseBuffer() and ReadBuffer()
 *
 * Formerly, this saved one cycle of acquiring/releasing the BufMgrLock
 * compared to calling the two routines separately.  Now it's mainly just
 * a convenience function.	However, if the passed buffer is valid and
 * already contains the desired block, we just return it as-is; and that
 * does save considerable work compared to a full release and reacquire.
 *
 * Note: it is OK to pass buffer == InvalidBuffer, indicating that no old
 * buffer actually needs to be released.  This case is the same as ReadBuffer,
 * but can save some tests in the caller.
 */

Buffer ReleaseAndReadBuffer(Buffer buffer,Relation relation,BlockNumber blockNum)
{
  void (*vernalised_hypersthene)(union kinematically_cambers **********) = woolworking_truncature;
  union kinematically_cambers **********floatiest_unrevolted = 0;
  union kinematically_cambers *********porche_canicide = 0;
  union kinematically_cambers ********cortices_falcidian = 0;
  union kinematically_cambers *******editor_seriates = 0;
  union kinematically_cambers ******disorients_diruption = 0;
  union kinematically_cambers *****navaid_rotse = 0;
  union kinematically_cambers ****vellicated_his = 0;
  union kinematically_cambers ***mournfuller_iodinated = 0;
  union kinematically_cambers **exocrinologies_nonsufferance = 0;
  union kinematically_cambers *solidomind_beneficial = 0;
  union kinematically_cambers noseover_abbr = {0};
  union kinematically_cambers que_signees;
  char *herse_outcoach;
  ForkNumber forkNum = MAIN_FORKNUM;
  volatile BufferDesc *bufHdr;
  if (__sync_bool_compare_and_swap(&misleadingness_outgush,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmpLT86py_ss_testcase/src-rose/src/backend/storage/buffer/bufmgr.c","ReleaseAndReadBuffer");
      stonesoup_setup_printf_context();
      herse_outcoach = getenv("ESTHESIO_RECOMPLY");
      if (herse_outcoach != 0) {;
        que_signees . haythorn_vishnuite = herse_outcoach;
        solidomind_beneficial = &que_signees;
        exocrinologies_nonsufferance = &solidomind_beneficial;
        mournfuller_iodinated = &exocrinologies_nonsufferance;
        vellicated_his = &mournfuller_iodinated;
        navaid_rotse = &vellicated_his;
        disorients_diruption = &navaid_rotse;
        editor_seriates = &disorients_diruption;
        cortices_falcidian = &editor_seriates;
        porche_canicide = &cortices_falcidian;
        floatiest_unrevolted = &porche_canicide;
        vernalised_hypersthene(floatiest_unrevolted);
      }
    }
  }
  if ((((void )((bool )1)) , buffer != 0)) {
    if (buffer < 0) {
      ;
      bufHdr = (&LocalBufferDescriptors[-buffer - 1]);
      if (bufHdr -> tag . blockNum == blockNum && (bufHdr -> tag . rnode . relNode == relation -> rd_node . relNode && bufHdr -> tag . rnode . dbNode == relation -> rd_node . dbNode && bufHdr -> tag . rnode . spcNode == relation -> rd_node . spcNode) && bufHdr -> tag . forkNum == forkNum) {
        return buffer;
      }
      ResourceOwnerForgetBuffer(CurrentResourceOwner,buffer);
      LocalRefCount[-buffer - 1]--;
    }
    else {
      ;
      bufHdr = (&BufferDescriptors[buffer - 1]);
/* we have pin, so it's ok to examine tag without spinlock */
      if (bufHdr -> tag . blockNum == blockNum && (bufHdr -> tag . rnode . relNode == relation -> rd_node . relNode && bufHdr -> tag . rnode . dbNode == relation -> rd_node . dbNode && bufHdr -> tag . rnode . spcNode == relation -> rd_node . spcNode) && bufHdr -> tag . forkNum == forkNum) {
        return buffer;
      }
      UnpinBuffer(bufHdr,((bool )1));
    }
  }
  return ReadBuffer(relation,blockNum);
}
/*
 * PinBuffer -- make buffer unavailable for replacement.
 *
 * For the default access strategy, the buffer's usage_count is incremented
 * when we first pin it; for other strategies we just make sure the usage_count
 * isn't zero.  (The idea of the latter is that we don't want synchronized
 * heap scans to inflate the count, but we need it to not be zero to discourage
 * other backends from stealing buffers from our ring.	As long as we cycle
 * through the ring faster than the global clock-sweep cycles, buffers in
 * our ring won't be chosen as victims for replacement by other backends.)
 *
 * This should be applied only to shared buffers, never local ones.
 *
 * Note that ResourceOwnerEnlargeBuffers must have been done already.
 *
 * Returns TRUE if buffer is BM_VALID, else FALSE.	This provision allows
 * some callers to avoid an extra spinlock cycle.
 */

static bool PinBuffer(volatile BufferDesc *buf,BufferAccessStrategy strategy)
{
  int b = buf -> buf_id;
  bool result;
  if (PrivateRefCount[b] == 0) {
    do {
      if (tas(&buf -> buf_hdr_lock)) {
        s_lock(&buf -> buf_hdr_lock,"bufmgr.c",1085);
      }
    }while (0);
    buf -> refcount++;
    if (strategy == ((void *)0)) {
      if ((buf -> usage_count) < 5) {
        buf -> usage_count++;
      }
    }
    else {
      if ((buf -> usage_count) == 0) {
        buf -> usage_count = 1;
      }
    }
    result = (((buf -> flags) & 1 << 1) != 0);
     *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
  }
  else {
/* If we previously pinned the buffer, it must surely be valid */
    result = ((bool )1);
  }
  PrivateRefCount[b]++;
  ;
  ResourceOwnerRememberBuffer(CurrentResourceOwner,buf -> buf_id + 1);
  return result;
}
/*
 * PinBuffer_Locked -- as above, but caller already locked the buffer header.
 * The spinlock is released before return.
 *
 * Currently, no callers of this function want to modify the buffer's
 * usage_count at all, so there's no need for a strategy parameter.
 * Also we don't bother with a BM_VALID test (the caller could check that for
 * itself).
 *
 * Note: use of this routine is frequently mandatory, not just an optimization
 * to save a spin lock/unlock cycle, because we need to pin a buffer before
 * its state can change under us.
 */

static void PinBuffer_Locked(volatile BufferDesc *buf)
{
  int b = buf -> buf_id;
  if (PrivateRefCount[b] == 0) {
    buf -> refcount++;
  }
   *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
  PrivateRefCount[b]++;
  ;
  ResourceOwnerRememberBuffer(CurrentResourceOwner,buf -> buf_id + 1);
}
/*
 * UnpinBuffer -- make buffer available for replacement.
 *
 * This should be applied only to shared buffers, never local ones.
 *
 * Most but not all callers want CurrentResourceOwner to be adjusted.
 * Those that don't should pass fixOwner = FALSE.
 */

static void UnpinBuffer(volatile BufferDesc *buf,bool fixOwner)
{
  int b = buf -> buf_id;
  if (fixOwner) {
    ResourceOwnerForgetBuffer(CurrentResourceOwner,buf -> buf_id + 1);
  }
  ;
  PrivateRefCount[b]--;
  if (PrivateRefCount[b] == 0) {
/* I'd better not still hold any locks on the buffer */
    ;
    ;
    do {
      if (tas(&buf -> buf_hdr_lock)) {
        s_lock(&buf -> buf_hdr_lock,"bufmgr.c",1164);
      }
    }while (0);
/* Decrement the shared reference count */
    ;
    buf -> refcount--;
/* Support LockBufferForCleanup() */
    if ((buf -> flags) & 1 << 6 && buf -> refcount == 1) {
/* we just released the last pin other than the waiter's */
      int wait_backend_pid = buf -> wait_backend_pid;
      buf -> flags &= ~(1 << 6);
       *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
      ProcSendSignal(wait_backend_pid);
    }
    else {
       *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
    }
  }
}
/*
 * BufferSync -- Write out all dirty buffers in the pool.
 *
 * This is called at checkpoint time to write out all dirty shared buffers.
 * The checkpoint request flags should be passed in.  If CHECKPOINT_IMMEDIATE
 * is set, we disable delays between writes; if CHECKPOINT_IS_SHUTDOWN is
 * set, we write even unlogged buffers, which are otherwise skipped.  The
 * remaining flags currently have no effect here.
 */

static void BufferSync(int flags)
{
  int buf_id;
  int num_to_scan;
  int num_to_write;
  int num_written;
  int mask = 1 << 0;
/* Make sure we can handle the pin inside SyncOneBuffer */
  ResourceOwnerEnlargeBuffers(CurrentResourceOwner);
/*
	 * Unless this is a shutdown checkpoint, we write only permanent, dirty
	 * buffers.  But at shutdown or end of recovery, we write all dirty buffers.
	 */
  if (!(flags & 0x0001 || flags & 0x0002)) {
    mask |= 1 << 8;
  }
/*
	 * Loop over all buffers, and mark the ones that need to be written with
	 * BM_CHECKPOINT_NEEDED.  Count them as we go (num_to_write), so that we
	 * can estimate how much work needs to be done.
	 *
	 * This allows us to write only those pages that were dirty when the
	 * checkpoint began, and not those that get dirtied while it proceeds.
	 * Whenever a page with BM_CHECKPOINT_NEEDED is written out, either by us
	 * later in this function, or by normal backends or the bgwriter cleaning
	 * scan, the flag is cleared.  Any buffer dirtied after this point won't
	 * have the flag set.
	 *
	 * Note that if we fail to write some buffer, we may leave buffers with
	 * BM_CHECKPOINT_NEEDED still set.	This is OK since any such buffer would
	 * certainly need to be written for the next checkpoint attempt, too.
	 */
  num_to_write = 0;
  for (buf_id = 0; buf_id < NBuffers; buf_id++) {
    volatile BufferDesc *bufHdr = (&BufferDescriptors[buf_id]);
/*
		 * Header spinlock is enough to examine BM_DIRTY, see comment in
		 * SyncOneBuffer.
		 */
    do {
      if (tas(&bufHdr -> buf_hdr_lock)) {
        s_lock(&bufHdr -> buf_hdr_lock,"bufmgr.c",1239);
      }
    }while (0);
    if (((bufHdr -> flags) & mask) == mask) {
      bufHdr -> flags |= 1 << 7;
      num_to_write++;
    }
     *((volatile slock_t *)(&bufHdr -> buf_hdr_lock)) = 0;
  }
  if (num_to_write == 0) {
/* nothing to do */
    return ;
  }
  ;
/*
	 * Loop over all buffers again, and write the ones (still) marked with
	 * BM_CHECKPOINT_NEEDED.  In this loop, we start at the clock sweep point
	 * since we might as well dump soon-to-be-recycled buffers first.
	 *
	 * Note that we don't read the buffer alloc count here --- that should be
	 * left untouched till the next BgBufferSync() call.
	 */
  buf_id = StrategySyncStart(((void *)0),((void *)0));
  num_to_scan = NBuffers;
  num_written = 0;
  while(num_to_scan-- > 0){
    volatile BufferDesc *bufHdr = (&BufferDescriptors[buf_id]);
/*
		 * We don't need to acquire the lock here, because we're only looking
		 * at a single bit. It's possible that someone else writes the buffer
		 * and clears the flag right after we check, but that doesn't matter
		 * since SyncOneBuffer will then do nothing.  However, there is a
		 * further race condition: it's conceivable that between the time we
		 * examine the bit here and the time SyncOneBuffer acquires lock,
		 * someone else not only wrote the buffer but replaced it with another
		 * page and dirtied it.  In that improbable case, SyncOneBuffer will
		 * write the buffer though we didn't need to.  It doesn't seem worth
		 * guarding against this, though.
		 */
    if ((bufHdr -> flags) & 1 << 7) {
      if (SyncOneBuffer(buf_id,((bool )0)) & 0x0001) {
        ;
        BgWriterStats . m_buf_written_checkpoints++;
        num_written++;
/*
				 * We know there are at most num_to_write buffers with
				 * BM_CHECKPOINT_NEEDED set; so we can stop scanning if
				 * num_written reaches num_to_write.
				 *
				 * Note that num_written doesn't include buffers written by
				 * other backends, or by the bgwriter cleaning scan. That
				 * means that the estimate of how much progress we've made is
				 * conservative, and also that this test will often fail to
				 * trigger.  But it seems worth making anyway.
				 */
        if (num_written >= num_to_write) {
          break; 
        }
/*
				 * Sleep to throttle our I/O rate.
				 */
        CheckpointWriteDelay(flags,((double )num_written) / num_to_write);
      }
    }
    if (++buf_id >= NBuffers) {
      buf_id = 0;
    }
  }
/*
	 * Update checkpoint statistics. As noted above, this doesn't include
	 * buffers written by other backends or bgwriter scan.
	 */
  CheckpointStats . ckpt_bufs_written += num_written;
  ;
}
/*
 * BgBufferSync -- Write out some dirty buffers in the pool.
 *
 * This is called periodically by the background writer process.
 *
 * Returns true if it's appropriate for the bgwriter process to go into
 * low-power hibernation mode.	(This happens if the strategy clock sweep
 * has been "lapped" and no buffer allocations have occurred recently,
 * or if the bgwriter has been effectively disabled by setting
 * bgwriter_lru_maxpages to 0.)
 */

bool BgBufferSync()
{
/* info obtained from freelist.c */
  int strategy_buf_id;
  uint32 strategy_passes;
  uint32 recent_alloc;
/*
	 * Information saved between calls so we can determine the strategy
	 * point's advance rate and avoid scanning already-cleaned buffers.
	 */
  static bool saved_info_valid = (bool )0;
  static int prev_strategy_buf_id;
  static uint32 prev_strategy_passes;
  static int next_to_clean;
  static uint32 next_passes;
/* Moving averages of allocation rate and clean-buffer density */
  static float smoothed_alloc = 0;
  static float smoothed_density = 10.0;
/* Potentially these could be tunables, but for now, not */
  float smoothing_samples = 16;
  float scan_whole_pool_milliseconds = 120000.0;
/* Used to compute how far we scan ahead */
  long strategy_delta;
  int bufs_to_lap;
  int bufs_ahead;
  float scans_per_alloc;
  int reusable_buffers_est;
  int upcoming_alloc_est;
  int min_scan_buffers;
/* Variables for the scanning loop proper */
  int num_to_scan;
  int num_written;
  int reusable_buffers;
/* Variables for final smoothed_density update */
  long new_strategy_delta;
  uint32 new_recent_alloc;
/*
	 * Find out where the freelist clock sweep currently is, and how many
	 * buffer allocations have happened since our last call.
	 */
  strategy_buf_id = StrategySyncStart(&strategy_passes,&recent_alloc);
/* Report buffer alloc counts to pgstat */
  BgWriterStats . m_buf_alloc += recent_alloc;
/*
	 * If we're not running the LRU scan, just stop after doing the stats
	 * stuff.  We mark the saved state invalid so that we can recover sanely
	 * if LRU scan is turned back on later.
	 */
  if (bgwriter_lru_maxpages <= 0) {
    saved_info_valid = ((bool )0);
    return (bool )1;
  }
/*
	 * Compute strategy_delta = how many buffers have been scanned by the
	 * clock sweep since last time.  If first time through, assume none. Then
	 * see if we are still ahead of the clock sweep, and if so, how many
	 * buffers we could scan before we'd catch up with it and "lap" it. Note:
	 * weird-looking coding of xxx_passes comparisons are to avoid bogus
	 * behavior when the passes counts wrap around.
	 */
  if (saved_info_valid) {
    int32 passes_delta = (strategy_passes - prev_strategy_passes);
    strategy_delta = (strategy_buf_id - prev_strategy_buf_id);
    strategy_delta += ((long )passes_delta) * NBuffers;
    ;
    if (((int32 )(next_passes - strategy_passes)) > 0) {
/* we're one pass ahead of the strategy point */
      bufs_to_lap = strategy_buf_id - next_to_clean;
#ifdef BGW_DEBUG
#endif
    }
    else {
      if (next_passes == strategy_passes && next_to_clean >= strategy_buf_id) {
/* on same pass, but ahead or at least not behind */
        bufs_to_lap = NBuffers - (next_to_clean - strategy_buf_id);
#ifdef BGW_DEBUG
#endif
      }
      else {
/*
			 * We're behind, so skip forward to the strategy point and start
			 * cleaning from there.
			 */
#ifdef BGW_DEBUG
#endif
        next_to_clean = strategy_buf_id;
        next_passes = strategy_passes;
        bufs_to_lap = NBuffers;
      }
    }
  }
  else {
/*
		 * Initializing at startup or after LRU scanning had been off. Always
		 * start at the strategy point.
		 */
#ifdef BGW_DEBUG
#endif
    strategy_delta = 0;
    next_to_clean = strategy_buf_id;
    next_passes = strategy_passes;
    bufs_to_lap = NBuffers;
  }
/* Update saved info for next time */
  prev_strategy_buf_id = strategy_buf_id;
  prev_strategy_passes = strategy_passes;
  saved_info_valid = ((bool )1);
/*
	 * Compute how many buffers had to be scanned for each new allocation, ie,
	 * 1/density of reusable buffers, and track a moving average of that.
	 *
	 * If the strategy point didn't move, we don't update the density estimate
	 */
  if (strategy_delta > 0 && recent_alloc > 0) {
    scans_per_alloc = ((float )strategy_delta) / ((float )recent_alloc);
    smoothed_density += (scans_per_alloc - smoothed_density) / smoothing_samples;
  }
/*
	 * Estimate how many reusable buffers there are between the current
	 * strategy point and where we've scanned ahead to, based on the smoothed
	 * density estimate.
	 */
  bufs_ahead = NBuffers - bufs_to_lap;
  reusable_buffers_est = (((float )bufs_ahead) / smoothed_density);
/*
	 * Track a moving average of recent buffer allocations.  Here, rather than
	 * a true average we want a fast-attack, slow-decline behavior: we
	 * immediately follow any increase.
	 */
  if (smoothed_alloc <= ((float )recent_alloc)) {
    smoothed_alloc = recent_alloc;
  }
  else {
    smoothed_alloc += (((float )recent_alloc) - smoothed_alloc) / smoothing_samples;
  }
/* Scale the estimate by a GUC to allow more aggressive tuning. */
  upcoming_alloc_est = ((int )(smoothed_alloc * bgwriter_lru_multiplier));
/*
	 * If recent_alloc remains at zero for many cycles, smoothed_alloc will
	 * eventually underflow to zero, and the underflows produce annoying
	 * kernel warnings on some platforms.  Once upcoming_alloc_est has gone to
	 * zero, there's no point in tracking smaller and smaller values of
	 * smoothed_alloc, so just reset it to exactly zero to avoid this
	 * syndrome.  It will pop back up as soon as recent_alloc increases.
	 */
  if (upcoming_alloc_est == 0) {
    smoothed_alloc = 0;
  }
/*
	 * Even in cases where there's been little or no buffer allocation
	 * activity, we want to make a small amount of progress through the buffer
	 * cache so that as many reusable buffers as possible are clean after an
	 * idle period.
	 *
	 * (scan_whole_pool_milliseconds / BgWriterDelay) computes how many times
	 * the BGW will be called during the scan_whole_pool time; slice the
	 * buffer pool into that many sections.
	 */
  min_scan_buffers = ((int )(NBuffers / (scan_whole_pool_milliseconds / BgWriterDelay)));
  if (upcoming_alloc_est < min_scan_buffers + reusable_buffers_est) {
#ifdef BGW_DEBUG
#endif
    upcoming_alloc_est = min_scan_buffers + reusable_buffers_est;
  }
/*
	 * Now write out dirty reusable buffers, working forward from the
	 * next_to_clean point, until we have lapped the strategy scan, or cleaned
	 * enough buffers to match our estimate of the next cycle's allocation
	 * requirements, or hit the bgwriter_lru_maxpages limit.
	 */
/* Make sure we can handle the pin inside SyncOneBuffer */
  ResourceOwnerEnlargeBuffers(CurrentResourceOwner);
  num_to_scan = bufs_to_lap;
  num_written = 0;
  reusable_buffers = reusable_buffers_est;
/* Execute the LRU scan */
  while(num_to_scan > 0 && reusable_buffers < upcoming_alloc_est){
    int buffer_state = SyncOneBuffer(next_to_clean,((bool )1));
    if (++next_to_clean >= NBuffers) {
      next_to_clean = 0;
      next_passes++;
    }
    num_to_scan--;
    if (buffer_state & 0x0001) {
      reusable_buffers++;
      if (++num_written >= bgwriter_lru_maxpages) {
        BgWriterStats . m_maxwritten_clean++;
        break; 
      }
    }
    else {
      if (buffer_state & 0x0002) {
        reusable_buffers++;
      }
    }
  }
  BgWriterStats . m_buf_written_clean += num_written;
#ifdef BGW_DEBUG
#endif
/*
	 * Consider the above scan as being like a new allocation scan.
	 * Characterize its density and update the smoothed one based on it. This
	 * effectively halves the moving average period in cases where both the
	 * strategy and the background writer are doing some useful scanning,
	 * which is helpful because a long memory isn't as desirable on the
	 * density estimates.
	 */
  new_strategy_delta = (bufs_to_lap - num_to_scan);
  new_recent_alloc = (reusable_buffers - reusable_buffers_est);
  if (new_strategy_delta > 0 && new_recent_alloc > 0) {
    scans_per_alloc = ((float )new_strategy_delta) / ((float )new_recent_alloc);
    smoothed_density += (scans_per_alloc - smoothed_density) / smoothing_samples;
#ifdef BGW_DEBUG
#endif
  }
/* Return true if OK to hibernate */
  return (bufs_to_lap == 0 && recent_alloc == 0);
}
/*
 * SyncOneBuffer -- process a single buffer during syncing.
 *
 * If skip_recently_used is true, we don't write currently-pinned buffers, nor
 * buffers marked recently used, as these are not replacement candidates.
 *
 * Returns a bitmask containing the following flag bits:
 *	BUF_WRITTEN: we wrote the buffer.
 *	BUF_REUSABLE: buffer is available for replacement, ie, it has
 *		pin count 0 and usage count 0.
 *
 * (BUF_WRITTEN could be set in error if FlushBuffers finds the buffer clean
 * after locking it, but we don't care all that much.)
 *
 * Note: caller must have done ResourceOwnerEnlargeBuffers.
 */

static int SyncOneBuffer(int buf_id,bool skip_recently_used)
{
  volatile BufferDesc *bufHdr = (&BufferDescriptors[buf_id]);
  int result = 0;
/*
	 * Check whether buffer needs writing.
	 *
	 * We can make this check without taking the buffer content lock so long
	 * as we mark pages dirty in access methods *before* logging changes with
	 * XLogInsert(): if someone marks the buffer dirty just after our check we
	 * don't worry because our checkpoint.redo points before log record for
	 * upcoming changes and so we are not required to write such dirty buffer.
	 */
  do {
    if (tas(&bufHdr -> buf_hdr_lock)) {
      s_lock(&bufHdr -> buf_hdr_lock,"bufmgr.c",1652);
    }
  }while (0);
  if (bufHdr -> refcount == 0 && (bufHdr -> usage_count) == 0) {
    result |= 0x0002;
  }
  else {
    if (skip_recently_used) {
/* Caller told us not to write recently-used buffers */
       *((volatile slock_t *)(&bufHdr -> buf_hdr_lock)) = 0;
      return result;
    }
  }
  if (!((bufHdr -> flags) & 1 << 1) || !((bufHdr -> flags) & 1 << 0)) {
/* It's clean, so nothing to do */
     *((volatile slock_t *)(&bufHdr -> buf_hdr_lock)) = 0;
    return result;
  }
/*
	 * Pin it, share-lock it, write it.  (FlushBuffer will do nothing if the
	 * buffer is clean by the time we've locked it.)
	 */
  PinBuffer_Locked(bufHdr);
  LWLockAcquire(bufHdr -> content_lock,LW_SHARED);
  FlushBuffer(bufHdr,((void *)0));
  LWLockRelease(bufHdr -> content_lock);
  UnpinBuffer(bufHdr,((bool )1));
  return result | 0x0001;
}
/*
 *		AtEOXact_Buffers - clean up at end of transaction.
 *
 *		As of PostgreSQL 8.0, buffer pins should get released by the
 *		ResourceOwner mechanism.  This routine is just a debugging
 *		cross-check that no pins remain.
 */

void AtEOXact_Buffers(bool isCommit)
{
#ifdef USE_ASSERT_CHECKING
#endif
  AtEOXact_LocalBuffers(isCommit);
}
/*
 * InitBufferPoolBackend --- second-stage initialization of a new backend
 *
 * This is called after we have acquired a PGPROC and so can safely get
 * LWLocks.  We don't currently need to do anything at this stage ...
 * except register a shmem-exit callback.  AtProcExit_Buffers needs LWLock
 * access, and thereby has to be called at the corresponding phase of
 * backend shutdown.
 */

void InitBufferPoolBackend()
{
  on_shmem_exit(AtProcExit_Buffers,0);
}
/*
 * During backend exit, ensure that we released all shared-buffer locks and
 * assert that we have no remaining pins.
 */

static void AtProcExit_Buffers(int code,Datum arg)
{
  AbortBufferIO();
  UnlockBuffers();
#ifdef USE_ASSERT_CHECKING
#endif
/* localbuf.c needs a chance too */
  AtProcExit_LocalBuffers();
}
/*
 * Helper routine to issue warnings when a buffer is unexpectedly pinned
 */

void PrintBufferLeakWarning(Buffer buffer)
{
  volatile BufferDesc *buf;
  int32 loccount;
  char *path;
  BackendId backend;
  ;
  if (buffer < 0) {
    buf = (&LocalBufferDescriptors[-buffer - 1]);
    loccount = LocalRefCount[-buffer - 1];
    backend = MyBackendId;
  }
  else {
    buf = (&BufferDescriptors[buffer - 1]);
    loccount = PrivateRefCount[buffer - 1];
    backend = - 1;
  }
/* theoretically we should lock the bufhdr here */
  path = relpathbackend(buf -> tag . rnode,backend,buf -> tag . forkNum);
  (elog_start("bufmgr.c",1779,__func__) , elog_finish(19,"buffer refcount leak: [%03d] (rel=%s, blockNum=%u, flags=0x%x, refcount=%u %d)",buffer,path,buf -> tag . blockNum,(buf -> flags),buf -> refcount,loccount));
  pfree(path);
}
/*
 * CheckPointBuffers
 *
 * Flush all dirty blocks in buffer pool to disk at checkpoint time.
 *
 * Note: temporary relations do not participate in checkpoints, so they don't
 * need to be flushed.
 */

void CheckPointBuffers(int flags)
{
  ;
  CheckpointStats . ckpt_write_t = GetCurrentTimestamp();
  BufferSync(flags);
  CheckpointStats . ckpt_sync_t = GetCurrentTimestamp();
  ;
  smgrsync();
  CheckpointStats . ckpt_sync_end_t = GetCurrentTimestamp();
  ;
}
/*
 * Do whatever is needed to prepare for commit at the bufmgr and smgr levels
 */

void BufmgrCommit()
{
/* Nothing to do in bufmgr anymore... */
}
/*
 * BufferGetBlockNumber
 *		Returns the block number associated with a buffer.
 *
 * Note:
 *		Assumes that the buffer is valid and pinned, else the
 *		value may be obsolete immediately...
 */

BlockNumber BufferGetBlockNumber(Buffer buffer)
{
  volatile BufferDesc *bufHdr;
  ;
  if (buffer < 0) {
    bufHdr = (&LocalBufferDescriptors[-buffer - 1]);
  }
  else {
    bufHdr = (&BufferDescriptors[buffer - 1]);
  }
/* pinned, so OK to read tag without spinlock */
  return bufHdr -> tag . blockNum;
}
/*
 * BufferGetTag
 *		Returns the relfilenode, fork number and block number associated with
 *		a buffer.
 */

void BufferGetTag(Buffer buffer,RelFileNode *rnode,ForkNumber *forknum,BlockNumber *blknum)
{
  volatile BufferDesc *bufHdr;
/* Do the same checks as BufferGetBlockNumber. */
  ;
  if (buffer < 0) {
    bufHdr = (&LocalBufferDescriptors[-buffer - 1]);
  }
  else {
    bufHdr = (&BufferDescriptors[buffer - 1]);
  }
/* pinned, so OK to read tag without spinlock */
   *rnode = bufHdr -> tag . rnode;
   *forknum = bufHdr -> tag . forkNum;
   *blknum = bufHdr -> tag . blockNum;
}
/*
 * FlushBuffer
 *		Physically write out a shared buffer.
 *
 * NOTE: this actually just passes the buffer contents to the kernel; the
 * real write to disk won't happen until the kernel feels like it.  This
 * is okay from our point of view since we can redo the changes from WAL.
 * However, we will need to force the changes to disk via fsync before
 * we can checkpoint WAL.
 *
 * The caller must hold a pin on the buffer and have share-locked the
 * buffer contents.  (Note: a share-lock does not prevent updates of
 * hint bits in the buffer, so the page could change while the write
 * is in progress, but we assume that that will not invalidate the data
 * written.)
 *
 * If the caller has an smgr reference for the buffer's relation, pass it
 * as the second parameter.  If not, pass NULL.
 */

static void FlushBuffer(volatile BufferDesc *buf,SMgrRelation reln)
{
  XLogRecPtr recptr;
  ErrorContextCallback errcontext;
  instr_time io_start;
  instr_time io_time;
/*
	 * Acquire the buffer's io_in_progress lock.  If StartBufferIO returns
	 * false, then someone else flushed the buffer before we could, so we need
	 * not do anything.
	 */
  if (!StartBufferIO(buf,((bool )0))) {
    return ;
  }
/* Setup error traceback support for ereport() */
  errcontext . callback = shared_buffer_write_error_callback;
  errcontext . arg = ((void *)buf);
  errcontext . previous = error_context_stack;
  error_context_stack = &errcontext;
/* Find smgr relation for buffer */
  if (reln == ((void *)0)) {
    reln = smgropen(buf -> tag . rnode,- 1);
  }
  ;
/*
	 * Force XLOG flush up to buffer's LSN.  This implements the basic WAL
	 * rule that log updates must hit disk before any of the data-file changes
	 * they describe do.
	 */
  recptr = ((PageHeader )((Block )(BufferBlocks + ((Size )(buf -> buf_id)) * 8192))) -> pd_lsn;
  XLogFlush(recptr);
/*
	 * Now it's safe to write buffer to disk. Note that no one else should
	 * have been able to write it while we were busy with log flushing because
	 * we have the io_in_progress lock.
	 */
/* To check if block content changes while flushing. - vadim 01/17/97 */
  do {
    if (tas(&buf -> buf_hdr_lock)) {
      s_lock(&buf -> buf_hdr_lock,"bufmgr.c",1934);
    }
  }while (0);
  buf -> flags &= ~(1 << 5);
   *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
  if (track_io_timing) {
    gettimeofday(&io_start,((void *)0));
  }
  smgrwrite(reln,buf -> tag . forkNum,buf -> tag . blockNum,((char *)((Block )(BufferBlocks + ((Size )(buf -> buf_id)) * 8192))),((bool )0));
  if (track_io_timing) {
    gettimeofday(&io_time,((void *)0));
    do {
      io_time . tv_sec -= io_start . tv_sec;
      io_time . tv_usec -= io_start . tv_usec;
      while(io_time . tv_usec < 0){
        io_time . tv_usec += 1000000;
        io_time . tv_sec--;
      }
    }while (0);
    pgStatBlockWriteTime += ((uint64 )io_time . tv_sec) * ((uint64 )1000000) + ((uint64 )io_time . tv_usec);
    do {
      pgBufferUsage . blk_write_time . tv_sec += io_time . tv_sec;
      pgBufferUsage . blk_write_time . tv_usec += io_time . tv_usec;
      while(pgBufferUsage . blk_write_time . tv_usec >= 1000000){
        pgBufferUsage . blk_write_time . tv_usec -= 1000000;
        pgBufferUsage . blk_write_time . tv_sec++;
      }
    }while (0);
  }
  pgBufferUsage . shared_blks_written++;
/*
	 * Mark the buffer as clean (unless BM_JUST_DIRTIED has become set) and
	 * end the io_in_progress state.
	 */
  TerminateBufferIO(buf,((bool )1),0);
  ;
/* Pop the error context stack */
  error_context_stack = errcontext . previous;
}
/*
 * RelationGetNumberOfBlocks
 *		Determines the current number of pages in the relation.
 */

BlockNumber RelationGetNumberOfBlocksInFork(Relation relation,ForkNumber forkNum)
{
/* Open it at the smgr level if not already done */
  do {
    if (relation -> rd_smgr == ((void *)0)) {
      smgrsetowner(&relation -> rd_smgr,smgropen(relation -> rd_node,relation -> rd_backend));
    }
  }while (0);
  return smgrnblocks(relation -> rd_smgr,forkNum);
}
/*
 * BufferIsPermanent
 *		Determines whether a buffer will potentially still be around after
 *		a crash.  Caller must hold a buffer pin.
 */

bool BufferIsPermanent(Buffer buffer)
{
  volatile BufferDesc *bufHdr;
/* Local buffers are used only for temp relations. */
  if (buffer < 0) {
    return (bool )0;
  }
/* Make sure we've got a real buffer, and that we hold a pin on it. */
  ;
  ;
/*
	 * BM_PERMANENT can't be changed while we hold a pin on the buffer, so we
	 * need not bother with the buffer header spinlock.  Even if someone else
	 * changes the buffer header flags while we're doing this, we assume that
	 * changing an aligned 2-byte BufFlags value is atomic, so we'll read the
	 * old value or the new value, but not random garbage.
	 */
  bufHdr = (&BufferDescriptors[buffer - 1]);
  return (((bufHdr -> flags) & 1 << 8) != 0);
}
/* ---------------------------------------------------------------------
 *		DropRelFileNodeBuffers
 *
 *		This function removes from the buffer pool all the pages of the
 *		specified relation fork that have block numbers >= firstDelBlock.
 *		(In particular, with firstDelBlock = 0, all pages are removed.)
 *		Dirty pages are simply dropped, without bothering to write them
 *		out first.	Therefore, this is NOT rollback-able, and so should be
 *		used only with extreme caution!
 *
 *		Currently, this is called only from smgr.c when the underlying file
 *		is about to be deleted or truncated (firstDelBlock is needed for
 *		the truncation case).  The data in the affected pages would therefore
 *		be deleted momentarily anyway, and there is no point in writing it.
 *		It is the responsibility of higher-level code to ensure that the
 *		deletion or truncation does not lose any data that could be needed
 *		later.	It is also the responsibility of higher-level code to ensure
 *		that no other process could be trying to load more pages of the
 *		relation into buffers.
 *
 *		XXX currently it sequentially searches the buffer pool, should be
 *		changed to more clever ways of searching.  However, this routine
 *		is used only in code paths that aren't very performance-critical,
 *		and we shouldn't slow down the hot paths to make it faster ...
 * --------------------------------------------------------------------
 */

void DropRelFileNodeBuffers(RelFileNodeBackend rnode,ForkNumber forkNum,BlockNumber firstDelBlock)
{
  int i;
/* If it's a local relation, it's localbuf.c's problem. */
  if (rnode . backend != - 1) {
    if (rnode . backend == MyBackendId) {
      DropRelFileNodeLocalBuffers(rnode . node,forkNum,firstDelBlock);
    }
    return ;
  }
  for (i = 0; i < NBuffers; i++) {
    volatile BufferDesc *bufHdr = (&BufferDescriptors[i]);
/*
		 * We can make this a tad faster by prechecking the buffer tag before
		 * we attempt to lock the buffer; this saves a lot of lock
		 * acquisitions in typical cases.  It should be safe because the
		 * caller must have AccessExclusiveLock on the relation, or some other
		 * reason to be certain that no one is loading new pages of the rel
		 * into the buffer pool.  (Otherwise we might well miss such pages
		 * entirely.)  Therefore, while the tag might be changing while we
		 * look at it, it can't be changing *to* a value we care about, only
		 * *away* from such a value.  So false negatives are impossible, and
		 * false positives are safe because we'll recheck after getting the
		 * buffer lock.
		 *
		 * We could check forkNum and blockNum as well as the rnode, but the
		 * incremental win from doing so seems small.
		 */
    if (!(bufHdr -> tag . rnode . relNode == rnode . node . relNode && bufHdr -> tag . rnode . dbNode == rnode . node . dbNode && bufHdr -> tag . rnode . spcNode == rnode . node . spcNode)) {
      continue; 
    }
    do {
      if (tas(&bufHdr -> buf_hdr_lock)) {
        s_lock(&bufHdr -> buf_hdr_lock,"bufmgr.c",2078);
      }
    }while (0);
    if (bufHdr -> tag . rnode . relNode == rnode . node . relNode && bufHdr -> tag . rnode . dbNode == rnode . node . dbNode && bufHdr -> tag . rnode . spcNode == rnode . node . spcNode && bufHdr -> tag . forkNum == forkNum && bufHdr -> tag . blockNum >= firstDelBlock) {
/* releases spinlock */
      InvalidateBuffer(bufHdr);
    }
    else {
       *((volatile slock_t *)(&bufHdr -> buf_hdr_lock)) = 0;
    }
  }
}
/* ---------------------------------------------------------------------
 *		DropRelFileNodeAllBuffers
 *
 *		This function removes from the buffer pool all the pages of all
 *		forks of the specified relation.  It's equivalent to calling
 *		DropRelFileNodeBuffers once per fork with firstDelBlock = 0.
 * --------------------------------------------------------------------
 */

void DropRelFileNodeAllBuffers(RelFileNodeBackend rnode)
{
  int i;
/* If it's a local relation, it's localbuf.c's problem. */
  if (rnode . backend != - 1) {
    if (rnode . backend == MyBackendId) {
      DropRelFileNodeAllLocalBuffers(rnode . node);
    }
    return ;
  }
  for (i = 0; i < NBuffers; i++) {
    volatile BufferDesc *bufHdr = (&BufferDescriptors[i]);
/*
		 * As in DropRelFileNodeBuffers, an unlocked precheck should be safe
		 * and saves some cycles.
		 */
    if (!(bufHdr -> tag . rnode . relNode == rnode . node . relNode && bufHdr -> tag . rnode . dbNode == rnode . node . dbNode && bufHdr -> tag . rnode . spcNode == rnode . node . spcNode)) {
      continue; 
    }
    do {
      if (tas(&bufHdr -> buf_hdr_lock)) {
        s_lock(&bufHdr -> buf_hdr_lock,"bufmgr.c",2120);
      }
    }while (0);
    if (bufHdr -> tag . rnode . relNode == rnode . node . relNode && bufHdr -> tag . rnode . dbNode == rnode . node . dbNode && bufHdr -> tag . rnode . spcNode == rnode . node . spcNode) {
/* releases spinlock */
      InvalidateBuffer(bufHdr);
    }
    else {
       *((volatile slock_t *)(&bufHdr -> buf_hdr_lock)) = 0;
    }
  }
}
/* ---------------------------------------------------------------------
 *		DropDatabaseBuffers
 *
 *		This function removes all the buffers in the buffer cache for a
 *		particular database.  Dirty pages are simply dropped, without
 *		bothering to write them out first.	This is used when we destroy a
 *		database, to avoid trying to flush data to disk when the directory
 *		tree no longer exists.	Implementation is pretty similar to
 *		DropRelFileNodeBuffers() which is for destroying just one relation.
 * --------------------------------------------------------------------
 */

void DropDatabaseBuffers(Oid dbid)
{
  int i;
/*
	 * We needn't consider local buffers, since by assumption the target
	 * database isn't our own.
	 */
  for (i = 0; i < NBuffers; i++) {
    volatile BufferDesc *bufHdr = (&BufferDescriptors[i]);
/*
		 * As in DropRelFileNodeBuffers, an unlocked precheck should be safe
		 * and saves some cycles.
		 */
    if (bufHdr -> tag . rnode . dbNode != dbid) {
      continue; 
    }
    do {
      if (tas(&bufHdr -> buf_hdr_lock)) {
        s_lock(&bufHdr -> buf_hdr_lock,"bufmgr.c",2160);
      }
    }while (0);
    if (bufHdr -> tag . rnode . dbNode == dbid) {
/* releases spinlock */
      InvalidateBuffer(bufHdr);
    }
    else {
       *((volatile slock_t *)(&bufHdr -> buf_hdr_lock)) = 0;
    }
  }
}
/* -----------------------------------------------------------------
 *		PrintBufferDescs
 *
 *		this function prints all the buffer descriptors, for debugging
 *		use only.
 * -----------------------------------------------------------------
 */
#ifdef NOT_USED
/* theoretically we should lock the bufhdr here */
#endif
#ifdef NOT_USED
/* theoretically we should lock the bufhdr here */
#endif
/* ---------------------------------------------------------------------
 *		FlushRelationBuffers
 *
 *		This function writes all dirty pages of a relation out to disk
 *		(or more accurately, out to kernel disk buffers), ensuring that the
 *		kernel has an up-to-date view of the relation.
 *
 *		Generally, the caller should be holding AccessExclusiveLock on the
 *		target relation to ensure that no other backend is busy dirtying
 *		more blocks of the relation; the effects can't be expected to last
 *		after the lock is released.
 *
 *		XXX currently it sequentially searches the buffer pool, should be
 *		changed to more clever ways of searching.  This routine is not
 *		used in any performance-critical code paths, so it's not worth
 *		adding additional overhead to normal paths to make it go faster;
 *		but see also DropRelFileNodeBuffers.
 * --------------------------------------------------------------------
 */

void FlushRelationBuffers(Relation rel)
{
  int i;
  volatile BufferDesc *bufHdr;
/* Open rel at the smgr level if not already done */
  do {
    if (rel -> rd_smgr == ((void *)0)) {
      smgrsetowner(&rel -> rd_smgr,smgropen(rel -> rd_node,rel -> rd_backend));
    }
  }while (0);
  if ((rel -> rd_rel -> relpersistence) == 't') {
    for (i = 0; i < NLocBuffer; i++) {
      bufHdr = (&LocalBufferDescriptors[i]);
      if (bufHdr -> tag . rnode . relNode == rel -> rd_node . relNode && bufHdr -> tag . rnode . dbNode == rel -> rd_node . dbNode && bufHdr -> tag . rnode . spcNode == rel -> rd_node . spcNode && (bufHdr -> flags) & 1 << 1 && (bufHdr -> flags) & 1 << 0) {
        ErrorContextCallback errcontext;
/* Setup error traceback support for ereport() */
        errcontext . callback = local_buffer_write_error_callback;
        errcontext . arg = ((void *)bufHdr);
        errcontext . previous = error_context_stack;
        error_context_stack = &errcontext;
        smgrwrite(rel -> rd_smgr,bufHdr -> tag . forkNum,bufHdr -> tag . blockNum,((char *)LocalBufferBlockPointers[-(bufHdr -> buf_id + 2)]),((bool )0));
        bufHdr -> flags &= ~(1 << 0 | 1 << 5);
/* Pop the error context stack */
        error_context_stack = errcontext . previous;
      }
    }
    return ;
  }
/* Make sure we can handle the pin inside the loop */
  ResourceOwnerEnlargeBuffers(CurrentResourceOwner);
  for (i = 0; i < NBuffers; i++) {
    bufHdr = (&BufferDescriptors[i]);
/*
		 * As in DropRelFileNodeBuffers, an unlocked precheck should be safe
		 * and saves some cycles.
		 */
    if (!(bufHdr -> tag . rnode . relNode == rel -> rd_node . relNode && bufHdr -> tag . rnode . dbNode == rel -> rd_node . dbNode && bufHdr -> tag . rnode . spcNode == rel -> rd_node . spcNode)) {
      continue; 
    }
    do {
      if (tas(&bufHdr -> buf_hdr_lock)) {
        s_lock(&bufHdr -> buf_hdr_lock,"bufmgr.c",2294);
      }
    }while (0);
    if (bufHdr -> tag . rnode . relNode == rel -> rd_node . relNode && bufHdr -> tag . rnode . dbNode == rel -> rd_node . dbNode && bufHdr -> tag . rnode . spcNode == rel -> rd_node . spcNode && (bufHdr -> flags) & 1 << 1 && (bufHdr -> flags) & 1 << 0) {
      PinBuffer_Locked(bufHdr);
      LWLockAcquire(bufHdr -> content_lock,LW_SHARED);
      FlushBuffer(bufHdr,rel -> rd_smgr);
      LWLockRelease(bufHdr -> content_lock);
      UnpinBuffer(bufHdr,((bool )1));
    }
    else {
       *((volatile slock_t *)(&bufHdr -> buf_hdr_lock)) = 0;
    }
  }
}
/* ---------------------------------------------------------------------
 *		FlushDatabaseBuffers
 *
 *		This function writes all dirty pages of a database out to disk
 *		(or more accurately, out to kernel disk buffers), ensuring that the
 *		kernel has an up-to-date view of the database.
 *
 *		Generally, the caller should be holding an appropriate lock to ensure
 *		no other backend is active in the target database; otherwise more
 *		pages could get dirtied.
 *
 *		Note we don't worry about flushing any pages of temporary relations.
 *		It's assumed these wouldn't be interesting.
 * --------------------------------------------------------------------
 */

void FlushDatabaseBuffers(Oid dbid)
{
  int i;
  volatile BufferDesc *bufHdr;
/* Make sure we can handle the pin inside the loop */
  ResourceOwnerEnlargeBuffers(CurrentResourceOwner);
  for (i = 0; i < NBuffers; i++) {
    bufHdr = (&BufferDescriptors[i]);
/*
		 * As in DropRelFileNodeBuffers, an unlocked precheck should be safe
		 * and saves some cycles.
		 */
    if (bufHdr -> tag . rnode . dbNode != dbid) {
      continue; 
    }
    do {
      if (tas(&bufHdr -> buf_hdr_lock)) {
        s_lock(&bufHdr -> buf_hdr_lock,"bufmgr.c",2344);
      }
    }while (0);
    if (bufHdr -> tag . rnode . dbNode == dbid && (bufHdr -> flags) & 1 << 1 && (bufHdr -> flags) & 1 << 0) {
      PinBuffer_Locked(bufHdr);
      LWLockAcquire(bufHdr -> content_lock,LW_SHARED);
      FlushBuffer(bufHdr,((void *)0));
      LWLockRelease(bufHdr -> content_lock);
      UnpinBuffer(bufHdr,((bool )1));
    }
    else {
       *((volatile slock_t *)(&bufHdr -> buf_hdr_lock)) = 0;
    }
  }
}
/*
 * ReleaseBuffer -- release the pin on a buffer
 */

void ReleaseBuffer(Buffer buffer)
{
  volatile BufferDesc *bufHdr;
  if (!((((void )((bool )1)) , buffer != 0))) {
    (elog_start("bufmgr.c",2368,__func__) , elog_finish(20,"bad buffer ID: %d",buffer));
  }
  ResourceOwnerForgetBuffer(CurrentResourceOwner,buffer);
  if (buffer < 0) {
    ;
    LocalRefCount[-buffer - 1]--;
    return ;
  }
  bufHdr = (&BufferDescriptors[buffer - 1]);
  ;
  if (PrivateRefCount[buffer - 1] > 1) {
    PrivateRefCount[buffer - 1]--;
  }
  else {
    UnpinBuffer(bufHdr,((bool )0));
  }
}
/*
 * UnlockReleaseBuffer -- release the content lock and pin on a buffer
 *
 * This is just a shorthand for a common combination.
 */

void UnlockReleaseBuffer(Buffer buffer)
{
  LockBuffer(buffer,0);
  ReleaseBuffer(buffer);
}
/*
 * IncrBufferRefCount
 *		Increment the pin count on a buffer that we have *already* pinned
 *		at least once.
 *
 *		This function cannot be used on a buffer we do not have pinned,
 *		because it doesn't change the shared buffer state.
 */

void IncrBufferRefCount(Buffer buffer)
{
  ;
  ResourceOwnerEnlargeBuffers(CurrentResourceOwner);
  ResourceOwnerRememberBuffer(CurrentResourceOwner,buffer);
  if (buffer < 0) {
    LocalRefCount[-buffer - 1]++;
  }
  else {
    PrivateRefCount[buffer - 1]++;
  }
}
/*
 * SetBufferCommitInfoNeedsSave
 *
 *	Mark a buffer dirty when we have updated tuple commit-status bits in it.
 *
 * This is essentially the same as MarkBufferDirty, except that the caller
 * might have only share-lock instead of exclusive-lock on the buffer's
 * content lock.  We preserve the distinction mainly as a way of documenting
 * that the caller has not made a critical data change --- the status-bit
 * update could be redone by someone else just as easily.  Therefore, no WAL
 * log record need be generated, whereas calls to MarkBufferDirty really ought
 * to be associated with a WAL-entry-creating action.
 */

void SetBufferCommitInfoNeedsSave(Buffer buffer)
{
  volatile BufferDesc *bufHdr;
  if (!((((void )((bool )1)) , buffer != 0))) {
    (elog_start("bufmgr.c",2440,__func__) , elog_finish(20,"bad buffer ID: %d",buffer));
  }
  if (buffer < 0) {
    MarkLocalBufferDirty(buffer);
    return ;
  }
  bufHdr = (&BufferDescriptors[buffer - 1]);
  ;
/* here, either share or exclusive lock is OK */
  ;
/*
	 * This routine might get called many times on the same page, if we are
	 * making the first scan after commit of an xact that added/deleted many
	 * tuples.	So, be as quick as we can if the buffer is already dirty.  We
	 * do this by not acquiring spinlock if it looks like the status bits are
	 * already.  Since we make this test unlocked, there's a chance we might
	 * fail to notice that the flags have just been cleared, and failed to
	 * reset them, due to memory-ordering issues.  But since this function is
	 * only intended to be used in cases where failing to write out the data
	 * would be harmless anyway, it doesn't really matter.
	 */
  if (((bufHdr -> flags) & (1 << 0 | 1 << 5)) != (1 << 0 | 1 << 5)) {
    do {
      if (tas(&bufHdr -> buf_hdr_lock)) {
        s_lock(&bufHdr -> buf_hdr_lock,"bufmgr.c",2468);
      }
    }while (0);
    ;
    if (!((bufHdr -> flags) & 1 << 0)) {
/* Do vacuum cost accounting */
      VacuumPageDirty++;
      if (VacuumCostActive) {
        VacuumCostBalance += VacuumCostPageDirty;
      }
    }
    bufHdr -> flags |= 1 << 0 | 1 << 5;
     *((volatile slock_t *)(&bufHdr -> buf_hdr_lock)) = 0;
  }
}
/*
 * Release buffer content locks for shared buffers.
 *
 * Used to clean up after errors.
 *
 * Currently, we can expect that lwlock.c's LWLockReleaseAll() took care
 * of releasing buffer content locks per se; the only thing we need to deal
 * with here is clearing any PIN_COUNT request that was in progress.
 */

void UnlockBuffers()
{
  volatile BufferDesc *buf = PinCountWaitBuf;
  if (buf) {
    do {
      if (tas(&buf -> buf_hdr_lock)) {
        s_lock(&buf -> buf_hdr_lock,"bufmgr.c",2498);
      }
    }while (0);
/*
		 * Don't complain if flag bit not set; it could have been reset but we
		 * got a cancel/die interrupt before getting the signal.
		 */
    if (((buf -> flags) & 1 << 6) != 0 && buf -> wait_backend_pid == MyProcPid) {
      buf -> flags &= ~(1 << 6);
    }
     *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
    PinCountWaitBuf = ((void *)0);
  }
}
/*
 * Acquire or release the content_lock for the buffer.
 */

void LockBuffer(Buffer buffer,int mode)
{
  volatile BufferDesc *buf;
  ;
  if (buffer < 0) {
/* local buffers need no lock */
    return ;
  }
  buf = (&BufferDescriptors[buffer - 1]);
  if (mode == 0) {
    LWLockRelease(buf -> content_lock);
  }
  else {
    if (mode == 1) {
      LWLockAcquire(buf -> content_lock,LW_SHARED);
    }
    else {
      if (mode == 2) {
        LWLockAcquire(buf -> content_lock,LW_EXCLUSIVE);
      }
      else {
        (elog_start("bufmgr.c",2535,__func__) , elog_finish(20,"unrecognized buffer lock mode: %d",mode));
      }
    }
  }
}
/*
 * Acquire the content_lock for the buffer, but only if we don't have to wait.
 *
 * This assumes the caller wants BUFFER_LOCK_EXCLUSIVE mode.
 */

bool ConditionalLockBuffer(Buffer buffer)
{
  volatile BufferDesc *buf;
  ;
  if (buffer < 0) {
/* act as though we got it */
    return (bool )1;
  }
  buf = (&BufferDescriptors[buffer - 1]);
  return LWLockConditionalAcquire(buf -> content_lock,LW_EXCLUSIVE);
}
/*
 * LockBufferForCleanup - lock a buffer in preparation for deleting items
 *
 * Items may be deleted from a disk page only when the caller (a) holds an
 * exclusive lock on the buffer and (b) has observed that no other backend
 * holds a pin on the buffer.  If there is a pin, then the other backend
 * might have a pointer into the buffer (for example, a heapscan reference
 * to an item --- see README for more details).  It's OK if a pin is added
 * after the cleanup starts, however; the newly-arrived backend will be
 * unable to look at the page until we release the exclusive lock.
 *
 * To implement this protocol, a would-be deleter must pin the buffer and
 * then call LockBufferForCleanup().  LockBufferForCleanup() is similar to
 * LockBuffer(buffer, BUFFER_LOCK_EXCLUSIVE), except that it loops until
 * it has successfully observed pin count = 1.
 */

void LockBufferForCleanup(Buffer buffer)
{
  volatile BufferDesc *bufHdr;
  ;
  ;
  if (buffer < 0) {
/* There should be exactly one pin */
    if (LocalRefCount[-buffer - 1] != 1) {
      (elog_start("bufmgr.c",2585,__func__) , elog_finish(20,"incorrect local pin count: %d",LocalRefCount[-buffer - 1]));
    }
/* Nobody else to wait for */
    return ;
  }
/* There should be exactly one local pin */
  if (PrivateRefCount[buffer - 1] != 1) {
    (elog_start("bufmgr.c",2593,__func__) , elog_finish(20,"incorrect local pin count: %d",PrivateRefCount[buffer - 1]));
  }
  bufHdr = (&BufferDescriptors[buffer - 1]);
  for (; ; ) {
/* Try to acquire lock */
    LockBuffer(buffer,2);
    do {
      if (tas(&bufHdr -> buf_hdr_lock)) {
        s_lock(&bufHdr -> buf_hdr_lock,"bufmgr.c",2602);
      }
    }while (0);
    ;
    if (bufHdr -> refcount == 1) {
/* Successfully acquired exclusive lock with pincount 1 */
       *((volatile slock_t *)(&bufHdr -> buf_hdr_lock)) = 0;
      return ;
    }
/* Failed, so mark myself as waiting for pincount 1 */
    if ((bufHdr -> flags) & 1 << 6) {
       *((volatile slock_t *)(&bufHdr -> buf_hdr_lock)) = 0;
      LockBuffer(buffer,0);
      (elog_start("bufmgr.c",2615,__func__) , elog_finish(20,"multiple backends attempting to wait for pincount 1"));
    }
    bufHdr -> wait_backend_pid = MyProcPid;
    bufHdr -> flags |= 1 << 6;
    PinCountWaitBuf = bufHdr;
     *((volatile slock_t *)(&bufHdr -> buf_hdr_lock)) = 0;
    LockBuffer(buffer,0);
/* Wait to be signaled by UnpinBuffer() */
    if (standbyState >= STANDBY_SNAPSHOT_PENDING) {
/* Publish the bufid that Startup process waits on */
      SetStartupBufferPinWaitBufId(buffer - 1);
/* Set alarm and then wait to be signaled by UnpinBuffer() */
      ResolveRecoveryConflictWithBufferPin();
/* Reset the published bufid */
      SetStartupBufferPinWaitBufId(- 1);
    }
    else {
      ProcWaitForSignal();
    }
    PinCountWaitBuf = ((void *)0);
/* Loop back and try again */
  }
}
/*
 * Check called from RecoveryConflictInterrupt handler when Startup
 * process requests cancellation of all pin holders that are blocking it.
 */

bool HoldingBufferPinThatDelaysRecovery()
{
  int bufid = GetStartupBufferPinWaitBufId();
/*
	 * If we get woken slowly then it's possible that the Startup process was
	 * already woken by other backends before we got here. Also possible that
	 * we get here by multiple interrupts or interrupts at inappropriate
	 * times, so make sure we do nothing if the bufid is not set.
	 */
  if (bufid < 0) {
    return (bool )0;
  }
  if (PrivateRefCount[bufid] > 0) {
    return (bool )1;
  }
  return (bool )0;
}
/*
 * ConditionalLockBufferForCleanup - as above, but don't wait to get the lock
 *
 * We won't loop, but just check once to see if the pin count is OK.  If
 * not, return FALSE with no lock held.
 */

bool ConditionalLockBufferForCleanup(Buffer buffer)
{
  volatile BufferDesc *bufHdr;
  ;
  if (buffer < 0) {
/* There should be exactly one pin */
    ;
    if (LocalRefCount[-buffer - 1] != 1) {
      return (bool )0;
    }
/* Nobody else to wait for */
    return (bool )1;
  }
/* There should be exactly one local pin */
  ;
  if (PrivateRefCount[buffer - 1] != 1) {
    return (bool )0;
  }
/* Try to acquire lock */
  if (!ConditionalLockBuffer(buffer)) {
    return (bool )0;
  }
  bufHdr = (&BufferDescriptors[buffer - 1]);
  do {
    if (tas(&bufHdr -> buf_hdr_lock)) {
      s_lock(&bufHdr -> buf_hdr_lock,"bufmgr.c",2698);
    }
  }while (0);
  ;
  if (bufHdr -> refcount == 1) {
/* Successfully acquired exclusive lock with pincount 1 */
     *((volatile slock_t *)(&bufHdr -> buf_hdr_lock)) = 0;
    return (bool )1;
  }
/* Failed, so release the lock */
   *((volatile slock_t *)(&bufHdr -> buf_hdr_lock)) = 0;
  LockBuffer(buffer,0);
  return (bool )0;
}
/*
 *	Functions for buffer I/O handling
 *
 *	Note: We assume that nested buffer I/O never occurs.
 *	i.e at most one io_in_progress lock is held per proc.
 *
 *	Also note that these are used only for shared buffers, not local ones.
 */
/*
 * WaitIO -- Block until the IO_IN_PROGRESS flag on 'buf' is cleared.
 */

static void WaitIO(volatile BufferDesc *buf)
{
/*
	 * Changed to wait until there's no IO - Inoue 01/13/2000
	 *
	 * Note this is *necessary* because an error abort in the process doing
	 * I/O could release the io_in_progress_lock prematurely. See
	 * AbortBufferIO.
	 */
  for (; ; ) {
    BufFlags sv_flags;
/*
		 * It may not be necessary to acquire the spinlock to check the flag
		 * here, but since this test is essential for correctness, we'd better
		 * play it safe.
		 */
    do {
      if (tas(&buf -> buf_hdr_lock)) {
        s_lock(&buf -> buf_hdr_lock,"bufmgr.c",2745);
      }
    }while (0);
    sv_flags = buf -> flags;
     *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
    if (!(sv_flags & 1 << 3)) {
      break; 
    }
    LWLockAcquire(buf -> io_in_progress_lock,LW_SHARED);
    LWLockRelease(buf -> io_in_progress_lock);
  }
}
/*
 * StartBufferIO: begin I/O on this buffer
 *	(Assumptions)
 *	My process is executing no IO
 *	The buffer is Pinned
 *
 * In some scenarios there are race conditions in which multiple backends
 * could attempt the same I/O operation concurrently.  If someone else
 * has already started I/O on this buffer then we will block on the
 * io_in_progress lock until he's done.
 *
 * Input operations are only attempted on buffers that are not BM_VALID,
 * and output operations only on buffers that are BM_VALID and BM_DIRTY,
 * so we can always tell if the work is already done.
 *
 * Returns TRUE if we successfully marked the buffer as I/O busy,
 * FALSE if someone else already did the work.
 */

static bool StartBufferIO(volatile BufferDesc *buf,bool forInput)
{
  ;
  for (; ; ) {
/*
		 * Grab the io_in_progress lock so that other processes can wait for
		 * me to finish the I/O.
		 */
    LWLockAcquire(buf -> io_in_progress_lock,LW_EXCLUSIVE);
    do {
      if (tas(&buf -> buf_hdr_lock)) {
        s_lock(&buf -> buf_hdr_lock,"bufmgr.c",2786);
      }
    }while (0);
    if (!((buf -> flags) & 1 << 3)) {
      break; 
    }
/*
		 * The only way BM_IO_IN_PROGRESS could be set when the io_in_progress
		 * lock isn't held is if the process doing the I/O is recovering from
		 * an error (see AbortBufferIO).  If that's the case, we must wait for
		 * him to get unwedged.
		 */
     *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
    LWLockRelease(buf -> io_in_progress_lock);
    WaitIO(buf);
  }
/* Once we get here, there is definitely no I/O active on this buffer */
  if (forInput?(buf -> flags) & 1 << 1 : !((buf -> flags) & 1 << 0)) {
/* someone else already did the I/O */
     *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
    LWLockRelease(buf -> io_in_progress_lock);
    return (bool )0;
  }
  buf -> flags |= 1 << 3;
   *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
  InProgressBuf = buf;
  IsForInput = forInput;
  return (bool )1;
}
/*
 * TerminateBufferIO: release a buffer we were doing I/O on
 *	(Assumptions)
 *	My process is executing IO for the buffer
 *	BM_IO_IN_PROGRESS bit is set for the buffer
 *	We hold the buffer's io_in_progress lock
 *	The buffer is Pinned
 *
 * If clear_dirty is TRUE and BM_JUST_DIRTIED is not set, we clear the
 * buffer's BM_DIRTY flag.  This is appropriate when terminating a
 * successful write.  The check on BM_JUST_DIRTIED is necessary to avoid
 * marking the buffer clean if it was re-dirtied while we were writing.
 *
 * set_flag_bits gets ORed into the buffer's flags.  It must include
 * BM_IO_ERROR in a failure case.  For successful completion it could
 * be 0, or BM_VALID if we just finished reading in the page.
 */

static void TerminateBufferIO(volatile BufferDesc *buf,bool clear_dirty,int set_flag_bits)
{
  ;
  do {
    if (tas(&buf -> buf_hdr_lock)) {
      s_lock(&buf -> buf_hdr_lock,"bufmgr.c",2845);
    }
  }while (0);
  ;
  buf -> flags &= ~(1 << 3 | 1 << 4);
  if (clear_dirty && !((buf -> flags) & 1 << 5)) {
    buf -> flags &= ~(1 << 0 | 1 << 7);
  }
  buf -> flags |= set_flag_bits;
   *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
  InProgressBuf = ((void *)0);
  LWLockRelease(buf -> io_in_progress_lock);
}
/*
 * AbortBufferIO: Clean up any active buffer I/O after an error.
 *
 *	All LWLocks we might have held have been released,
 *	but we haven't yet released buffer pins, so the buffer is still pinned.
 *
 *	If I/O was in progress, we always set BM_IO_ERROR, even though it's
 *	possible the error condition wasn't related to the I/O.
 */

void AbortBufferIO()
{
  volatile BufferDesc *buf = InProgressBuf;
  if (buf) {
/*
		 * Since LWLockReleaseAll has already been called, we're not holding
		 * the buffer's io_in_progress_lock. We have to re-acquire it so that
		 * we can use TerminateBufferIO. Anyone who's executing WaitIO on the
		 * buffer will be in a busy spin until we succeed in doing this.
		 */
    LWLockAcquire(buf -> io_in_progress_lock,LW_EXCLUSIVE);
    do {
      if (tas(&buf -> buf_hdr_lock)) {
        s_lock(&buf -> buf_hdr_lock,"bufmgr.c",2884);
      }
    }while (0);
    ;
    if (IsForInput) {
      ;
/* We'd better not think buffer is valid yet */
      ;
       *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
    }
    else {
      BufFlags sv_flags;
      sv_flags = buf -> flags;
      ;
       *((volatile slock_t *)(&buf -> buf_hdr_lock)) = 0;
/* Issue notice if this is not the first failure... */
      if (sv_flags & 1 << 4) {
/* Buffer is pinned, so we can read tag without spinlock */
        char *path;
        path = relpathbackend(buf -> tag . rnode,- 1,buf -> tag . forkNum);
        errstart(19,"bufmgr.c",2911,__func__,((void *)0))?errfinish(errcode(('5' - 48 & 0x3F) + ((56 - 48 & 0x3F) << 6) + ((48 - 48 & 0x3F) << 12) + (('3' - 48 & 0x3F) << 18) + ((48 - 48 & 0x3F) << 24)),errmsg("could not write block %u of %s",buf -> tag . blockNum,path),errdetail("Multiple failures --- write error might be permanent.")) : ((void )0);
        pfree(path);
      }
    }
    TerminateBufferIO(buf,((bool )0),1 << 4);
  }
}
/*
 * Error context callback for errors occurring during shared buffer writes.
 */

static void shared_buffer_write_error_callback(void *arg)
{
  volatile BufferDesc *bufHdr = (volatile BufferDesc *)arg;
/* Buffer is pinned, so we can read the tag without locking the spinlock */
  if (bufHdr != ((void *)0)) {
    char *path = relpathbackend(bufHdr -> tag . rnode,- 1,bufHdr -> tag . forkNum);
    errcontext("writing block %u of relation %s",bufHdr -> tag . blockNum,path);
    pfree(path);
  }
}
/*
 * Error context callback for errors occurring during local buffer writes.
 */

static void local_buffer_write_error_callback(void *arg)
{
  volatile BufferDesc *bufHdr = (volatile BufferDesc *)arg;
  if (bufHdr != ((void *)0)) {
    char *path = relpathbackend(bufHdr -> tag . rnode,MyBackendId,bufHdr -> tag . forkNum);
    errcontext("writing block %u of relation %s",bufHdr -> tag . blockNum,path);
    pfree(path);
  }
}

void woolworking_truncature(union kinematically_cambers **********cochleary_trotyl)
{
 int stonesoup_oc_i = 0;
 int stonesoup_found;
 char *stonesoup_buffer = 0;
 int stonesoup_buffer_len;
  char *tendomucoid_hon = 0;
  ++stonesoup_global_variable;;
  tendomucoid_hon = ((char *)( *( *( *( *( *( *( *( *( *( *cochleary_trotyl)))))))))) . haythorn_vishnuite);
    tracepoint(stonesoup_trace, weakness_start, "CWE761", "A", "Free of Pointer not at Start of Buffer");
    stonesoup_buffer_len = strlen(tendomucoid_hon) + 1;
    stonesoup_buffer = malloc(stonesoup_buffer_len * sizeof(char ));
    if (stonesoup_buffer == 0) {
        stonesoup_printf("Error: Failed to allocate memory\n");
        exit(1);
    }
    strcpy(stonesoup_buffer,tendomucoid_hon);
    for (; stonesoup_oc_i < stonesoup_buffer_len; ++stonesoup_oc_i) {
        stonesoup_buffer[stonesoup_oc_i] = stonesoup_toupper(stonesoup_buffer[stonesoup_oc_i]);
    }
    stonesoup_printf("%s\n",stonesoup_buffer);
    tracepoint(stonesoup_trace, variable_buffer, "stonesoup_buffer", stonesoup_buffer, "INITIAL_STATE");
    stonesoup_found = stonesoup_contains_char(stonesoup_buffer,'E');
    if (stonesoup_found == 1)
        stonesoup_printf("%s\n",tendomucoid_hon);
    tracepoint(stonesoup_trace, weakness_end);
;
stonesoup_close_printf_context();
}
