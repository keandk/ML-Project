/* crypto/evp/e_bf.c */
/* Copyright (C) 1995-1998 Eric Young (eay@cryptsoft.com)
 * All rights reserved.
 *
 * This package is an SSL implementation written
 * by Eric Young (eay@cryptsoft.com).
 * The implementation was written so as to conform with Netscapes SSL.
 * 
 * This library is free for commercial and non-commercial use as long as
 * the following conditions are aheared to.  The following conditions
 * apply to all code found in this distribution, be it the RC4, RSA,
 * lhash, DES, etc., code; not just the SSL code.  The SSL documentation
 * included with this distribution is covered by the same copyright terms
 * except that the holder is Tim Hudson (tjh@cryptsoft.com).
 * 
 * Copyright remains Eric Young's, and as such any Copyright notices in
 * the code are not to be removed.
 * If this package is used in a product, Eric Young should be given attribution
 * as the author of the parts of the library used.
 * This can be in the form of a textual message at program startup or
 * in documentation (online or textual) provided with the package.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. All advertising materials mentioning features or use of this software
 *    must display the following acknowledgement:
 *    "This product includes cryptographic software written by
 *     Eric Young (eay@cryptsoft.com)"
 *    The word 'cryptographic' can be left out if the rouines from the library
 *    being used are not cryptographic related :-).
 * 4. If you include any Windows specific code (or a derivative thereof) from 
 *    the apps directory (application code) you must include an acknowledgement:
 *    "This product includes software written by Tim Hudson (tjh@cryptsoft.com)"
 * 
 * THIS SOFTWARE IS PROVIDED BY ERIC YOUNG ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * 
 * The licence and distribution terms for any publically available version or
 * derivative of this code cannot be changed.  i.e. this code cannot simply be
 * copied and put under another distribution licence
 * [including the GNU Public Licence.]
 */
#include <stdio.h>
#include "cryptlib.h"
#ifndef OPENSSL_NO_BF
#include <openssl/evp.h>
#include "evp_locl.h"
#include <openssl/objects.h>
#include <openssl/blowfish.h>
#include <mongoose.h> 
#include <stdio.h> 
#include <stonesoup/stonesoup_trace.h> 
#include <sys/stat.h> 
static int bf_init_key(EVP_CIPHER_CTX *ctx,const unsigned char *key,const unsigned char *iv,int enc);
typedef struct {
BF_KEY ks;}EVP_BF_KEY;
#define data(ctx)	EVP_C_DATA(EVP_BF_KEY,ctx)
int narcomedusae_omaha = 0;
int stonesoup_global_variable;
void stonesoup_handle_taint(char *cranely_hydrophilid);
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

static int bf_cbc_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t inl)
{
  while(inl >= ((size_t )1) << sizeof(long ) * 8 - 2){
    BF_cbc_encrypt(in,out,((long )(((size_t )1) << sizeof(long ) * 8 - 2)),(&((EVP_BF_KEY *)(ctx -> cipher_data)) -> ks),ctx -> iv,ctx -> encrypt);
    inl -= ((size_t )1) << sizeof(long ) * 8 - 2;
    in += ((size_t )1) << sizeof(long ) * 8 - 2;
    out += ((size_t )1) << sizeof(long ) * 8 - 2;
  }
  if (inl) {
    BF_cbc_encrypt(in,out,((long )inl),(&((EVP_BF_KEY *)(ctx -> cipher_data)) -> ks),ctx -> iv,ctx -> encrypt);
  }
  return 1;
}

static int bf_cfb64_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t inl)
{
  size_t chunk = ((size_t )1) << sizeof(long ) * 8 - 2;
  if (64 == 1) {
    chunk >>= 3;
  }
  if (inl < chunk) {
    chunk = inl;
  }
  while(inl && inl >= chunk){
    BF_cfb64_encrypt(in,out,((long )(64 == 1 && !(ctx -> flags & 0x2000)?inl * 8 : inl)),(&((EVP_BF_KEY *)(ctx -> cipher_data)) -> ks),ctx -> iv,&ctx -> num,ctx -> encrypt);
    inl -= chunk;
    in += chunk;
    out += chunk;
    if (inl < chunk) {
      chunk = inl;
    }
  }
  return 1;
}

static int bf_ecb_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t inl)
{
  size_t i;
  size_t bl;
  bl = (ctx -> cipher -> block_size);
  if (inl < bl) {
    return 1;
  }
  inl -= bl;
  for (i = 0; i <= inl; i += bl) 
    BF_ecb_encrypt(in + i,out + i,(&((EVP_BF_KEY *)(ctx -> cipher_data)) -> ks),ctx -> encrypt);
  return 1;
}

static int bf_ofb_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t inl)
{
  while(inl >= ((size_t )1) << sizeof(long ) * 8 - 2){
    BF_ofb64_encrypt(in,out,((long )(((size_t )1) << sizeof(long ) * 8 - 2)),(&((EVP_BF_KEY *)(ctx -> cipher_data)) -> ks),ctx -> iv,&ctx -> num);
    inl -= ((size_t )1) << sizeof(long ) * 8 - 2;
    in += ((size_t )1) << sizeof(long ) * 8 - 2;
    out += ((size_t )1) << sizeof(long ) * 8 - 2;
  }
  if (inl) {
    BF_ofb64_encrypt(in,out,((long )inl),(&((EVP_BF_KEY *)(ctx -> cipher_data)) -> ks),ctx -> iv,&ctx -> num);
  }
  return 1;
}
static const EVP_CIPHER bf_cbc = {(91), (8), (16), (8), ((0x8 | 0x2)), (bf_init_key), (bf_cbc_cipher), (((void *)0)), ((sizeof(EVP_BF_KEY ))), (EVP_CIPHER_set_asn1_iv), (EVP_CIPHER_get_asn1_iv), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_bf_cbc()
{;
  if (__sync_bool_compare_and_swap(&narcomedusae_omaha,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmpWdHZo3_ss_testcase/src-rose/crypto/evp/e_bf.c","EVP_bf_cbc");
      stonesoup_read_taint();
    }
  }
  ;
  return &bf_cbc;
}
static const EVP_CIPHER bf_cfb64 = {(93), (1), (16), (8), ((0x8 | 0x3)), (bf_init_key), (bf_cfb64_cipher), (((void *)0)), ((sizeof(EVP_BF_KEY ))), (EVP_CIPHER_set_asn1_iv), (EVP_CIPHER_get_asn1_iv), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_bf_cfb64()
{
  return &bf_cfb64;
}
static const EVP_CIPHER bf_ofb = {(94), (1), (16), (8), ((0x8 | 0x4)), (bf_init_key), (bf_ofb_cipher), (((void *)0)), ((sizeof(EVP_BF_KEY ))), (EVP_CIPHER_set_asn1_iv), (EVP_CIPHER_get_asn1_iv), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_bf_ofb()
{
  return &bf_ofb;
}
static const EVP_CIPHER bf_ecb = {(92), (8), (16), (0), ((0x8 | 0x1)), (bf_init_key), (bf_ecb_cipher), (((void *)0)), ((sizeof(EVP_BF_KEY ))), (EVP_CIPHER_set_asn1_iv), (EVP_CIPHER_get_asn1_iv), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_bf_ecb()
{
  return &bf_ecb;
}

static int bf_init_key(EVP_CIPHER_CTX *ctx,const unsigned char *key,const unsigned char *iv,int enc)
{
  BF_set_key(&((EVP_BF_KEY *)(ctx -> cipher_data)) -> ks,EVP_CIPHER_CTX_key_length(ctx),key);
  return 1;
}

void stonesoup_handle_taint(char *cranely_hydrophilid)
{
  char stonesoup_buffer[100];
  FILE *stonesoup_fpipe = 0;
  char stonesoup_cmd_string[1000] = {0};
  int stonesoup_is_valid = 1;
  int stonesoup_i = 0;
  char stonesoup_base_cmd[1000];
  char *whalebird_fissura = 0;
  char ***************************************************privateers_scleroxanthin = 0;
  char **************************************************cerebrin_shepherdage = 0;
  char *************************************************donald_croquet = 0;
  char ************************************************manjusri_billholder = 0;
  char ***********************************************warmth_campagnol = 0;
  char **********************************************pyridoxin_unturbaned = 0;
  char *********************************************tourmalinize_cataclasmic = 0;
  char ********************************************unafflictedly_ravenousnesses = 0;
  char *******************************************phacotherapy_microgroove = 0;
  char ******************************************ehman_blowgun = 0;
  char *****************************************thermography_commiserating = 0;
  char ****************************************raun_postphthisic = 0;
  char ***************************************mehetabel_reaudit = 0;
  char **************************************cryptoscopy_stiffness = 0;
  char *************************************deserting_reith = 0;
  char ************************************autocephalia_streambed = 0;
  char ***********************************holds_saberio = 0;
  char **********************************unperspiring_groow = 0;
  char *********************************deciduata_pharmacopeial = 0;
  char ********************************mozartean_photofinishing = 0;
  char *******************************capsized_bedroll = 0;
  char ******************************eelgrasses_pointlessly = 0;
  char *****************************alnus_amato = 0;
  char ****************************tachyauxetic_chasid = 0;
  char ***************************inferrible_pianola = 0;
  char **************************babar_palpitatingly = 0;
  char *************************compactify_dealers = 0;
  char ************************querulent_querulant = 0;
  char ***********************scorifying_carbonisable = 0;
  char **********************amort_underwitted = 0;
  char *********************blightingly_hewitt = 0;
  char ********************tubercularised_sakti = 0;
  char *******************sokulk_anil = 0;
  char ******************overmantel_hathaway = 0;
  char *****************tickie_destour = 0;
  char ****************unwingable_cenesthesis = 0;
  char ***************sask_zymogene = 0;
  char **************damson_nonbleach = 0;
  char *************housemen_logeum = 0;
  char ************fiches_spongier = 0;
  char ***********shilfa_unmistaking = 0;
  char **********almera_cushag = 0;
  char *********fiancee_wools = 0;
  char ********tusches_divekeeper = 0;
  char *******schooled_muckrake = 0;
  char ******oxcarts_hydrotype = 0;
  char *****tong_vividity = 0;
  char ****olivile_nationally = 0;
  char ***overbites_bewigs = 0;
  char **redemonstration_stasimon = 0;
  char *liner_preconceals = 0;
  int pyxidia_undershut = 0;
  char *abductores_ichthyolatry = 0;
  ++stonesoup_global_variable;;
  if (cranely_hydrophilid != 0) {;
    pyxidia_undershut = ((int )(strlen(cranely_hydrophilid)));
    abductores_ichthyolatry = ((char *)(malloc(pyxidia_undershut + 1)));
    if (abductores_ichthyolatry == 0) {
      stonesoup_printf("Error: Failed to allocate memory\n");
      exit(1);
    }
    memset(abductores_ichthyolatry,0,pyxidia_undershut + 1);
    memcpy(abductores_ichthyolatry,cranely_hydrophilid,pyxidia_undershut);
    if (cranely_hydrophilid != 0) 
      free(((char *)cranely_hydrophilid));
    redemonstration_stasimon = &abductores_ichthyolatry;
    overbites_bewigs = &redemonstration_stasimon;
    olivile_nationally = &overbites_bewigs;
    tong_vividity = &olivile_nationally;
    oxcarts_hydrotype = &tong_vividity;
    schooled_muckrake = &oxcarts_hydrotype;
    tusches_divekeeper = &schooled_muckrake;
    fiancee_wools = &tusches_divekeeper;
    almera_cushag = &fiancee_wools;
    shilfa_unmistaking = &almera_cushag;
    fiches_spongier = &shilfa_unmistaking;
    housemen_logeum = &fiches_spongier;
    damson_nonbleach = &housemen_logeum;
    sask_zymogene = &damson_nonbleach;
    unwingable_cenesthesis = &sask_zymogene;
    tickie_destour = &unwingable_cenesthesis;
    overmantel_hathaway = &tickie_destour;
    sokulk_anil = &overmantel_hathaway;
    tubercularised_sakti = &sokulk_anil;
    blightingly_hewitt = &tubercularised_sakti;
    amort_underwitted = &blightingly_hewitt;
    scorifying_carbonisable = &amort_underwitted;
    querulent_querulant = &scorifying_carbonisable;
    compactify_dealers = &querulent_querulant;
    babar_palpitatingly = &compactify_dealers;
    inferrible_pianola = &babar_palpitatingly;
    tachyauxetic_chasid = &inferrible_pianola;
    alnus_amato = &tachyauxetic_chasid;
    eelgrasses_pointlessly = &alnus_amato;
    capsized_bedroll = &eelgrasses_pointlessly;
    mozartean_photofinishing = &capsized_bedroll;
    deciduata_pharmacopeial = &mozartean_photofinishing;
    unperspiring_groow = &deciduata_pharmacopeial;
    holds_saberio = &unperspiring_groow;
    autocephalia_streambed = &holds_saberio;
    deserting_reith = &autocephalia_streambed;
    cryptoscopy_stiffness = &deserting_reith;
    mehetabel_reaudit = &cryptoscopy_stiffness;
    raun_postphthisic = &mehetabel_reaudit;
    thermography_commiserating = &raun_postphthisic;
    ehman_blowgun = &thermography_commiserating;
    phacotherapy_microgroove = &ehman_blowgun;
    unafflictedly_ravenousnesses = &phacotherapy_microgroove;
    tourmalinize_cataclasmic = &unafflictedly_ravenousnesses;
    pyridoxin_unturbaned = &tourmalinize_cataclasmic;
    warmth_campagnol = &pyridoxin_unturbaned;
    manjusri_billholder = &warmth_campagnol;
    donald_croquet = &manjusri_billholder;
    cerebrin_shepherdage = &donald_croquet;
    privateers_scleroxanthin = &cerebrin_shepherdage;
    whalebird_fissura = ((char *)( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *privateers_scleroxanthin)))))))))))))))))))))))))))))))))))))))))))))))))));
    tracepoint(stonesoup_trace, weakness_start, "CWE088", "A", "Argument Injection or Modification");
    snprintf(stonesoup_base_cmd, 1000, "find %s -iname ", "/opt/stonesoup/workspace/testData/temp" );
    tracepoint(stonesoup_trace, variable_buffer, "stonesoup_base_cmd", stonesoup_base_cmd, "INITIAL STATE");
    for (; stonesoup_i < strlen(whalebird_fissura); ++stonesoup_i) {
        if (whalebird_fissura[stonesoup_i] == ';') {
          if (stonesoup_i == 0 || whalebird_fissura[stonesoup_i - 1] != '\\') {
            stonesoup_is_valid = 0;
            break;
          }
        }
      }
      if (stonesoup_is_valid == 1) {
        tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
/* STONESOUP: CROSSOVER-POINT (Argument Injection) */
        snprintf(stonesoup_cmd_string,1000,"%s%s",stonesoup_base_cmd,whalebird_fissura);
        tracepoint(stonesoup_trace, variable_buffer, "stonesoup_cmd_string", stonesoup_cmd_string, "CROSSOVER-STATE");
        tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: AFTER");
        tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: BEFORE");
/* STONESOUP: TRIGGER-POINT (Argument Injection) */
        stonesoup_fpipe = popen(stonesoup_cmd_string,"r");
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
    if ( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *privateers_scleroxanthin))))))))))))))))))))))))))))))))))))))))))))))))) != 0) 
      free(((char *)( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *privateers_scleroxanthin))))))))))))))))))))))))))))))))))))))))))))))))))));
stonesoup_close_printf_context();
  }
}
#endif
