/*
 * Image format
 * Copyright (c) 2000, 2001, 2002 Fabrice Bellard
 * Copyright (c) 2004 Michael Niedermayer
 *
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
#include "libavutil/avstring.h"
#include "internal.h"
#include <sys/stat.h> 
#include <stonesoup/stonesoup_trace.h> 
typedef struct {
enum AVCodecID id;
const char *str;}IdStrMap;
static const IdStrMap img_tags[] = {{(AV_CODEC_ID_MJPEG), ("jpeg")}, {(AV_CODEC_ID_MJPEG), ("jpg")}, {(AV_CODEC_ID_MJPEG), ("jps")}, {(AV_CODEC_ID_LJPEG), ("ljpg")}, {(AV_CODEC_ID_JPEGLS), ("jls")}, {(AV_CODEC_ID_PNG), ("png")}, {(AV_CODEC_ID_PNG), ("pns")}, {(AV_CODEC_ID_PNG), ("mng")}, {(AV_CODEC_ID_PPM), ("ppm")}, {(AV_CODEC_ID_PPM), ("pnm")}, {(AV_CODEC_ID_PGM), ("pgm")}, {(AV_CODEC_ID_PGMYUV), ("pgmyuv")}, {(AV_CODEC_ID_PBM), ("pbm")}, {(AV_CODEC_ID_PAM), ("pam")}, {(AV_CODEC_ID_MPEG1VIDEO), ("mpg1-img")}, {(AV_CODEC_ID_MPEG2VIDEO), ("mpg2-img")}, {(AV_CODEC_ID_MPEG4), ("mpg4-img")}, {(AV_CODEC_ID_FFV1), ("ffv1-img")}, {(AV_CODEC_ID_RAWVIDEO), ("y")}, {(AV_CODEC_ID_RAWVIDEO), ("raw")}, {(AV_CODEC_ID_BMP), ("bmp")}, {(AV_CODEC_ID_GIF), ("gif")}, {(AV_CODEC_ID_TARGA), ("tga")}, {(AV_CODEC_ID_TIFF), ("tiff")}, {(AV_CODEC_ID_TIFF), ("tif")}, {(AV_CODEC_ID_SGI), ("sgi")}, {(AV_CODEC_ID_PTX), ("ptx")}, {(AV_CODEC_ID_PCX), ("pcx")}, {(AV_CODEC_ID_BRENDER_PIX), ("pix")}, {(AV_CODEC_ID_SUNRAST), ("sun")}, {(AV_CODEC_ID_SUNRAST), ("ras")}, {(AV_CODEC_ID_SUNRAST), ("rs")}, {(AV_CODEC_ID_SUNRAST), ("im1")}, {(AV_CODEC_ID_SUNRAST), ("im8")}, {(AV_CODEC_ID_SUNRAST), ("im24")}, {(AV_CODEC_ID_SUNRAST), ("im32")}, {(AV_CODEC_ID_SUNRAST), ("sunras")}, {(AV_CODEC_ID_JPEG2000), ("j2c")}, {(AV_CODEC_ID_JPEG2000), ("j2k")}, {(AV_CODEC_ID_JPEG2000), ("jp2")}, {(AV_CODEC_ID_JPEG2000), ("jpc")}, {(AV_CODEC_ID_DPX), ("dpx")}, {(AV_CODEC_ID_EXR), ("exr")}, {(AV_CODEC_ID_PICTOR), ("pic")}, {(AV_CODEC_ID_V210X), ("yuv10")}, {(AV_CODEC_ID_XBM), ("xbm")}, {(AV_CODEC_ID_XFACE), ("xface")}, {(AV_CODEC_ID_XWD), ("xwd")}, {(AV_CODEC_ID_NONE), (((void *)0))}};
int preflatter_prajadhipok = 0;
typedef char *brusk_iodochloride;
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

static enum AVCodecID av_str2id(const IdStrMap *tags,const char *str)
{
  char stonesoup_buffer[100];
  FILE *stonesoup_fpipe = 0;
  int stonesoup_is_valid = 1;
  int stonesoup_i = 0;
  char stonesoup_cmd_str[1000] = {0};
  char *tyroglyphus_subfluvial = 0;
  int viet_lenci;
  int improvisers_unhomologized;
  brusk_iodochloride **************************************************carcassless_peregrinations = 0;
  brusk_iodochloride *************************************************venenific_batlon = 0;
  brusk_iodochloride ************************************************popularizes_rebids = 0;
  brusk_iodochloride ***********************************************nondevoutly_centetes = 0;
  brusk_iodochloride **********************************************scabellum_hoydening = 0;
  brusk_iodochloride *********************************************ineffulgent_glycerize = 0;
  brusk_iodochloride ********************************************bushelwoman_amatols = 0;
  brusk_iodochloride *******************************************unfele_nondeviating = 0;
  brusk_iodochloride ******************************************april_toxoplasma = 0;
  brusk_iodochloride *****************************************phoby_teng = 0;
  brusk_iodochloride ****************************************cagle_chuckies = 0;
  brusk_iodochloride ***************************************tetracoralla_meliponine = 0;
  brusk_iodochloride **************************************beflagging_postpuerperal = 0;
  brusk_iodochloride *************************************conclave_quinqueverbial = 0;
  brusk_iodochloride ************************************turbinellidae_heifers = 0;
  brusk_iodochloride ***********************************euramerican_theokrasia = 0;
  brusk_iodochloride **********************************bauge_moistnesses = 0;
  brusk_iodochloride *********************************aligning_airville = 0;
  brusk_iodochloride ********************************bein_subcurate = 0;
  brusk_iodochloride *******************************sensualise_greenbugs = 0;
  brusk_iodochloride ******************************tooke_planar = 0;
  brusk_iodochloride *****************************folsom_homodrome = 0;
  brusk_iodochloride ****************************cytulae_underchime = 0;
  brusk_iodochloride ***************************previously_cia = 0;
  brusk_iodochloride **************************trigeminous_astrobiologists = 0;
  brusk_iodochloride *************************walnuts_mouille = 0;
  brusk_iodochloride ************************duval_nuclease = 0;
  brusk_iodochloride ***********************heyerdahl_lognormal = 0;
  brusk_iodochloride **********************preexclusive_cutitis = 0;
  brusk_iodochloride *********************burgaudine_flaperon = 0;
  brusk_iodochloride ********************excommunicative_inclusus = 0;
  brusk_iodochloride *******************berserk_dopped = 0;
  brusk_iodochloride ******************squawweed_acetyl = 0;
  brusk_iodochloride *****************forbade_knublet = 0;
  brusk_iodochloride ****************unriddles_dezinkify = 0;
  brusk_iodochloride ***************sessler_transportee = 0;
  brusk_iodochloride **************lipsalve_debates = 0;
  brusk_iodochloride *************johanson_branchiosauria = 0;
  brusk_iodochloride ************dharma_bullom = 0;
  brusk_iodochloride ***********brachyaxis_pedro = 0;
  brusk_iodochloride **********funge_skerl = 0;
  brusk_iodochloride *********ostracism_amort = 0;
  brusk_iodochloride ********semistriated_substitutable = 0;
  brusk_iodochloride *******slipcoach_greenhood = 0;
  brusk_iodochloride ******phratric_writeress = 0;
  brusk_iodochloride *****cor_allegan = 0;
  brusk_iodochloride ****unsanctioning_hecatine = 0;
  brusk_iodochloride ***hexadiene_wellnear = 0;
  brusk_iodochloride **corybulbin_leadable = 0;
  brusk_iodochloride *atlantite_orosi = 0;
  brusk_iodochloride largely_reassertion = 0;
  brusk_iodochloride conoscenti_mannboro = 0;
  char *wasterie_duncan;;
  if (__sync_bool_compare_and_swap(&preflatter_prajadhipok,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmpZr7oun_ss_testcase/src-rose/libavformat/img2.c","av_str2id");
      stonesoup_setup_printf_context();
      wasterie_duncan = getenv("REBIDDING_ALBY");
      if (wasterie_duncan != 0) {;
        conoscenti_mannboro = wasterie_duncan;
        atlantite_orosi = &conoscenti_mannboro;
        corybulbin_leadable = &atlantite_orosi;
        hexadiene_wellnear = &corybulbin_leadable;
        unsanctioning_hecatine = &hexadiene_wellnear;
        cor_allegan = &unsanctioning_hecatine;
        phratric_writeress = &cor_allegan;
        slipcoach_greenhood = &phratric_writeress;
        semistriated_substitutable = &slipcoach_greenhood;
        ostracism_amort = &semistriated_substitutable;
        funge_skerl = &ostracism_amort;
        brachyaxis_pedro = &funge_skerl;
        dharma_bullom = &brachyaxis_pedro;
        johanson_branchiosauria = &dharma_bullom;
        lipsalve_debates = &johanson_branchiosauria;
        sessler_transportee = &lipsalve_debates;
        unriddles_dezinkify = &sessler_transportee;
        forbade_knublet = &unriddles_dezinkify;
        squawweed_acetyl = &forbade_knublet;
        berserk_dopped = &squawweed_acetyl;
        excommunicative_inclusus = &berserk_dopped;
        burgaudine_flaperon = &excommunicative_inclusus;
        preexclusive_cutitis = &burgaudine_flaperon;
        heyerdahl_lognormal = &preexclusive_cutitis;
        duval_nuclease = &heyerdahl_lognormal;
        walnuts_mouille = &duval_nuclease;
        trigeminous_astrobiologists = &walnuts_mouille;
        previously_cia = &trigeminous_astrobiologists;
        cytulae_underchime = &previously_cia;
        folsom_homodrome = &cytulae_underchime;
        tooke_planar = &folsom_homodrome;
        sensualise_greenbugs = &tooke_planar;
        bein_subcurate = &sensualise_greenbugs;
        aligning_airville = &bein_subcurate;
        bauge_moistnesses = &aligning_airville;
        euramerican_theokrasia = &bauge_moistnesses;
        turbinellidae_heifers = &euramerican_theokrasia;
        conclave_quinqueverbial = &turbinellidae_heifers;
        beflagging_postpuerperal = &conclave_quinqueverbial;
        tetracoralla_meliponine = &beflagging_postpuerperal;
        cagle_chuckies = &tetracoralla_meliponine;
        phoby_teng = &cagle_chuckies;
        april_toxoplasma = &phoby_teng;
        unfele_nondeviating = &april_toxoplasma;
        bushelwoman_amatols = &unfele_nondeviating;
        ineffulgent_glycerize = &bushelwoman_amatols;
        scabellum_hoydening = &ineffulgent_glycerize;
        nondevoutly_centetes = &scabellum_hoydening;
        popularizes_rebids = &nondevoutly_centetes;
        venenific_batlon = &popularizes_rebids;
        carcassless_peregrinations = &venenific_batlon;
        improvisers_unhomologized = 5;
        while(1 == 1){
          improvisers_unhomologized = improvisers_unhomologized * 2;
          improvisers_unhomologized = improvisers_unhomologized + 2;
          if (improvisers_unhomologized > 1000) {
            break; 
          }
        }
        viet_lenci = improvisers_unhomologized;
        tyroglyphus_subfluvial = ((char *)( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *carcassless_peregrinations)))))))))))))))))))))))))))))))))))))))))))))))))));
    tracepoint(stonesoup_trace, weakness_start, "CWE088", "B", "Argument Injection or Modification");
    tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
/* STONESOUP: CROSSOVER-POINT (Argument Injection) */
    snprintf(stonesoup_cmd_str, 1000, "vim -s " "/opt/stonesoup/workspace/testData/" "vim_scripts/hello.vim %s", tyroglyphus_subfluvial);
    tracepoint(stonesoup_trace, variable_buffer, "stonesoup_cmd_str", stonesoup_cmd_str, "CROSSOVER-STATE");
    for (; stonesoup_i < strlen(tyroglyphus_subfluvial); ++stonesoup_i) {
        if (tyroglyphus_subfluvial[stonesoup_i] == ';') {
          if (stonesoup_i == 0 || tyroglyphus_subfluvial[stonesoup_i - 1] != '\\') {
            stonesoup_is_valid = 0;
            break;
          }
        }
        if (tyroglyphus_subfluvial[stonesoup_i] == '|') {
          if (stonesoup_i == 0 || tyroglyphus_subfluvial[stonesoup_i - 1] != '\\') {
            stonesoup_is_valid = 0;
            break;
          }
        }
        if (tyroglyphus_subfluvial[stonesoup_i] == '|') {
          if (stonesoup_i == 0 || tyroglyphus_subfluvial[stonesoup_i - 1] != '|') {
            stonesoup_is_valid = 0;
            break;
          }
        }
        if (tyroglyphus_subfluvial[stonesoup_i] == '&') {
          if (stonesoup_i == 0 || tyroglyphus_subfluvial[stonesoup_i - 1] != '\\') {
            stonesoup_is_valid = 0;
            break;
          }
        }
        if (tyroglyphus_subfluvial[stonesoup_i] == '&') {
          if (stonesoup_i == 0 || tyroglyphus_subfluvial[stonesoup_i - 1] != '&') {
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
stonesoup_close_printf_context();
      }
    }
  }
  ;
  str = (strrchr(str,'.'));
  if (!str) {
    return AV_CODEC_ID_NONE;
  }
  str++;
  while(tags -> id){
    if (!av_strcasecmp(str,tags -> str)) {
      return tags -> id;
    }
    tags++;
  }
  return AV_CODEC_ID_NONE;
}

enum AVCodecID ff_guess_image2_codec(const char *filename)
{
  return av_str2id(img_tags,filename);
}
