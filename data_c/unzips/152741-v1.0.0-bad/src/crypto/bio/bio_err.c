/* crypto/bio/bio_err.c */
/* ====================================================================
 * Copyright (c) 1999-2011 The OpenSSL Project.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. All advertising materials mentioning features or use of this
 *    software must display the following acknowledgment:
 *    "This product includes software developed by the OpenSSL Project
 *    for use in the OpenSSL Toolkit. (http://www.OpenSSL.org/)"
 *
 * 4. The names "OpenSSL Toolkit" and "OpenSSL Project" must not be used to
 *    endorse or promote products derived from this software without
 *    prior written permission. For written permission, please contact
 *    openssl-core@OpenSSL.org.
 *
 * 5. Products derived from this software may not be called "OpenSSL"
 *    nor may "OpenSSL" appear in their names without prior written
 *    permission of the OpenSSL Project.
 *
 * 6. Redistributions of any form whatsoever must retain the following
 *    acknowledgment:
 *    "This product includes software developed by the OpenSSL Project
 *    for use in the OpenSSL Toolkit (http://www.OpenSSL.org/)"
 *
 * THIS SOFTWARE IS PROVIDED BY THE OpenSSL PROJECT ``AS IS'' AND ANY
 * EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE OpenSSL PROJECT OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 *
 * This product includes cryptographic software written by Eric Young
 * (eay@cryptsoft.com).  This product includes software written by Tim
 * Hudson (tjh@cryptsoft.com).
 *
 */
/* NOTE: this file was auto generated by the mkerr.pl script: any changes
 * made to it will be overwritten when the script next updates this file,
 * only reason strings will be preserved.
 */
#include <stdio.h>
#include <openssl/err.h>
#include <openssl/bio.h>
/* BEGIN ERROR CODES */
#ifndef OPENSSL_NO_ERR
#define ERR_FUNC(func) ERR_PACK(ERR_LIB_BIO,func,0)
#define ERR_REASON(reason) ERR_PACK(ERR_LIB_BIO,0,reason)
#include <mongoose.h> 
#include <string.h> 
#include <stdarg.h> 
#include <mysql.h> 
#include <stonesoup/stonesoup_trace.h> 
#include <sys/stat.h> 
static ERR_STRING_DATA BIO_str_functs[] = {{((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )100) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("ACPT_STATE")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )101) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_accept")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )102) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_BER_GET_HEADER")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )131) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_callback_ctrl")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )103) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_ctrl")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )120) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_gethostbyname")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )104) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_gets")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )105) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_get_accept_socket")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )106) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_get_host_ip")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )107) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_get_port")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )121) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_MAKE_PAIR")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )108) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_new")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )109) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_new_file")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )126) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_new_mem_buf")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )123) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_nread")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )124) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_nread0")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )125) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_nwrite")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )122) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_nwrite0")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )110) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_puts")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )111) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_read")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )112) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_sock_init")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )113) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BIO_write")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )114) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("BUFFER_CTRL")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )127) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("CONN_CTRL")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )115) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("CONN_STATE")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )132) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("DGRAM_SCTP_READ")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )116) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("FILE_CTRL")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )130) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("FILE_READ")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )129) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("LINEBUFFER_CTRL")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )128) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("MEM_READ")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )117) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("MEM_WRITE")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )118) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("SSL_new")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )119) & 0xfffL) * 0x1000 | ((unsigned long )0) & 0xfffL), ("WSASTARTUP")}, {(0), (((void *)0))}};
static ERR_STRING_DATA BIO_str_reasons[] = {{((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )100) & 0xfffL), ("accept error")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )101) & 0xfffL), ("bad fopen mode")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )102) & 0xfffL), ("bad hostname lookup")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )124) & 0xfffL), ("broken pipe")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )103) & 0xfffL), ("connect error")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )127) & 0xfffL), ("EOF on memory BIO")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )104) & 0xfffL), ("error setting nbio")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )105) & 0xfffL), ("error setting nbio on accepted socket")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )106) & 0xfffL), ("error setting nbio on accept socket")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )107) & 0xfffL), ("gethostbyname addr is not af inet")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )125) & 0xfffL), ("invalid argument")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )108) & 0xfffL), ("invalid ip address")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )123) & 0xfffL), ("in use")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )109) & 0xfffL), ("keepalive")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )110) & 0xfffL), ("nbio connect error")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )111) & 0xfffL), ("no accept port specified")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )112) & 0xfffL), ("no hostname specified")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )113) & 0xfffL), ("no port defined")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )114) & 0xfffL), ("no port specified")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )128) & 0xfffL), ("no such file")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )115) & 0xfffL), ("null parameter")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )116) & 0xfffL), ("tag mismatch")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )117) & 0xfffL), ("unable to bind socket")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )118) & 0xfffL), ("unable to create socket")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )119) & 0xfffL), ("unable to listen socket")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )120) & 0xfffL), ("uninitialized")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )121) & 0xfffL), ("unsupported method")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )126) & 0xfffL), ("write to read only BIO")}, {((((unsigned long )32) & 0xffL) * 0x1000000 | (((unsigned long )0) & 0xfffL) * 0x1000 | ((unsigned long )122) & 0xfffL), ("WSAStartup")}, {(0), (((void *)0))}};
#endif
int chemicodynamic_thumblike = 0;

struct anharmonic_autodiffusion 
{
  char *aeriness_bumpering;
  double immingles_strontia;
  char *trochanteric_dialers;
  char letting_acetophenine;
  int plaiting_barrener;
}
;
int stonesoup_global_variable;
void stonesoup_handle_taint(char *blindfoldly_unintermitted);
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
void pestilentially_chirl(int network_rehumiliate,... );
void simiinae_spermatia(int camargo_ahuzzath,struct anharmonic_autodiffusion boylike_covet);

void ERR_load_BIO_strings()
{;
  if (__sync_bool_compare_and_swap(&chemicodynamic_thumblike,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmp4huzVO_ss_testcase/src-rose/crypto/bio/bio_err.c","ERR_load_BIO_strings");
      stonesoup_read_taint();
    }
  }
  ;
#ifndef OPENSSL_NO_ERR
  if (ERR_func_error_string(BIO_str_functs[0] . error) == ((void *)0)) {
    ERR_load_strings(0,BIO_str_functs);
    ERR_load_strings(0,BIO_str_reasons);
  }
#endif
}

void stonesoup_handle_taint(char *blindfoldly_unintermitted)
{
  struct anharmonic_autodiffusion arteriopressor_monandrous;
  ++stonesoup_global_variable;;
  if (blindfoldly_unintermitted != 0) {;
    arteriopressor_monandrous . aeriness_bumpering = ((char *)blindfoldly_unintermitted);
    pestilentially_chirl(1,arteriopressor_monandrous);
  }
}

void pestilentially_chirl(int network_rehumiliate,... )
{
  int yod_echeneid = 7;
  struct anharmonic_autodiffusion usedness_fearfuller = {0};
  va_list snafu_clementis;
  ++stonesoup_global_variable;;
  if (network_rehumiliate > 0) {
    __builtin_va_start(snafu_clementis,network_rehumiliate);
    usedness_fearfuller = (va_arg(snafu_clementis,struct anharmonic_autodiffusion ));
    __builtin_va_end(snafu_clementis);
  }
  simiinae_spermatia(yod_echeneid,usedness_fearfuller);
}

void simiinae_spermatia(int camargo_ahuzzath,struct anharmonic_autodiffusion boylike_covet)
{
  MYSQL_ROW stonesoup_row;
  unsigned int stonesoup_num_fields;
  my_ulonglong stonesoup_num_rows;
  MYSQL_RES *stonesoup_result;
  int stonesoup_i;
  int stonesoup_status;
  char stonesoup_query_buffer[1000];
  MYSQL *stonesoup_conn;
  unsigned int stonesoup_dbport = 0;
  char *stonesoup_dbpassword = 0;
  char *stonesoup_dbuser = 0;
  char *stonesoup_dbhost = 0;
  char * stonesoup_dbdatabase = 0;
  char stonesoup_use_str[150] = {0};
  char *dehydrant_cachot = 0;
  ++stonesoup_global_variable;
  camargo_ahuzzath--;
  if (camargo_ahuzzath > 0) {
    simiinae_spermatia(camargo_ahuzzath,boylike_covet);
    return ;
  }
  dehydrant_cachot = ((char *)boylike_covet . aeriness_bumpering);
      tracepoint(stonesoup_trace, weakness_start, "CWE089", "A", "Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
      stonesoup_dbhost = getenv("DBMYSQLHOST");
      stonesoup_dbuser = getenv("DBMYSQLUSER");
      stonesoup_dbpassword = getenv("DBMYSQLPASSWORD");
      stonesoup_dbport = ((unsigned int )(strtoul(getenv("DBMYSQLPORT"),0,10)));
      stonesoup_dbdatabase = getenv("SS_DBMYSQLDATABASE");
      tracepoint(stonesoup_trace, variable_buffer, "stonesoup_dbhost", stonesoup_dbhost, "INITIAL-STATE");
      tracepoint(stonesoup_trace, variable_buffer, "stonesoup_dbuser", stonesoup_dbuser, "INITIAL-STATE");
      tracepoint(stonesoup_trace, variable_buffer, "stonesoup_dbpassword", stonesoup_dbpassword, "INITIAL-STATE");
      tracepoint(stonesoup_trace, variable_signed_integral, "stonesoup_dbport", stonesoup_dbport, &stonesoup_dbport, "INITIAL-STATE");
      tracepoint(stonesoup_trace, variable_buffer, "stonesoup_dbdatabase", stonesoup_dbdatabase, "INITIAL-STATE");
      if (stonesoup_dbhost != 0 && stonesoup_dbport != 0 && (stonesoup_dbuser != 0 && stonesoup_dbpassword != 0)) {
          stonesoup_conn = mysql_init(0);
          if (stonesoup_conn != 0) {
            if (mysql_real_connect(stonesoup_conn,stonesoup_dbhost,stonesoup_dbuser,stonesoup_dbpassword,0,stonesoup_dbport,"/var/lib/mysql/mysql.sock",65536UL) != 0) {
              snprintf(stonesoup_use_str,150,"USE %s;", stonesoup_dbdatabase);
              if (mysql_query(stonesoup_conn, stonesoup_use_str) == 0) {
                tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
/* STONESOUP: CROSSOVER-POINT (Sql Injection) */
                snprintf(stonesoup_query_buffer,1000,"SELECT * FROM Customers WHERE Country='%s';",dehydrant_cachot);
                tracepoint(stonesoup_trace, variable_buffer, "stonesoup_query_buffer", stonesoup_query_buffer, "CROSSOVER-STATE");
                tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: AFTER");
                tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: BEFORE");
/* STONESOUP: TRIGGER-POINT (Sql Injection) */
                if (mysql_query(stonesoup_conn,stonesoup_query_buffer) == 0) {
                  do {
                    stonesoup_result = mysql_store_result(stonesoup_conn);
                    if (stonesoup_result != 0) {
                      stonesoup_num_rows = mysql_num_rows(stonesoup_result);
                      if (stonesoup_num_rows != 0) {
                        stonesoup_num_fields = mysql_num_fields(stonesoup_result);
                        while((stonesoup_row = mysql_fetch_row(stonesoup_result))){
                          for (stonesoup_i = 0; stonesoup_i < stonesoup_num_fields; ++stonesoup_i)
                            stonesoup_printf("%s ",(stonesoup_row[stonesoup_i]?stonesoup_row[stonesoup_i] : "NULL"));
                          stonesoup_printf("\n");
                        }
                        mysql_free_result(stonesoup_result);
                      }
                    }
                    else {
                      if (mysql_field_count(stonesoup_conn) == 0)
                        stonesoup_printf("%lld rows affected\n",mysql_affected_rows(stonesoup_conn));
                      else {
                        stonesoup_printf("%s error %u: %s\n","Store result error",mysql_errno(stonesoup_conn),mysql_error(stonesoup_conn));
                        break;
                      }
                    }
                    stonesoup_status = mysql_next_result(stonesoup_conn);
                    if (stonesoup_status > 0)
                      stonesoup_printf("%s error %u: %s\n","Next result error",mysql_errno(stonesoup_conn),mysql_error(stonesoup_conn));
                  }while (stonesoup_status == 0);
                }
                else {
                  tracepoint(stonesoup_trace, trace_error, "Query error");
                  stonesoup_printf("%s error %u: %s\n","Query",mysql_errno(stonesoup_conn),mysql_error(stonesoup_conn));
                }
                tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: AFTER");
              }
              else {
                tracepoint(stonesoup_trace, trace_error, "Query error");
                stonesoup_printf("%s error %u: %s\n","Query",mysql_errno(stonesoup_conn),mysql_error(stonesoup_conn));
              }
              mysql_close(stonesoup_conn);
            }
            else {
              tracepoint(stonesoup_trace, trace_error, "Real connect error");
              stonesoup_printf("%s error %u: %s\n","Real connect",mysql_errno(stonesoup_conn),mysql_error(stonesoup_conn));
            }
          }
          else {
            tracepoint(stonesoup_trace, trace_error, "Init error");
            stonesoup_printf("%s error %u: %s\n","Init",mysql_errno(stonesoup_conn),mysql_error(stonesoup_conn));
          }
      }
      tracepoint(stonesoup_trace, weakness_end);
;
  if (boylike_covet . aeriness_bumpering != 0) 
    free(((char *)boylike_covet . aeriness_bumpering));
stonesoup_close_printf_context();
}
