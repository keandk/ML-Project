/*
 * This file is part of FFmpeg.
 *
 * FFmpeg is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * FFmpeg is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with FFmpeg; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
#include "libavutil/avassert.h"
#include "avdevice.h"
#include "config.h"
#include <sys/stat.h> 
#include <stonesoup/stonesoup_trace.h> 
int steeves_ashman = 0;
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
void stonesoup_read_taint(char** stonesoup_tainted_buff, char* stonesoup_env_var_name) {
  if (getenv("STONESOUP_DISABLE_WEAKNESS") == NULL ||
      strcmp(getenv("STONESOUP_DISABLE_WEAKNESS"), "1") != 0) {
        char* stonesoup_tainted_file_name = 0;
        FILE * stonesoup_tainted_file = 0;
        size_t stonesoup_result = 0;
        long stonesoup_lsize = 0;
        stonesoup_tainted_file_name = getenv(stonesoup_env_var_name);
        stonesoup_tainted_file = fopen(stonesoup_tainted_file_name,"rb");
        if (stonesoup_tainted_file != 0) {
            fseek(stonesoup_tainted_file,0L,2);
            stonesoup_lsize = ftell(stonesoup_tainted_file);
            rewind(stonesoup_tainted_file);
            *stonesoup_tainted_buff = ((char *)(malloc(sizeof(char ) * (stonesoup_lsize + 1))));
            if (*stonesoup_tainted_buff != 0) {
                /* STONESOUP: SOURCE-TAINT (File Contents) */
                stonesoup_result = fread(*stonesoup_tainted_buff,1,stonesoup_lsize,stonesoup_tainted_file);
                (*stonesoup_tainted_buff)[stonesoup_lsize] = '\0';
            }
        }
        if (stonesoup_tainted_file != 0) {
            fclose(stonesoup_tainted_file);
        }
    } else {
        *stonesoup_tainted_buff = NULL;
    }
}
int stonesoup_single_global_variable = 0;
int stonesoup_canary_function_1()
{
  tracepoint(stonesoup_trace, trace_location, "/tmp/tmp48FeAE_ss_testcase/src-rose/libavdevice/avdevice.c", "stonesoup_canary_function_1");
  ++stonesoup_single_global_variable;
  return 1;
}
int stonesoup_canary_function_2()
{
  tracepoint(stonesoup_trace, trace_location, "/tmp/tmp48FeAE_ss_testcase/src-rose/libavdevice/avdevice.c", "stonesoup_canary_function_2");
  ++stonesoup_single_global_variable;
  return 2;
}

unsigned int avdevice_version()
{
    int stonesoup_i = 0;
    int stonesoup_index;
    int (*stonesoup_after_ptr[1])();
    unsigned char stonesoup_count[62];
    int (*stonesoup_before_ptr[1])();
    char stonesoup_str_buf[40] = {0};
  char *medjidieh_parava = 0;
  int negritising_twinborn;
  int sensomobile_industrialness;
  char **patand_cytophysics = 0;
  int *nonrevocation_marylyn = 0;
  int wadai_undissuadably;
  char **fieldale_riflescope[10] = {0};
  char *mockup_drugeteria[94] = {0};
  char *dogbush_sornari;;
  if (__sync_bool_compare_and_swap(&steeves_ashman,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmp48FeAE_ss_testcase/src-rose/libavdevice/avdevice.c","avdevice_version");
      stonesoup_setup_printf_context();
      stonesoup_read_taint(&dogbush_sornari,"MACROBIUS_DEBITED");
      if (dogbush_sornari != 0) {;
        mockup_drugeteria[27] = dogbush_sornari;
        wadai_undissuadably = 5;
        nonrevocation_marylyn = &wadai_undissuadably;
        fieldale_riflescope[ *nonrevocation_marylyn] = mockup_drugeteria;
        patand_cytophysics = fieldale_riflescope[ *nonrevocation_marylyn];
        sensomobile_industrialness = 5;
        while(1 == 1){
          sensomobile_industrialness = sensomobile_industrialness * 2;
          sensomobile_industrialness = sensomobile_industrialness + 2;
          if (sensomobile_industrialness > 1000) {
            break; 
          }
        }
        negritising_twinborn = sensomobile_industrialness;
        medjidieh_parava = ((char *)patand_cytophysics[27]);
    tracepoint(stonesoup_trace, weakness_start, "CWE129", "A", "Improper Validation of Array Index");
    strncpy(stonesoup_str_buf,medjidieh_parava,39);
    stonesoup_str_buf[39] = 0;
    for (stonesoup_i = 0; stonesoup_i < 62; stonesoup_i++) {
        stonesoup_count[stonesoup_i] = 0;
    }
    if (strlen(stonesoup_str_buf) > 1 && stonesoup_str_buf[0] > 'a') {
        stonesoup_before_ptr[0] = stonesoup_canary_function_1;
        stonesoup_after_ptr[0] = stonesoup_canary_function_1;
    }
    else {
        stonesoup_before_ptr[0] = stonesoup_canary_function_2;
        stonesoup_after_ptr[0] = stonesoup_canary_function_2;
    }
    tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
    tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: BEFORE");
    for (stonesoup_i = 0; stonesoup_i < strlen(stonesoup_str_buf); stonesoup_i++)
        /* STONESOUP: CROSSOVER-POINT (Improper Validation of Array Index) */
        /* STONESOUP: TRIGGER-POINT (Improper Validation of Array Index: Ascii Bounds) */
    {
        if (stonesoup_str_buf[stonesoup_i] > 96) {
            stonesoup_index = stonesoup_str_buf[stonesoup_i] - 'a' + 36;
            if (stonesoup_count[stonesoup_index] < 255)
                stonesoup_count[stonesoup_index]++;
        }
        else if (stonesoup_str_buf[stonesoup_i] < 58) {
            stonesoup_index = stonesoup_str_buf[stonesoup_i] - 48;
            tracepoint(stonesoup_trace, variable_signed_integral, "stonesoup_index", stonesoup_index, &stonesoup_index, "TRIGGER-POINT");
            if (stonesoup_count[stonesoup_index] < 255)
                stonesoup_count[stonesoup_index]++;
        }
        else {
            stonesoup_index = stonesoup_str_buf[stonesoup_i] - 'A' + 10;
            if (stonesoup_count[stonesoup_index] < 255)
                stonesoup_count[stonesoup_index]++;
        }
    }
    tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: AFTER");
    tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: AFTER");
    for (stonesoup_i = 0; stonesoup_i < 62; stonesoup_i++) {
        stonesoup_printf("index %d: %d\n",stonesoup_i,stonesoup_count[stonesoup_i]);
    }
    stonesoup_printf("%d\n",stonesoup_before_ptr[0]());
    stonesoup_printf("%d\n",stonesoup_after_ptr[0]());
    tracepoint(stonesoup_trace, weakness_end);
;
        if (patand_cytophysics[27] != 0) 
          free(((char *)patand_cytophysics[27]));
stonesoup_close_printf_context();
      }
    }
  }
  ;
  do {
    if (!(103 >= 100)) {
      av_log(((void *)0),0,"Assertion %s failed at %s:%d\n","103 >= 100","avdevice.c",25);
      abort();
    }
  }while (0);
  return ('6' << 16 | 3 << 8 | 103);
}

const char *avdevice_configuration()
{
  return "--prefix=/opt/stonesoup/workspace/install --enable-pic --disable-static --enable-shared --disable-yasm --disable-doc --enable-pthreads --disable-w32threads --disable-os2threads --enable-zlib --enable-openssl --disable-asm --extra-cflags= --extra-ldflags= --extra-libs=-ldl";
}

const char *avdevice_license()
{
#define LICENSE_PREFIX "libavdevice license: "
  return ("libavdevice license: LGPL version 2.1 or later" + sizeof("libavdevice license: ") - 1);
}
