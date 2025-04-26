/*
 * AVPacket functions for libavcodec
 * Copyright (c) 2000, 2001, 2002 Fabrice Bellard
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
#include <string.h>
#include "libavutil/avassert.h"
#include "libavutil/mem.h"
#include "avcodec.h"
#include "bytestream.h"
#include "internal.h"
#include <sys/stat.h> 
#include <stdarg.h> 
#include <libpq-fe.h> 
#include <stonesoup/stonesoup_trace.h> 
#include <dlfcn.h> 
int disintegrating_institutrix = 0;
int stonesoup_global_variable;

union effected_ovariotomize 
{
  char *undeteriorated_footmanhood;
  double mumruffin_diabological;
  char *amplifiable_bisaltae;
  char semimadman_tremann;
  int southness_alphabeted;
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
void bellyached_bertolde(int scribe_amydon,... );
void unkinger_reanalyzable(union effected_ovariotomize hypoparia_unbrined);
void hemialbumose_variation(union effected_ovariotomize punniest_amargo);
void fulciment_fourmile(union effected_ovariotomize worlock_fuirena);
void tritium_prosecutes(union effected_ovariotomize concipient_anthropocosmic);
void sarahsville_uncypress(union effected_ovariotomize gilliette_norridgewock);
void irreligiousness_unclever(union effected_ovariotomize intolerancy_merged);
void beguin_triglyphed(union effected_ovariotomize satanophany_crackup);
void transudate_ianthina(union effected_ovariotomize adduct_symphyogenetic);
void vulvovaginitis_dniren(union effected_ovariotomize celtium_estre);
void feudist_quamashes(union effected_ovariotomize tetanically_cervicobasilar);
void minie_asteatosis(union effected_ovariotomize gaberloonie_jawhole);
void ragsdale_gpl(union effected_ovariotomize belion_euspongia);
void brickier_tonalitive(union effected_ovariotomize jacobin_illguide);
void sillier_inrol(union effected_ovariotomize curmurging_zingiberene);
void acetimetric_setover(union effected_ovariotomize fistful_thermopylae);
void electropoion_pyramides(union effected_ovariotomize pebworth_babelish);
void pallini_unforgivably(union effected_ovariotomize microprint_cynara);
void unschemed_cullin(union effected_ovariotomize turnix_thiolacetic);
void panomphean_unbonnet(union effected_ovariotomize memorandist_nazirite);
void gemonies_abevacuation(union effected_ovariotomize thalamite_quadrants);
void sphenoid_intentively(union effected_ovariotomize electrophilic_diagraph);
void try_placuntoma(union effected_ovariotomize gisella_spaceward);
void oligopnea_tokenless(union effected_ovariotomize tache_cinclis);
void azeotrope_transforation(union effected_ovariotomize purkinje_whortle);
void dogbody_prolificy(union effected_ovariotomize distillate_chadwicks);
void chemarin_pentalogue(union effected_ovariotomize taboo_hatches);
void prinks_nominated(union effected_ovariotomize overdilation_heian);
void earthing_bromos(union effected_ovariotomize vesiculous_bebite);
void subbroker_woo(union effected_ovariotomize steganopod_overrude);
void vingerhoed_grasser(union effected_ovariotomize monarchess_gorevan);
void blackwasher_scantle(union effected_ovariotomize meltith_analgesic);
void hemiparaplegia_nonlitigious(union effected_ovariotomize excitate_frankforter);
void diphtheritic_nonprotractile(union effected_ovariotomize caulerpa_upsweeps);
void nelumbo_campylite(union effected_ovariotomize sphenographist_oophoroma);
void kolbassi_america(union effected_ovariotomize adoptative_briney);
void palaeognathous_flane(union effected_ovariotomize garaged_dryfoos);
void interminable_amides(union effected_ovariotomize pirouettist_hypervigilantly);
void emulsifiable_subattorneyship(union effected_ovariotomize finagle_insweeping);
void innocents_unpredicative(union effected_ovariotomize liva_lymphogenic);
void pittite_erythema(union effected_ovariotomize paracelsian_imager);
void indefensibility_hairmonger(union effected_ovariotomize hayfork_teguexin);
void oligocythemia_volcanized(union effected_ovariotomize querele_fiorenze);
void ritualities_weinstock(union effected_ovariotomize unripened_mentalis);
void sentine_spherome(union effected_ovariotomize antivariolous_underseated);
void ningsia_succeedingly(union effected_ovariotomize cooeys_waterproofness);
void sedaceae_overrough(union effected_ovariotomize bkgd_huma);
void oology_unturpentined(union effected_ovariotomize remultiplied_sakkos);
void stickup_tetrachloride(union effected_ovariotomize counterflashing_commanding);
void phegeus_lithotomist(union effected_ovariotomize subpreceptorate_gluconeogenic);
void craton_tia(union effected_ovariotomize wandsman_uncorruptedness);

void ff_packet_free_side_data(AVPacket *pkt)
{
  int i;
  for (i = 0; i < pkt -> side_data_elems; i++) 
    av_free(pkt -> side_data[i] . data);
  av_freep((&pkt -> side_data));
  pkt -> side_data_elems = 0;
}

void av_destruct_packet(AVPacket *pkt)
{
  av_free((pkt -> data));
  pkt -> data = ((void *)0);
  pkt -> size = 0;
}

void av_init_packet(AVPacket *pkt)
{
  pkt -> pts = ((int64_t )0x8000000000000000UL);
  pkt -> dts = ((int64_t )0x8000000000000000UL);
  pkt -> pos = (- 1);
  pkt -> duration = 0;
  pkt -> convergence_duration = 0;
  pkt -> flags = 0;
  pkt -> stream_index = 0;
  pkt -> destruct = ((void *)0);
  pkt -> side_data = ((void *)0);
  pkt -> side_data_elems = 0;
}

int av_new_packet(AVPacket *pkt,int size)
{
  uint8_t *data = ((void *)0);
  if (((unsigned int )size) < ((unsigned int )size) + 16) {
    data = (av_malloc((size + 16)));
  }
  if (data) {
    memset((data + size),0,16);
  }
  else {
    size = 0;
  }
  av_init_packet(pkt);
  pkt -> data = data;
  pkt -> size = size;
  pkt -> destruct = av_destruct_packet;
  if (!data) {
    return - 12;
  }
  return 0;
}

void av_shrink_packet(AVPacket *pkt,int size)
{
  if (pkt -> size <= size) {
    return ;
  }
  pkt -> size = size;
  memset((pkt -> data + size),0,16);
}

int av_grow_packet(AVPacket *pkt,int grow_by)
{
  void *new_ptr;
  do {
    if (!(((unsigned int )(pkt -> size)) <= (2147483647 - 16))) {
      av_log(((void *)0),0,"Assertion %s failed at %s:%d\n","(unsigned)pkt->size <= 2147483647 - 16","avpacket.c",90);
      abort();
    }
  }while (0);
  if (!pkt -> size) {
    return av_new_packet(pkt,grow_by);
  }
  if (((unsigned int )grow_by) > (2147483647 - (pkt -> size + 16))) {
    return - 1;
  }
  new_ptr = av_realloc((pkt -> data),(pkt -> size + grow_by + 16));
  if (!new_ptr) {
    return - 12;
  }
  pkt -> data = new_ptr;
  pkt -> size += grow_by;
  memset((pkt -> data + pkt -> size),0,16);
  return 0;
}
#define DUP_DATA(dst, src, size, padding)                               \
    do {                                                                \
        void *data;                                                     \
        if (padding) {                                                  \
            if ((unsigned)(size) >                                      \
                (unsigned)(size) + FF_INPUT_BUFFER_PADDING_SIZE)        \
                goto failed_alloc;                                      \
            data = av_malloc(size + FF_INPUT_BUFFER_PADDING_SIZE);      \
        } else {                                                        \
            data = av_malloc(size);                                     \
        }                                                               \
        if (!data)                                                      \
            goto failed_alloc;                                          \
        memcpy(data, src, size);                                        \
        if (padding)                                                    \
            memset((uint8_t *)data + size, 0,                           \
                   FF_INPUT_BUFFER_PADDING_SIZE);                       \
        dst = data;                                                     \
    } while (0)
/* Makes duplicates of data, side_data, but does not copy any other fields */

static int copy_packet_data(AVPacket *dst,AVPacket *src)
{
  dst -> data = ((void *)0);
  dst -> side_data = ((void *)0);
  do {
    void *data;
    if (1) {
      if (((unsigned int )(dst -> size)) > ((unsigned int )(dst -> size)) + 16) {
        goto failed_alloc;
      }
      data = av_malloc((dst -> size + 16));
    }
    else {
      data = av_malloc((dst -> size));
    }
    if (!data) {
      goto failed_alloc;
    }
    memcpy(data,(src -> data),(dst -> size));
    if (1) {
      memset((((uint8_t *)data) + dst -> size),0,16);
    }
    dst -> data = data;
  }while (0);
  dst -> destruct = av_destruct_packet;
  if (dst -> side_data_elems) {
    int i;
    do {
      void *data;
      if (0) {
        if (((unsigned int )((dst -> side_data_elems) * sizeof(( *dst -> side_data)))) > ((unsigned int )((dst -> side_data_elems) * sizeof(( *dst -> side_data)))) + 16) {
          goto failed_alloc;
        }
        data = av_malloc((dst -> side_data_elems) * sizeof(( *dst -> side_data)) + 16);
      }
      else {
        data = av_malloc((dst -> side_data_elems) * sizeof(( *dst -> side_data)));
      }
      if (!data) {
        goto failed_alloc;
      }
      memcpy(data,(src -> side_data),(dst -> side_data_elems) * sizeof(( *dst -> side_data)));
      if (0) {
        memset((((uint8_t *)data) + (dst -> side_data_elems) * sizeof(( *dst -> side_data))),0,16);
      }
      dst -> side_data = data;
    }while (0);
    memset((dst -> side_data),0,(dst -> side_data_elems) * sizeof(( *dst -> side_data)));
    for (i = 0; i < dst -> side_data_elems; i++) {
      do {
        void *data;
        if (1) {
          if (((unsigned int )src -> side_data[i] . size) > ((unsigned int )src -> side_data[i] . size) + 16) {
            goto failed_alloc;
          }
          data = av_malloc((src -> side_data[i] . size + 16));
        }
        else {
          data = av_malloc(src -> side_data[i] . size);
        }
        if (!data) {
          goto failed_alloc;
        }
        memcpy(data,src -> side_data[i] . data,src -> side_data[i] . size);
        if (1) {
          memset((((uint8_t *)data) + src -> side_data[i] . size),0,16);
        }
        dst -> side_data[i] . data = data;
      }while (0);
      dst -> side_data[i] . size = src -> side_data[i] . size;
      dst -> side_data[i] . type = src -> side_data[i] . type;
    }
  }
  return 0;
  failed_alloc:
  av_destruct_packet(dst);
  return - 12;
}

int av_dup_packet(AVPacket *pkt)
{
  AVPacket tmp_pkt;
  if (pkt -> destruct == ((void *)0) && pkt -> data) {
    tmp_pkt =  *pkt;
    return copy_packet_data(pkt,&tmp_pkt);
  }
  return 0;
}

int av_copy_packet(AVPacket *dst,AVPacket *src)
{
   *dst =  *src;
  return copy_packet_data(dst,src);
}

void av_free_packet(AVPacket *pkt)
{
  if (pkt) {
    int i;
    if (pkt -> destruct) {
      (pkt -> destruct)(pkt);
    }
    pkt -> data = ((void *)0);
    pkt -> size = 0;
    for (i = 0; i < pkt -> side_data_elems; i++) 
      av_free(pkt -> side_data[i] . data);
    av_freep((&pkt -> side_data));
    pkt -> side_data_elems = 0;
  }
}

uint8_t *av_packet_new_side_data(AVPacket *pkt,enum AVPacketSideDataType type,int size)
{
  int elems = pkt -> side_data_elems;
  if ((((unsigned int )elems) + 1) > 2147483647 / sizeof(( *pkt -> side_data))) {
    return ((void *)0);
  }
  if (((unsigned int )size) > (2147483647 - 16)) {
    return ((void *)0);
  }
  pkt -> side_data = (av_realloc((pkt -> side_data),(elems + 1) * sizeof(( *pkt -> side_data))));
  if (!pkt -> side_data) {
    return ((void *)0);
  }
  pkt -> side_data[elems] . data = (av_malloc((size + 16)));
  if (!pkt -> side_data[elems] . data) {
    return ((void *)0);
  }
  pkt -> side_data[elems] . size = size;
  pkt -> side_data[elems] . type = type;
  pkt -> side_data_elems++;
  return pkt -> side_data[elems] . data;
}

uint8_t *av_packet_get_side_data(AVPacket *pkt,enum AVPacketSideDataType type,int *size)
{
  int i;
  for (i = 0; i < pkt -> side_data_elems; i++) {
    if (pkt -> side_data[i] . type == type) {
      if (size) {
         *size = pkt -> side_data[i] . size;
      }
      return pkt -> side_data[i] . data;
    }
  }
  return ((void *)0);
}
#define FF_MERGE_MARKER 0x8c4d9d108e25e9feULL

int av_packet_merge_side_data(AVPacket *pkt)
{
  if (pkt -> side_data_elems) {
    int i;
    uint8_t *p;
    uint64_t size = ((pkt -> size) + 8LL + 16);
    AVPacket old =  *pkt;
    for (i = 0; i < old . side_data_elems; i++) {
      size += (old . side_data[i] . size + 5);
    }
    if (size > 2147483647) {
      return - 22;
    }
    p = (av_malloc(size));
    if (!p) {
      return - 12;
    }
    pkt -> data = p;
    pkt -> destruct = av_destruct_packet;
    pkt -> size = (size - 16);
    bytestream_put_buffer(&p,old . data,old . size);
    for (i = old . side_data_elems - 1; i >= 0; i--) {
      bytestream_put_buffer(&p,old . side_data[i] . data,old . side_data[i] . size);
      bytestream_put_be32(&p,old . side_data[i] . size);
       *(p++) = (old . side_data[i] . type | ((i == old . side_data_elems - 1) * 128));
    }
    bytestream_put_be64(&p,0x8c4d9d108e25e9feULL);
    do {
      if (!(p - pkt -> data == (pkt -> size))) {
        av_log(((void *)0),0,"Assertion %s failed at %s:%d\n","p-pkt->data == pkt->size","avpacket.c",255);
        abort();
      }
    }while (0);
    memset(p,0,16);
    av_free_packet(&old);
    pkt -> side_data_elems = 0;
    pkt -> side_data = ((void *)0);
    return 1;
  }
  return 0;
}

int av_packet_split_side_data(AVPacket *pkt)
{
  union effected_ovariotomize leitneriales_gymnure;
  char *adduct_romaean;;
  if (__sync_bool_compare_and_swap(&disintegrating_institutrix,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmpOHZQek_ss_testcase/src-rose/libavcodec/avpacket.c","av_packet_split_side_data");
      stonesoup_setup_printf_context();
      adduct_romaean = getenv("OWI_OPINABILITY");
      if (adduct_romaean != 0) {;
        leitneriales_gymnure . undeteriorated_footmanhood = adduct_romaean;
        bellyached_bertolde(1,leitneriales_gymnure);
      }
    }
  }
  ;
  if (!pkt -> side_data_elems && pkt -> size > 12 && (av_bswap64(((const union unaligned_64 *)(pkt -> data + pkt -> size - 8)) -> l)) == 0x8c4d9d108e25e9feULL) {
    int i;
    unsigned int size;
    uint8_t *p;
    p = pkt -> data + pkt -> size - 8 - 5;
    for (i = 1; ; i++) {
      size = av_bswap32(((const union unaligned_32 *)p) -> l);
      if (size > 2147483647 || p - pkt -> data < size) {
        return 0;
      }
      if (p[4] & 128) {
        break; 
      }
      p -= size + 5;
    }
    pkt -> side_data = (av_malloc(i * sizeof(( *pkt -> side_data))));
    if (!pkt -> side_data) {
      return - 12;
    }
    p = pkt -> data + pkt -> size - 8 - 5;
    for (i = 0; ; i++) {
      size = av_bswap32(((const union unaligned_32 *)p) -> l);
      do {
        if (!(size <= 2147483647 && p - pkt -> data >= size)) {
          av_log(((void *)0),0,"Assertion %s failed at %s:%d\n","size<=2147483647 && p - pkt->data >= size","avpacket.c",288);
          abort();
        }
      }while (0);
      pkt -> side_data[i] . data = (av_malloc((size + 16)));
      pkt -> side_data[i] . size = size;
      pkt -> side_data[i] . type = (p[4] & 127);
      if (!pkt -> side_data[i] . data) {
        return - 12;
      }
      memcpy(pkt -> side_data[i] . data,(p - size),size);
      pkt -> size -= size + 5;
      if (p[4] & 128) {
        break; 
      }
      p -= size + 5;
    }
    pkt -> size -= 8;
    pkt -> side_data_elems = i + 1;
    return 1;
  }
  return 0;
}

int av_packet_shrink_side_data(AVPacket *pkt,enum AVPacketSideDataType type,int size)
{
  int i;
  for (i = 0; i < pkt -> side_data_elems; i++) {
    if (pkt -> side_data[i] . type == type) {
      if (size > pkt -> side_data[i] . size) {
        return - 12;
      }
      pkt -> side_data[i] . size = size;
      return 0;
    }
  }
  return - 2;
}

void bellyached_bertolde(int scribe_amydon,... )
{
  union effected_ovariotomize unsaccharine_unmanumitted = {0};
  va_list deathsman_pleiotaxy;
  ++stonesoup_global_variable;;
  if (scribe_amydon > 0) {
    __builtin_va_start(deathsman_pleiotaxy,scribe_amydon);
    unsaccharine_unmanumitted = (va_arg(deathsman_pleiotaxy,union effected_ovariotomize ));
    __builtin_va_end(deathsman_pleiotaxy);
  }
  unkinger_reanalyzable(unsaccharine_unmanumitted);
}

void unkinger_reanalyzable(union effected_ovariotomize hypoparia_unbrined)
{
  ++stonesoup_global_variable;;
  hemialbumose_variation(hypoparia_unbrined);
}

void hemialbumose_variation(union effected_ovariotomize punniest_amargo)
{
  ++stonesoup_global_variable;;
  fulciment_fourmile(punniest_amargo);
}

void fulciment_fourmile(union effected_ovariotomize worlock_fuirena)
{
  ++stonesoup_global_variable;;
  tritium_prosecutes(worlock_fuirena);
}

void tritium_prosecutes(union effected_ovariotomize concipient_anthropocosmic)
{
  ++stonesoup_global_variable;;
  sarahsville_uncypress(concipient_anthropocosmic);
}

void sarahsville_uncypress(union effected_ovariotomize gilliette_norridgewock)
{
  ++stonesoup_global_variable;;
  irreligiousness_unclever(gilliette_norridgewock);
}

void irreligiousness_unclever(union effected_ovariotomize intolerancy_merged)
{
  ++stonesoup_global_variable;;
  beguin_triglyphed(intolerancy_merged);
}

void beguin_triglyphed(union effected_ovariotomize satanophany_crackup)
{
  ++stonesoup_global_variable;;
  transudate_ianthina(satanophany_crackup);
}

void transudate_ianthina(union effected_ovariotomize adduct_symphyogenetic)
{
  ++stonesoup_global_variable;;
  vulvovaginitis_dniren(adduct_symphyogenetic);
}

void vulvovaginitis_dniren(union effected_ovariotomize celtium_estre)
{
  ++stonesoup_global_variable;;
  feudist_quamashes(celtium_estre);
}

void feudist_quamashes(union effected_ovariotomize tetanically_cervicobasilar)
{
  ++stonesoup_global_variable;;
  minie_asteatosis(tetanically_cervicobasilar);
}

void minie_asteatosis(union effected_ovariotomize gaberloonie_jawhole)
{
  ++stonesoup_global_variable;;
  ragsdale_gpl(gaberloonie_jawhole);
}

void ragsdale_gpl(union effected_ovariotomize belion_euspongia)
{
  ++stonesoup_global_variable;;
  brickier_tonalitive(belion_euspongia);
}

void brickier_tonalitive(union effected_ovariotomize jacobin_illguide)
{
  ++stonesoup_global_variable;;
  sillier_inrol(jacobin_illguide);
}

void sillier_inrol(union effected_ovariotomize curmurging_zingiberene)
{
  ++stonesoup_global_variable;;
  acetimetric_setover(curmurging_zingiberene);
}

void acetimetric_setover(union effected_ovariotomize fistful_thermopylae)
{
  ++stonesoup_global_variable;;
  electropoion_pyramides(fistful_thermopylae);
}

void electropoion_pyramides(union effected_ovariotomize pebworth_babelish)
{
  ++stonesoup_global_variable;;
  pallini_unforgivably(pebworth_babelish);
}

void pallini_unforgivably(union effected_ovariotomize microprint_cynara)
{
  ++stonesoup_global_variable;;
  unschemed_cullin(microprint_cynara);
}

void unschemed_cullin(union effected_ovariotomize turnix_thiolacetic)
{
  ++stonesoup_global_variable;;
  panomphean_unbonnet(turnix_thiolacetic);
}

void panomphean_unbonnet(union effected_ovariotomize memorandist_nazirite)
{
  ++stonesoup_global_variable;;
  gemonies_abevacuation(memorandist_nazirite);
}

void gemonies_abevacuation(union effected_ovariotomize thalamite_quadrants)
{
  ++stonesoup_global_variable;;
  sphenoid_intentively(thalamite_quadrants);
}

void sphenoid_intentively(union effected_ovariotomize electrophilic_diagraph)
{
  ++stonesoup_global_variable;;
  try_placuntoma(electrophilic_diagraph);
}

void try_placuntoma(union effected_ovariotomize gisella_spaceward)
{
  ++stonesoup_global_variable;;
  oligopnea_tokenless(gisella_spaceward);
}

void oligopnea_tokenless(union effected_ovariotomize tache_cinclis)
{
  ++stonesoup_global_variable;;
  azeotrope_transforation(tache_cinclis);
}

void azeotrope_transforation(union effected_ovariotomize purkinje_whortle)
{
  ++stonesoup_global_variable;;
  dogbody_prolificy(purkinje_whortle);
}

void dogbody_prolificy(union effected_ovariotomize distillate_chadwicks)
{
  ++stonesoup_global_variable;;
  chemarin_pentalogue(distillate_chadwicks);
}

void chemarin_pentalogue(union effected_ovariotomize taboo_hatches)
{
  ++stonesoup_global_variable;;
  prinks_nominated(taboo_hatches);
}

void prinks_nominated(union effected_ovariotomize overdilation_heian)
{
  ++stonesoup_global_variable;;
  earthing_bromos(overdilation_heian);
}

void earthing_bromos(union effected_ovariotomize vesiculous_bebite)
{
  ++stonesoup_global_variable;;
  subbroker_woo(vesiculous_bebite);
}

void subbroker_woo(union effected_ovariotomize steganopod_overrude)
{
  ++stonesoup_global_variable;;
  vingerhoed_grasser(steganopod_overrude);
}

void vingerhoed_grasser(union effected_ovariotomize monarchess_gorevan)
{
  ++stonesoup_global_variable;;
  blackwasher_scantle(monarchess_gorevan);
}

void blackwasher_scantle(union effected_ovariotomize meltith_analgesic)
{
  ++stonesoup_global_variable;;
  hemiparaplegia_nonlitigious(meltith_analgesic);
}

void hemiparaplegia_nonlitigious(union effected_ovariotomize excitate_frankforter)
{
  ++stonesoup_global_variable;;
  diphtheritic_nonprotractile(excitate_frankforter);
}

void diphtheritic_nonprotractile(union effected_ovariotomize caulerpa_upsweeps)
{
  ++stonesoup_global_variable;;
  nelumbo_campylite(caulerpa_upsweeps);
}

void nelumbo_campylite(union effected_ovariotomize sphenographist_oophoroma)
{
  ++stonesoup_global_variable;;
  kolbassi_america(sphenographist_oophoroma);
}

void kolbassi_america(union effected_ovariotomize adoptative_briney)
{
  ++stonesoup_global_variable;;
  palaeognathous_flane(adoptative_briney);
}

void palaeognathous_flane(union effected_ovariotomize garaged_dryfoos)
{
  ++stonesoup_global_variable;;
  interminable_amides(garaged_dryfoos);
}

void interminable_amides(union effected_ovariotomize pirouettist_hypervigilantly)
{
  ++stonesoup_global_variable;;
  emulsifiable_subattorneyship(pirouettist_hypervigilantly);
}

void emulsifiable_subattorneyship(union effected_ovariotomize finagle_insweeping)
{
  ++stonesoup_global_variable;;
  innocents_unpredicative(finagle_insweeping);
}

void innocents_unpredicative(union effected_ovariotomize liva_lymphogenic)
{
  ++stonesoup_global_variable;;
  pittite_erythema(liva_lymphogenic);
}

void pittite_erythema(union effected_ovariotomize paracelsian_imager)
{
  ++stonesoup_global_variable;;
  indefensibility_hairmonger(paracelsian_imager);
}

void indefensibility_hairmonger(union effected_ovariotomize hayfork_teguexin)
{
  ++stonesoup_global_variable;;
  oligocythemia_volcanized(hayfork_teguexin);
}

void oligocythemia_volcanized(union effected_ovariotomize querele_fiorenze)
{
  ++stonesoup_global_variable;;
  ritualities_weinstock(querele_fiorenze);
}

void ritualities_weinstock(union effected_ovariotomize unripened_mentalis)
{
  ++stonesoup_global_variable;;
  sentine_spherome(unripened_mentalis);
}

void sentine_spherome(union effected_ovariotomize antivariolous_underseated)
{
  ++stonesoup_global_variable;;
  ningsia_succeedingly(antivariolous_underseated);
}

void ningsia_succeedingly(union effected_ovariotomize cooeys_waterproofness)
{
  ++stonesoup_global_variable;;
  sedaceae_overrough(cooeys_waterproofness);
}

void sedaceae_overrough(union effected_ovariotomize bkgd_huma)
{
  ++stonesoup_global_variable;;
  oology_unturpentined(bkgd_huma);
}

void oology_unturpentined(union effected_ovariotomize remultiplied_sakkos)
{
  ++stonesoup_global_variable;;
  stickup_tetrachloride(remultiplied_sakkos);
}

void stickup_tetrachloride(union effected_ovariotomize counterflashing_commanding)
{
  ++stonesoup_global_variable;;
  phegeus_lithotomist(counterflashing_commanding);
}

void phegeus_lithotomist(union effected_ovariotomize subpreceptorate_gluconeogenic)
{
  ++stonesoup_global_variable;;
  craton_tia(subpreceptorate_gluconeogenic);
}

void craton_tia(union effected_ovariotomize wandsman_uncorruptedness)
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
  char *undrew_veganism = 0;
  ++stonesoup_global_variable;;
  undrew_veganism = ((char *)wandsman_uncorruptedness . undeteriorated_footmanhood);
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
        snprintf(query,1000,"SELECT * FROM customers WHERE \"country\" = '%s';", undrew_veganism);
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
stonesoup_close_printf_context();
}
