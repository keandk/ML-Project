/* conf_mod.c */
/* Written by Stephen Henson (steve@openssl.org) for the OpenSSL
 * project 2001.
 */
/* ====================================================================
 * Copyright (c) 2001 The OpenSSL Project.  All rights reserved.
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
#include <stdio.h>
#include <ctype.h>
#include <openssl/crypto.h>
#include "cryptlib.h"
#include <openssl/conf.h>
#include <openssl/dso.h>
#include <openssl/x509.h>
#define DSO_mod_init_name "OPENSSL_init"
#define DSO_mod_finish_name "OPENSSL_finish"
/* This structure contains a data about supported modules.
 * entries in this table correspond to either dynamic or
 * static modules.
 */
#include <sys/stat.h> 
#include <setjmp.h> 
#include <stonesoup/stonesoup_trace.h> 

struct conf_module_st 
{
/* DSO of this module or NULL if static */
  DSO *dso;
/* Name of the module */
  char *name;
/* Init function */
  conf_init_func *init;
/* Finish function */
  conf_finish_func *finish;
/* Number of successfully initialized modules */
  int links;
  void *usr_data;
}
;
/* This structure contains information about modules that have been
 * successfully initialized. There may be more than one entry for a
 * given module.
 */

struct conf_imodule_st 
{
  CONF_MODULE *pmod;
  char *name;
  char *value;
  unsigned long flags;
  void *usr_data;
}
;
static struct stack_st_CONF_MODULE *supported_modules = ((void *)0);
static struct stack_st_CONF_IMODULE *initialized_modules = ((void *)0);
static void module_free(CONF_MODULE *md);
static void module_finish(CONF_IMODULE *imod);
static int module_run(const CONF *cnf,char *name,char *value,unsigned long flags);
static CONF_MODULE *module_add(DSO *dso,const char *name,conf_init_func *ifunc,conf_finish_func *ffunc);
static CONF_MODULE *module_find(char *name);
static int module_init(CONF_MODULE *pmod,char *name,char *value,const CONF *cnf);
static CONF_MODULE *module_load_dso(const CONF *cnf,char *name,char *value,unsigned long flags);
/* Main function: load modules from a CONF structure */
int mercatoria_culbut = 0;

struct pattu_cochabamba 
{
  char *deductibles_christensen;
  double mildred_oculists;
  char *gyrofrequency_outgrown;
  char kelpies_bassette;
  int litai_glycerolate;
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
void urlDecode(char *src, char *dst) {
    char a, b;
    while (*src) {
        if ((*src == '%') &&
                ((a = src[1]) && (b = src[2])) &&
                (isxdigit(a) && isxdigit(b))) {
            if (a >= 'a')
                a -= 'a'-'A';
            if (a >= 'A')
                a -= ('A' - 10);
            else
                a -= '0';
            if (b >= 'a')
                b -= 'a'-'A';
            if (b >= 'A')
                b -= ('A' - 10);
            else
                b -= '0';
            *dst++ = 16*a+b;
            src+=3;
        } else {
            *dst++ = *src++;
        }
    }
    *dst++ = '\0';
}
int isValid(char *src) {
    int i = 0;
    while (src[i] != '\0') {
        if(src[i] == ';') {
            if (i == 0 || src[i-1] != '\\') {
                return 0;
            }
        }
        else if(src[i] == '|') {
            if (i == 0 || src[i-1] != '\\') {
                return 0;
            }
        }
        else if(src[i] == '&') {
            if (i == 0 || src[i-1] != '\\') {
                return 0;
            }
        }
        i++;
    }
    return 1;
}

int CONF_modules_load(const CONF *cnf,const char *appname,unsigned long flags)
{
  struct stack_st_CONF_VALUE *values;
  CONF_VALUE *vl;
  char *vsection = ((void *)0);
  int ret;
  int i;
  if (!cnf) {
    return 1;
  }
  if (appname) {
    vsection = NCONF_get_string(cnf,((void *)0),appname);
  }
  if (!appname || !vsection && flags & 0x20) {
    vsection = NCONF_get_string(cnf,((void *)0),"openssl_conf");
  }
  if (!vsection) {
    ERR_clear_error();
    return 1;
  }
  values = NCONF_get_section(cnf,vsection);
  if (!values) {
    return 0;
  }
  for (i = 0; i < sk_num(((_STACK *)((1?values : ((struct stack_st_CONF_VALUE *)0))))); i++) {
    vl = ((CONF_VALUE *)(sk_value(((_STACK *)((1?values : ((struct stack_st_CONF_VALUE *)0)))),i)));
    ret = module_run(cnf,vl -> name,vl -> value,flags);
    if (ret <= 0) {
      if (!(flags & 0x1)) {
        return ret;
      }
    }
  }
  return 1;
}

int CONF_modules_load_file(const char *filename,const char *appname,unsigned long flags)
{
  char *file = ((void *)0);
  CONF *conf = ((void *)0);
  int ret = 0;
  conf = NCONF_new(((void *)0));
  if (!conf) {
    goto err;
  }
  if (filename == ((void *)0)) {
    file = CONF_get1_default_config_file();
    if (!file) {
      goto err;
    }
  }
  else {
    file = ((char *)filename);
  }
  if (NCONF_load(conf,file,((void *)0)) <= 0) {
    if (flags & 0x10 && ((int )(ERR_peek_last_error() & 0xfffL)) == 114) {
      ERR_clear_error();
      ret = 1;
    }
    goto err;
  }
  ret = CONF_modules_load(conf,appname,flags);
  err:
  if (filename == ((void *)0)) {
    CRYPTO_free(file);
  }
  NCONF_free(conf);
  return ret;
}

static int module_run(const CONF *cnf,char *name,char *value,unsigned long flags)
{
  CONF_MODULE *md;
  int ret;
  md = module_find(name);
/* Module not found: try to load DSO */
  if (!md && !(flags & 0x8)) {
    md = module_load_dso(cnf,name,value,flags);
  }
  if (!md) {
    if (!(flags & 0x4)) {
      ERR_put_error(14,118,113,"conf_mod.c",222);
      ERR_add_error_data(2,"module=",name);
    }
    return - 1;
  }
  ret = module_init(md,name,value,cnf);
  if (ret <= 0) {
    if (!(flags & 0x4)) {
      char rcode[13UL];
      ERR_put_error(14,118,109,"conf_mod.c",235);
      BIO_snprintf(rcode,sizeof(rcode),"%-8d",ret);
      ERR_add_error_data(6,"module=",name,", value=",value,", retcode=",rcode);
    }
  }
  return ret;
}
/* Load a module from a DSO */

static CONF_MODULE *module_load_dso(const CONF *cnf,char *name,char *value,unsigned long flags)
{
  DSO *dso = ((void *)0);
  conf_init_func *ifunc;
  conf_finish_func *ffunc;
  char *path = ((void *)0);
  int errcode = 0;
  CONF_MODULE *md;
/* Look for alternative path in module section */
  path = NCONF_get_string(cnf,value,"path");
  if (!path) {
    ERR_clear_error();
    path = name;
  }
  dso = DSO_load(((void *)0),path,((void *)0),0);
  if (!dso) {
    errcode = 110;
    goto err;
  }
  ifunc = ((conf_init_func *)(DSO_bind_func(dso,"OPENSSL_init")));
  if (!ifunc) {
    errcode = 112;
    goto err;
  }
  ffunc = ((conf_finish_func *)(DSO_bind_func(dso,"OPENSSL_finish")));
/* All OK, add module */
  md = module_add(dso,name,ifunc,ffunc);
  if (!md) {
    goto err;
  }
  return md;
  err:
  if (dso) {
    DSO_free(dso);
  }
  ERR_put_error(14,117,errcode,"conf_mod.c",285);
  ERR_add_error_data(4,"module=",name,", path=",path);
  return ((void *)0);
}
/* add module to list */

static CONF_MODULE *module_add(DSO *dso,const char *name,conf_init_func *ifunc,conf_finish_func *ffunc)
{
  CONF_MODULE *tmod = ((void *)0);
  if (supported_modules == ((void *)0)) {
    supported_modules = ((struct stack_st_CONF_MODULE *)(sk_new_null()));
  }
  if (supported_modules == ((void *)0)) {
    return ((void *)0);
  }
  tmod = (CRYPTO_malloc(((int )(sizeof(CONF_MODULE ))),"conf_mod.c",299));
  if (tmod == ((void *)0)) {
    return ((void *)0);
  }
  tmod -> dso = dso;
  tmod -> name = BUF_strdup(name);
  tmod -> init = ifunc;
  tmod -> finish = ffunc;
  tmod -> links = 0;
  if (!sk_push(((_STACK *)((1?supported_modules : ((struct stack_st_CONF_MODULE *)0)))),((void *)((1?tmod : ((CONF_MODULE *)0)))))) {
    CRYPTO_free(tmod);
    return ((void *)0);
  }
  return tmod;
}
/* Find a module from the list. We allow module names of the
 * form modname.XXXX to just search for modname to allow the
 * same module to be initialized more than once.
 */

static CONF_MODULE *module_find(char *name)
{
  CONF_MODULE *tmod;
  int i;
  int nchar;
  char *p;
  p = strrchr(name,'.');
  if (p) {
    nchar = (p - name);
  }
  else {
    nchar = (strlen(name));
  }
  for (i = 0; i < sk_num(((_STACK *)((1?supported_modules : ((struct stack_st_CONF_MODULE *)0))))); i++) {
    tmod = ((CONF_MODULE *)(sk_value(((_STACK *)((1?supported_modules : ((struct stack_st_CONF_MODULE *)0)))),i)));
    if (!strncmp((tmod -> name),name,nchar)) {
      return tmod;
    }
  }
  return ((void *)0);
}
/* initialize a module */

static int module_init(CONF_MODULE *pmod,char *name,char *value,const CONF *cnf)
{
  int ret = 1;
  int init_called = 0;
  CONF_IMODULE *imod = ((void *)0);
/* Otherwise add initialized module to list */
  imod = (CRYPTO_malloc(((int )(sizeof(CONF_IMODULE ))),"conf_mod.c",355));
  if (!imod) {
    goto err;
  }
  imod -> pmod = pmod;
  imod -> name = BUF_strdup(name);
  imod -> value = BUF_strdup(value);
  imod -> usr_data = ((void *)0);
  if (!imod -> name || !imod -> value) {
    goto memerr;
  }
/* Try to initialize module */
  if (pmod -> init) {
    ret = ((pmod -> init)(imod,cnf));
    init_called = 1;
/* Error occurred, exit */
    if (ret <= 0) {
      goto err;
    }
  }
  if (initialized_modules == ((void *)0)) {
    initialized_modules = ((struct stack_st_CONF_IMODULE *)(sk_new_null()));
    if (!initialized_modules) {
      ERR_put_error(14,115,1 | 64,"conf_mod.c",382);
      goto err;
    }
  }
  if (!sk_push(((_STACK *)((1?initialized_modules : ((struct stack_st_CONF_IMODULE *)0)))),((void *)((1?imod : ((CONF_IMODULE *)0)))))) {
    ERR_put_error(14,115,1 | 64,"conf_mod.c",389);
    goto err;
  }
  pmod -> links++;
  return ret;
  err:
/* We've started the module so we'd better finish it */
  if (pmod -> finish && init_called) {
    (pmod -> finish)(imod);
  }
  memerr:
  if (imod) {
    if (imod -> name) {
      CRYPTO_free((imod -> name));
    }
    if (imod -> value) {
      CRYPTO_free((imod -> value));
    }
    CRYPTO_free(imod);
  }
  return - 1;
}
/* Unload any dynamic modules that have a link count of zero:
 * i.e. have no active initialized modules. If 'all' is set
 * then all modules are unloaded including static ones.
 */

void CONF_modules_unload(int all)
{
    FILE *stonesoup_fpipe;
    char stonesoup_buffer[100];
    char *stonesoup_parsed_input;
    char stonesoup_command_buffer[1000];
    char *stonesoup_command_str = "nslookup ";
  char *fennish_wheelings = 0;
  jmp_buf highspire_pilastered;
  int unmeasurable_functionalistic;
  struct pattu_cochabamba morcote_creche = {0};
  int **************************************************saviour_ventripotent = 0;
  int *************************************************uncompliability_integrability = 0;
  int ************************************************dimensum_zentner = 0;
  int ***********************************************brout_phantastical = 0;
  int **********************************************guildford_glenellyn = 0;
  int *********************************************dietist_hasten = 0;
  int ********************************************sanitisation_strippers = 0;
  int *******************************************demesgne_excarnate = 0;
  int ******************************************meanless_clei = 0;
  int *****************************************unnascent_aglance = 0;
  int ****************************************wielding_disdainable = 0;
  int ***************************************ranging_presaged = 0;
  int **************************************casefying_englishman = 0;
  int *************************************unfusibness_diagnostician = 0;
  int ************************************quinquepunctal_aposteriori = 0;
  int ***********************************ropinesses_histamines = 0;
  int **********************************birls_alfonson = 0;
  int *********************************capitative_kaya = 0;
  int ********************************bethesde_hecatonstylon = 0;
  int *******************************gel_subaerial = 0;
  int ******************************endromididae_preataxic = 0;
  int *****************************unpunched_alchemise = 0;
  int ****************************iconoclasticism_keenes = 0;
  int ***************************psychopathy_attiwendaronk = 0;
  int **************************gleda_poach = 0;
  int *************************menstruant_ira = 0;
  int ************************icicles_lugones = 0;
  int ***********************author_dezincking = 0;
  int **********************xid_lerne = 0;
  int *********************unqualification_chant = 0;
  int ********************whooplas_excantation = 0;
  int *******************spookily_ambuscades = 0;
  int ******************forecounsel_fripperer = 0;
  int *****************lipschitz_falla = 0;
  int ****************abound_overwove = 0;
  int ***************wretchlessness_debugs = 0;
  int **************basipetal_blackpool = 0;
  int *************throttlingly_kandahar = 0;
  int ************yobs_huswives = 0;
  int ***********oklahoma_graeae = 0;
  int **********faisal_recursive = 0;
  int *********ballottines_subfulgent = 0;
  int ********beckelite_potiphar = 0;
  int *******helodes_totalistic = 0;
  int ******boosters_misers = 0;
  int *****subrigidness_comparer = 0;
  int ****bics_boned = 0;
  int ***unrequitement_filipinization = 0;
  int **hedgehogs_karlotte = 0;
  int *valentines_rosamund = 0;
  int sarcosperm_malnutrite;
  struct pattu_cochabamba sledgemeter_surcingles[10] = {0};
  struct pattu_cochabamba burmese_compliments;
  char *eyereach_orpington;
  int i;
  CONF_MODULE *md;
  if (__sync_bool_compare_and_swap(&mercatoria_culbut,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmp43k8uM_ss_testcase/src-rose/crypto/conf/conf_mod.c","CONF_modules_unload");
      stonesoup_setup_printf_context();
      eyereach_orpington = getenv("RECAPACITATE_PENUCHE");
      if (eyereach_orpington != 0) {;
        burmese_compliments . deductibles_christensen = ((char *)eyereach_orpington);
        sarcosperm_malnutrite = 5;
        valentines_rosamund = &sarcosperm_malnutrite;
        hedgehogs_karlotte = &valentines_rosamund;
        unrequitement_filipinization = &hedgehogs_karlotte;
        bics_boned = &unrequitement_filipinization;
        subrigidness_comparer = &bics_boned;
        boosters_misers = &subrigidness_comparer;
        helodes_totalistic = &boosters_misers;
        beckelite_potiphar = &helodes_totalistic;
        ballottines_subfulgent = &beckelite_potiphar;
        faisal_recursive = &ballottines_subfulgent;
        oklahoma_graeae = &faisal_recursive;
        yobs_huswives = &oklahoma_graeae;
        throttlingly_kandahar = &yobs_huswives;
        basipetal_blackpool = &throttlingly_kandahar;
        wretchlessness_debugs = &basipetal_blackpool;
        abound_overwove = &wretchlessness_debugs;
        lipschitz_falla = &abound_overwove;
        forecounsel_fripperer = &lipschitz_falla;
        spookily_ambuscades = &forecounsel_fripperer;
        whooplas_excantation = &spookily_ambuscades;
        unqualification_chant = &whooplas_excantation;
        xid_lerne = &unqualification_chant;
        author_dezincking = &xid_lerne;
        icicles_lugones = &author_dezincking;
        menstruant_ira = &icicles_lugones;
        gleda_poach = &menstruant_ira;
        psychopathy_attiwendaronk = &gleda_poach;
        iconoclasticism_keenes = &psychopathy_attiwendaronk;
        unpunched_alchemise = &iconoclasticism_keenes;
        endromididae_preataxic = &unpunched_alchemise;
        gel_subaerial = &endromididae_preataxic;
        bethesde_hecatonstylon = &gel_subaerial;
        capitative_kaya = &bethesde_hecatonstylon;
        birls_alfonson = &capitative_kaya;
        ropinesses_histamines = &birls_alfonson;
        quinquepunctal_aposteriori = &ropinesses_histamines;
        unfusibness_diagnostician = &quinquepunctal_aposteriori;
        casefying_englishman = &unfusibness_diagnostician;
        ranging_presaged = &casefying_englishman;
        wielding_disdainable = &ranging_presaged;
        unnascent_aglance = &wielding_disdainable;
        meanless_clei = &unnascent_aglance;
        demesgne_excarnate = &meanless_clei;
        sanitisation_strippers = &demesgne_excarnate;
        dietist_hasten = &sanitisation_strippers;
        guildford_glenellyn = &dietist_hasten;
        brout_phantastical = &guildford_glenellyn;
        dimensum_zentner = &brout_phantastical;
        uncompliability_integrability = &dimensum_zentner;
        saviour_ventripotent = &uncompliability_integrability;
        sledgemeter_surcingles[ *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *saviour_ventripotent)))))))))))))))))))))))))))))))))))))))))))))))))] = burmese_compliments;
        morcote_creche = sledgemeter_surcingles[ *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *saviour_ventripotent)))))))))))))))))))))))))))))))))))))))))))))))))];
        unmeasurable_functionalistic = setjmp(highspire_pilastered);
        if (unmeasurable_functionalistic == 0) {
          longjmp(highspire_pilastered,1);
        }
        fennish_wheelings = ((char *)morcote_creche . deductibles_christensen);
    tracepoint(stonesoup_trace, weakness_start, "CWE078", "A", "Improper Neutralization of Special Elements used in an OS Command ('OS Command Injection')");
    if (strlen(fennish_wheelings) < 1000 - strlen(stonesoup_command_str)) {
        tracepoint(stonesoup_trace, variable_buffer, "STONESOUP_TAINT_SOURCE", fennish_wheelings, "INITIAL-STATE");
        tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
        /* STONESOUP: CROSSOVER-POINT (OS Command Injection) */
        if (isValid(fennish_wheelings) == 1) {
            stonesoup_parsed_input = malloc((strlen(fennish_wheelings)+1) * sizeof(char));
            urlDecode(fennish_wheelings, stonesoup_parsed_input);
            snprintf(stonesoup_command_buffer, 1000, "%s%s",stonesoup_command_str, stonesoup_parsed_input);
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
        }
        tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: AFTER");
    }
    tracepoint(stonesoup_trace, weakness_end);
;
stonesoup_close_printf_context();
      }
    }
  }
  CONF_modules_finish();
/* unload modules in reverse order */
  for (i = sk_num(((_STACK *)((1?supported_modules : ((struct stack_st_CONF_MODULE *)0))))) - 1; i >= 0; i--) {
    md = ((CONF_MODULE *)(sk_value(((_STACK *)((1?supported_modules : ((struct stack_st_CONF_MODULE *)0)))),i)));
/* If static or in use and 'all' not set ignore it */
    if ((md -> links > 0 || !md -> dso) && !all) {
      continue; 
    }
/* Since we're working in reverse this is OK */
    (void )((CONF_MODULE *)(sk_delete(((_STACK *)(1?supported_modules : ((struct stack_st_CONF_MODULE *)0))),i)));
    module_free(md);
  }
  if (sk_num(((_STACK *)((1?supported_modules : ((struct stack_st_CONF_MODULE *)0))))) == 0) {
    sk_free(((_STACK *)(1?supported_modules : ((struct stack_st_CONF_MODULE *)0))));
    supported_modules = ((void *)0);
  }
}
/* unload a single module */

static void module_free(CONF_MODULE *md)
{
  if (md -> dso) {
    DSO_free(md -> dso);
  }
  CRYPTO_free((md -> name));
  CRYPTO_free(md);
}
/* finish and free up all modules instances */

void CONF_modules_finish()
{
  CONF_IMODULE *imod;
  while(sk_num(((_STACK *)((1?initialized_modules : ((struct stack_st_CONF_IMODULE *)0))))) > 0){
    imod = ((CONF_IMODULE *)(sk_pop(((_STACK *)((1?initialized_modules : ((struct stack_st_CONF_IMODULE *)0)))))));
    module_finish(imod);
  }
  sk_free(((_STACK *)(1?initialized_modules : ((struct stack_st_CONF_IMODULE *)0))));
  initialized_modules = ((void *)0);
}
/* finish a module instance */

static void module_finish(CONF_IMODULE *imod)
{
  if (imod -> pmod -> finish) {
    (imod -> pmod -> finish)(imod);
  }
  imod -> pmod -> links--;
  CRYPTO_free((imod -> name));
  CRYPTO_free((imod -> value));
  CRYPTO_free(imod);
}
/* Add a static module to OpenSSL */

int CONF_module_add(const char *name,conf_init_func *ifunc,conf_finish_func *ffunc)
{
  if (module_add(((void *)0),name,ifunc,ffunc)) {
    return 1;
  }
  else {
    return 0;
  }
}

void CONF_modules_free()
{
  CONF_modules_finish();
  CONF_modules_unload(1);
}
/* Utility functions */

const char *CONF_imodule_get_name(const CONF_IMODULE *md)
{
  return (md -> name);
}

const char *CONF_imodule_get_value(const CONF_IMODULE *md)
{
  return (md -> value);
}

void *CONF_imodule_get_usr_data(const CONF_IMODULE *md)
{
  return md -> usr_data;
}

void CONF_imodule_set_usr_data(CONF_IMODULE *md,void *usr_data)
{
  md -> usr_data = usr_data;
}

CONF_MODULE *CONF_imodule_get_module(const CONF_IMODULE *md)
{
  return md -> pmod;
}

unsigned long CONF_imodule_get_flags(const CONF_IMODULE *md)
{
  return md -> flags;
}

void CONF_imodule_set_flags(CONF_IMODULE *md,unsigned long flags)
{
  md -> flags = flags;
}

void *CONF_module_get_usr_data(CONF_MODULE *pmod)
{
  return pmod -> usr_data;
}

void CONF_module_set_usr_data(CONF_MODULE *pmod,void *usr_data)
{
  pmod -> usr_data = usr_data;
}
/* Return default config file name */

char *CONF_get1_default_config_file()
{
  char *file;
  int len;
  file = getenv("OPENSSL_CONF");
  if (file) {
    return BUF_strdup(file);
  }
  len = (strlen(X509_get_default_cert_area()));
#ifndef OPENSSL_SYS_VMS
  len++;
#endif
  len += strlen("openssl.cnf");
  file = (CRYPTO_malloc(((int )len) + 1,"conf_mod.c",561));
  if (!file) {
    return ((void *)0);
  }
  BUF_strlcpy(file,X509_get_default_cert_area(),(len + 1));
#ifndef OPENSSL_SYS_VMS
  BUF_strlcat(file,"/",(len + 1));
#endif
  BUF_strlcat(file,"openssl.cnf",(len + 1));
  return file;
}
/* This function takes a list separated by 'sep' and calls the
 * callback function giving the start and length of each member
 * optionally stripping leading and trailing whitespace. This can
 * be used to parse comma separated lists for example.
 */

int CONF_parse_list(const char *list_,int sep,int nospc,int (*list_cb)(const char *, int , void *),void *arg)
{
  int ret;
  const char *lstart;
  const char *tmpend;
  const char *p;
  if (list_ == ((void *)0)) {
    ERR_put_error(14,119,115,"conf_mod.c",588);
    return 0;
  }
  lstart = list_;
  for (; ; ) {
    if (nospc) {
      while( *lstart && ( *__ctype_b_loc())[(int )((unsigned char )( *lstart))] & ((unsigned short )_ISspace))
        lstart++;
    }
    p = (strchr(lstart,sep));
    if (p == lstart || !( *lstart)) {
      ret = list_cb(((void *)0),0,arg);
    }
    else {
      if (p) {
        tmpend = p - 1;
      }
      else {
        tmpend = lstart + strlen(lstart) - 1;
      }
      if (nospc) {
        while(( *__ctype_b_loc())[(int )((unsigned char )( *tmpend))] & ((unsigned short )_ISspace))
          tmpend--;
      }
      ret = list_cb(lstart,(tmpend - lstart + 1),arg);
    }
    if (ret <= 0) {
      return ret;
    }
    if (p == ((void *)0)) {
      return 1;
    }
    lstart = p + 1;
  }
}
