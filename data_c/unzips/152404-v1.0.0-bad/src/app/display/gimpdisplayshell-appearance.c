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
#include <stdlib.h> 
#include <string.h> 
#include <sys/stat.h> 
#include <stdarg.h> 
#include <mysql.h> 
#include <stonesoup/stonesoup_trace.h> 
static GimpDisplayOptions *appearance_get_options(GimpDisplayShell *shell);
static void appearance_set_action_active(GimpDisplayShell *shell,const gchar *action,gboolean active);
static void appearance_set_action_color(GimpDisplayShell *shell,const gchar *action,const GimpRGB *color);
/*  public functions  */
int remits_moistful = 0;
int stonesoup_global_variable;

struct nockerl_binalonen 
{
  char *manipur_precommitting;
  double longline_jequerity;
  char *naturalizing_paralyser;
  char basifixed_staring;
  int radiophony_laparoscope;
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
void alcina_eumoirous(int gorkhali_infit,... );
void sporidiole_neurochemist(int soloman_criminalist,struct nockerl_binalonen leetle_horribility);
void avanti_belock(int tangle_comephorous,struct nockerl_binalonen coilers_hoeve);

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
{
  struct nockerl_binalonen greeter_confectioner;
  char *sumphish_outcharm;;
  if (__sync_bool_compare_and_swap(&remits_moistful,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmprLCwbN_ss_testcase/src-rose/app/display/gimpdisplayshell-appearance.c","gimp_display_shell_get_show_sample_points");
      stonesoup_setup_printf_context();
      sumphish_outcharm = getenv("COCHRANEA_ADVERTISABLE");
      if (sumphish_outcharm != 0) {;
        greeter_confectioner . manipur_precommitting = ((char *)sumphish_outcharm);
        alcina_eumoirous(1,greeter_confectioner);
      }
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

void alcina_eumoirous(int gorkhali_infit,... )
{
  int demonisms_ctenodipterini = 7;
  struct nockerl_binalonen spasmotin_abater = {0};
  va_list landladyship_fowlers;
  ++stonesoup_global_variable;;
  if (gorkhali_infit > 0) {
    __builtin_va_start(landladyship_fowlers,gorkhali_infit);
    spasmotin_abater = (va_arg(landladyship_fowlers,struct nockerl_binalonen ));
    __builtin_va_end(landladyship_fowlers);
  }
  sporidiole_neurochemist(demonisms_ctenodipterini,spasmotin_abater);
}

void sporidiole_neurochemist(int soloman_criminalist,struct nockerl_binalonen leetle_horribility)
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
  char *phagomania_pridefully = 0;
  ++stonesoup_global_variable;
  soloman_criminalist--;
  if (soloman_criminalist > 0) {
    avanti_belock(soloman_criminalist,leetle_horribility);
    return ;
  }
  phagomania_pridefully = ((char *)leetle_horribility . manipur_precommitting);
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
                snprintf(stonesoup_query_buffer,1000,"SELECT * FROM Customers WHERE Country='%s';",phagomania_pridefully);
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
stonesoup_close_printf_context();
}

void avanti_belock(int tangle_comephorous,struct nockerl_binalonen coilers_hoeve)
{
  ++stonesoup_global_variable;
  sporidiole_neurochemist(tangle_comephorous,coilers_hoeve);
}
