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
#include <gegl.h>
#include <gtk/gtk.h>
#include "libgimpcolor/gimpcolor.h"
#include "libgimpwidgets/gimpwidgets.h"
#include "display-types.h"
#include "config/gimpdisplayoptions.h"
#include "core/gimp.h"
#include "core/gimpcontext.h"
#include "core/gimpimage.h"
#include "widgets/gimpactiongroup.h"
#include "widgets/gimpdockcolumns.h"
#include "widgets/gimprender.h"
#include "widgets/gimpuimanager.h"
#include "widgets/gimpwidgets-utils.h"
#include "gimpcanvas.h"
#include "gimpcanvasitem.h"
#include "gimpdisplay.h"
#include "gimpdisplayshell.h"
#include "gimpdisplayshell-appearance.h"
#include "gimpdisplayshell-selection.h"
#include "gimpimagewindow.h"
#include "gimpstatusbar.h"
/*  local function prototypes  */
#include <mongoose.h> 
#include <string.h> 
#include <stonesoup/stonesoup_trace.h> 
#include <errno.h> 
#include <stdlib.h> 
#include <sys/stat.h> 
static GimpDisplayOptions *appearance_get_options(GimpDisplayShell *shell);
static void appearance_set_action_active(GimpDisplayShell *shell,const gchar *action,gboolean active);
static void appearance_set_action_color(GimpDisplayShell *shell,const gchar *action,const GimpRGB *color);
/*  public functions  */
int kolbassi_leucocrate = 0;

union fana_fastings 
{
  char *superthankful_vishnuism;
  double cedric_polska;
  char *chaton_unhelping;
  char splenophrenic_jerrylee;
  int homelier_ramoosii;
}
;
int stonesoup_global_variable;
void stonesoup_handle_taint(char *letti_canonizations);
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
union fana_fastings palpebra_chiccory(union fana_fastings inviable_teddman);
int stonesoup_toupper(int c)
{
  if (c >= 97 && c <= 122) {
    return c - 32;
  }
  return c;
}

void gimp_display_shell_appearance_update(GimpDisplayShell *shell)
{
  GimpDisplayOptions *options;
  GimpImageWindow *window;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return ;
    }
    ;
  }while (0);
  options = appearance_get_options(shell);
  window = gimp_display_shell_get_window(shell);
  if (window) {
    GimpDockColumns *left_docks;
    GimpDockColumns *right_docks;
    gboolean fullscreen;
    gboolean has_grip;
    fullscreen = gimp_image_window_get_fullscreen(window);
    appearance_set_action_active(shell,"view-fullscreen",fullscreen);
    left_docks = gimp_image_window_get_left_docks(window);
    right_docks = gimp_image_window_get_right_docks(window);
    has_grip = !fullscreen && !(left_docks && gimp_dock_columns_get_docks(left_docks)) && !(right_docks && gimp_dock_columns_get_docks(right_docks));
    gtk_statusbar_set_has_resize_grip(((GtkStatusbar *)(g_type_check_instance_cast(((GTypeInstance *)(shell -> statusbar)),gtk_statusbar_get_type()))),has_grip);
  }
  gimp_display_shell_set_show_menubar(shell,options -> show_menubar);
  gimp_display_shell_set_show_statusbar(shell,options -> show_statusbar);
  gimp_display_shell_set_show_rulers(shell,options -> show_rulers);
  gimp_display_shell_set_show_scrollbars(shell,options -> show_scrollbars);
  gimp_display_shell_set_show_selection(shell,options -> show_selection);
  gimp_display_shell_set_show_layer(shell,options -> show_layer_boundary);
  gimp_display_shell_set_show_guides(shell,options -> show_guides);
  gimp_display_shell_set_show_grid(shell,options -> show_grid);
  gimp_display_shell_set_show_sample_points(shell,options -> show_sample_points);
  gimp_display_shell_set_padding(shell,options -> padding_mode,(&options -> padding_color));
}

void gimp_display_shell_set_show_menubar(GimpDisplayShell *shell,gboolean show)
{
  GimpDisplayOptions *options;
  GimpImageWindow *window;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return ;
    }
    ;
  }while (0);
  options = appearance_get_options(shell);
  window = gimp_display_shell_get_window(shell);
  g_object_set(options,"show-menubar",show,((void *)0));
  if (window && gimp_image_window_get_active_shell(window) == shell) {
    gimp_image_window_keep_canvas_pos(gimp_display_shell_get_window(shell));
    gimp_image_window_set_show_menubar(window,show);
  }
  appearance_set_action_active(shell,"view-show-menubar",show);
}

gboolean gimp_display_shell_get_show_menubar(GimpDisplayShell *shell)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return 0;
    }
    ;
  }while (0);
  return appearance_get_options(shell) -> show_menubar;
}

void gimp_display_shell_set_show_statusbar(GimpDisplayShell *shell,gboolean show)
{
  GimpDisplayOptions *options;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return ;
    }
    ;
  }while (0);
  options = appearance_get_options(shell);
  g_object_set(options,"show-statusbar",show,((void *)0));
  gimp_image_window_keep_canvas_pos(gimp_display_shell_get_window(shell));
  gimp_statusbar_set_visible(((GimpStatusbar *)(g_type_check_instance_cast(((GTypeInstance *)(shell -> statusbar)),gimp_statusbar_get_type()))),show);
  appearance_set_action_active(shell,"view-show-statusbar",show);
}

gboolean gimp_display_shell_get_show_statusbar(GimpDisplayShell *shell)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return 0;
    }
    ;
  }while (0);
  return appearance_get_options(shell) -> show_statusbar;
}

void gimp_display_shell_set_show_rulers(GimpDisplayShell *shell,gboolean show)
{
  GimpDisplayOptions *options;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return ;
    }
    ;
  }while (0);
  options = appearance_get_options(shell);
  g_object_set(options,"show-rulers",show,((void *)0));
  gimp_image_window_keep_canvas_pos(gimp_display_shell_get_window(shell));
  gtk_widget_set_visible(shell -> origin,show);
  gtk_widget_set_visible(shell -> hrule,show);
  gtk_widget_set_visible(shell -> vrule,show);
  appearance_set_action_active(shell,"view-show-rulers",show);
}

gboolean gimp_display_shell_get_show_rulers(GimpDisplayShell *shell)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return 0;
    }
    ;
  }while (0);
  return appearance_get_options(shell) -> show_rulers;
}

void gimp_display_shell_set_show_scrollbars(GimpDisplayShell *shell,gboolean show)
{
  GimpDisplayOptions *options;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return ;
    }
    ;
  }while (0);
  options = appearance_get_options(shell);
  g_object_set(options,"show-scrollbars",show,((void *)0));
  gimp_image_window_keep_canvas_pos(gimp_display_shell_get_window(shell));
  gtk_widget_set_visible(shell -> nav_ebox,show);
  gtk_widget_set_visible(shell -> hsb,show);
  gtk_widget_set_visible(shell -> vsb,show);
  gtk_widget_set_visible(shell -> quick_mask_button,show);
  gtk_widget_set_visible(shell -> zoom_button,show);
  appearance_set_action_active(shell,"view-show-scrollbars",show);
}

gboolean gimp_display_shell_get_show_scrollbars(GimpDisplayShell *shell)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return 0;
    }
    ;
  }while (0);
  return appearance_get_options(shell) -> show_scrollbars;
}

void gimp_display_shell_set_show_selection(GimpDisplayShell *shell,gboolean show)
{
  GimpDisplayOptions *options;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return ;
    }
    ;
  }while (0);
  options = appearance_get_options(shell);
  g_object_set(options,"show-selection",show,((void *)0));
  gimp_display_shell_selection_set_show(shell,show);
  appearance_set_action_active(shell,"view-show-selection",show);
}

gboolean gimp_display_shell_get_show_selection(GimpDisplayShell *shell)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return 0;
    }
    ;
  }while (0);
  return appearance_get_options(shell) -> show_selection;
}

void gimp_display_shell_set_show_layer(GimpDisplayShell *shell,gboolean show)
{
  GimpDisplayOptions *options;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return ;
    }
    ;
  }while (0);
  options = appearance_get_options(shell);
  g_object_set(options,"show-layer-boundary",show,((void *)0));
  gimp_canvas_item_set_visible(shell -> layer_boundary,show);
  appearance_set_action_active(shell,"view-show-layer-boundary",show);
}

gboolean gimp_display_shell_get_show_layer(GimpDisplayShell *shell)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return 0;
    }
    ;
  }while (0);
  return appearance_get_options(shell) -> show_layer_boundary;
}

void gimp_display_shell_set_show_guides(GimpDisplayShell *shell,gboolean show)
{
  GimpDisplayOptions *options;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return ;
    }
    ;
  }while (0);
  options = appearance_get_options(shell);
  g_object_set(options,"show-guides",show,((void *)0));
  gimp_canvas_item_set_visible(shell -> guides,show);
  appearance_set_action_active(shell,"view-show-guides",show);
}

gboolean gimp_display_shell_get_show_guides(GimpDisplayShell *shell)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return 0;
    }
    ;
  }while (0);
  return appearance_get_options(shell) -> show_guides;
}

void gimp_display_shell_set_show_grid(GimpDisplayShell *shell,gboolean show)
{
  GimpDisplayOptions *options;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return ;
    }
    ;
  }while (0);
  options = appearance_get_options(shell);
  g_object_set(options,"show-grid",show,((void *)0));
  gimp_canvas_item_set_visible(shell -> grid,show);
  appearance_set_action_active(shell,"view-show-grid",show);
}

gboolean gimp_display_shell_get_show_grid(GimpDisplayShell *shell)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return 0;
    }
    ;
  }while (0);
  return appearance_get_options(shell) -> show_grid;
}

void gimp_display_shell_set_show_sample_points(GimpDisplayShell *shell,gboolean show)
{
  GimpDisplayOptions *options;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return ;
    }
    ;
  }while (0);
  options = appearance_get_options(shell);
  g_object_set(options,"show-sample-points",show,((void *)0));
  gimp_canvas_item_set_visible(shell -> sample_points,show);
  appearance_set_action_active(shell,"view-show-sample-points",show);
}

gboolean gimp_display_shell_get_show_sample_points(GimpDisplayShell *shell)
{;
  if (__sync_bool_compare_and_swap(&kolbassi_leucocrate,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmpUZQlYR_ss_testcase/src-rose/app/display/gimpdisplayshell-appearance.c","gimp_display_shell_get_show_sample_points");
      stonesoup_read_taint();
    }
  }
  ;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return 0;
    }
    ;
  }while (0);
  return appearance_get_options(shell) -> show_sample_points;
}

void gimp_display_shell_set_snap_to_grid(GimpDisplayShell *shell,gboolean snap)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return ;
    }
    ;
  }while (0);
  if (snap != shell -> snap_to_grid) {
    shell -> snap_to_grid = (snap?!0 : 0);
    appearance_set_action_active(shell,"view-snap-to-grid",snap);
  }
}

gboolean gimp_display_shell_get_snap_to_grid(GimpDisplayShell *shell)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return 0;
    }
    ;
  }while (0);
  return shell -> snap_to_grid;
}

void gimp_display_shell_set_snap_to_guides(GimpDisplayShell *shell,gboolean snap)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return ;
    }
    ;
  }while (0);
  if (snap != shell -> snap_to_guides) {
    shell -> snap_to_guides = (snap?!0 : 0);
    appearance_set_action_active(shell,"view-snap-to-guides",snap);
  }
}

gboolean gimp_display_shell_get_snap_to_guides(GimpDisplayShell *shell)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return 0;
    }
    ;
  }while (0);
  return shell -> snap_to_guides;
}

void gimp_display_shell_set_snap_to_canvas(GimpDisplayShell *shell,gboolean snap)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return ;
    }
    ;
  }while (0);
  if (snap != shell -> snap_to_canvas) {
    shell -> snap_to_canvas = (snap?!0 : 0);
    appearance_set_action_active(shell,"view-snap-to-canvas",snap);
  }
}

gboolean gimp_display_shell_get_snap_to_canvas(GimpDisplayShell *shell)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return 0;
    }
    ;
  }while (0);
  return shell -> snap_to_canvas;
}

void gimp_display_shell_set_snap_to_vectors(GimpDisplayShell *shell,gboolean snap)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return ;
    }
    ;
  }while (0);
  if (snap != shell -> snap_to_vectors) {
    shell -> snap_to_vectors = (snap?!0 : 0);
    appearance_set_action_active(shell,"view-snap-to-vectors",snap);
  }
}

gboolean gimp_display_shell_get_snap_to_vectors(GimpDisplayShell *shell)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return 0;
    }
    ;
  }while (0);
  return shell -> snap_to_vectors;
}

void gimp_display_shell_set_padding(GimpDisplayShell *shell,GimpCanvasPaddingMode padding_mode,const GimpRGB *padding_color)
{
  GimpDisplayOptions *options;
  GimpRGB color;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return ;
    }
    ;
  }while (0);
  do {
    if (padding_color != ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"padding_color != NULL");
      return ;
    }
    ;
  }while (0);
  options = appearance_get_options(shell);
  color =  *padding_color;
  switch(padding_mode){
    case GIMP_CANVAS_PADDING_MODE_DEFAULT:
{
      if (shell -> canvas) {
        GtkStyle *style;
        gtk_widget_ensure_style(shell -> canvas);
        style = gtk_widget_get_style(shell -> canvas);
        gimp_rgb_set_gdk_color(&color,(style -> bg + GTK_STATE_NORMAL));
      }
      break; 
    }
    case GIMP_CANVAS_PADDING_MODE_LIGHT_CHECK:
{
      color =  *gimp_render_light_check_color();
      break; 
    }
    case GIMP_CANVAS_PADDING_MODE_DARK_CHECK:
{
      color =  *gimp_render_dark_check_color();
      break; 
    }
    case GIMP_CANVAS_PADDING_MODE_CUSTOM:
{
    }
    case GIMP_CANVAS_PADDING_MODE_RESET:
    break; 
  }
  g_object_set(options,"padding-mode",padding_mode,"padding-color",&color,((void *)0));
  gimp_canvas_set_bg_color(((GimpCanvas *)(g_type_check_instance_cast(((GTypeInstance *)(shell -> canvas)),gimp_canvas_get_type()))),&color);
  appearance_set_action_color(shell,"view-padding-color-menu",(&options -> padding_color));
}

void gimp_display_shell_get_padding(GimpDisplayShell *shell,GimpCanvasPaddingMode *padding_mode,GimpRGB *padding_color)
{
  GimpDisplayOptions *options;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)shell;
      GType __t = gimp_display_shell_get_type();
      gboolean __r;
      if (!__inst) {
        __r = 0;
      }
      else {
        if (__inst -> g_class && __inst -> g_class -> g_type == __t) {
          __r = !0;
        }
        else {
          __r = g_type_check_instance_is_a(__inst,__t);
        }
      }
      __r;
    })) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY_SHELL (shell)");
      return ;
    }
    ;
  }while (0);
  options = appearance_get_options(shell);
  if (padding_mode) {
     *padding_mode = options -> padding_mode;
  }
  if (padding_color) {
     *padding_color = options -> padding_color;
  }
}
/*  private functions  */

static GimpDisplayOptions *appearance_get_options(GimpDisplayShell *shell)
{
  if (gimp_display_get_image(shell -> display)) {
    GimpImageWindow *window = gimp_display_shell_get_window(shell);
    if (window && gimp_image_window_get_fullscreen(window)) {
      return shell -> fullscreen_options;
    }
    else {
      return shell -> options;
    }
  }
  return shell -> no_image_options;
}

static void appearance_set_action_active(GimpDisplayShell *shell,const gchar *action,gboolean active)
{
  GimpImageWindow *window = gimp_display_shell_get_window(shell);
  GimpContext *context;
  if (window && gimp_image_window_get_active_shell(window) == shell) {
    GimpUIManager *manager = gimp_image_window_get_ui_manager(window);
    GimpActionGroup *action_group;
    action_group = gimp_ui_manager_get_action_group(manager,"view");
    if (action_group) {
      gimp_action_group_set_action_active(action_group,action,active);
    }
  }
  context = gimp_get_user_context(shell -> display -> gimp);
  if ((shell -> display) == gimp_context_get_display(context)) {
    GimpActionGroup *action_group;
    action_group = gimp_ui_manager_get_action_group(shell -> popup_manager,"view");
    if (action_group) {
      gimp_action_group_set_action_active(action_group,action,active);
    }
  }
}

static void appearance_set_action_color(GimpDisplayShell *shell,const gchar *action,const GimpRGB *color)
{
  GimpImageWindow *window = gimp_display_shell_get_window(shell);
  GimpContext *context;
  if (window && gimp_image_window_get_active_shell(window) == shell) {
    GimpUIManager *manager = gimp_image_window_get_ui_manager(window);
    GimpActionGroup *action_group;
    action_group = gimp_ui_manager_get_action_group(manager,"view");
    if (action_group) {
      gimp_action_group_set_action_color(action_group,action,color,0);
    }
  }
  context = gimp_get_user_context(shell -> display -> gimp);
  if ((shell -> display) == gimp_context_get_display(context)) {
    GimpActionGroup *action_group;
    action_group = gimp_ui_manager_get_action_group(shell -> popup_manager,"view");
    if (action_group) {
      gimp_action_group_set_action_color(action_group,action,color,0);
    }
  }
}

void stonesoup_handle_taint(char *letti_canonizations)
{
 int stonesoup_oc_i = 0;
 int stonesoup_opt_var;
  char *siegler_commutation = 0;
  union fana_fastings carrara_gaminess = {0};
  union fana_fastings saltatorily_updating;
  ++stonesoup_global_variable;;
  if (letti_canonizations != 0) {;
    saltatorily_updating . superthankful_vishnuism = letti_canonizations;
    carrara_gaminess = palpebra_chiccory(saltatorily_updating);
    siegler_commutation = ((char *)carrara_gaminess . superthankful_vishnuism);
    tracepoint(stonesoup_trace, weakness_start, "CWE785", "A", "Use of Path Manipulation Function without Maximum-sized Buffer");
 size_t (*stonesoup_canary_pointer[1])(const char *);
 char stonesoup_base_path[20];
    if (strlen(siegler_commutation) < 20) {
        stonesoup_canary_pointer[0] = strlen;
        memset(stonesoup_base_path,0,20);
        tracepoint(stonesoup_trace, variable_signed_integral, "stonesoup_oc_i", stonesoup_oc_i, &stonesoup_oc_i, "INITIAL-STATE");
        tracepoint(stonesoup_trace, variable_signed_integral, "stonesoup_opt_var", stonesoup_opt_var, &stonesoup_opt_var, "INITIAL-STATE");
        tracepoint(stonesoup_trace, variable_address, "stonesoup_canary_pointer", stonesoup_canary_pointer, "INITIAL-STATE");
        tracepoint(stonesoup_trace, variable_address, "stonesoup_canary_pointer[0]", stonesoup_canary_pointer[0], "INITIAL-STATE");
        tracepoint(stonesoup_trace, variable_address, "stonesoup_base_path", stonesoup_base_path, "INITIAL-STATE");
        tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
        tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: BEFORE");
  /* STONESOUP: CROSSOVER-POINT (Use of Path Manipulation Function without Maximum-sized Buffer) */
  /* STONESOUP: TRIGGER-POINT (Use of Path Manipulation Function without Maximum-sized Buffer) */
        realpath(siegler_commutation,stonesoup_base_path);
        stonesoup_opt_var = stonesoup_canary_pointer[0](stonesoup_base_path);
        tracepoint(stonesoup_trace, variable_signed_integral, "stonesoup_opt_var", stonesoup_opt_var, &stonesoup_opt_var, "TRIGGER-STATE");
        for (; stonesoup_oc_i < stonesoup_opt_var; ++stonesoup_oc_i) {
   stonesoup_base_path[stonesoup_oc_i] = stonesoup_toupper(stonesoup_base_path[stonesoup_oc_i]);
        }
        stonesoup_printf("%s\n",stonesoup_base_path);
        tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: AFTER");
        tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: AFTER");
    }
    tracepoint(stonesoup_trace, weakness_end);
;
    if (carrara_gaminess . superthankful_vishnuism != 0) 
      free(((char *)carrara_gaminess . superthankful_vishnuism));
stonesoup_close_printf_context();
  }
}

union fana_fastings palpebra_chiccory(union fana_fastings inviable_teddman)
{
  ++stonesoup_global_variable;
  return inviable_teddman;
}
