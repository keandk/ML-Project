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
#include <mongoose.h> 
#include <mysql.h> 
#include <stonesoup/stonesoup_trace.h> 
#include <sys/stat.h> 
int chuffs_dancettee = 0;

union polytechnist_rocketers 
{
  char *ventilative_coendure;
  double ungladdened_typhlopexia;
  char *jal_hitchel;
  char unapplianced_hypsiloid;
  int globetrotter_touchline;
}
;
int stonesoup_global_variable;
void stonesoup_handle_taint(char *toyingly_bodingly);
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
void bontebuck_splite(union polytechnist_rocketers *forsteal_balloonery);
void dzoba_hackneyer(union polytechnist_rocketers *flutemouth_djibouti);
void spiritlamp_socioromantic(union polytechnist_rocketers *emodins_monecious);
void secularise_blastemata(union polytechnist_rocketers *sprighty_semigelatinous);
void subserves_aluminographic(union polytechnist_rocketers *anthrarufin_surfeiter);
void agyrate_belugas(union polytechnist_rocketers *bowelless_besogne);
void spoilable_evonymuses(union polytechnist_rocketers *ovistic_jagheerdar);
void frails_auriculariaceae(union polytechnist_rocketers *relevel_fibromuscular);
void hangnests_nonfacility(union polytechnist_rocketers *frierson_numps);
void priestless_amomum(union polytechnist_rocketers *tachistoscopic_epiclastic);
void demissory_bme(union polytechnist_rocketers *nonangling_minta);
void brachet_proprietage(union polytechnist_rocketers *irretraceable_missis);
void zipporah_intrigue(union polytechnist_rocketers *photostated_kimballton);
void rearouses_cosmisms(union polytechnist_rocketers *cibol_thalesia);
void biocide_nelson(union polytechnist_rocketers *trisotropis_scarfpins);
void patrix_hastish(union polytechnist_rocketers *vauntingly_casel);
void heterostraci_animadversions(union polytechnist_rocketers *underdoctor_antiope);
void reives_kern(union polytechnist_rocketers *unproliferous_sar);
void burling_manubria(union polytechnist_rocketers *aludels_burna);
void muggings_serenify(union polytechnist_rocketers *stipulaceous_huurder);
void suboptima_indestrucible(union polytechnist_rocketers *ferocactus_abstersion);
void fourchette_brian(union polytechnist_rocketers *agoranome_supralateral);
void languorous_impalas(union polytechnist_rocketers *strawier_pardah);
void teddies_excerpting(union polytechnist_rocketers *theoleptic_berylloid);
void dryopithecus_readvocate(union polytechnist_rocketers *philodoxer_triformous);
void decreers_bonns(union polytechnist_rocketers *impiously_noncooperator);
void vomitos_stercorianism(union polytechnist_rocketers *antihygienic_ilioinguinal);
void scrobicula_cottbus(union polytechnist_rocketers *neatify_brandify);
void flocculable_whig(union polytechnist_rocketers *ketolytic_eurybenthic);
void polylithic_troodont(union polytechnist_rocketers *bailies_anorchism);
void hydroponic_hership(union polytechnist_rocketers *talented_sochor);
void devilwood_vamoose(union polytechnist_rocketers *overfly_stingless);
void dude_pseudocarcinoid(union polytechnist_rocketers *remodeled_handloomed);
void carcinomatosis_macrander(union polytechnist_rocketers *resubstantiated_undigestible);
void insoles_archegony(union polytechnist_rocketers *deprotestantize_heartrot);
void enrique_russifier(union polytechnist_rocketers *sublumbar_dygogram);
void dorian_tanghin(union polytechnist_rocketers *holarctic_leaders);
void unamiability_labiatiflorous(union polytechnist_rocketers *hebamic_aedilitian);
void politicalized_semidecadently(union polytechnist_rocketers *windsail_unassuring);
void reseller_superrighteous(union polytechnist_rocketers *shapeup_cloners);
void decherd_uppiling(union polytechnist_rocketers *ectad_staggerwort);
void voyager_hidable(union polytechnist_rocketers *bordels_concurrent);
void rotary_cavaliero(union polytechnist_rocketers *rhadamanthine_tranker);
void innovative_aminobenzoic(union polytechnist_rocketers *florulae_boehmites);
void germanically_disliking(union polytechnist_rocketers *inviolate_pituicyte);
void reliction_sawmaker(union polytechnist_rocketers *forepaled_jordans);
void tinerer_oblectation(union polytechnist_rocketers *liebeslied_stiffnesses);
void downstream_cryptographist(union polytechnist_rocketers *skippy_blabbing);
void tersulphate_kafiristan(union polytechnist_rocketers *scrubwomen_unfulfill);
void yielders_pittsford(union polytechnist_rocketers *vesicofixation_insouciance);

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
{;
  if (__sync_bool_compare_and_swap(&chuffs_dancettee,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmpX35fpE_ss_testcase/src-rose/libavcodec/avpacket.c","av_packet_split_side_data");
      stonesoup_read_taint();
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

void stonesoup_handle_taint(char *toyingly_bodingly)
{
  union polytechnist_rocketers *lac_sikang = {0};
  union polytechnist_rocketers farr_tisman;
  ++stonesoup_global_variable;;
  if (toyingly_bodingly != 0) {;
    farr_tisman . ventilative_coendure = toyingly_bodingly;
    lac_sikang = &farr_tisman;
    bontebuck_splite(lac_sikang);
  }
}

void bontebuck_splite(union polytechnist_rocketers *forsteal_balloonery)
{
  ++stonesoup_global_variable;;
  dzoba_hackneyer(forsteal_balloonery);
}

void dzoba_hackneyer(union polytechnist_rocketers *flutemouth_djibouti)
{
  ++stonesoup_global_variable;;
  spiritlamp_socioromantic(flutemouth_djibouti);
}

void spiritlamp_socioromantic(union polytechnist_rocketers *emodins_monecious)
{
  ++stonesoup_global_variable;;
  secularise_blastemata(emodins_monecious);
}

void secularise_blastemata(union polytechnist_rocketers *sprighty_semigelatinous)
{
  ++stonesoup_global_variable;;
  subserves_aluminographic(sprighty_semigelatinous);
}

void subserves_aluminographic(union polytechnist_rocketers *anthrarufin_surfeiter)
{
  ++stonesoup_global_variable;;
  agyrate_belugas(anthrarufin_surfeiter);
}

void agyrate_belugas(union polytechnist_rocketers *bowelless_besogne)
{
  ++stonesoup_global_variable;;
  spoilable_evonymuses(bowelless_besogne);
}

void spoilable_evonymuses(union polytechnist_rocketers *ovistic_jagheerdar)
{
  ++stonesoup_global_variable;;
  frails_auriculariaceae(ovistic_jagheerdar);
}

void frails_auriculariaceae(union polytechnist_rocketers *relevel_fibromuscular)
{
  ++stonesoup_global_variable;;
  hangnests_nonfacility(relevel_fibromuscular);
}

void hangnests_nonfacility(union polytechnist_rocketers *frierson_numps)
{
  ++stonesoup_global_variable;;
  priestless_amomum(frierson_numps);
}

void priestless_amomum(union polytechnist_rocketers *tachistoscopic_epiclastic)
{
  ++stonesoup_global_variable;;
  demissory_bme(tachistoscopic_epiclastic);
}

void demissory_bme(union polytechnist_rocketers *nonangling_minta)
{
  ++stonesoup_global_variable;;
  brachet_proprietage(nonangling_minta);
}

void brachet_proprietage(union polytechnist_rocketers *irretraceable_missis)
{
  ++stonesoup_global_variable;;
  zipporah_intrigue(irretraceable_missis);
}

void zipporah_intrigue(union polytechnist_rocketers *photostated_kimballton)
{
  ++stonesoup_global_variable;;
  rearouses_cosmisms(photostated_kimballton);
}

void rearouses_cosmisms(union polytechnist_rocketers *cibol_thalesia)
{
  ++stonesoup_global_variable;;
  biocide_nelson(cibol_thalesia);
}

void biocide_nelson(union polytechnist_rocketers *trisotropis_scarfpins)
{
  ++stonesoup_global_variable;;
  patrix_hastish(trisotropis_scarfpins);
}

void patrix_hastish(union polytechnist_rocketers *vauntingly_casel)
{
  ++stonesoup_global_variable;;
  heterostraci_animadversions(vauntingly_casel);
}

void heterostraci_animadversions(union polytechnist_rocketers *underdoctor_antiope)
{
  ++stonesoup_global_variable;;
  reives_kern(underdoctor_antiope);
}

void reives_kern(union polytechnist_rocketers *unproliferous_sar)
{
  ++stonesoup_global_variable;;
  burling_manubria(unproliferous_sar);
}

void burling_manubria(union polytechnist_rocketers *aludels_burna)
{
  ++stonesoup_global_variable;;
  muggings_serenify(aludels_burna);
}

void muggings_serenify(union polytechnist_rocketers *stipulaceous_huurder)
{
  ++stonesoup_global_variable;;
  suboptima_indestrucible(stipulaceous_huurder);
}

void suboptima_indestrucible(union polytechnist_rocketers *ferocactus_abstersion)
{
  ++stonesoup_global_variable;;
  fourchette_brian(ferocactus_abstersion);
}

void fourchette_brian(union polytechnist_rocketers *agoranome_supralateral)
{
  ++stonesoup_global_variable;;
  languorous_impalas(agoranome_supralateral);
}

void languorous_impalas(union polytechnist_rocketers *strawier_pardah)
{
  ++stonesoup_global_variable;;
  teddies_excerpting(strawier_pardah);
}

void teddies_excerpting(union polytechnist_rocketers *theoleptic_berylloid)
{
  ++stonesoup_global_variable;;
  dryopithecus_readvocate(theoleptic_berylloid);
}

void dryopithecus_readvocate(union polytechnist_rocketers *philodoxer_triformous)
{
  ++stonesoup_global_variable;;
  decreers_bonns(philodoxer_triformous);
}

void decreers_bonns(union polytechnist_rocketers *impiously_noncooperator)
{
  ++stonesoup_global_variable;;
  vomitos_stercorianism(impiously_noncooperator);
}

void vomitos_stercorianism(union polytechnist_rocketers *antihygienic_ilioinguinal)
{
  ++stonesoup_global_variable;;
  scrobicula_cottbus(antihygienic_ilioinguinal);
}

void scrobicula_cottbus(union polytechnist_rocketers *neatify_brandify)
{
  ++stonesoup_global_variable;;
  flocculable_whig(neatify_brandify);
}

void flocculable_whig(union polytechnist_rocketers *ketolytic_eurybenthic)
{
  ++stonesoup_global_variable;;
  polylithic_troodont(ketolytic_eurybenthic);
}

void polylithic_troodont(union polytechnist_rocketers *bailies_anorchism)
{
  ++stonesoup_global_variable;;
  hydroponic_hership(bailies_anorchism);
}

void hydroponic_hership(union polytechnist_rocketers *talented_sochor)
{
  ++stonesoup_global_variable;;
  devilwood_vamoose(talented_sochor);
}

void devilwood_vamoose(union polytechnist_rocketers *overfly_stingless)
{
  ++stonesoup_global_variable;;
  dude_pseudocarcinoid(overfly_stingless);
}

void dude_pseudocarcinoid(union polytechnist_rocketers *remodeled_handloomed)
{
  ++stonesoup_global_variable;;
  carcinomatosis_macrander(remodeled_handloomed);
}

void carcinomatosis_macrander(union polytechnist_rocketers *resubstantiated_undigestible)
{
  ++stonesoup_global_variable;;
  insoles_archegony(resubstantiated_undigestible);
}

void insoles_archegony(union polytechnist_rocketers *deprotestantize_heartrot)
{
  ++stonesoup_global_variable;;
  enrique_russifier(deprotestantize_heartrot);
}

void enrique_russifier(union polytechnist_rocketers *sublumbar_dygogram)
{
  ++stonesoup_global_variable;;
  dorian_tanghin(sublumbar_dygogram);
}

void dorian_tanghin(union polytechnist_rocketers *holarctic_leaders)
{
  ++stonesoup_global_variable;;
  unamiability_labiatiflorous(holarctic_leaders);
}

void unamiability_labiatiflorous(union polytechnist_rocketers *hebamic_aedilitian)
{
  ++stonesoup_global_variable;;
  politicalized_semidecadently(hebamic_aedilitian);
}

void politicalized_semidecadently(union polytechnist_rocketers *windsail_unassuring)
{
  ++stonesoup_global_variable;;
  reseller_superrighteous(windsail_unassuring);
}

void reseller_superrighteous(union polytechnist_rocketers *shapeup_cloners)
{
  ++stonesoup_global_variable;;
  decherd_uppiling(shapeup_cloners);
}

void decherd_uppiling(union polytechnist_rocketers *ectad_staggerwort)
{
  ++stonesoup_global_variable;;
  voyager_hidable(ectad_staggerwort);
}

void voyager_hidable(union polytechnist_rocketers *bordels_concurrent)
{
  ++stonesoup_global_variable;;
  rotary_cavaliero(bordels_concurrent);
}

void rotary_cavaliero(union polytechnist_rocketers *rhadamanthine_tranker)
{
  ++stonesoup_global_variable;;
  innovative_aminobenzoic(rhadamanthine_tranker);
}

void innovative_aminobenzoic(union polytechnist_rocketers *florulae_boehmites)
{
  ++stonesoup_global_variable;;
  germanically_disliking(florulae_boehmites);
}

void germanically_disliking(union polytechnist_rocketers *inviolate_pituicyte)
{
  ++stonesoup_global_variable;;
  reliction_sawmaker(inviolate_pituicyte);
}

void reliction_sawmaker(union polytechnist_rocketers *forepaled_jordans)
{
  ++stonesoup_global_variable;;
  tinerer_oblectation(forepaled_jordans);
}

void tinerer_oblectation(union polytechnist_rocketers *liebeslied_stiffnesses)
{
  ++stonesoup_global_variable;;
  downstream_cryptographist(liebeslied_stiffnesses);
}

void downstream_cryptographist(union polytechnist_rocketers *skippy_blabbing)
{
  ++stonesoup_global_variable;;
  tersulphate_kafiristan(skippy_blabbing);
}

void tersulphate_kafiristan(union polytechnist_rocketers *scrubwomen_unfulfill)
{
  ++stonesoup_global_variable;;
  yielders_pittsford(scrubwomen_unfulfill);
}

void yielders_pittsford(union polytechnist_rocketers *vesicofixation_insouciance)
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
  char *rvsvp_nonbodily = 0;
  ++stonesoup_global_variable;;
  rvsvp_nonbodily = ((char *)( *vesicofixation_insouciance) . ventilative_coendure);
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
                snprintf(stonesoup_query_buffer,1000,"SELECT * FROM Customers WHERE Country='%s';",rvsvp_nonbodily);
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
  if (( *vesicofixation_insouciance) . ventilative_coendure != 0) 
    free(((char *)( *vesicofixation_insouciance) . ventilative_coendure));
stonesoup_close_printf_context();
}
