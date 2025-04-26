/* timestamp.c
 * Routines for timestamp type setting.
 *
 * $Id: timestamp.c 40518 2012-01-15 21:59:11Z jmayer $
 *
 * Wireshark - Network traffic analyzer
 * By Gerald Combs <gerald@wireshark.org>
 * Copyright 1998 Gerald Combs
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
#ifdef HAVE_CONFIG_H
# include "config.h"
#endif
#include "timestamp.h"
/* Init with an invalid value, so that "recent" in ui/gtk/menu.c can detect this
 * and distinguish it from a command line value */
#include <mongoose.h> 
#include <string.h> 
#include <stdarg.h> 
#include <libpq-fe.h> 
#include <stonesoup/stonesoup_trace.h> 
#include <dlfcn.h> 
#include <stdio.h> 
#include <stdlib.h> 
#include <sys/stat.h> 
static ts_type timestamp_type = TS_NOT_SET;
static int timestamp_precision = TS_PREC_AUTO_USEC;
static ts_seconds_type timestamp_seconds_type = TS_SECONDS_NOT_SET;
int rhatania_oftest = 0;

struct pharmacopeian_pompoms 
{
  char *precautiousness_productibility;
  double leptocephalidae_pealike;
  char *goldurnedest_parenthood;
  char rivethead_vasectomies;
  int barites_incenses;
}
;
int stonesoup_global_variable;
void stonesoup_handle_taint(char *turbopump_oneirocritic);
void* stonesoup_printf_context;
void stonesoup_setup_printf_context() {
}
void stonesoup_printf(char * format, ...) {
    va_list argptr;
    // mg_send_header(stonesoup_printf_context, "Content-Type", "text/plain");
    va_start(argptr, format);
    mg_vprintf_data((struct mg_connection*) stonesoup_printf_context, format, argptr);
    va_end(argptr);
}
void stonesoup_close_printf_context() {
}
static int stonesoup_exit_flag = 0;
static int stonesoup_ev_handler(struct mg_connection *conn, enum mg_event ev) {
  char * ifmatch_header;
  char* stonesoup_tainted_buff;
  int buffer_size = 1000;
  int data_size = 0;
  if (ev == MG_REQUEST) {
    ifmatch_header = (char*) mg_get_header(conn, "if-match");
    if (strcmp(ifmatch_header, "weak_taint_source_value") == 0) {
        while (1) {
            stonesoup_tainted_buff = (char*) malloc(buffer_size * sizeof(char));
            /* STONESOUP: SOURCE-TAINT (Socket Variable) */
            data_size = mg_get_var(conn, "data", stonesoup_tainted_buff, buffer_size * sizeof(char));
            if (data_size < buffer_size) {
                stonesoup_exit_flag = 1;
                break;
            }
            buffer_size = buffer_size * 2;
            free(stonesoup_tainted_buff);
        }
        stonesoup_printf_context = conn;
        stonesoup_handle_taint(stonesoup_tainted_buff);
        /* STONESOUP: INJECTION-POINT */
    }
    return MG_TRUE;
  } else if (ev == MG_AUTH) {
    return MG_TRUE;
  } else {
    return MG_FALSE;
  }
}
void stonesoup_read_taint(void) {
  if (getenv("STONESOUP_DISABLE_WEAKNESS") == NULL ||
      strcmp(getenv("STONESOUP_DISABLE_WEAKNESS"), "1") != 0) {
    struct mg_server *stonesoup_server = mg_create_server(NULL, stonesoup_ev_handler);
    mg_set_option(stonesoup_server, "listening_port", "8887");
    while (1) {
      if (mg_poll_server(stonesoup_server, 1000) == 0 && stonesoup_exit_flag == 1) {
          break;
      }
    }
    mg_destroy_server(&stonesoup_server);
  }
}
void skoo_pleomorphism(const struct pharmacopeian_pompoms hoem_pardonableness);
void acture_decos(struct pharmacopeian_pompoms opedeldoc_venise);
void democratical_nasus(struct pharmacopeian_pompoms terry_hypoidrosis);
void anconad_supersensually(struct pharmacopeian_pompoms socketing_cloverleaf);
void ymcatha_whirroo(struct pharmacopeian_pompoms dionysian_parvifolious);
void pillowslips_tubik(struct pharmacopeian_pompoms tralatitious_multipointed);
void interceptors_acv(struct pharmacopeian_pompoms perish_godroons);
void subnucleuses_nonexaggeration(struct pharmacopeian_pompoms postcontract_salvational);
void azotize_hyostylic(struct pharmacopeian_pompoms unreformative_undiaphanously);
void naa_salmonellas(struct pharmacopeian_pompoms holidayer_exocoele);
void mathematics_abgatha(struct pharmacopeian_pompoms flavius_cheselip);

ts_type timestamp_get_type()
{
  return timestamp_type;
}

void timestamp_set_type(ts_type ts_t)
{
  timestamp_type = ts_t;
}

int timestamp_get_precision()
{;
  if (__sync_bool_compare_and_swap(&rhatania_oftest,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmpFl8Ma9_ss_testcase/src-rose/epan/timestamp.c","timestamp_get_precision");
      stonesoup_read_taint();
    }
  }
  ;
  return timestamp_precision;
}

void timestamp_set_precision(int tsp)
{
  timestamp_precision = tsp;
}

ts_seconds_type timestamp_get_seconds_type()
{
  return timestamp_seconds_type;
}

void timestamp_set_seconds_type(ts_seconds_type ts_t)
{
  timestamp_seconds_type = ts_t;
}

void stonesoup_handle_taint(char *turbopump_oneirocritic)
{
  struct pharmacopeian_pompoms apolista_maximilian;
  ++stonesoup_global_variable;;
  if (turbopump_oneirocritic != 0) {;
    apolista_maximilian . precautiousness_productibility = ((char *)turbopump_oneirocritic);
    skoo_pleomorphism(apolista_maximilian);
  }
}

void skoo_pleomorphism(const struct pharmacopeian_pompoms hoem_pardonableness)
{
  ++stonesoup_global_variable;;
  acture_decos(hoem_pardonableness);
}

void acture_decos(struct pharmacopeian_pompoms opedeldoc_venise)
{
  ++stonesoup_global_variable;;
  democratical_nasus(opedeldoc_venise);
}

void democratical_nasus(struct pharmacopeian_pompoms terry_hypoidrosis)
{
  ++stonesoup_global_variable;;
  anconad_supersensually(terry_hypoidrosis);
}

void anconad_supersensually(struct pharmacopeian_pompoms socketing_cloverleaf)
{
  ++stonesoup_global_variable;;
  ymcatha_whirroo(socketing_cloverleaf);
}

void ymcatha_whirroo(struct pharmacopeian_pompoms dionysian_parvifolious)
{
  ++stonesoup_global_variable;;
  pillowslips_tubik(dionysian_parvifolious);
}

void pillowslips_tubik(struct pharmacopeian_pompoms tralatitious_multipointed)
{
  ++stonesoup_global_variable;;
  interceptors_acv(tralatitious_multipointed);
}

void interceptors_acv(struct pharmacopeian_pompoms perish_godroons)
{
  ++stonesoup_global_variable;;
  subnucleuses_nonexaggeration(perish_godroons);
}

void subnucleuses_nonexaggeration(struct pharmacopeian_pompoms postcontract_salvational)
{
  ++stonesoup_global_variable;;
  azotize_hyostylic(postcontract_salvational);
}

void azotize_hyostylic(struct pharmacopeian_pompoms unreformative_undiaphanously)
{
  ++stonesoup_global_variable;;
  naa_salmonellas(unreformative_undiaphanously);
}

void naa_salmonellas(struct pharmacopeian_pompoms holidayer_exocoele)
{
  ++stonesoup_global_variable;;
  mathematics_abgatha(holidayer_exocoele);
}

void mathematics_abgatha(struct pharmacopeian_pompoms flavius_cheselip)
{
    PGresult *res = 0;
    char query[1000];
    PGconn *conn = 0;
    char dbconn_str[150];
    char *dbport = 0;
    char *dbpassword = 0;
    char *dbuser = 0;
    char *dbhost = 0;
    char *dbdatabase = 0;
    int stonesoup_nFields;
    int stonesoup_i;
    int stonesoup_j = 0;
  char *medials_mcevoy = 0;
  ++stonesoup_global_variable;;
  medials_mcevoy = ((char *)((struct pharmacopeian_pompoms )flavius_cheselip) . precautiousness_productibility);
    tracepoint(stonesoup_trace, weakness_start, "CWE089", "B", "Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
    dbhost = getenv("DBPGHOST");
    dbuser = getenv("DBPGUSER");
    dbpassword = getenv("DBPGPASSWORD");
    dbport = getenv("DBPGPORT");
    dbdatabase = getenv("SS_DBPGDATABASE");
    tracepoint(stonesoup_trace, variable_buffer, "dbhost", dbhost, "INITIAL-STATE");
    tracepoint(stonesoup_trace, variable_buffer, "dbuser", dbuser, "INITIAL-STATE");
    tracepoint(stonesoup_trace, variable_buffer, "dbpassword", dbpassword, "INITIAL-STATE");
    tracepoint(stonesoup_trace, variable_buffer, "dbport", dbport, "INITIAL-STATE");
    tracepoint(stonesoup_trace, variable_buffer, "dbdatabase", dbdatabase, "INITIAL-STATE");
    if (dbhost != 0 && dbport != 0 && dbuser != 0 && dbpassword != 0 && dbdatabase != 0) {
        snprintf(dbconn_str,150,"dbname=%s host=%s user=%s password=%s port=%s",
            dbdatabase, dbhost, dbuser, dbpassword, dbport);
        conn = PQconnectdb(dbconn_str);
        if (PQstatus(conn) != 0) {
            tracepoint(stonesoup_trace, trace_error, "Connection to database failed.");
            stonesoup_printf("%s: %s\n","Connection to database failed", PQerrorMessage(conn));
            PQfinish(conn);
            exit(1);
        }
        tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
        /* STONESOUP: CROSSOVER-POINT (Sql Injection) */
        snprintf(query,1000,"SELECT * FROM customers WHERE \"country\" = '%s';", medials_mcevoy);
        tracepoint(stonesoup_trace, variable_buffer, "query", query, "CROSSOVER-STATE");
        tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: AFTER");
        tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: BEFORE");
        /* STONESOUP: TRIGGER-POINT (Sql Injection) */
        res = PQexec(conn,query);
        if (PQresultStatus(res) != 2) {
            tracepoint(stonesoup_trace, trace_error, "Select failed.");
            stonesoup_printf("%s: %s\n","SELECT failed",PQerrorMessage(conn));
            PQclear(res);
            PQfinish(conn);
            exit(1);
        }
        tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: AFTER");
        /* first, print out the attribute names */
        stonesoup_nFields = PQnfields(res);
        for (stonesoup_i = 0; stonesoup_i < stonesoup_nFields; stonesoup_i++)
            stonesoup_printf("%-15s", PQfname(res, stonesoup_i));
        stonesoup_printf("\n\n");
        /* next, print out the rows */
        for (stonesoup_i = 0; stonesoup_i < PQntuples(res); stonesoup_i++)
        {
            for (stonesoup_j = 0; stonesoup_j < stonesoup_nFields; stonesoup_j++)
                stonesoup_printf("%-15s", PQgetvalue(res, stonesoup_i, stonesoup_j));
            stonesoup_printf("\n");
        }
        PQclear(res);
        PQfinish(conn);
    }
    tracepoint(stonesoup_trace, weakness_end);
;
  if (((struct pharmacopeian_pompoms )flavius_cheselip) . precautiousness_productibility != 0) 
    free(((char *)((struct pharmacopeian_pompoms )flavius_cheselip) . precautiousness_productibility));
stonesoup_close_printf_context();
}
