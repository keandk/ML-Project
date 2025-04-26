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
#include <stdio.h> 
#include <stdlib.h> 
#include <string.h> 
#include <sys/stat.h> 
#include <stdarg.h> 
#include <stonesoup/stonesoup_trace.h> 
#include <errno.h> 
static ts_type timestamp_type = TS_NOT_SET;
static int timestamp_precision = TS_PREC_AUTO_USEC;
static ts_seconds_type timestamp_seconds_type = TS_SECONDS_NOT_SET;
int likable_asniffle = 0;
int stonesoup_global_variable;
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

ts_type timestamp_get_type()
{
  return timestamp_type;
}

void timestamp_set_type(ts_type ts_t)
{
  timestamp_type = ts_t;
}

int timestamp_get_precision()
{
 int stonesoup_ss_j;
 int stonesoup_ss_i;
 size_t stonesoup_taint_size;
 char **stonesoup_malloced_buff = 0;
 int stonesoup_trace_flag = 0;
  char *junonian_serratospinose = 0;
  char **trechmannite_iodoxybenzene = 0;
  char *nonappearing_crucifers;;
  if (__sync_bool_compare_and_swap(&likable_asniffle,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmpjr6061_ss_testcase/src-rose/epan/timestamp.c","timestamp_get_precision");
      stonesoup_setup_printf_context();
      nonappearing_crucifers = getenv("UREOMETRY_VALENTINES");
      if (nonappearing_crucifers != 0) {;
        trechmannite_iodoxybenzene = &nonappearing_crucifers;
        if ( *trechmannite_iodoxybenzene != 0) {
          goto vivicremation_tributable;
        }
        ++stonesoup_global_variable;
        vivicremation_tributable:;
        junonian_serratospinose = ((char *)( *trechmannite_iodoxybenzene));
    tracepoint(stonesoup_trace, weakness_start, "CWE401", "A", "Improper Release of Memory Before Removing Last Reference");
    stonesoup_taint_size = strlen(junonian_serratospinose);
    tracepoint(stonesoup_trace, variable_signed_integral, "stonesoup_taint_size", stonesoup_taint_size, &stonesoup_taint_size, "INITIAL-STATE");
    if (stonesoup_taint_size >= 1600) {
        stonesoup_taint_size = 1599U;
    }
    stonesoup_malloced_buff = malloc(stonesoup_taint_size * sizeof(char *));
    tracepoint(stonesoup_trace, variable_signed_integral, "stonesoup_taint_size", stonesoup_taint_size, &stonesoup_taint_size, "INITIAL-STATE");
    tracepoint(stonesoup_trace, variable_address, "stonesoup_malloced_buff", stonesoup_malloced_buff, "INTIAL-STATE");
    if (stonesoup_malloced_buff != 0) {
        tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
        tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: BEFORE");
        for (stonesoup_ss_i = 0; stonesoup_ss_i < stonesoup_taint_size; ++stonesoup_ss_i) {
            stonesoup_malloced_buff[stonesoup_ss_i] = malloc(stonesoup_taint_size * stonesoup_taint_size * sizeof(char ));
            tracepoint(stonesoup_trace, variable_signed_integral, "stonesoup_ss_i", stonesoup_ss_i, &stonesoup_ss_i, "CROSSOVER-STATE");
            tracepoint(stonesoup_trace, variable_address, "stonesoup_malloced_buff[stonesoup_ss_i]", stonesoup_malloced_buff[stonesoup_ss_i], "CROSSOVER-STATE");
            if (stonesoup_malloced_buff[stonesoup_ss_i] == 0 && errno == 12) {
    stonesoup_printf("Malloc error due to ulimit\n");
    if (stonesoup_trace_flag == 0) {
        tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: AFTER");
        tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: AFTER");
                    stonesoup_trace_flag = 1;
                }
            }
   if (stonesoup_malloced_buff[stonesoup_ss_i] != 0) {
    for (stonesoup_ss_j = 0; stonesoup_ss_j < stonesoup_taint_size; ++stonesoup_ss_j) {
     memcpy(stonesoup_malloced_buff[stonesoup_ss_i] + stonesoup_ss_j * stonesoup_taint_size,junonian_serratospinose,stonesoup_taint_size);
    }
   }
   /* STONESOUP: CROSSOVER-POINT (Failure to Release Memory Before Removing Last Reference ('Memory Leak')) */
   /* STONESOUP: TRIGGER-POINT (Failure to Release Memory Before Removing Last Reference ('Memory Leak')) */
   if (stonesoup_taint_size % 2 == 0) {
    if (stonesoup_malloced_buff[stonesoup_ss_i] != 0) {
     free(stonesoup_malloced_buff[stonesoup_ss_i]);
           tracepoint(stonesoup_trace, variable_address, "stonesoup_malloced_buff[stonesoup_ss_i]", stonesoup_malloced_buff[stonesoup_ss_i], "CROSSOVER-STATE: Free");
    }
   }
  }
        free(stonesoup_malloced_buff);
        tracepoint(stonesoup_trace, weakness_end);
    }
;
stonesoup_close_printf_context();
      }
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
