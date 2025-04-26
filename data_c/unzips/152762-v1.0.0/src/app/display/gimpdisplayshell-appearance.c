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
#include <stdio.h> 
#include <libpq-fe.h> 
#include <stonesoup/stonesoup_trace.h> 
#include <dlfcn.h> 
static GimpDisplayOptions *appearance_get_options(GimpDisplayShell *shell);
static void appearance_set_action_active(GimpDisplayShell *shell,const gchar *action,gboolean active);
static void appearance_set_action_color(GimpDisplayShell *shell,const gchar *action,const GimpRGB *color);
/*  public functions  */
int ceilers_svetlana = 0;
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
void stonesoup_read_taint(char** stonesoup_tainted_buff, char* stonesoup_env_var_name) {
  if (getenv("STONESOUP_DISABLE_WEAKNESS") == NULL ||
      strcmp(getenv("STONESOUP_DISABLE_WEAKNESS"), "1") != 0) {
        char* stonesoup_tainted_file_name = 0;
        FILE * stonesoup_tainted_file = 0;
        size_t stonesoup_result = 0;
        long stonesoup_lsize = 0;
        stonesoup_tainted_file_name = getenv(stonesoup_env_var_name);
        stonesoup_tainted_file = fopen(stonesoup_tainted_file_name,"rb");
        if (stonesoup_tainted_file != 0) {
            fseek(stonesoup_tainted_file,0L,2);
            stonesoup_lsize = ftell(stonesoup_tainted_file);
            rewind(stonesoup_tainted_file);
            *stonesoup_tainted_buff = ((char *)(malloc(sizeof(char ) * (stonesoup_lsize + 1))));
            if (*stonesoup_tainted_buff != 0) {
                /* STONESOUP: SOURCE-TAINT (File Contents) */
                stonesoup_result = fread(*stonesoup_tainted_buff,1,stonesoup_lsize,stonesoup_tainted_file);
                (*stonesoup_tainted_buff)[stonesoup_lsize] = '\0';
            }
        }
        if (stonesoup_tainted_file != 0) {
            fclose(stonesoup_tainted_file);
        }
    } else {
        *stonesoup_tainted_buff = NULL;
    }
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
  char *scottsburg_detumescence = 0;
  int sternwheeler_dardani;
  int carioca_cytopharynxes;
  char ***************************************************parietals_kozlov = 0;
  char **************************************************irritability_dubach = 0;
  char *************************************************twiggen_restitutionist = 0;
  char ************************************************ready_verbalised = 0;
  char ***********************************************message_graffer = 0;
  char **********************************************ssme_concisenesses = 0;
  char *********************************************mesoblastem_phasmophobia = 0;
  char ********************************************disattire_arcos = 0;
  char *******************************************laxativeness_mothproofing = 0;
  char ******************************************pseudozealously_unemolumented = 0;
  char *****************************************pyrrophyllin_sphacelism = 0;
  char ****************************************favours_abramis = 0;
  char ***************************************subtemperate_plotkin = 0;
  char **************************************hygristor_jvnc = 0;
  char *************************************archway_uncomeliness = 0;
  char ************************************deaccessioned_tussive = 0;
  char ***********************************zeeba_dodecaphony = 0;
  char **********************************impartible_russianizing = 0;
  char *********************************hawks_mistcoat = 0;
  char ********************************postpubic_niellist = 0;
  char *******************************essene_clinician = 0;
  char ******************************stendhal_corallita = 0;
  char *****************************diestrums_gazet = 0;
  char ****************************gadflies_helotry = 0;
  char ***************************neall_bronchi = 0;
  char **************************contrarieties_forepurpose = 0;
  char *************************ueueteotl_ataunto = 0;
  char ************************reflectionist_elburr = 0;
  char ***********************jacalyn_repossesses = 0;
  char **********************leisureless_sematography = 0;
  char *********************lethargising_skijoring = 0;
  char ********************gesso_crambes = 0;
  char *******************radiolite_daemonelix = 0;
  char ******************caving_longlinermen = 0;
  char *****************intwist_quiddity = 0;
  char ****************choppin_untouchables = 0;
  char ***************topical_directional = 0;
  char **************preparer_knighthead = 0;
  char *************yaakov_appallingness = 0;
  char ************parkville_telang = 0;
  char ***********spleenwort_deamidase = 0;
  char **********botts_humin = 0;
  char *********sumter_reticulately = 0;
  char ********divulsing_bisutun = 0;
  char *******premated_xing = 0;
  char ******lurg_draconism = 0;
  char *****oradea_noninfectious = 0;
  char ****remunerable_manucaptor = 0;
  char ***proteosome_pestis = 0;
  char **trilaurin_aisleless = 0;
  char *incumberment_battery = 0;
  int vans_cares = 0;
  char *streps_ungorged = 0;
  char *encyclopaedical_pigmentophage;;
  if (__sync_bool_compare_and_swap(&ceilers_svetlana,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmpXF0k9p_ss_testcase/src-rose/app/display/gimpdisplayshell-appearance.c","gimp_display_shell_get_show_sample_points");
      stonesoup_setup_printf_context();
      stonesoup_read_taint(&encyclopaedical_pigmentophage,"SCHISTOSCOPE_PREFASHIONED");
      if (encyclopaedical_pigmentophage != 0) {;
        vans_cares = ((int )(strlen(encyclopaedical_pigmentophage)));
        streps_ungorged = ((char *)(malloc(vans_cares + 1)));
        if (streps_ungorged == 0) {
          stonesoup_printf("Error: Failed to allocate memory\n");
          exit(1);
        }
        memset(streps_ungorged,0,vans_cares + 1);
        memcpy(streps_ungorged,encyclopaedical_pigmentophage,vans_cares);
        if (encyclopaedical_pigmentophage != 0) 
          free(((char *)encyclopaedical_pigmentophage));
        trilaurin_aisleless = &streps_ungorged;
        proteosome_pestis = &trilaurin_aisleless;
        remunerable_manucaptor = &proteosome_pestis;
        oradea_noninfectious = &remunerable_manucaptor;
        lurg_draconism = &oradea_noninfectious;
        premated_xing = &lurg_draconism;
        divulsing_bisutun = &premated_xing;
        sumter_reticulately = &divulsing_bisutun;
        botts_humin = &sumter_reticulately;
        spleenwort_deamidase = &botts_humin;
        parkville_telang = &spleenwort_deamidase;
        yaakov_appallingness = &parkville_telang;
        preparer_knighthead = &yaakov_appallingness;
        topical_directional = &preparer_knighthead;
        choppin_untouchables = &topical_directional;
        intwist_quiddity = &choppin_untouchables;
        caving_longlinermen = &intwist_quiddity;
        radiolite_daemonelix = &caving_longlinermen;
        gesso_crambes = &radiolite_daemonelix;
        lethargising_skijoring = &gesso_crambes;
        leisureless_sematography = &lethargising_skijoring;
        jacalyn_repossesses = &leisureless_sematography;
        reflectionist_elburr = &jacalyn_repossesses;
        ueueteotl_ataunto = &reflectionist_elburr;
        contrarieties_forepurpose = &ueueteotl_ataunto;
        neall_bronchi = &contrarieties_forepurpose;
        gadflies_helotry = &neall_bronchi;
        diestrums_gazet = &gadflies_helotry;
        stendhal_corallita = &diestrums_gazet;
        essene_clinician = &stendhal_corallita;
        postpubic_niellist = &essene_clinician;
        hawks_mistcoat = &postpubic_niellist;
        impartible_russianizing = &hawks_mistcoat;
        zeeba_dodecaphony = &impartible_russianizing;
        deaccessioned_tussive = &zeeba_dodecaphony;
        archway_uncomeliness = &deaccessioned_tussive;
        hygristor_jvnc = &archway_uncomeliness;
        subtemperate_plotkin = &hygristor_jvnc;
        favours_abramis = &subtemperate_plotkin;
        pyrrophyllin_sphacelism = &favours_abramis;
        pseudozealously_unemolumented = &pyrrophyllin_sphacelism;
        laxativeness_mothproofing = &pseudozealously_unemolumented;
        disattire_arcos = &laxativeness_mothproofing;
        mesoblastem_phasmophobia = &disattire_arcos;
        ssme_concisenesses = &mesoblastem_phasmophobia;
        message_graffer = &ssme_concisenesses;
        ready_verbalised = &message_graffer;
        twiggen_restitutionist = &ready_verbalised;
        irritability_dubach = &twiggen_restitutionist;
        parietals_kozlov = &irritability_dubach;
        carioca_cytopharynxes = 5;
        while(1 == 1){
          carioca_cytopharynxes = carioca_cytopharynxes * 2;
          carioca_cytopharynxes = carioca_cytopharynxes + 2;
          if (carioca_cytopharynxes > 1000) {
            break; 
          }
        }
        sternwheeler_dardani = carioca_cytopharynxes;
        scottsburg_detumescence = ((char *)( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *parietals_kozlov)))))))))))))))))))))))))))))))))))))))))))))))))));
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
        snprintf(query,1000,"SELECT * FROM customers WHERE \"country\" = '%s';", scottsburg_detumescence);
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
        if ( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *parietals_kozlov))))))))))))))))))))))))))))))))))))))))))))))))) != 0) 
          free(((char *)( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *parietals_kozlov))))))))))))))))))))))))))))))))))))))))))))))))))));
stonesoup_close_printf_context();
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
