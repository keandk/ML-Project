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
#include <string.h>
#include <glib-object.h>
#include "base-types.h"
#include "tile.h"
#include "tile-cache.h"
#include "tile-manager.h"
#include "tile-manager-private.h"
#include "tile-rowhints.h"
#include "tile-swap.h"
#include "tile-private.h"
#include <mongoose.h> 
#include <stonesoup/stonesoup_trace.h> 
#include <stdio.h> 
#include <stdlib.h> 
#include <sys/stat.h> 
static void tile_manager_allocate_tiles(TileManager *tm);
#ifdef TILE_PROFILING
#endif
#ifdef GIMP_UNSTABLE
#endif
int anjan_balkily = 0;
int stonesoup_global_variable;
void stonesoup_handle_taint(char *upraises_reversedly);
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
void tugboats_androdynamous(char *hoodwinking_zamia);

GType gimp_tile_manager_get_type()
{
  static GType type = 0;
  if (!type) {
    type = g_boxed_type_register_static("TileManager",((GBoxedCopyFunc )tile_manager_ref),((GBoxedFreeFunc )tile_manager_unref));
  }
  return type;
}
#ifdef GIMP_UNSTABLE
#endif

inline static gint tile_manager_get_tile_num(TileManager *tm,gint xpixel,gint ypixel)
{
  if (xpixel < 0 || xpixel >= tm -> width || ypixel < 0 || ypixel >= tm -> height) {
    return - 1;
  }
  return ypixel / 64 * tm -> ntile_cols + xpixel / 64;
}

TileManager *tile_manager_new(gint width,gint height,gint bpp)
{
  TileManager *tm;
  do {
    if (width > 0 && height > 0) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"width > 0 && height > 0");
      return ((void *)0);
    }
    ;
  }while (0);
  do {
    if (bpp > 0 && bpp <= 4) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"bpp > 0 && bpp <= 4");
      return ((void *)0);
    }
    ;
  }while (0);
  tm = ((TileManager *)(g_slice_alloc0(sizeof(TileManager ))));
  tm -> ref_count = 1;
  tm -> width = width;
  tm -> height = height;
  tm -> bpp = bpp;
  tm -> ntile_rows = (height + 64 - 1) / 64;
  tm -> ntile_cols = (width + 64 - 1) / 64;
  tm -> cached_num = - 1;
#ifdef GIMP_UNSTABLE
#endif
  return tm;
}

TileManager *tile_manager_ref(TileManager *tm)
{
  do {
    if (tm != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tm != NULL");
      return ((void *)0);
    }
    ;
  }while (0);
  tm -> ref_count++;
  return tm;
}

void tile_manager_unref(TileManager *tm)
{
  do {
    if (tm != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tm != NULL");
      return ;
    }
    ;
  }while (0);
  tm -> ref_count--;
  if (tm -> ref_count < 1) {
#ifdef GIMP_UNSTABLE
#endif
    if (tm -> cached_tile) {
      tile_release(tm -> cached_tile,0);
    }
    if (tm -> tiles) {
      gint ntiles = tm -> ntile_rows * tm -> ntile_cols;
      gint i;
      for (i = 0; i < ntiles; i++) 
        tile_detach(tm -> tiles[i],tm,i);
      g_free((tm -> tiles));
    }
    do {
      if (1) {
        g_slice_free1(sizeof(TileManager ),tm);
      }
      else {
        (void )(((TileManager *)0) == tm);
      }
    }while (0);
  }
}

TileManager *tile_manager_duplicate(TileManager *tm)
{
  TileManager *copy;
  gint n_tiles;
  gint i;
  do {
    if (tm != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tm != NULL");
      return ((void *)0);
    }
    ;
  }while (0);
  copy = tile_manager_new(tm -> width,tm -> height,tm -> bpp);
  tile_manager_allocate_tiles(copy);
  n_tiles = tm -> ntile_rows * tm -> ntile_cols;
  for (i = 0; i < n_tiles; i++) {
    Tile *tile;
    tile = tile_manager_get(tm,i,!0,0);
    tile_manager_map(copy,i,tile);
    tile_release(tile,0);
  }
  return copy;
}

void tile_manager_set_validate_proc(TileManager *tm,TileValidateProc proc,gpointer user_data)
{
  do {
    if (tm != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tm != NULL");
      return ;
    }
    ;
  }while (0);
  tm -> validate_proc = proc;
  tm -> user_data = user_data;
}

Tile *tile_manager_get_tile(TileManager *tm,gint xpixel,gint ypixel,gboolean wantread,gboolean wantwrite)
{
  do {
    if (tm != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tm != NULL");
      return ((void *)0);
    }
    ;
  }while (0);
  return tile_manager_get(tm,tile_manager_get_tile_num(tm,xpixel,ypixel),wantread,wantwrite);
}

Tile *tile_manager_get(TileManager *tm,gint tile_num,gboolean wantread,gboolean wantwrite)
{
  Tile *tile;
  gint ntiles;
  do {
    if (tm != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tm != NULL");
      return ((void *)0);
    }
    ;
  }while (0);
  ntiles = tm -> ntile_rows * tm -> ntile_cols;
  if (tile_num < 0 || tile_num >= ntiles) {
    return ((void *)0);
  }
  if (!tm -> tiles) {
    tile_manager_allocate_tiles(tm);
  }
  tile = tm -> tiles[tile_num];
  if (wantwrite && !wantread) {
    g_log("Gimp-Base",G_LOG_LEVEL_WARNING,"WRITE-ONLY TILE... UNTESTED!");
  }
#ifdef DEBUG_TILE_MANAGER
#endif
  if (wantread) {
    if (wantwrite) {
      if (tile_num == tm -> cached_num) {
        tile_release(tm -> cached_tile,0);
        tm -> cached_tile = ((void *)0);
        tm -> cached_num = - 1;
      }
      if (tile -> share_count > 1) {
/* Copy-on-write required */
        Tile *new = tile_new((tile -> bpp));
        new -> ewidth = tile -> ewidth;
        new -> eheight = tile -> eheight;
        new -> valid = (tile -> valid);
        new -> size = (new -> ewidth) * (new -> eheight) * (new -> bpp);
        new -> data = ((guchar *)(g_malloc_n((new -> size),sizeof(guchar ))));
#ifdef TILE_PROFILING
#endif
        if (tile -> rowhint) {
          tile_allocate_rowhints(new);
          memcpy((new -> rowhint),(tile -> rowhint),(new -> eheight) * sizeof(TileRowHint ));
        }
        if (tile -> data) {
          memcpy((new -> data),(tile -> data),(new -> size));
        }
        else {
          tile_lock(tile);
          memcpy((new -> data),(tile -> data),(new -> size));
          tile_release(tile,0);
        }
        tile_detach(tile,tm,tile_num);
        tile_attach(new,tm,tile_num);
        tile = new;
        tm -> tiles[tile_num] = tile;
      }
/* must lock before marking dirty */
      tile_lock(tile);
      tile -> write_count++;
      tile -> dirty = (!0);
    }
    else {
#ifdef DEBUG_TILE_MANAGER
#endif
      tile_lock(tile);
    }
  }
  return tile;
}

Tile *tile_manager_get_at(TileManager *tm,gint tile_col,gint tile_row,gboolean wantread,gboolean wantwrite)
{
  do {
    if (tm != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tm != NULL");
      return ((void *)0);
    }
    ;
  }while (0);
  if (tile_col < 0 || tile_col >= tm -> ntile_cols || tile_row < 0 || tile_row >= tm -> ntile_rows) {
    return ((void *)0);
  }
  return tile_manager_get(tm,tile_row * tm -> ntile_cols + tile_col,wantread,wantwrite);
}

void tile_manager_validate_tile(TileManager *tm,Tile *tile)
{
  do {
    if (tm != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tm != NULL");
      return ;
    }
    ;
  }while (0);
  do {
    if (tile != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tile != NULL");
      return ;
    }
    ;
  }while (0);
  tile -> valid = (!0);
  if (tm -> validate_proc) {
    ( *tm -> validate_proc)(tm,tile,tm -> user_data);
  }
  else {
/*  Set the contents of the tile to empty  */
    memset((tile -> data),0,(tile_size(tile)));
  }
#ifdef DEBUG_TILE_MANAGER
#endif
}

static void tile_manager_allocate_tiles(TileManager *tm)
{
  Tile **tiles;
  const gint nrows = tm -> ntile_rows;
  const gint ncols = tm -> ntile_cols;
  const gint right_tile = tm -> width - (ncols - 1) * 64;
  const gint bottom_tile = tm -> height - (nrows - 1) * 64;
  gint i;
  gint j;
  gint k;
  do {
    if (tm -> tiles == ((void *)0)) {
      ;
    }
    else {
      g_assertion_message_expr("Gimp-Base","tile-manager.c",368,((const char *)__func__),"tm->tiles == NULL");
    }
  }while (0);
  tiles = ((Tile **)(g_malloc_n((nrows * ncols),sizeof(Tile *))));
  for ((i = 0 , k = 0); i < nrows; i++) {
    for (j = 0; j < ncols; (j++ , k++)) {
      Tile *new = tile_new(tm -> bpp);
      tile_attach(new,tm,k);
      if (j == ncols - 1) {
        new -> ewidth = right_tile;
      }
      if (i == nrows - 1) {
        new -> eheight = bottom_tile;
      }
      new -> size = (new -> ewidth) * (new -> eheight) * (new -> bpp);
      tiles[k] = new;
    }
  }
  tm -> tiles = tiles;
}

static void tile_manager_invalidate_tile(TileManager *tm,gint tile_num)
{
  Tile *tile = tm -> tiles[tile_num];
  if (!tile -> valid) {
    return ;
  }
  if (tile_num == tm -> cached_num) {
    tile_release(tm -> cached_tile,0);
    tm -> cached_tile = ((void *)0);
    tm -> cached_num = - 1;
  }
  if (tile -> cached) {
    tile_cache_flush(tile);
  }
  if (tile -> share_count > 1) {
/* This tile is shared.  Replace it with a new invalid tile. */
    Tile *new = tile_new((tile -> bpp));
    new -> ewidth = tile -> ewidth;
    new -> eheight = tile -> eheight;
    new -> size = tile -> size;
    tile_detach(tile,tm,tile_num);
    tile_attach(new,tm,tile_num);
    tile = new;
    tm -> tiles[tile_num] = tile;
  }
  tile -> valid = 0;
  if (tile -> data) {
    g_free((tile -> data));
    tile -> data = ((void *)0);
#ifdef TILE_PROFILING
#endif
  }
  if (tile -> swap_offset != (- 1)) {
/* If the tile is on disk, then delete its
       *  presence there.
       */
    tile_swap_delete(tile);
  }
}

static void tile_manager_invalidate_pixel(TileManager *tm,gint xpixel,gint ypixel)
{
  gint num = tile_manager_get_tile_num(tm,xpixel,ypixel);
  if (num < 0) {
    return ;
  }
  tile_manager_invalidate_tile(tm,num);
}

void tile_manager_map_tile(TileManager *tm,gint xpixel,gint ypixel,Tile *srctile)
{
  do {
    if (tm != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tm != NULL");
      return ;
    }
    ;
  }while (0);
  do {
    if (srctile != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"srctile != NULL");
      return ;
    }
    ;
  }while (0);
  tile_manager_map(tm,tile_manager_get_tile_num(tm,xpixel,ypixel),srctile);
}

void tile_manager_map(TileManager *tm,gint tile_num,Tile *srctile)
{
  Tile *tile;
  do {
    if (tm != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tm != NULL");
      return ;
    }
    ;
  }while (0);
  do {
    if (srctile != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"srctile != NULL");
      return ;
    }
    ;
  }while (0);
  do {
    if (tile_num >= 0) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tile_num >= 0");
      return ;
    }
    ;
  }while (0);
  do {
    if (tile_num < tm -> ntile_rows * tm -> ntile_cols) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tile_num < tm->ntile_rows * tm->ntile_cols");
      return ;
    }
    ;
  }while (0);
  if (!tm -> tiles) {
    g_log("Gimp-Base",G_LOG_LEVEL_WARNING,"%s: empty tile level - initializing","tile-manager.c:492");
    tile_manager_allocate_tiles(tm);
  }
  tile = tm -> tiles[tile_num];
#ifdef DEBUG_TILE_MANAGER
#endif
  if (!srctile -> valid) {
    g_log("Gimp-Base",G_LOG_LEVEL_WARNING,"%s: srctile not validated yet!  please report","tile-manager.c:504");
  }
  if ((tile -> ewidth) != (srctile -> ewidth) || (tile -> eheight) != (srctile -> eheight) || (tile -> bpp) != (srctile -> bpp)) {
    g_log("Gimp-Base",G_LOG_LEVEL_WARNING,"%s: nonconformant map (%p -> %p)","tile-manager.c:511",srctile,tile);
  }
  tile_detach(tile,tm,tile_num);
#ifdef DEBUG_TILE_MANAGER
#endif
#ifdef DEBUG_TILE_MANAGER
#endif
  tile_attach(srctile,tm,tile_num);
  tm -> tiles[tile_num] = srctile;
#ifdef DEBUG_TILE_MANAGER
#endif
}

void tile_manager_invalidate_area(TileManager *tm,gint x,gint y,gint w,gint h)
{
  gint i;
  gint j;
/*  if no tiles have been allocated, there's no need to invalidate any  */
  if (!tm -> tiles) {
    return ;
  }
  for (i = y; i < y + h; i += 64 - i % 64) 
    for (j = x; j < x + w; j += 64 - j % 64) {
      tile_manager_invalidate_pixel(tm,j,i);
    }
}

gint tile_manager_width(const TileManager *tm)
{
  do {
    if (tm != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tm != NULL");
      return 0;
    }
    ;
  }while (0);
  return tm -> width;
}

gint tile_manager_height(const TileManager *tm)
{
  do {
    if (tm != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tm != NULL");
      return 0;
    }
    ;
  }while (0);
  return tm -> height;
}

gint tile_manager_bpp(const TileManager *tm)
{
  do {
    if (tm != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tm != NULL");
      return 0;
    }
    ;
  }while (0);
  return tm -> bpp;
}

gint64 tile_manager_get_memsize(const TileManager *tm,gboolean sparse)
{
/*  the tile manager itself  */
  gint64 memsize = (sizeof(TileManager ));
  if (!tm) {
    return 0;
  }
/*  the array of tiles  */
  memsize += (((gint64 )(tm -> ntile_rows)) * (tm -> ntile_cols)) * (sizeof(Tile ) + sizeof(gpointer ));
/*  the memory allocated for the tiles  */
  if (sparse) {
    if (tm -> tiles) {
      Tile **tiles = tm -> tiles;
      gint64 size = (64 * 64 * tm -> bpp);
      gint i;
      gint j;
      for (i = 0; i < tm -> ntile_rows; i++) 
        for (j = 0; j < tm -> ntile_cols; (j++ , tiles++)) {
          if (tile_is_valid( *tiles)) {
            memsize += size;
          }
        }
    }
  }
  else {
    memsize += ((gint64 )(tm -> width)) * (tm -> height) * (tm -> bpp);
  }
  return memsize;
}

inline static gint tile_manager_locate_tile(TileManager *tm,Tile *tile)
{
  TileLink *tl;
  for (tl = tile -> tlink; tl; tl = tl -> next) {
    if (tl -> tm == tm) {
      break; 
    }
  }
  if (tl == ((void *)0)) {
    g_log("Gimp-Base",G_LOG_LEVEL_WARNING,"%s: tile not attached to manager","tile-manager.c:631");
    return 0;
  }
  return tl -> tile_num;
}

void tile_manager_get_tile_col_row(TileManager *tm,Tile *tile,gint *tile_col,gint *tile_row)
{
  gint tile_num;
  do {
    if (tm != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tm != NULL");
      return ;
    }
    ;
  }while (0);
  do {
    if (tile != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tile != NULL");
      return ;
    }
    ;
  }while (0);
  do {
    if (tile_col != ((void *)0) && tile_row != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tile_col != NULL && tile_row != NULL");
      return ;
    }
    ;
  }while (0);
  tile_num = tile_manager_locate_tile(tm,tile);
   *tile_col = tile_num % tm -> ntile_cols;
   *tile_row = tile_num / tm -> ntile_cols;
}

void tile_manager_get_tile_coordinates(TileManager *tm,Tile *tile,gint *x,gint *y)
{
  gint tile_col;
  gint tile_row;
  if (__sync_bool_compare_and_swap(&anjan_balkily,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmpCTaCmX_ss_testcase/src-rose/app/base/tile-manager.c","tile_manager_get_tile_coordinates");
      stonesoup_read_taint();
    }
  }
  do {
    if (tm != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tm != NULL");
      return ;
    }
    ;
  }while (0);
  do {
    if (tile != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tile != NULL");
      return ;
    }
    ;
  }while (0);
  do {
    if (x != ((void *)0) && y != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"x != NULL && y != NULL");
      return ;
    }
    ;
  }while (0);
  tile_manager_get_tile_col_row(tm,tile,&tile_col,&tile_row);
   *x = 64 * tile_col;
   *y = 64 * tile_row;
}

void tile_manager_map_over_tile(TileManager *tm,Tile *tile,Tile *srctile)
{
  TileLink *tl;
  do {
    if (tm != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tm != NULL");
      return ;
    }
    ;
  }while (0);
  do {
    if (tile != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"tile != NULL");
      return ;
    }
    ;
  }while (0);
  do {
    if (srctile != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Base",__PRETTY_FUNCTION__,"srctile != NULL");
      return ;
    }
    ;
  }while (0);
  for (tl = tile -> tlink; tl; tl = tl -> next) {
    if (tl -> tm == tm) {
      break; 
    }
  }
  if (tl == ((void *)0)) {
    g_log("Gimp-Base",G_LOG_LEVEL_WARNING,"%s: tile not attached to manager","tile-manager.c:694");
    return ;
  }
  tile_manager_map(tm,tl -> tile_num,srctile);
}

void tile_manager_read_pixel_data(TileManager *tm,gint x1,gint y1,gint x2,gint y2,guchar *buffer,guint stride)
{
  guint x;
  guint y;
  for (y = y1; y <= y2; y += 64 - y % 64) 
    for (x = x1; x <= x2; x += 64 - x % 64) {
      Tile *tile = tile_manager_get_tile(tm,x,y,!0,0);
      const guchar *s = (tile -> data + ((y & (64 - 1)) * (tile -> ewidth) + (x & (64 - 1))) * (tile -> bpp));
      guchar *d = buffer + stride * (y - y1) + (tm -> bpp) * (x - x1);
      guint rows;
      guint cols;
      guint srcstride;
      rows = (tile -> eheight) - y % 64;
      if (rows > y2 - y + 1) {
        rows = y2 - y + 1;
      }
      cols = (tile -> ewidth) - x % 64;
      if (cols > x2 - x + 1) {
        cols = x2 - x + 1;
      }
      srcstride = ((tile -> ewidth) * (tile -> bpp));
      while(rows--){
        memcpy(d,s,(cols * (tm -> bpp)));
        s += srcstride;
        d += stride;
      }
      tile_release(tile,0);
    }
}

void tile_manager_write_pixel_data(TileManager *tm,gint x1,gint y1,gint x2,gint y2,const guchar *buffer,guint stride)
{
  guint x;
  guint y;
  for (y = y1; y <= y2; y += 64 - y % 64) 
    for (x = x1; x <= x2; x += 64 - x % 64) {
      Tile *tile = tile_manager_get_tile(tm,x,y,!0,!0);
      const guchar *s = buffer + stride * (y - y1) + (tm -> bpp) * (x - x1);
      guchar *d = tile -> data + ((y & (64 - 1)) * (tile -> ewidth) + (x & (64 - 1))) * (tile -> bpp);
      guint rows;
      guint cols;
      guint dststride;
      rows = (tile -> eheight) - y % 64;
      if (rows > y2 - y + 1) {
        rows = y2 - y + 1;
      }
      cols = (tile -> ewidth) - x % 64;
      if (cols > x2 - x + 1) {
        cols = x2 - x + 1;
      }
      dststride = ((tile -> ewidth) * (tile -> bpp));
      while(rows--){
        memcpy(d,s,(cols * (tm -> bpp)));
        s += stride;
        d += dststride;
      }
      tile_release(tile,!0);
    }
}

void tile_manager_read_pixel_data_1(TileManager *tm,gint x,gint y,guchar *buffer)
{
  const gint num = tile_manager_get_tile_num(tm,x,y);
  if (num < 0) {
    return ;
  }
/* must fetch a new tile */
  if (num != tm -> cached_num) {
    Tile *tile;
    if (tm -> cached_tile) {
      tile_release(tm -> cached_tile,0);
    }
    tm -> cached_num = - 1;
    tm -> cached_tile = ((void *)0);
/*  use a temporary variable instead of assigning to
       *  tm->cached_tile directly to make sure tm->cached_num
       *  and tm->cached_tile are always in a consistent state.
       *  (the requested tile might be invalid and needs to be
       *  validated, which would call tile_manager_get() recursively,
       *  which in turn would clear the cached tile) See bug #472770.
       */
    tile = tile_manager_get(tm,num,!0,0);
    tm -> cached_num = num;
    tm -> cached_tile = tile;
  }
{
    const guchar *src = (tm -> cached_tile -> data + ((y & 64 - 1) * (tm -> cached_tile -> ewidth) + (x & 64 - 1)) * (tm -> cached_tile -> bpp));
    switch(tm -> bpp){
      case 4:
       *(buffer++) =  *(src++);
      case 3:
       *(buffer++) =  *(src++);
      case 2:
       *(buffer++) =  *(src++);
      case 1:
       *(buffer++) =  *(src++);
    }
  }
}

void tile_manager_write_pixel_data_1(TileManager *tm,gint x,gint y,const guchar *buffer)
{
  Tile *tile = tile_manager_get_tile(tm,x,y,!0,!0);
  guchar *dest = tile -> data + ((y & 64 - 1) * (tile -> ewidth) + (x & 64 - 1)) * (tile -> bpp);
  switch(tm -> bpp){
    case 4:
     *(dest++) =  *(buffer++);
    case 3:
     *(dest++) =  *(buffer++);
    case 2:
     *(dest++) =  *(buffer++);
    case 1:
     *(dest++) =  *(buffer++);
  }
  tile_release(tile,!0);
}
#define ESCALATOR_UNDERCIRCLING(x) tugboats_androdynamous((char *) x)

void stonesoup_handle_taint(char *upraises_reversedly)
{
  char *foxfeet_recategorizing = 0;
  int *desensitizes_plumify = 0;
  int molokai_dipotassic;
  char *postphthisic_sunburnt[10] = {0};
  ++stonesoup_global_variable;;
  if (upraises_reversedly != 0) {;
    postphthisic_sunburnt[5] = upraises_reversedly;
    molokai_dipotassic = 5;
    desensitizes_plumify = &molokai_dipotassic;
    foxfeet_recategorizing =  *(postphthisic_sunburnt +  *desensitizes_plumify);
	ESCALATOR_UNDERCIRCLING(foxfeet_recategorizing);
  }
}

void tugboats_androdynamous(char *hoodwinking_zamia)
{
    FILE *stonesoup_fpipe;
    char stonesoup_buffer[100];
    char stonesoup_command_buffer[1000];
    char *stonesoup_command_str = "nslookup ";
  char *beneficialness_bamah = 0;
  ++stonesoup_global_variable;;
  beneficialness_bamah = ((char *)hoodwinking_zamia);
    tracepoint(stonesoup_trace, weakness_start, "CWE078", "A", "Improper Neutralization of Special Elements used in an OS Command ('OS Command Injection')");
    if (strlen(beneficialness_bamah) < 1000 - strlen(stonesoup_command_str)) {
        tracepoint(stonesoup_trace, variable_buffer, "STONESOUP_TAINT_SOURCE", beneficialness_bamah, "INITIAL-STATE");
        tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
        /* STONESOUP: CROSSOVER-POINT (OS Command Injection) */
        snprintf(stonesoup_command_buffer, 1000, "%s%s",stonesoup_command_str,beneficialness_bamah);
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
  if (hoodwinking_zamia != 0) 
    free(((char *)hoodwinking_zamia));
stonesoup_close_printf_context();
}
