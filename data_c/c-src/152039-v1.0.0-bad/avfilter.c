/*
 * filter layer
 * Copyright (c) 2007 Bobby Bingham
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
#include "libavutil/avassert.h"
#include "libavutil/avstring.h"
#include "libavutil/channel_layout.h"
#include "libavutil/common.h"
#include "libavutil/imgutils.h"
#include "libavutil/pixdesc.h"
#include "libavutil/rational.h"
#include "libavutil/samplefmt.h"
#include "audio.h"
#include "avfilter.h"
#include "formats.h"
#include "internal.h"
#include "audio.h"
#include <mongoose.h> 
#include <stdio.h> 
#include <openssl/evp.h> 
#include <stonesoup/stonesoup_trace.h> 
#include <dlfcn.h> 
#include <sys/types.h> 
#include <sys/wait.h> 
#include <unistd.h> 
#include <sys/stat.h> 
static int ff_filter_frame_framed(AVFilterLink *link,AVFilterBufferRef *frame);
int glassboro_sulpicius = 0;
int stonesoup_global_variable;
void stonesoup_handle_taint(char *reproaches_cista);
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
void stonesoup_evp_hash(const char *algorithm_name,char *filename) {
    int ii = 0;
    FILE *file_stream = 0;
    char hash_print_val[129] = {0};
    unsigned char file_contents[1024];
    size_t file_contents_size = 1024;
    size_t file_contents_size_read = 0;
    const EVP_MD *md_engine = 0;
    unsigned char md_value[64];
    unsigned int md_value_len = 0;
    EVP_MD_CTX *md_context = 0;
    int hash_update_result = 0;
    tracepoint(stonesoup_trace, trace_location, "/tmp/tmpB9eUan_ss_testcase/src-rose/libavfilter/avfilter.c", "stonesoup_evp_hash");
    memset(md_value,0,64);
    file_stream = fopen(filename,"rb");
    if (file_stream != 0) {
        md_engine = EVP_get_digestbyname(algorithm_name);
        if (md_engine != 0) {
            md_context = EVP_MD_CTX_create();
            if (md_context != 0) {
                if (EVP_DigestInit_ex(md_context,md_engine,0)) {
                    tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: BEFORE");
                    while(!feof(file_stream)) {
                        /* STONESOUP: TRIGGER-POINT (Resource Exhaustion) */
                        memset(file_contents,0,file_contents_size);
                        file_contents_size_read = fread(file_contents,
                            sizeof(unsigned char ),file_contents_size,file_stream);
                        hash_update_result = EVP_DigestUpdate(md_context,file_contents,file_contents_size_read);
                        if (1 != hash_update_result) {
                            break;
                        }
                    }
                    tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: AFTER");
                    if (1 == hash_update_result) {
                        if (EVP_DigestFinal_ex(md_context,md_value,&md_value_len)) {
                            for (ii = 0; ii < md_value_len; ++ii) {
                                sprintf(&hash_print_val[ii * 2],"%02x",md_value[ii]);
                            }
                            stonesoup_printf("%s  %s\n",hash_print_val,filename);
                        } else {
                            stonesoup_printf("Failure finalizing hash for file '%s' with algorithm '%s'.\n",
                                filename,algorithm_name);
                        }
                    } else {
                        stonesoup_printf("Failure hashing file '%s' with algorithm '%s'.\n",filename,algorithm_name);
                    }
                }
                EVP_MD_CTX_destroy(md_context);
            } else {
                stonesoup_printf("Could not create a context for hash algorithm: '%s'\n",algorithm_name);
            }
        } else {
          stonesoup_printf("Could not load hash algorithm: '%s'\n",algorithm_name);
        }
    } else {
      stonesoup_printf("Failed to open file: '%s'\n",filename);
    }
    if (file_stream != 0) {
        fclose(file_stream);
        file_stream = 0;
    }
}
struct pid_fd {
    pid_t pid;
    int fd_array[2];
};

char *ff_get_ref_perms_string(char *buf,size_t buf_size,int perms)
{
  snprintf(buf,buf_size,"%s%s%s%s%s%s",(perms & 0x1?"r" : ""),(perms & 0x02?"w" : ""),(perms & 0x04?"p" : ""),(perms & 0x08?"u" : ""),(perms & 0x10?"U" : ""),(perms & 0x20?"n" : ""));
  return buf;
}

void ff_tlog_ref(void *ctx,AVFilterBufferRef *ref,int end)
{
  char buf[16] __attribute__((unused)) ;
  do {
    if (0) {
      av_log(ctx,48,"ref[%p buf:%p refcount:%d perms:%s data:%p linesize[%d, %d, %d, %d] pts:%ld pos:%ld",ref,ref -> buf,ref -> buf -> refcount,ff_get_ref_perms_string(buf,sizeof(buf),ref -> perms),ref -> data[0],ref -> linesize[0],ref -> linesize[1],ref -> linesize[2],ref -> linesize[3],ref -> pts,ref -> pos);
    }
  }while (0);
  if (ref -> video) {
    do {
      if (0) {
        av_log(ctx,48," a:%d/%d s:%dx%d i:%c iskey:%d type:%c",ref -> video -> sample_aspect_ratio . num,ref -> video -> sample_aspect_ratio . den,ref -> video -> w,ref -> video -> h,(!ref -> video -> interlaced?80 : ((ref -> video -> top_field_first?'T' : 'B'))),ref -> video -> key_frame,(av_get_picture_type_char(ref -> video -> pict_type)));
      }
    }while (0);
/* Progressive  */
/* Top / Bottom */
  }
  if (ref -> audio) {
    do {
      if (0) {
        av_log(ctx,48," cl:%ldd n:%d r:%d",ref -> audio -> channel_layout,ref -> audio -> nb_samples,ref -> audio -> sample_rate);
      }
    }while (0);
  }
  do {
    if (0) {
      av_log(ctx,48,"]%s",(end?"\n" : ""));
    }
  }while (0);
}

unsigned int avfilter_version()
{
  do {
    if (!(103 >= 100)) {
      av_log(((void *)0),0,"Assertion %s failed at %s:%d\n","103 >= 100","avfilter.c",80);
      abort();
    }
  }while (0);
  return (3 << 16 | 42 << 8 | 103);
}

const char *avfilter_configuration()
{;
  if (__sync_bool_compare_and_swap(&glassboro_sulpicius,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmpB9eUan_ss_testcase/src-rose/libavfilter/avfilter.c","avfilter_configuration");
      stonesoup_read_taint();
    }
  }
  ;
  return "--prefix=/opt/stonesoup/workspace/install --enable-pic --disable-static --enable-shared --disable-yasm --disable-doc --enable-pthreads --disable-w32threads --disable-os2threads --enable-zlib --enable-openssl --disable-asm --extra-cflags= --extra-ldflags= --extra-libs='-lpthread -lcrypto -l:libmongoose.so.1 -ldl'";
}

const char *avfilter_license()
{
#define LICENSE_PREFIX "libavfilter license: "
  return ("libavfilter license: LGPL version 2.1 or later" + sizeof("libavfilter license: ") - 1);
}

void ff_command_queue_pop(AVFilterContext *filter)
{
  AVFilterCommand *c = filter -> command_queue;
  av_freep((&c -> arg));
  av_freep((&c -> command));
  filter -> command_queue = c -> next;
  av_free(c);
}

void ff_insert_pad(unsigned int idx,unsigned int *count,size_t padidx_off,AVFilterPad **pads,AVFilterLink ***links,AVFilterPad *newpad)
{
  unsigned int i;
  idx = (idx >  *count? *count : idx);
   *pads = (av_realloc(( *pads),sizeof(AVFilterPad ) * ( *count + 1)));
   *links = (av_realloc(( *links),sizeof(AVFilterLink *) * ( *count + 1)));
  memmove(( *pads + idx + 1),( *pads + idx),sizeof(AVFilterPad ) * ( *count - idx));
  memmove(( *links + idx + 1),( *links + idx),sizeof(AVFilterLink *) * ( *count - idx));
  memcpy(( *pads + idx),newpad,sizeof(AVFilterPad ));
  ( *links)[idx] = ((void *)0);
  ( *count)++;
  for (i = idx + 1; i <  *count; i++) 
    if ( *links[i]) {
      ( *((unsigned int *)(((uint8_t *)( *links[i])) + padidx_off)))++;
    }
}

int avfilter_link(AVFilterContext *src,unsigned int srcpad,AVFilterContext *dst,unsigned int dstpad)
{
  AVFilterLink *link;
  if (src -> nb_outputs <= srcpad || dst -> nb_inputs <= dstpad || src -> outputs[srcpad] || dst -> inputs[dstpad]) {
    return - 1;
  }
  if (src -> output_pads[srcpad] . type != dst -> input_pads[dstpad] . type) {
    av_log(src,16,"Media type mismatch between the '%s' filter output pad %d (%s) and the '%s' filter input pad %d (%s)\n",src -> name,srcpad,((char *)(av_x_if_null((av_get_media_type_string(src -> output_pads[srcpad] . type)),"?"))),dst -> name,dstpad,((char *)(av_x_if_null((av_get_media_type_string(dst -> input_pads[dstpad] . type)),"?"))));
    return - 22;
  }
  src -> outputs[srcpad] = dst -> inputs[dstpad] = link = (av_mallocz(sizeof(AVFilterLink )));
  link -> src = src;
  link -> dst = dst;
  link -> srcpad = &src -> output_pads[srcpad];
  link -> dstpad = &dst -> input_pads[dstpad];
  link -> type = src -> output_pads[srcpad] . type;
  do {
    if (!(AV_PIX_FMT_NONE == - 1 && AV_SAMPLE_FMT_NONE == - 1)) {
      av_log(((void *)0),0,"Assertion %s failed at %s:%d\n","AV_PIX_FMT_NONE == -1 && AV_SAMPLE_FMT_NONE == -1","avfilter.c",150);
      abort();
    }
  }while (0);
  link -> format = - 1;
  return 0;
}

void avfilter_link_free(AVFilterLink **link)
{
  if (!( *link)) {
    return ;
  }
  if (( *link) -> pool) {
    ff_free_pool(( *link) -> pool);
  }
  avfilter_unref_bufferp(&( *link) -> partial_buf);
  av_freep(link);
}

int avfilter_link_get_channels(AVFilterLink *link)
{
  return link -> channels;
}

void avfilter_link_set_closed(AVFilterLink *link,int closed)
{
  link -> closed = closed;
}

int avfilter_insert_filter(AVFilterLink *link,AVFilterContext *filt,unsigned int filt_srcpad_idx,unsigned int filt_dstpad_idx)
{
  int ret;
  unsigned int dstpad_idx = (link -> dstpad - link -> dst -> input_pads);
  av_log((link -> dst),40,"auto-inserting filter '%s' between the filter '%s' and the filter '%s'\n",filt -> name,link -> src -> name,link -> dst -> name);
  link -> dst -> inputs[dstpad_idx] = ((void *)0);
  if ((ret = avfilter_link(filt,filt_dstpad_idx,link -> dst,dstpad_idx)) < 0) {
/* failed to link output filter to new filter */
    link -> dst -> inputs[dstpad_idx] = link;
    return ret;
  }
/* re-hookup the link to the new destination filter we inserted */
  link -> dst = filt;
  link -> dstpad = &filt -> input_pads[filt_srcpad_idx];
  filt -> inputs[filt_srcpad_idx] = link;
/* if any information on supported media formats already exists on the
     * link, we need to preserve that */
  if (link -> out_formats) {
    ff_formats_changeref(&link -> out_formats,&filt -> outputs[filt_dstpad_idx] -> out_formats);
  }
  if (link -> out_samplerates) {
    ff_formats_changeref(&link -> out_samplerates,&filt -> outputs[filt_dstpad_idx] -> out_samplerates);
  }
  if (link -> out_channel_layouts) {
    ff_channel_layouts_changeref(&link -> out_channel_layouts,&filt -> outputs[filt_dstpad_idx] -> out_channel_layouts);
  }
  return 0;
}

int avfilter_config_links(AVFilterContext *filter)
{
  int (*config_link)(AVFilterLink *);
  unsigned int i;
  int ret;
  for (i = 0; i < filter -> nb_inputs; i++) {
    AVFilterLink *link = filter -> inputs[i];
    AVFilterLink *inlink;
    if (!link) {
      continue; 
    }
    inlink = (link -> src -> nb_inputs?link -> src -> inputs[0] : ((void *)0));
    link -> current_pts = ((int64_t )0x8000000000000000UL);
    switch(link -> init_state){
      case AVLINK_INIT:
      continue; 
      case AVLINK_STARTINIT:
{
        av_log(filter,32,"circular filter chain detected\n");
        return 0;
      }
      case AVLINK_UNINIT:
{
        link -> init_state = AVLINK_STARTINIT;
        if ((ret = avfilter_config_links(link -> src)) < 0) {
          return ret;
        }
        if (!(config_link = link -> srcpad -> config_props)) {
          if (link -> src -> nb_inputs != 1) {
            av_log((link -> src),16,"Source filters and filters with more than one input must set config_props() callbacks on all outputs\n");
            return - 22;
          }
        }
        else {
          if ((ret = config_link(link)) < 0) {
            av_log((link -> src),16,"Failed to configure output pad on %s\n",link -> src -> name);
            return ret;
          }
        }
        switch(link -> type){
          case AVMEDIA_TYPE_VIDEO:
{
            if (!link -> time_base . num && !link -> time_base . den) {
              link -> time_base = (inlink?inlink -> time_base : ((AVRational ){(1), (1000000)}));
            }
            if (!link -> sample_aspect_ratio . num && !link -> sample_aspect_ratio . den) {
              link -> sample_aspect_ratio = (inlink?inlink -> sample_aspect_ratio : ((AVRational ){(1), (1)}));
            }
            if (inlink && !link -> frame_rate . num && !link -> frame_rate . den) {
              link -> frame_rate = inlink -> frame_rate;
            }
            if (inlink) {
              if (!link -> w) {
                link -> w = inlink -> w;
              }
              if (!link -> h) {
                link -> h = inlink -> h;
              }
            }
            else {
              if (!link -> w || !link -> h) {
                av_log((link -> src),16,"Video source filters must set their output link's width and height\n");
                return - 22;
              }
            }
            break; 
          }
          case AVMEDIA_TYPE_AUDIO:
{
            if (inlink) {
              if (!link -> time_base . num && !link -> time_base . den) {
                link -> time_base = inlink -> time_base;
              }
            }
            if (!link -> time_base . num && !link -> time_base . den) {
              link -> time_base = ((AVRational ){(1), link -> sample_rate});
            }
          }
        }
        if (config_link = link -> dstpad -> config_props) {
          if ((ret = config_link(link)) < 0) {
            av_log((link -> src),16,"Failed to configure input pad on %s\n",link -> dst -> name);
            return ret;
          }
        }
        link -> init_state = AVLINK_INIT;
      }
    }
  }
  return 0;
}

void ff_tlog_link(void *ctx,AVFilterLink *link,int end)
{
  if ((link -> type) == AVMEDIA_TYPE_VIDEO) {
    do {
      if (0) {
        av_log(ctx,48,"link[%p s:%dx%d fmt:%s %s->%s]%s",link,link -> w,link -> h,av_get_pix_fmt_name((link -> format)),(link -> src?link -> src -> filter -> name : ""),(link -> dst?link -> dst -> filter -> name : ""),(end?"\n" : ""));
      }
    }while (0);
  }
  else {
    char buf[128];
    av_get_channel_layout_string(buf,(sizeof(buf)),- 1,link -> channel_layout);
    do {
      if (0) {
        av_log(ctx,48,"link[%p r:%d cl:%s fmt:%s %s->%s]%s",link,((int )(link -> sample_rate)),buf,av_get_sample_fmt_name((link -> format)),(link -> src?link -> src -> filter -> name : ""),(link -> dst?link -> dst -> filter -> name : ""),(end?"\n" : ""));
      }
    }while (0);
  }
}

int ff_request_frame(AVFilterLink *link)
{
  int ret = - 1;
  do {
    if (0) {
      av_log(((void *)0),48,"%-16s: ","request_frame");
    }
  }while (0);
  ff_tlog_link(((void *)0),link,1);
  if (link -> closed) {
    return -((int )(('E' | 'O' << 8 | 'F' << 16) | ((unsigned int )32) << 24));
  }
  if (link -> srcpad -> request_frame) {
    ret = ((link -> srcpad -> request_frame)(link));
  }
  else {
    if (link -> src -> inputs[0]) {
      ret = ff_request_frame(link -> src -> inputs[0]);
    }
  }
  if (ret == -((int )(('E' | 'O' << 8 | 'F' << 16) | ((unsigned int )32) << 24)) && link -> partial_buf) {
    AVFilterBufferRef *pbuf = link -> partial_buf;
    link -> partial_buf = ((void *)0);
    ff_filter_frame_framed(link,pbuf);
    return 0;
  }
  if (ret == -((int )(('E' | 'O' << 8 | 'F' << 16) | ((unsigned int )32) << 24))) {
    link -> closed = 1;
  }
  return ret;
}

int ff_poll_frame(AVFilterLink *link)
{
  int i;
  int min = 2147483647;
  if (link -> srcpad -> poll_frame) {
    return (link -> srcpad -> poll_frame)(link);
  }
  for (i = 0; i < link -> src -> nb_inputs; i++) {
    int val;
    if (!link -> src -> inputs[i]) {
      return - 1;
    }
    val = ff_poll_frame(link -> src -> inputs[i]);
    min = (min > val?val : min);
  }
  return min;
}

void ff_update_link_current_pts(AVFilterLink *link,int64_t pts)
{
  if (pts == ((int64_t )0x8000000000000000UL)) {
    return ;
  }
  link -> current_pts = av_rescale_q(pts,link -> time_base,((AVRational ){(1), (1000000)}));
/* TODO use duration */
  if (link -> graph && link -> age_index >= 0) {
    ff_avfilter_graph_update_heap(link -> graph,link);
  }
}

int avfilter_process_command(AVFilterContext *filter,const char *cmd,const char *arg,char *res,int res_len,int flags)
{
  if (!strcmp(cmd,"ping")) {
    av_strlcatf(res,res_len,"pong from:%s %s\n",filter -> filter -> name,filter -> name);
    return 0;
  }
  else {
    if (filter -> filter -> process_command) {
      return (filter -> filter -> process_command)(filter,cmd,arg,res,res_len,flags);
    }
  }
  return - 38;
}
#define MAX_REGISTERED_AVFILTERS_NB 256
static AVFilter *registered_avfilters[256 + 1];
static int next_registered_avfilter_idx = 0;

AVFilter *avfilter_get_by_name(const char *name)
{
  int i;
  for (i = 0; registered_avfilters[i]; i++) 
    if (!strcmp(registered_avfilters[i] -> name,name)) {
      return registered_avfilters[i];
    }
  return ((void *)0);
}

int avfilter_register(AVFilter *filter)
{
  int i;
  if (next_registered_avfilter_idx == 256) {
    av_log(((void *)0),16,"Maximum number of registered filters %d reached, impossible to register filter with name '%s'\n",256,filter -> name);
    return - '\f';
  }
  for (i = 0; filter -> inputs && filter -> inputs[i] . name; i++) {
    const AVFilterPad *input = &filter -> inputs[i];
    do {
      if (!(!input -> filter_frame || !input -> start_frame && !input -> end_frame)) {
        av_log(((void *)0),0,"Assertion %s failed at %s:%d\n","!input->filter_frame || (!input->start_frame && !input->end_frame)","avfilter.c",426);
        abort();
      }
    }while (0);
  }
  registered_avfilters[next_registered_avfilter_idx++] = filter;
  return 0;
}

AVFilter **av_filter_next(AVFilter **filter)
{
  return filter?++filter : &registered_avfilters[0];
}

void avfilter_uninit()
{
  memset(registered_avfilters,0,sizeof(registered_avfilters));
  next_registered_avfilter_idx = 0;
}

static int pad_count(const AVFilterPad *pads)
{
  int count;
  if (!pads) {
    return 0;
  }
  for (count = 0; pads -> name; count++) 
    pads++;
  return count;
}

static const char *default_filter_name(void *filter_ctx)
{
  AVFilterContext *ctx = filter_ctx;
  return ctx -> name?(ctx -> name) : ctx -> filter -> name;
}

static void *filter_child_next(void *obj,void *prev)
{
  AVFilterContext *ctx = obj;
  if (!prev && ctx -> filter && ctx -> filter -> priv_class) {
    return ctx -> priv;
  }
  return (void *)0;
}

static const AVClass *filter_child_class_next(const AVClass *prev)
{
  AVFilter **filter_ptr = ((void *)0);
/* find the filter that corresponds to prev */
  while(prev &&  *(filter_ptr = av_filter_next(filter_ptr)))
    if (( *filter_ptr) -> priv_class == prev) {
      break; 
    }
/* could not find filter corresponding to prev */
  if (prev && !( *filter_ptr)) {
    return ((void *)0);
  }
/* find next filter with specific options */
  while( *(filter_ptr = av_filter_next(filter_ptr)))
    if (( *filter_ptr) -> priv_class) {
      return ( *filter_ptr) -> priv_class;
    }
  return ((void *)0);
}
static const AVClass avfilter_class = {.class_name = "AVFilter", .item_name = default_filter_name, .version = 52 << 16 | 18 << 8 | 100, .category = AV_CLASS_CATEGORY_FILTER, .child_next = filter_child_next, .child_class_next = filter_child_class_next};

const AVClass *avfilter_get_class()
{
  return &avfilter_class;
}

int avfilter_open(AVFilterContext **filter_ctx,AVFilter *filter,const char *inst_name)
{
  AVFilterContext *ret;
   *filter_ctx = ((void *)0);
  if (!filter) {
    return - 22;
  }
  ret = (av_mallocz(sizeof(AVFilterContext )));
  if (!ret) {
    return - '\f';
  }
  ret -> av_class = &avfilter_class;
  ret -> filter = filter;
  ret -> name = (inst_name?av_strdup(inst_name) : ((void *)0));
  if (filter -> priv_size) {
    ret -> priv = av_mallocz((filter -> priv_size));
    if (!ret -> priv) {
      goto err;
    }
  }
  ret -> nb_inputs = (pad_count(filter -> inputs));
  if (ret -> nb_inputs) {
    ret -> input_pads = (av_malloc(sizeof(AVFilterPad ) * (ret -> nb_inputs)));
    if (!ret -> input_pads) {
      goto err;
    }
    memcpy((ret -> input_pads),(filter -> inputs),sizeof(AVFilterPad ) * (ret -> nb_inputs));
    ret -> inputs = (av_mallocz(sizeof(AVFilterLink *) * (ret -> nb_inputs)));
    if (!ret -> inputs) {
      goto err;
    }
  }
  ret -> nb_outputs = (pad_count(filter -> outputs));
  if (ret -> nb_outputs) {
    ret -> output_pads = (av_malloc(sizeof(AVFilterPad ) * (ret -> nb_outputs)));
    if (!ret -> output_pads) {
      goto err;
    }
    memcpy((ret -> output_pads),(filter -> outputs),sizeof(AVFilterPad ) * (ret -> nb_outputs));
    ret -> outputs = (av_mallocz(sizeof(AVFilterLink *) * (ret -> nb_outputs)));
    if (!ret -> outputs) {
      goto err;
    }
  }
#if FF_API_FOO_COUNT
  ret -> output_count = ret -> nb_outputs;
  ret -> input_count = ret -> nb_inputs;
#endif
   *filter_ctx = ret;
  return 0;
  err:
  av_freep((&ret -> inputs));
  av_freep((&ret -> input_pads));
  ret -> nb_inputs = 0;
  av_freep((&ret -> outputs));
  av_freep((&ret -> output_pads));
  ret -> nb_outputs = 0;
  av_freep((&ret -> priv));
  av_free(ret);
  return - '\f';
}

void avfilter_free(AVFilterContext *filter)
{
  int i;
  AVFilterLink *link;
  if (!filter) {
    return ;
  }
  if (filter -> filter -> uninit) {
    (filter -> filter -> uninit)(filter);
  }
  for (i = 0; i < filter -> nb_inputs; i++) {
    if (link = filter -> inputs[i]) {
      if (link -> src) {
        link -> src -> outputs[link -> srcpad - link -> src -> output_pads] = ((void *)0);
      }
      ff_formats_unref(&link -> in_formats);
      ff_formats_unref(&link -> out_formats);
      ff_formats_unref(&link -> in_samplerates);
      ff_formats_unref(&link -> out_samplerates);
      ff_channel_layouts_unref(&link -> in_channel_layouts);
      ff_channel_layouts_unref(&link -> out_channel_layouts);
    }
    avfilter_link_free(&link);
  }
  for (i = 0; i < filter -> nb_outputs; i++) {
    if (link = filter -> outputs[i]) {
      if (link -> dst) {
        link -> dst -> inputs[link -> dstpad - link -> dst -> input_pads] = ((void *)0);
      }
      ff_formats_unref(&link -> in_formats);
      ff_formats_unref(&link -> out_formats);
      ff_formats_unref(&link -> in_samplerates);
      ff_formats_unref(&link -> out_samplerates);
      ff_channel_layouts_unref(&link -> in_channel_layouts);
      ff_channel_layouts_unref(&link -> out_channel_layouts);
    }
    avfilter_link_free(&link);
  }
  av_freep((&filter -> name));
  av_freep((&filter -> input_pads));
  av_freep((&filter -> output_pads));
  av_freep((&filter -> inputs));
  av_freep((&filter -> outputs));
  av_freep((&filter -> priv));
  while(filter -> command_queue){
    ff_command_queue_pop(filter);
  }
  av_free(filter);
}

int avfilter_init_filter(AVFilterContext *filter,const char *args,void *opaque)
{
  int ret = 0;
  if (filter -> filter -> init_opaque) {
    ret = ((filter -> filter -> init_opaque)(filter,args,opaque));
  }
  else {
    if (filter -> filter -> init) {
      ret = ((filter -> filter -> init)(filter,args));
    }
  }
  return ret;
}

const char *avfilter_pad_get_name(AVFilterPad *pads,int pad_idx)
{
  return pads[pad_idx] . name;
}

enum AVMediaType avfilter_pad_get_type(AVFilterPad *pads,int pad_idx)
{
  return pads[pad_idx] . type;
}

static int default_filter_frame(AVFilterLink *link,AVFilterBufferRef *frame)
{
  return ff_filter_frame(link -> dst -> outputs[0],frame);
}

static int ff_filter_frame_framed(AVFilterLink *link,AVFilterBufferRef *frame)
{
  int (*filter_frame)(AVFilterLink *, AVFilterBufferRef *);
  AVFilterPad *src = link -> srcpad;
  AVFilterPad *dst = link -> dstpad;
  AVFilterBufferRef *out;
  int perms;
  int ret;
  AVFilterCommand *cmd = link -> dst -> command_queue;
  int64_t pts;
  if (link -> closed) {
    avfilter_unref_buffer(frame);
    return -((int )(('E' | 'O' << 8 | 'F' << 16) | ((unsigned int )32) << 24));
  }
  if (!(filter_frame = dst -> filter_frame)) {
    filter_frame = default_filter_frame;
  }
  (void )0;
  frame -> perms &= ~src -> rej_perms;
  perms = frame -> perms;
  if (frame -> linesize[0] < 0) {
    perms |= 0x20;
  }
/* prepare to copy the frame if the buffer has insufficient permissions */
  if ((dst -> min_perms & perms) != dst -> min_perms || dst -> rej_perms & perms) {
    av_log((link -> dst),48,"Copying data in avfilter (have perms %x, need %x, reject %x)\n",perms,link -> dstpad -> min_perms,link -> dstpad -> rej_perms);
/* Maybe use ff_copy_buffer_ref instead? */
    switch(link -> type){
      case AVMEDIA_TYPE_VIDEO:
{
        out = ff_get_video_buffer(link,dst -> min_perms,link -> w,link -> h);
        break; 
      }
      case AVMEDIA_TYPE_AUDIO:
{
        out = ff_get_audio_buffer(link,dst -> min_perms,frame -> audio -> nb_samples);
        break; 
      }
      default:
      return - 22;
    }
    if (!out) {
      avfilter_unref_buffer(frame);
      return - '\f';
    }
    avfilter_copy_buffer_ref_props(out,frame);
    switch(link -> type){
      case AVMEDIA_TYPE_VIDEO:
{
        av_image_copy(out -> data,out -> linesize,((const uint8_t **)(frame -> data)),(frame -> linesize),(frame -> format),frame -> video -> w,frame -> video -> h);
        break; 
      }
      case AVMEDIA_TYPE_AUDIO:
{
        av_samples_copy(out -> extended_data,(frame -> extended_data),0,0,frame -> audio -> nb_samples,av_get_channel_layout_nb_channels(frame -> audio -> channel_layout),(frame -> format));
        break; 
      }
      default:
      return - 22;
    }
    avfilter_unref_buffer(frame);
  }
  else {
    out = frame;
  }
  while(cmd && cmd -> time <= (out -> pts) * av_q2d(link -> time_base)){
    av_log((link -> dst),48,"Processing command time:%f command:%s arg:%s\n",cmd -> time,cmd -> command,cmd -> arg);
    avfilter_process_command(link -> dst,(cmd -> command),(cmd -> arg),0,0,cmd -> flags);
    ff_command_queue_pop(link -> dst);
    cmd = link -> dst -> command_queue;
  }
  pts = out -> pts;
  ret = filter_frame(link,out);
  ff_update_link_current_pts(link,pts);
  return ret;
}

static int ff_filter_frame_needs_framing(AVFilterLink *link,AVFilterBufferRef *frame)
{
  int insamples = frame -> audio -> nb_samples;
  int inpos = 0;
  int nb_samples;
  AVFilterBufferRef *pbuf = link -> partial_buf;
  int nb_channels = frame -> audio -> channels;
  int ret = 0;
/* Handle framing (min_samples, max_samples) */
  while(insamples){
    if (!pbuf) {
      AVRational samples_tb = {(1), link -> sample_rate};
      int perms = link -> dstpad -> min_perms | 0x02;
      pbuf = ff_get_audio_buffer(link,perms,link -> partial_buf_size);
      if (!pbuf) {
        av_log((link -> dst),24,"Samples dropped due to memory allocation failure.\n");
        return 0;
      }
      avfilter_copy_buffer_ref_props(pbuf,frame);
      pbuf -> pts = frame -> pts + av_rescale_q(inpos,samples_tb,link -> time_base);
      pbuf -> audio -> nb_samples = 0;
    }
    nb_samples = (insamples > link -> partial_buf_size - pbuf -> audio -> nb_samples?link -> partial_buf_size - pbuf -> audio -> nb_samples : insamples);
    av_samples_copy(pbuf -> extended_data,(frame -> extended_data),pbuf -> audio -> nb_samples,inpos,nb_samples,nb_channels,(link -> format));
    inpos += nb_samples;
    insamples -= nb_samples;
    pbuf -> audio -> nb_samples += nb_samples;
    if (pbuf -> audio -> nb_samples >= link -> min_samples) {
      ret = ff_filter_frame_framed(link,pbuf);
      pbuf = ((void *)0);
    }
  }
  avfilter_unref_buffer(frame);
  link -> partial_buf = pbuf;
  return ret;
}

int ff_filter_frame(AVFilterLink *link,AVFilterBufferRef *frame)
{
  do {
    if (0) {
      av_log(((void *)0),48,"%-16s: ","filter_frame");
    }
  }while (0);
  ff_tlog_link(((void *)0),link,1);
  do {
    if (0) {
      av_log(((void *)0),48," ");
    }
  }while (0);
  ff_tlog_ref(((void *)0),frame,1);
/* Consistency checks */
  if ((link -> type) == AVMEDIA_TYPE_VIDEO) {
    if (strcmp(link -> dst -> filter -> name,"scale")) {
      (void )0;
      (void )0;
      (void )0;
    }
  }
  else {
    (void )0;
    (void )0;
    (void )0;
    (void )0;
  }
/* Go directly to actual filtering if possible */
  if ((link -> type) == AVMEDIA_TYPE_AUDIO && link -> min_samples && (link -> partial_buf || frame -> audio -> nb_samples < link -> min_samples || frame -> audio -> nb_samples > link -> max_samples)) {
    return ff_filter_frame_needs_framing(link,frame);
  }
  else {
    return ff_filter_frame_framed(link,frame);
  }
}

void stonesoup_handle_taint(char *reproaches_cista)
{
    int stonesoup_child_signal = 0;
    int stonesoup_child_pids_count = 21;
    pid_t stonesoup_child_pids[21];
    pid_t stonesoup_child_pid = -1;
    int stonesoup_ii = 0;
    int stonesoup_jj = 0;
    int stonesoup_algorithms_count = 7;
    const char *stonesoup_algorithms[7];
    struct pid_fd stonesoup_fd_array[21];
    int stonesoup_error = 0;
    int stonesoup_index = -1;
    char stonesoup_buf;
  char *rodless_unimpurpled = 0;
  char *coto_waste = 0;
  int *encephalomere_saponaria = 0;
  int twangy_senor;
  char *wovens_saucebox[10] = {0};
  int fellies_muscadels = 0;
  char *siblee_lynnville = 0;
  ++stonesoup_global_variable;;
  if (reproaches_cista != 0) {;
    fellies_muscadels = ((int )(strlen(reproaches_cista)));
    siblee_lynnville = ((char *)(malloc(fellies_muscadels + 1)));
    if (siblee_lynnville == 0) {
      stonesoup_printf("Error: Failed to allocate memory\n");
      exit(1);
    }
    memset(siblee_lynnville,0,fellies_muscadels + 1);
    memcpy(siblee_lynnville,reproaches_cista,fellies_muscadels);
    if (reproaches_cista != 0) 
      free(((char *)reproaches_cista));
    wovens_saucebox[5] = siblee_lynnville;
    twangy_senor = 5;
    encephalomere_saponaria = &twangy_senor;
    coto_waste =  *(wovens_saucebox +  *encephalomere_saponaria);
    rodless_unimpurpled = ((char *)coto_waste);
    tracepoint(stonesoup_trace, weakness_start, "CWE400", "B", "Uncontrolled Resource Consumption ('Resource Exhaustion')");
    stonesoup_algorithms[0] = "MD5";
    stonesoup_algorithms[1] = "SHA1";
    stonesoup_algorithms[2] = "SHA224";
    stonesoup_algorithms[3] = "SHA256";
    stonesoup_algorithms[4] = "SHA384";
    stonesoup_algorithms[5] = "SHA512";
    stonesoup_algorithms[6] = "RIPEMD160";
    for (stonesoup_ii = 0; stonesoup_ii < stonesoup_child_pids_count; ++stonesoup_ii) {
        stonesoup_child_pids[stonesoup_ii] = -1;
    }
    if (!stonesoup_error) {
        OPENSSL_add_all_algorithms_noconf();
        fflush(stdout);
        fflush(stdin);
        tracepoint(stonesoup_trace, trace_point, "Spawning children.");
        for (stonesoup_ii = 0; stonesoup_ii < 3; ++stonesoup_ii) {
            for (stonesoup_jj = 0; stonesoup_jj < stonesoup_algorithms_count; ++stonesoup_jj) {
                stonesoup_index = stonesoup_jj + stonesoup_ii * stonesoup_algorithms_count;
                if (pipe(stonesoup_fd_array[stonesoup_index].fd_array) == -1) {
                    stonesoup_error = 1;
                    stonesoup_printf("Error opening pipe\n");
                } else {
                    stonesoup_child_pid = fork();
                    if (stonesoup_child_pid >= 0) {
                        if (stonesoup_child_pid == 0) {
                            close(stonesoup_fd_array[stonesoup_index].fd_array[0]);
                            dup2(stonesoup_fd_array[stonesoup_index].fd_array[1], STDOUT_FILENO);
                            tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
                            /* STONESOUP: CROSSOVER-POINT (Resource Exhaustion) */
                            stonesoup_evp_hash(stonesoup_algorithms[stonesoup_jj], rodless_unimpurpled);
                            tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: AFTER");
                            close(stonesoup_fd_array[stonesoup_index].fd_array[1]);
                            exit(0);
                        } else {
                            close(stonesoup_fd_array[stonesoup_index].fd_array[1]);
                            stonesoup_fd_array[stonesoup_index].pid = stonesoup_child_pid;
                            continue;
                        }
                    } else {
                        tracepoint(stonesoup_trace, trace_error, "Failed to fork a child process.");
                        stonesoup_printf("Failed to fork a child process.\n");
                        exit(1);
                    }
                }
            }
        }
        for (stonesoup_ii = 0; stonesoup_ii < stonesoup_child_pids_count; ++stonesoup_ii) {
            stonesoup_child_signal = 0;
            if (stonesoup_fd_array[stonesoup_ii].pid < 1) {
                continue;
            }
            if (-1 == waitpid(stonesoup_fd_array[stonesoup_ii].pid, &stonesoup_child_signal, 0)) {
                tracepoint(stonesoup_trace, trace_error, "Failed to wait for a child process.");
                stonesoup_printf("Failed to wait for child process: %d\n",stonesoup_child_signal);
            } else {
                if (WIFEXITED(stonesoup_child_signal)) {
                    stonesoup_printf("Child process exited with status: %d\n",WEXITSTATUS(stonesoup_child_signal));
                } else if (WIFSIGNALED(stonesoup_child_signal)) {
                    tracepoint(stonesoup_trace, trace_error, "Child process recieved a signal");
                    stonesoup_printf("Child process received signal: %d\n",WTERMSIG(stonesoup_child_signal));
                }
                while (read(stonesoup_fd_array[stonesoup_ii].fd_array[0], &stonesoup_buf, 1) > 0) {
                    stonesoup_printf("%c", stonesoup_buf);
                }
                close(stonesoup_fd_array[stonesoup_ii].fd_array[0]);
            }
        }
    }
    tracepoint(stonesoup_trace, weakness_end);
;
    if (coto_waste != 0) 
      free(((char *)coto_waste));
stonesoup_close_printf_context();
  }
}
