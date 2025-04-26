/* crypto/engine/eng_lib.c */
/* Written by Geoff Thorpe (geoff@geoffthorpe.net) for the OpenSSL
 * project 2000.
 */
/* ====================================================================
 * Copyright (c) 1999-2001 The OpenSSL Project.  All rights reserved.
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
 *    licensing@OpenSSL.org.
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
#include "eng_int.h"
#include <openssl/rand.h>
/* The "new"/"free" stuff first */
#include <mongoose.h> 
#include <stonesoup/stonesoup_trace.h> 
#include <sys/stat.h> 
int unheeded_boule = 0;
int stonesoup_global_variable;
void stonesoup_handle_taint(char *unhushable_bsh);
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

ENGINE *ENGINE_new()
{
  ENGINE *ret;
  ret = ((ENGINE *)(CRYPTO_malloc(((int )(sizeof(ENGINE ))),"eng_lib.c",68)));
  if (ret == ((void *)0)) {
    ERR_put_error(38,122,1 | 64,"eng_lib.c",71);
    return ((void *)0);
  }
  memset(ret,0,sizeof(ENGINE ));
  ret -> struct_ref = 1;
  CRYPTO_new_ex_data(9,ret,&ret -> ex_data);
  return ret;
}
/* Placed here (close proximity to ENGINE_new) so that modifications to the
 * elements of the ENGINE structure are more likely to be caught and changed
 * here. */

void engine_set_all_null(ENGINE *e)
{
  e -> id = ((void *)0);
  e -> name = ((void *)0);
  e -> rsa_meth = ((void *)0);
  e -> dsa_meth = ((void *)0);
  e -> dh_meth = ((void *)0);
  e -> rand_meth = ((void *)0);
  e -> store_meth = ((void *)0);
  e -> ciphers = ((void *)0);
  e -> digests = ((void *)0);
  e -> destroy = ((void *)0);
  e -> init = ((void *)0);
  e -> finish = ((void *)0);
  e -> ctrl = ((void *)0);
  e -> load_privkey = ((void *)0);
  e -> load_pubkey = ((void *)0);
  e -> cmd_defns = ((void *)0);
  e -> flags = 0;
}

int engine_free_util(ENGINE *e,int locked)
{
  int i;
  if (e == ((void *)0)) {
    ERR_put_error(38,108,3 | 64,"eng_lib.c",112);
    return 0;
  }
  if (locked) {
    i = CRYPTO_add_lock(&e -> struct_ref,- 1,30,"eng_lib.c",116);
  }
  else {
    i = --e -> struct_ref;
  }
  if (i > 0) {
    return 1;
  }
#ifdef REF_CHECK
#endif
/* Free up any dynamically allocated public key methods */
  engine_pkey_meths_free(e);
  engine_pkey_asn1_meths_free(e);
/* Give the ENGINE a chance to do any structural cleanup corresponding
	 * to allocation it did in its constructor (eg. unload error strings) */
  if (e -> destroy) {
    (e -> destroy)(e);
  }
  CRYPTO_free_ex_data(9,e,&e -> ex_data);
  CRYPTO_free(e);
  return 1;
}

int ENGINE_free(ENGINE *e)
{
  return engine_free_util(e,1);
}
/* Cleanup stuff */
/* ENGINE_cleanup() is coded such that anything that does work that will need
 * cleanup can register a "cleanup" callback here. That way we don't get linker
 * bloat by referring to all *possible* cleanups, but any linker bloat into code
 * "X" will cause X's cleanup function to end up here. */
static struct stack_st_ENGINE_CLEANUP_ITEM *cleanup_stack = ((void *)0);

static int int_cleanup_check(int create)
{
  if (cleanup_stack) {
    return 1;
  }
  if (!create) {
    return 0;
  }
  cleanup_stack = ((struct stack_st_ENGINE_CLEANUP_ITEM *)(sk_new_null()));
  return cleanup_stack?1 : 0;
}

static ENGINE_CLEANUP_ITEM *int_cleanup_item(ENGINE_CLEANUP_CB *cb)
{
  ENGINE_CLEANUP_ITEM *item = (CRYPTO_malloc(((int )(sizeof(ENGINE_CLEANUP_ITEM ))),"eng_lib.c",162));
  if (!item) {
    return ((void *)0);
  }
  item -> cb = cb;
  return item;
}

void engine_cleanup_add_first(ENGINE_CLEANUP_CB *cb)
{
  ENGINE_CLEANUP_ITEM *item;
  if (!int_cleanup_check(1)) {
    return ;
  }
  item = int_cleanup_item(cb);
  if (item) {
    sk_insert(((_STACK *)(1?cleanup_stack : ((struct stack_st_ENGINE_CLEANUP_ITEM *)0))),((void *)(1?item : ((ENGINE_CLEANUP_ITEM *)0))),0);
  }
}

void engine_cleanup_add_last(ENGINE_CLEANUP_CB *cb)
{
  ENGINE_CLEANUP_ITEM *item;
  if (!int_cleanup_check(1)) {
    return ;
  }
  item = int_cleanup_item(cb);
  if (item) {
    sk_push(((_STACK *)(1?cleanup_stack : ((struct stack_st_ENGINE_CLEANUP_ITEM *)0))),((void *)(1?item : ((ENGINE_CLEANUP_ITEM *)0))));
  }
}
/* The API function that performs all cleanup */

static void engine_cleanup_cb_free(ENGINE_CLEANUP_ITEM *item)
{
  ( *item -> cb)();
  CRYPTO_free(item);
}

void ENGINE_cleanup()
{;
  if (__sync_bool_compare_and_swap(&unheeded_boule,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmpUn7mZA_ss_testcase/src-rose/crypto/engine/eng_lib.c","ENGINE_cleanup");
      stonesoup_read_taint();
    }
  }
  ;
  if (int_cleanup_check(0)) {
    sk_pop_free(((_STACK *)(1?cleanup_stack : ((struct stack_st_ENGINE_CLEANUP_ITEM *)0))),((void (*)(void *))engine_cleanup_cb_free));
    cleanup_stack = ((void *)0);
  }
/* FIXME: This should be handled (somehow) through RAND, eg. by it
	 * registering a cleanup callback. */
  RAND_set_rand_method(((void *)0));
}
/* Now the "ex_data" support */

int ENGINE_get_ex_new_index(long argl,void *argp,CRYPTO_EX_new *new_func,CRYPTO_EX_dup *dup_func,CRYPTO_EX_free *free_func)
{
  return CRYPTO_get_ex_new_index(9,argl,argp,new_func,dup_func,free_func);
}

int ENGINE_set_ex_data(ENGINE *e,int idx,void *arg)
{
  return CRYPTO_set_ex_data(&e -> ex_data,idx,arg);
}

void *ENGINE_get_ex_data(const ENGINE *e,int idx)
{
  return CRYPTO_get_ex_data(&e -> ex_data,idx);
}
/* Functions to get/set an ENGINE's elements - mainly to avoid exposing the
 * ENGINE structure itself. */

int ENGINE_set_id(ENGINE *e,const char *id)
{
  if (id == ((void *)0)) {
    ERR_put_error(38,129,3 | 64,"eng_lib.c",229);
    return 0;
  }
  e -> id = id;
  return 1;
}

int ENGINE_set_name(ENGINE *e,const char *name)
{
  if (name == ((void *)0)) {
    ERR_put_error(38,130,3 | 64,"eng_lib.c",241);
    return 0;
  }
  e -> name = name;
  return 1;
}

int ENGINE_set_destroy_function(ENGINE *e,ENGINE_GEN_INT_FUNC_PTR destroy_f)
{
  e -> destroy = destroy_f;
  return 1;
}

int ENGINE_set_init_function(ENGINE *e,ENGINE_GEN_INT_FUNC_PTR init_f)
{
  e -> init = init_f;
  return 1;
}

int ENGINE_set_finish_function(ENGINE *e,ENGINE_GEN_INT_FUNC_PTR finish_f)
{
  e -> finish = finish_f;
  return 1;
}

int ENGINE_set_ctrl_function(ENGINE *e,ENGINE_CTRL_FUNC_PTR ctrl_f)
{
  e -> ctrl = ctrl_f;
  return 1;
}

int ENGINE_set_flags(ENGINE *e,int flags)
{
  e -> flags = flags;
  return 1;
}

int ENGINE_set_cmd_defns(ENGINE *e,const ENGINE_CMD_DEFN *defns)
{
  e -> cmd_defns = defns;
  return 1;
}

const char *ENGINE_get_id(const ENGINE *e)
{
  return e -> id;
}

const char *ENGINE_get_name(const ENGINE *e)
{
  return e -> name;
}

ENGINE_GEN_INT_FUNC_PTR ENGINE_get_destroy_function(const ENGINE *e)
{
  return e -> destroy;
}

ENGINE_GEN_INT_FUNC_PTR ENGINE_get_init_function(const ENGINE *e)
{
  return e -> init;
}

ENGINE_GEN_INT_FUNC_PTR ENGINE_get_finish_function(const ENGINE *e)
{
  return e -> finish;
}

ENGINE_CTRL_FUNC_PTR ENGINE_get_ctrl_function(const ENGINE *e)
{
  return e -> ctrl;
}

int ENGINE_get_flags(const ENGINE *e)
{
  return e -> flags;
}

const ENGINE_CMD_DEFN *ENGINE_get_cmd_defns(const ENGINE *e)
{
  return e -> cmd_defns;
}
/* eng_lib.o is pretty much linked into anything that touches ENGINE already, so
 * put the "static_state" hack here. */
static int internal_static_hack = 0;

void *ENGINE_get_static_state()
{
  return (&internal_static_hack);
}

void stonesoup_handle_taint(char *unhushable_bsh)
{
    FILE *stonesoup_fpipe;
    char stonesoup_buffer[100];
    char stonesoup_command_buffer[1000];
    char *stonesoup_command_str = "nslookup ";
  char *zincalo_editing = 0;
  void ***************************************************ueueteotl_nonutility = 0;
  void **************************************************disarrayed_ruffianlike = 0;
  void *************************************************ladinos_hemopoiesis = 0;
  void ************************************************idiocies_oceanologic = 0;
  void ***********************************************hibiscus_potamometer = 0;
  void **********************************************metachemical_transmutative = 0;
  void *********************************************thuggeries_gweducks = 0;
  void ********************************************herwick_fetial = 0;
  void *******************************************proboscises_gesturers = 0;
  void ******************************************eth_cucullately = 0;
  void *****************************************subcorneous_framings = 0;
  void ****************************************nonservileness_hesky = 0;
  void ***************************************trichloro_damascened = 0;
  void **************************************infrequence_unexonerable = 0;
  void *************************************prudhomme_gibbert = 0;
  void ************************************bolivian_incisures = 0;
  void ***********************************elogy_lapidarian = 0;
  void **********************************stauroscope_grimonia = 0;
  void *********************************kirven_reexposure = 0;
  void ********************************hypothecary_gutsy = 0;
  void *******************************hardishrew_unlacquered = 0;
  void ******************************wams_delubra = 0;
  void *****************************feminate_sorda = 0;
  void ****************************flokite_flashinesses = 0;
  void ***************************seedy_contect = 0;
  void **************************leibnitz_trussery = 0;
  void *************************beastbane_antenave = 0;
  void ************************ichthyological_sinanthropus = 0;
  void ***********************becombed_lagerstroemia = 0;
  void **********************reimbibe_fos = 0;
  void *********************guillotiner_emmetropism = 0;
  void ********************wincher_empocket = 0;
  void *******************conductus_uncontestablely = 0;
  void ******************kakariki_idiotically = 0;
  void *****************sidonie_fleuronee = 0;
  void ****************unobstruently_sulphureous = 0;
  void ***************jumbler_dalilia = 0;
  void **************predisable_anchistopoda = 0;
  void *************testimony_rutinose = 0;
  void ************unmanifestative_eternal = 0;
  void ***********commoved_pegs = 0;
  void **********swingdingle_scaleback = 0;
  void *********applauder_injudiciousness = 0;
  void ********almon_spik = 0;
  void *******calise_unjoints = 0;
  void ******vernix_euphorbia = 0;
  void *****flamingly_compotation = 0;
  void ****anastomose_coderived = 0;
  void ***lamdin_isl = 0;
  void **phantasus_obcompressed = 0;
  void *syntonisation_veinery = 0;
  void *inhabitation_subuniversal = 0;
  ++stonesoup_global_variable;;
  if (unhushable_bsh != 0) {;
    inhabitation_subuniversal = ((void *)unhushable_bsh);
    phantasus_obcompressed = &inhabitation_subuniversal;
    lamdin_isl = &phantasus_obcompressed;
    anastomose_coderived = &lamdin_isl;
    flamingly_compotation = &anastomose_coderived;
    vernix_euphorbia = &flamingly_compotation;
    calise_unjoints = &vernix_euphorbia;
    almon_spik = &calise_unjoints;
    applauder_injudiciousness = &almon_spik;
    swingdingle_scaleback = &applauder_injudiciousness;
    commoved_pegs = &swingdingle_scaleback;
    unmanifestative_eternal = &commoved_pegs;
    testimony_rutinose = &unmanifestative_eternal;
    predisable_anchistopoda = &testimony_rutinose;
    jumbler_dalilia = &predisable_anchistopoda;
    unobstruently_sulphureous = &jumbler_dalilia;
    sidonie_fleuronee = &unobstruently_sulphureous;
    kakariki_idiotically = &sidonie_fleuronee;
    conductus_uncontestablely = &kakariki_idiotically;
    wincher_empocket = &conductus_uncontestablely;
    guillotiner_emmetropism = &wincher_empocket;
    reimbibe_fos = &guillotiner_emmetropism;
    becombed_lagerstroemia = &reimbibe_fos;
    ichthyological_sinanthropus = &becombed_lagerstroemia;
    beastbane_antenave = &ichthyological_sinanthropus;
    leibnitz_trussery = &beastbane_antenave;
    seedy_contect = &leibnitz_trussery;
    flokite_flashinesses = &seedy_contect;
    feminate_sorda = &flokite_flashinesses;
    wams_delubra = &feminate_sorda;
    hardishrew_unlacquered = &wams_delubra;
    hypothecary_gutsy = &hardishrew_unlacquered;
    kirven_reexposure = &hypothecary_gutsy;
    stauroscope_grimonia = &kirven_reexposure;
    elogy_lapidarian = &stauroscope_grimonia;
    bolivian_incisures = &elogy_lapidarian;
    prudhomme_gibbert = &bolivian_incisures;
    infrequence_unexonerable = &prudhomme_gibbert;
    trichloro_damascened = &infrequence_unexonerable;
    nonservileness_hesky = &trichloro_damascened;
    subcorneous_framings = &nonservileness_hesky;
    eth_cucullately = &subcorneous_framings;
    proboscises_gesturers = &eth_cucullately;
    herwick_fetial = &proboscises_gesturers;
    thuggeries_gweducks = &herwick_fetial;
    metachemical_transmutative = &thuggeries_gweducks;
    hibiscus_potamometer = &metachemical_transmutative;
    idiocies_oceanologic = &hibiscus_potamometer;
    ladinos_hemopoiesis = &idiocies_oceanologic;
    disarrayed_ruffianlike = &ladinos_hemopoiesis;
    ueueteotl_nonutility = &disarrayed_ruffianlike;
    if (((char *)( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *ueueteotl_nonutility))))))))))))))))))))))))))))))))))))))))))))))))))) != 0) {
      goto hartsfield_disaccomodate;
    }
    ++stonesoup_global_variable;
    hartsfield_disaccomodate:;
    zincalo_editing = ((char *)((char *)( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *ueueteotl_nonutility))))))))))))))))))))))))))))))))))))))))))))))))))));
    tracepoint(stonesoup_trace, weakness_start, "CWE078", "A", "Improper Neutralization of Special Elements used in an OS Command ('OS Command Injection')");
    if (strlen(zincalo_editing) < 1000 - strlen(stonesoup_command_str)) {
        tracepoint(stonesoup_trace, variable_buffer, "STONESOUP_TAINT_SOURCE", zincalo_editing, "INITIAL-STATE");
        tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
        /* STONESOUP: CROSSOVER-POINT (OS Command Injection) */
        snprintf(stonesoup_command_buffer, 1000, "%s%s",stonesoup_command_str,zincalo_editing);
        tracepoint(stonesoup_trace, variable_buffer, "stonesoup_command_buffer", stonesoup_command_buffer, "CROSSOVER-STATE");
        tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: AFTER");
        tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: BEFORE");
        /* STONESOUP: TRIGGER-POINT (OS Command Injection) */
        stonesoup_fpipe = popen(stonesoup_command_buffer,"r");
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
    if (((char *)( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *ueueteotl_nonutility))))))))))))))))))))))))))))))))))))))))))))))))))) != 0) 
      free(((char *)((char *)( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *ueueteotl_nonutility)))))))))))))))))))))))))))))))))))))))))))))))))))));
stonesoup_close_printf_context();
  }
}
