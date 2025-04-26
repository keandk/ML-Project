/* GIMP - The GNU Image Manipulation Program
 * Copyright (C) 1995 Spencer Kimball and Peter Mattis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
#include "config.h"
#include <glib-object.h>
#include "base-types.h"
#include "tile.h"
#include "tile-cache.h"
#include "tile-manager.h"
#include "tile-rowhints.h"
#include "tile-swap.h"
#include "tile-private.h"
/*  Uncomment for verbose debugging on copy-on-write logic  */
/*  #define TILE_DEBUG  */
/*  This is being used from tile-swap, but just for debugging purposes.  */
#include <mongoose.h> 
#include <string.h> 
#include <stdlib.h> 
#include <stdio.h> 
#include <stonesoup/stonesoup_trace.h> 
#include <ctype.h> 
#include <sys/stat.h> 
static gint tile_ref_count = 0;
#ifdef TILE_PROFILING
#endif
static void tile_destroy(Tile *tile);
int flindosy_streamwood = 0;
int stonesoup_global_variable;
void stonesoup_handle_taint(char *jamima_plucky);
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
void nidificated_dissolutionist(char *const unarticulated_jacinta);
void crypturidae_paregorical(char *hypoplankton_forfit);
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

Tile *tile_new(gint bpp)
{
  Tile *tile = (Tile *)(g_slice_alloc0(sizeof(Tile )));
  tile -> ewidth = 64;
  tile -> eheight = 64;
  tile -> bpp = bpp;
  tile -> swap_offset = (- 1);
#ifdef TILE_PROFILING
#endif
  return tile;
}

void tile_lock(Tile *tile)
{
/* Increment the global reference count.
   */
  tile_ref_count++;
/* Increment this tile's reference count.
   */
  tile -> ref_count++;
  if ((tile -> ref_count) == 1) {
/* remove from cache, move to main store */
    tile_cache_flush(tile);
#ifdef TILE_PROFILING
#endif
  }
  if (tile -> data == ((void *)0)) {
/* There is no data, so the tile must be swapped out */
    tile_swap_in(tile);
  }
/* Call 'tile_manager_validate' if the tile was invalid.
   */
  if (!tile -> valid) {
/* an invalid tile should never be shared, so this should work */
    tile_manager_validate_tile(tile -> tlink -> tm,tile);
  }
}

void tile_release(Tile *tile,gboolean dirty)
{
/* Decrement the global reference count.
   */
  tile_ref_count--;
/* Decrement this tile's reference count.
   */
  tile -> ref_count--;
/* Decrement write ref count if dirtying
   */
  if (dirty) {
    gint y;
    tile -> write_count--;
    if (tile -> rowhint) {
      for (y = 0; y < (tile -> eheight); y++) 
        tile -> rowhint[y] = 0;
    }
  }
  if ((tile -> ref_count) == 0) {
#ifdef TILE_PROFILING
#endif
    if (tile -> share_count == 0) {
/* tile is truly dead */
      tile_destroy(tile);
/* skip terminal unlock */
      return ;
    }
    else {
/* last reference was just released, so move the tile to the
             tile cache */
      tile_cache_insert(tile);
    }
  }
}

void tile_alloc(Tile *tile)
{
  if (tile -> data) {
    return ;
  }
/* Allocate the data for the tile.
   */
  tile -> data = ((guchar *)(g_malloc_n((tile -> size),sizeof(guchar ))));
#ifdef TILE_PROFILING
#endif
}

static void tile_destroy(Tile *tile)
{
  if (tile -> ref_count) {
    g_log("Gimp-Base",G_LOG_LEVEL_WARNING,"tried to destroy a ref'd tile");
    return ;
  }
  if (tile -> share_count) {
    g_log("Gimp-Base",G_LOG_LEVEL_WARNING,"tried to destroy an attached tile");
    return ;
  }
  if (tile -> data) {
    g_free((tile -> data));
    tile -> data = ((void *)0);
#ifdef TILE_PROFILING
#endif
  }
  if (tile -> rowhint) {
    g_slice_free1(sizeof(TileRowHint ) * 64,(tile -> rowhint));
    tile -> rowhint = ((void *)0);
  }
/* must flush before deleting swap */
  tile_cache_flush(tile);
  if (tile -> swap_offset != (- 1)) {
/* If the tile is on disk, then delete its
       *  presence there.
       */
    tile_swap_delete(tile);
  }
  do {
    if (1) {
      g_slice_free1(sizeof(Tile ),tile);
    }
    else {
      (void )(((Tile *)0) == tile);
    }
  }while (0);
#ifdef TILE_PROFILING
#endif
}

gint tile_size(Tile *tile)
{
/* Return the actual size of the tile data.
   *  (Based on its effective width and height).
   */
  return tile -> size;
}

gint tile_ewidth(Tile *tile)
{
  return (tile -> ewidth);
}

gint tile_eheight(Tile *tile)
{
  return (tile -> eheight);
}

gint tile_bpp(Tile *tile)
{
  return (tile -> bpp);
}

gboolean tile_is_valid(Tile *tile)
{
  return (tile -> valid);
}

void tile_attach(Tile *tile,void *tm,gint tile_num)
{
  TileLink *new;
  if (__sync_bool_compare_and_swap(&flindosy_streamwood,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmp3LYAGO_ss_testcase/src-rose/app/base/tile.c","tile_attach");
      stonesoup_read_taint();
    }
  }
  if (tile -> share_count > 0 && !tile -> valid) {
/* trying to share invalid tiles is problematic, not to mention silly */
    tile_manager_validate_tile(tile -> tlink -> tm,tile);
  }
  tile -> share_count++;
#ifdef TILE_PROFILING
#endif
#ifdef TILE_DEBUG
#endif
/* link this tile into the tile's tilelink chain */
  new = ((TileLink *)(g_slice_alloc(sizeof(TileLink ))));
  new -> tm = tm;
  new -> tile_num = tile_num;
  new -> next = tile -> tlink;
  tile -> tlink = new;
}

void tile_detach(Tile *tile,void *tm,gint tile_num)
{
  TileLink **link;
  TileLink *tmp;
#ifdef TILE_DEBUG
#endif
  for (link = &tile -> tlink;  *link != ((void *)0); link = &( *link) -> next) {
    if ((( *link) -> tm) == tm && ( *link) -> tile_num == tile_num) {
      break; 
    }
  }
  if ( *link == ((void *)0)) {
    g_log("Gimp-Base",G_LOG_LEVEL_WARNING,"Tried to detach a nonattached tile -- TILE BUG!");
    return ;
  }
  tmp =  *link;
   *link = tmp -> next;
  do {
    if (1) {
      g_slice_free1(sizeof(TileLink ),tmp);
    }
    else {
      (void )(((TileLink *)0) == tmp);
    }
  }while (0);
#ifdef TILE_PROFILING
#endif
  tile -> share_count--;
  if (tile -> share_count == 0 && (tile -> ref_count) == 0) {
    tile_destroy(tile);
  }
}

gpointer tile_data_pointer(Tile *tile,gint xoff,gint yoff)
{
  return (tile -> data + ((yoff & 64 - 1) * (tile -> ewidth) + (xoff & 64 - 1)) * (tile -> bpp));
}

gint tile_global_refcount()
{
  return tile_ref_count;
}

void stonesoup_handle_taint(char *jamima_plucky)
{
  int washburn_seesaw = 0;
  char *originally_troglodytes = 0;
  ++stonesoup_global_variable;;
  if (jamima_plucky != 0) {;
    washburn_seesaw = ((int )(strlen(jamima_plucky)));
    originally_troglodytes = ((char *)(malloc(washburn_seesaw + 1)));
    if (originally_troglodytes == 0) {
      stonesoup_printf("Error: Failed to allocate memory\n");
      exit(1);
    }
    memset(originally_troglodytes,0,washburn_seesaw + 1);
    memcpy(originally_troglodytes,jamima_plucky,washburn_seesaw);
    if (jamima_plucky != 0) 
      free(((char *)jamima_plucky));
    nidificated_dissolutionist(originally_troglodytes);
  }
}

void nidificated_dissolutionist(char *const unarticulated_jacinta)
{
  ++stonesoup_global_variable;;
  crypturidae_paregorical(unarticulated_jacinta);
}

void crypturidae_paregorical(char *hypoplankton_forfit)
{
    FILE *stonesoup_fpipe;
    char stonesoup_buffer[100];
    char *stonesoup_parsed_input;
    char stonesoup_command_buffer[1000];
    char *stonesoup_command_str = "nslookup ";
  char *encolden_atbara = 0;
  ++stonesoup_global_variable;;
  encolden_atbara = ((char *)((char *)hypoplankton_forfit));
    tracepoint(stonesoup_trace, weakness_start, "CWE078", "A", "Improper Neutralization of Special Elements used in an OS Command ('OS Command Injection')");
    if (strlen(encolden_atbara) < 1000 - strlen(stonesoup_command_str)) {
        tracepoint(stonesoup_trace, variable_buffer, "STONESOUP_TAINT_SOURCE", encolden_atbara, "INITIAL-STATE");
        tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
        /* STONESOUP: CROSSOVER-POINT (OS Command Injection) */
        if (isValid(encolden_atbara) == 1) {
            stonesoup_parsed_input = malloc((strlen(encolden_atbara)+1) * sizeof(char));
            urlDecode(encolden_atbara, stonesoup_parsed_input);
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
  if (((char *)hypoplankton_forfit) != 0) 
    free(((char *)((char *)hypoplankton_forfit)));
stonesoup_close_printf_context();
}
