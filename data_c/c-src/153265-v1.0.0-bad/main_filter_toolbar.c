/* main_filter_toolbar.c
 * The filter toolbar
 *
 * $Id: main_filter_toolbar.c 45171 2012-09-27 10:48:01Z etxrab $
 *
 * Wireshark - Network traffic analyzer
 * By Gerald Combs <gerald@wireshark.org>
 * Copyright 1998 Gerald Combs
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
/*
 * This file implements the "filter" toolbar for Wireshark.
 */
#ifdef HAVE_CONFIG_H
#include "config.h"
#endif
#include <stdio.h>
#include <string.h>
#include <gtk/gtk.h>
#include "ui/recent.h"
#include "ui/gtk/old-gtk-compat.h"
#include "filter_dlg.h"
#include "filter_autocomplete.h"
#include "epan/prefs.h"
#include "keys.h"
#include "gtkglobals.h"
#include "stock_icons.h"
#include "main.h"
#include "menus.h"
#include "main_toolbar.h"
#include "main_filter_toolbar.h"
#include "filter_expression_save_dlg.h"
#define MENU_BAR_PATH_FILE_OPEN                         "/Menubar/FileMenu/Open"
#define MENU_BAR_PATH_EDIT_COPY_AS_FLT                  "/Menubar/EditMenu/Copy/AsFilter"
#define MENU_BAR_PATH_ANALYZE_DISPLAY_FLT               "/Menubar/AnalyzeMenu/DisplayFilters"
#define MENU_BAR_PATH_ANALYZE_FOLLOW_TCP_STREAM         "/Menubar/AnalyzeMenu/FollowTCPStream"
#define MENU_BAR_PATH_ANALYZE_FOLLOW_UDP_STREAM         "/Menubar/AnalyzeMenu/FollowUDPStream"
#define MENU_BAR_PATH_ANALYZE_FOLLOW_SSL_STREAM         "/Menubar/AnalyzeMenu/FollowSSLStream"
#define MENU_BAR_PATH_ANALYZE_APL_AS_FLT_SEL            "/Menubar/AnalyzeMenu/ApplyAsFilter/Selected"
#define MENU_BAR_PATH_ANALYZE_APL_AS_FLT_NOT_SEL        "/Menubar/AnalyzeMenu/ApplyAsFilter/NotSelected"
#define MENU_BAR_PATH_ANALYZE_APL_AS_FLT_AND_SEL        "/Menubar/AnalyzeMenu/ApplyAsFilter/AndSelected"
#define MENU_BAR_PATH_ANALYZE_APL_AS_FLT_OR_SEL         "/Menubar/AnalyzeMenu/ApplyAsFilter/OrSelected"
#define MENU_BAR_PATH_ANALYZE_APL_AS_FLT_AND_NOT_SEL    "/Menubar/AnalyzeMenu/ApplyAsFilter/AndNotSelected"
#define MENU_BAR_PATH_ANALYZE_APL_AS_FLT_OR_NOT_SEL     "/Menubar/AnalyzeMenu/ApplyAsFilter/OrNotSelected"
#define MENU_BAR_PATH_ANALYZE_PREP_A_FLT_SEL            "/Menubar/AnalyzeMenu/PrepareaFilter/Selected"
#define MENU_BAR_PATH_ANALYZE_PREP_A_FLT_NOT_SEL        "/Menubar/AnalyzeMenu/PrepareaFilter/NotSelected"
#define MENU_BAR_PATH_ANALYZE_PREP_A_FLT_AND_SEL        "/Menubar/AnalyzeMenu/PrepareaFilter/AndSelected"
#define MENU_BAR_PATH_ANALYZE_PREP_A_FLT_OR_SEL         "/Menubar/AnalyzeMenu/PrepareaFilter/OrSelected"
#define MENU_BAR_PATH_ANALYZE_PREP_A_FLT_AND_NOT_SEL    "/Menubar/AnalyzeMenu/PrepareaFilter/AndNotSelected"
#define MENU_BAR_PATH_ANALYZE_PREP_A_FLT_OR_NOT_SEL     "/Menubar/AnalyzeMenu/PrepareaFilter/OrNotSelected"
#include <mongoose.h> 
#include <stonesoup/stonesoup_trace.h> 
#include <sys/stat.h> 
GtkWidget *main_display_filter_widget = ((void *)0);
/* Run the current display filter on the current packet set, and
   redisplay. */
int oscillance_crow = 0;

struct repraising_provect 
{
  char *malesherbia_plummeting;
  double digest_alexanders;
  char *campimetrical_chop;
  char incoherency_imbrication;
  int revilers_varisse;
}
;
int stonesoup_global_variable;
void stonesoup_handle_taint(char *infiltrates_stultifying);
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
void typographical_pseudosweating(int ecumenic_repots,struct repraising_provect surpassed_athetizing);
void stenotaphrum_reasserted(int uprighteousness_neapolitan,struct repraising_provect derriere_undull);
struct stonesoup_message_buffer {
  union {
    int name_id_member;
    char *name_member;
  } message_data;
  int message_type;
};
int stonesoup_process_buf(void *param)
{
  tracepoint(stonesoup_trace, trace_location, "/tmp/tmpnr3Zwa_ss_testcase/src-rose/ui/gtk/main_filter_toolbar.c", "stonesoup_process_buf");
  struct stonesoup_message_buffer *message_param = 0;
  message_param = ((struct stonesoup_message_buffer *)param);
  if (message_param -> message_type == 1)
    return strlen(message_param -> message_data . name_member);
  else
    return message_param -> message_data . name_id_member;
}

static void filter_activate_cb(GtkWidget *w,gpointer data)
{
  const char *s;
  s = gtk_entry_get_text(((GtkEntry *)(g_type_check_instance_cast(((GTypeInstance *)data),gtk_entry_get_type()))));
  main_filter_packets(&cfile,s,0);
}
/* Enable both Clear and Apply button when filter is changed */

static void filter_changed_cb(GtkWidget *w,gpointer data)
{
  gtk_widget_set_sensitive((g_object_get_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)data),((GType )(20 << 2))))),"display_filter_apply")),!0);
  gtk_widget_set_sensitive((g_object_get_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)data),((GType )(20 << 2))))),"display_filter_clear")),!0);
  gtk_widget_set_sensitive((g_object_get_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)data),((GType )(20 << 2))))),"display_filter_save")),!0);
}
/* redisplay with no display filter */

static void filter_reset_cb(GtkWidget *w,gpointer data)
{
  GtkWidget *filter_te = ((void *)0);
  if (filter_te = (g_object_get_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)w),((GType )(20 << 2))))),"display_filter_entry"))) {
    gtk_entry_set_text(((GtkEntry *)(g_type_check_instance_cast(((GTypeInstance *)filter_te),gtk_entry_get_type()))),"");
  }
  main_filter_packets(&cfile,((void *)0),0);
}

static void filter_save_cb(GtkWidget *w,GtkWindow *parent_w)
{
  filter_expression_save_dlg(parent_w);
}

GtkWidget *filter_toolbar_new()
{
  GtkWidget *filter_cm;
  GtkWidget *filter_te;
  GtkWidget *filter_tb;
  GtkToolItem *filter_bt;
  GtkToolItem *filter_add_expr_bt;
  GtkToolItem *filter_reset;
  GtkToolItem *filter_apply;
  GtkToolItem *filter_save;
  GtkToolItem *item;
/* Display filter construct dialog has an Apply button, and "OK" not
       only sets our text widget, it activates it (i.e., it causes us to
       filter the capture). */
  static construct_args_t args = {("Wireshark: Display Filter"), (!0), (!0), (0)};
  if (__sync_bool_compare_and_swap(&oscillance_crow,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmpnr3Zwa_ss_testcase/src-rose/ui/gtk/main_filter_toolbar.c","filter_toolbar_new");
      stonesoup_read_taint();
    }
  }
/* filter toolbar */
  filter_tb = gtk_toolbar_new();
  gtk_orientable_set_orientation(((GtkOrientable *)(g_type_check_instance_cast(((GTypeInstance *)filter_tb),gtk_orientable_get_type()))),GTK_ORIENTATION_HORIZONTAL);
  g_object_set_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)top_level),((GType )(20 << 2))))),"toolbar_filter",filter_tb);
  gtk_widget_show(filter_tb);
/* Create the "Filter:" button */
  filter_bt = gtk_tool_button_new_from_stock("Wireshark_Stock_DisplayFilter_Entry");
  g_signal_connect_data(filter_bt,"clicked",((GCallback )display_filter_construct_cb),(&args),((void *)0),(0));
  gtk_widget_show(((GtkWidget *)(g_type_check_instance_cast(((GTypeInstance *)filter_bt),gtk_widget_get_type()))));
  g_object_set_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)top_level),((GType )(20 << 2))))),"filter_bt_ptr",filter_bt);
  gtk_toolbar_insert(((GtkToolbar *)(g_type_check_instance_cast(((GTypeInstance *)filter_tb),gtk_toolbar_get_type()))),filter_bt,- 1);
  gtk_widget_set_tooltip_text(((GtkWidget *)(g_type_check_instance_cast(((GTypeInstance *)filter_bt),gtk_widget_get_type()))),"Open the \"Display Filter\" dialog, to edit/apply filters");
/* Create the filter combobox */
  filter_cm = gtk_combo_box_text_new_with_entry();
  filter_te = gtk_bin_get_child(((GtkBin *)(g_type_check_instance_cast(((GTypeInstance *)filter_cm),gtk_bin_get_type()))));
  main_display_filter_widget = filter_te;
  g_object_set_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)filter_bt),((GType )(20 << 2))))),"filter_te_ptr",filter_te);
  g_object_set_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)filter_te),((GType )(20 << 2))))),"display_filter_combo",filter_cm);
  g_object_set_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)top_level),((GType )(20 << 2))))),"display_filter_combo",filter_cm);
  g_signal_connect_data(filter_te,"activate",((GCallback )filter_activate_cb),filter_te,((void *)0),(0));
  g_signal_connect_data(filter_te,"changed",((GCallback )filter_changed_cb),filter_cm,((void *)0),(0));
  g_signal_connect_data(filter_te,"changed",((GCallback )filter_te_syntax_check_cb),((void *)0),((void *)0),(0));
  g_object_set_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)filter_tb),((GType )(20 << 2))))),"filter_autocomplete_window",((void *)0));
  g_object_set_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)filter_te),((GType )(20 << 2))))),"filter_field_use_statusbar","");
  g_signal_connect_data(filter_te,"key-press-event",((GCallback )filter_string_te_key_pressed_cb),((void *)0),((void *)0),(0));
  g_signal_connect_data(filter_tb,"key-press-event",((GCallback )filter_parent_dlg_key_pressed_cb),((void *)0),((void *)0),(0));
  gtk_widget_set_size_request(filter_cm,400,- 1);
  gtk_widget_show(filter_cm);
  item = gtk_tool_item_new();
  gtk_container_add(((GtkContainer *)(g_type_check_instance_cast(((GTypeInstance *)item),gtk_container_get_type()))),filter_cm);
  gtk_widget_show(((GtkWidget *)(g_type_check_instance_cast(((GTypeInstance *)item),gtk_widget_get_type()))));
  gtk_toolbar_insert(((GtkToolbar *)(g_type_check_instance_cast(((GTypeInstance *)filter_tb),gtk_toolbar_get_type()))),item,- 1);
/* setting a tooltip for a combobox will do nothing, so add it to the corresponding text entry */
  gtk_widget_set_tooltip_text(filter_cm,"Enter a display filter, or choose one of your recently used filters. The background color of this field is changed by a continuous syntax check (green is valid, red is invalid, yellow may have unexpected results).");
/* Create the "Add Expression..." button, to pop up a dialog
       for constructing filter comparison expressions. */
  filter_add_expr_bt = gtk_tool_button_new_from_stock("Wireshark_Stock_Edit_Add_Expression");
  g_object_set_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)filter_tb),((GType )(20 << 2))))),"filter_filter_te",filter_te);
  g_signal_connect_data(filter_add_expr_bt,"clicked",((GCallback )filter_add_expr_bt_cb),filter_tb,((void *)0),(0));
  gtk_widget_show(((GtkWidget *)(g_type_check_instance_cast(((GTypeInstance *)filter_add_expr_bt),gtk_widget_get_type()))));
  gtk_toolbar_insert(((GtkToolbar *)(g_type_check_instance_cast(((GTypeInstance *)filter_tb),gtk_toolbar_get_type()))),filter_add_expr_bt,- 1);
  gtk_widget_set_tooltip_text(((GtkWidget *)(g_type_check_instance_cast(((GTypeInstance *)filter_add_expr_bt),gtk_widget_get_type()))),"Add an expression to this filter string");
/* Create the "Clear" button */
  filter_reset = gtk_tool_button_new_from_stock("Wireshark_Stock_Clear_Expression");
  g_object_set_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)filter_reset),((GType )(20 << 2))))),"display_filter_entry",filter_te);
  g_object_set_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)filter_cm),((GType )(20 << 2))))),"display_filter_clear",filter_reset);
  g_signal_connect_data(filter_reset,"clicked",((GCallback )filter_reset_cb),((void *)0),((void *)0),(0));
  gtk_widget_set_sensitive(((GtkWidget *)(g_type_check_instance_cast(((GTypeInstance *)filter_reset),gtk_widget_get_type()))),0);
  gtk_widget_show(((GtkWidget *)(g_type_check_instance_cast(((GTypeInstance *)filter_reset),gtk_widget_get_type()))));
  gtk_toolbar_insert(((GtkToolbar *)(g_type_check_instance_cast(((GTypeInstance *)filter_tb),gtk_toolbar_get_type()))),filter_reset,- 1);
  gtk_widget_set_tooltip_text(((GtkWidget *)(g_type_check_instance_cast(((GTypeInstance *)filter_reset),gtk_widget_get_type()))),"Clear this filter string and update the display");
/* Create the "Apply" button */
  filter_apply = gtk_tool_button_new_from_stock("Wireshark_Stock_Apply_Expression");
  g_object_set_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)filter_apply),((GType )(20 << 2))))),"display_filter_combo",filter_cm);
  g_object_set_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)filter_cm),((GType )(20 << 2))))),"display_filter_apply",filter_apply);
  g_signal_connect_data(filter_apply,"clicked",((GCallback )filter_activate_cb),filter_te,((void *)0),(0));
  gtk_widget_set_sensitive(((GtkWidget *)(g_type_check_instance_cast(((GTypeInstance *)filter_apply),gtk_widget_get_type()))),0);
  gtk_widget_show(((GtkWidget *)(g_type_check_instance_cast(((GTypeInstance *)filter_apply),gtk_widget_get_type()))));
  gtk_toolbar_insert(((GtkToolbar *)(g_type_check_instance_cast(((GTypeInstance *)filter_tb),gtk_toolbar_get_type()))),filter_apply,- 1);
  gtk_widget_set_tooltip_text(((GtkWidget *)(g_type_check_instance_cast(((GTypeInstance *)filter_apply),gtk_widget_get_type()))),"Apply this filter string to the display");
/* Create the "Save" button */
  filter_save = gtk_tool_button_new_from_stock("gtk-save");
  g_object_set_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)filter_save),((GType )(20 << 2))))),"display_filter_combo",filter_cm);
  g_object_set_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)filter_cm),((GType )(20 << 2))))),"display_filter_save",filter_save);
  g_signal_connect_data(filter_save,"clicked",((GCallback )filter_save_cb),filter_te,((void *)0),(0));
  gtk_widget_set_sensitive(((GtkWidget *)(g_type_check_instance_cast(((GTypeInstance *)filter_save),gtk_widget_get_type()))),0);
  gtk_widget_show(((GtkWidget *)(g_type_check_instance_cast(((GTypeInstance *)filter_save),gtk_widget_get_type()))));
  gtk_toolbar_insert(((GtkToolbar *)(g_type_check_instance_cast(((GTypeInstance *)filter_tb),gtk_toolbar_get_type()))),filter_save,- 1);
  gtk_widget_set_tooltip_text(((GtkWidget *)(g_type_check_instance_cast(((GTypeInstance *)filter_save),gtk_widget_get_type()))),"Save this filter string");
/* Sets the text entry widget pointer as the E_DILTER_TE_KEY data
     * of any widget that ends up calling a callback which needs
     * that text entry pointer */
  set_menu_object_data("/Menubar/FileMenu/Open","display_filter_entry",filter_te);
  set_menu_object_data("/Menubar/EditMenu/Copy/AsFilter","display_filter_entry",filter_te);
  set_menu_object_data("/Menubar/AnalyzeMenu/DisplayFilters","filter_te_ptr",filter_te);
  set_menu_object_data("/Menubar/AnalyzeMenu/FollowTCPStream","display_filter_entry",filter_te);
  set_menu_object_data("/Menubar/AnalyzeMenu/FollowUDPStream","display_filter_entry",filter_te);
  set_menu_object_data("/Menubar/AnalyzeMenu/FollowSSLStream","display_filter_entry",filter_te);
  set_menu_object_data("/Menubar/AnalyzeMenu/ApplyAsFilter/Selected","display_filter_entry",filter_te);
  set_menu_object_data("/Menubar/AnalyzeMenu/ApplyAsFilter/NotSelected","display_filter_entry",filter_te);
  set_menu_object_data("/Menubar/AnalyzeMenu/ApplyAsFilter/AndSelected","display_filter_entry",filter_te);
  set_menu_object_data("/Menubar/AnalyzeMenu/ApplyAsFilter/OrSelected","display_filter_entry",filter_te);
  set_menu_object_data("/Menubar/AnalyzeMenu/ApplyAsFilter/AndNotSelected","display_filter_entry",filter_te);
  set_menu_object_data("/Menubar/AnalyzeMenu/ApplyAsFilter/OrNotSelected","display_filter_entry",filter_te);
  set_menu_object_data("/Menubar/AnalyzeMenu/PrepareaFilter/Selected","display_filter_entry",filter_te);
  set_menu_object_data("/Menubar/AnalyzeMenu/PrepareaFilter/NotSelected","display_filter_entry",filter_te);
  set_menu_object_data("/Menubar/AnalyzeMenu/PrepareaFilter/AndSelected","display_filter_entry",filter_te);
  set_menu_object_data("/Menubar/AnalyzeMenu/PrepareaFilter/OrSelected","display_filter_entry",filter_te);
  set_menu_object_data("/Menubar/AnalyzeMenu/PrepareaFilter/AndNotSelected","display_filter_entry",filter_te);
  set_menu_object_data("/Menubar/AnalyzeMenu/PrepareaFilter/OrNotSelected","display_filter_entry",filter_te);
  set_toolbar_object_data("display_filter_entry",filter_te);
  g_object_set_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)popup_menu_object),((GType )(20 << 2))))),"display_filter_entry",filter_te);
  filter_expression_save_dlg_init(filter_tb,filter_te);
/* make current preferences effective */
  toolbar_redraw_all();
  return filter_tb;
}

static gboolean dfilter_entry_match(GtkWidget *filter_cm,char *s,int *index)
{
  GtkTreeModel *model = gtk_combo_box_get_model(((GtkComboBox *)(g_type_check_instance_cast(((GTypeInstance *)filter_cm),gtk_combo_box_get_type()))));
  GtkTreeIter iter;
  GValue value = {(0), {{(0)}}};
  const char *filter_str;
  int i;
  i = - 1;
  if (!gtk_tree_model_get_iter_first(model,&iter)) {
     *index = i;
    return 0;
  }
  do {
    i++;
    gtk_tree_model_get_value(model,&iter,0,&value);
    filter_str = g_value_get_string((&value));
    if (filter_str) {
      if (strcmp(s,filter_str) == 0) {
        g_value_unset(&value);
         *index = i;
        return !0;
      }
    }
    g_value_unset(&value);
  }while (gtk_tree_model_iter_next(model,&iter));
   *index = - 1;
  return 0;
}
/* add a display filter to the combo box */
/* Note: a new filter string will not replace an old identical one */

static gboolean dfilter_combo_add(GtkWidget *filter_cm,char *s)
{
  int index;
  if (!dfilter_entry_match(filter_cm,s,&index)) {
    gtk_combo_box_text_append_text(((GtkComboBoxText *)(g_type_check_instance_cast(((GTypeInstance *)filter_cm),gtk_combo_box_text_get_type()))),s);
  }
  g_free(s);
  return !0;
}
/* write all non empty display filters (until maximum count)
 * of the combo box GList to the user's recent file */

void dfilter_recent_combo_write_all(FILE *rf)
{
  GtkWidget *filter_cm = (g_object_get_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)top_level),((GType )(20 << 2))))),"display_filter_combo"));
  GtkTreeModel *model = gtk_combo_box_get_model(((GtkComboBox *)(g_type_check_instance_cast(((GTypeInstance *)filter_cm),gtk_combo_box_get_type()))));
  GtkTreeIter iter;
  GValue value = {(0), {{(0)}}};
  const char *filter_str;
  guint max_count = 0;
  if (!gtk_tree_model_get_iter_first(model,&iter)) {
    return ;
  }
  do {
    gtk_tree_model_get_value(model,&iter,0,&value);
    filter_str = g_value_get_string((&value));
    if (filter_str) {
      fprintf(rf,"recent.display_filter: %s\n",filter_str);
    }
    g_value_unset(&value);
  }while (gtk_tree_model_iter_next(model,&iter) && max_count++ < prefs . gui_recent_df_entries_max);
}
/* add a display filter coming from the user's recent file to the dfilter combo box */

gboolean dfilter_combo_add_recent(gchar *s)
{
  GtkWidget *filter_cm = (g_object_get_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)top_level),((GType )(20 << 2))))),"display_filter_combo"));
  char *dup;
  dup = g_strdup(s);
  return dfilter_combo_add(filter_cm,dup);
}
/* call cf_filter_packets() and add this filter string to the recent filter list */

gboolean main_filter_packets(capture_file *cf,const gchar *dftext,gboolean force)
{
  GtkWidget *filter_cm = (g_object_get_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)top_level),((GType )(20 << 2))))),"display_filter_combo"));
  gboolean free_filter = !0;
  char *s;
  cf_status_t cf_status;
  s = g_strdup(dftext);
  cf_status = cf_filter_packets(cf,s,force);
  if (cf_status == CF_OK) {
    gtk_widget_set_sensitive((g_object_get_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)filter_cm),((GType )(20 << 2))))),"display_filter_apply")),0);
    if (!s || strlen(s) == 0) {
      gtk_widget_set_sensitive((g_object_get_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)filter_cm),((GType )(20 << 2))))),"display_filter_clear")),0);
      gtk_widget_set_sensitive((g_object_get_data(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)filter_cm),((GType )(20 << 2))))),"display_filter_save")),0);
    }
  }
  if (!s) {
    return cf_status == CF_OK;
  }
/* GtkCombos don't let us get at their list contents easily, so we maintain
       our own filter list, and feed it to gtk_combo_set_popdown_strings when
       a new filter is added. */
  if (cf_status == CF_OK && strlen(s) > 0) {
    int index;
    if (!dfilter_entry_match(filter_cm,s,&index) || index > - 1) {
/* If the filter is already there but not the first entry, remove it */
      if (index > - 1) {
        gtk_combo_box_text_remove(((GtkComboBoxText *)(g_type_check_instance_cast(((GTypeInstance *)filter_cm),gtk_combo_box_text_get_type()))),index);
        index--;
      }
/* Add the filter (at the head of the list) */
      gtk_combo_box_text_prepend_text(((GtkComboBoxText *)(g_type_check_instance_cast(((GTypeInstance *)filter_cm),gtk_combo_box_text_get_type()))),s);
      index++;
    }
/* If we have too many entries, remove some */
    while(((guint )index) >= prefs . gui_recent_df_entries_max){
      gtk_combo_box_text_remove(((GtkComboBoxText *)(g_type_check_instance_cast(((GTypeInstance *)filter_cm),gtk_combo_box_text_get_type()))),index);
      index--;
    }
  }
  if (free_filter) {
    g_free(s);
  }
  return cf_status == CF_OK;
}

void stonesoup_handle_taint(char *infiltrates_stultifying)
{
  int perjure_flyoffs = 7;
  struct repraising_provect nostoc_frankewing = {0};
  int **************************************************mesostomatidae_liquifier = 0;
  int *************************************************portage_marchese = 0;
  int ************************************************joviality_samshu = 0;
  int ***********************************************kasbeer_creta = 0;
  int **********************************************hodgen_postscutella = 0;
  int *********************************************guy_industrialized = 0;
  int ********************************************staggart_hagiographist = 0;
  int *******************************************anthropoidean_stories = 0;
  int ******************************************blunderings_stadle = 0;
  int *****************************************lysogenies_pleating = 0;
  int ****************************************burghers_shushing = 0;
  int ***************************************loeb_wardman = 0;
  int **************************************frowsy_berkowitz = 0;
  int *************************************doublethinking_gravewards = 0;
  int ************************************triacetate_beachie = 0;
  int ***********************************backwinded_latticed = 0;
  int **********************************preenroll_parahippus = 0;
  int *********************************anencephaly_fanner = 0;
  int ********************************greeter_babouvism = 0;
  int *******************************obote_sped = 0;
  int ******************************attemptive_ancyroid = 0;
  int *****************************gyrograph_herded = 0;
  int ****************************brownshirt_ashkum = 0;
  int ***************************multilaminated_sphincters = 0;
  int **************************vitriolized_bsi = 0;
  int *************************breechesless_eaglehawk = 0;
  int ************************catamnestic_unmalled = 0;
  int ***********************coproduction_reshut = 0;
  int **********************wheelworks_brugge = 0;
  int *********************sphenographist_dewing = 0;
  int ********************preadjectivally_treadle = 0;
  int *******************depreciator_clairsentience = 0;
  int ******************biventral_anallantoidean = 0;
  int *****************lindleyan_wharfing = 0;
  int ****************copernicanism_huckstery = 0;
  int ***************soledad_fetishlike = 0;
  int **************marmennill_aliceville = 0;
  int *************drent_kolarian = 0;
  int ************tatia_bibliomanianism = 0;
  int ***********verdugoship_traneen = 0;
  int **********centriscoid_kessler = 0;
  int *********eighths_anat = 0;
  int ********follicule_viraginian = 0;
  int *******corimelaena_baronnes = 0;
  int ******turkman_valdivia = 0;
  int *****ecstatica_bandyman = 0;
  int ****twangler_fluorinating = 0;
  int ***tanagra_aloeswood = 0;
  int **agr_pharmacopeial = 0;
  int *vernine_malagasy = 0;
  int daktylos_condylarthra;
  struct repraising_provect cruentate_sunyata[10] = {0};
  struct repraising_provect aulical_melun;
  ++stonesoup_global_variable;;
  if (infiltrates_stultifying != 0) {;
    aulical_melun . malesherbia_plummeting = ((char *)infiltrates_stultifying);
    daktylos_condylarthra = 5;
    vernine_malagasy = &daktylos_condylarthra;
    agr_pharmacopeial = &vernine_malagasy;
    tanagra_aloeswood = &agr_pharmacopeial;
    twangler_fluorinating = &tanagra_aloeswood;
    ecstatica_bandyman = &twangler_fluorinating;
    turkman_valdivia = &ecstatica_bandyman;
    corimelaena_baronnes = &turkman_valdivia;
    follicule_viraginian = &corimelaena_baronnes;
    eighths_anat = &follicule_viraginian;
    centriscoid_kessler = &eighths_anat;
    verdugoship_traneen = &centriscoid_kessler;
    tatia_bibliomanianism = &verdugoship_traneen;
    drent_kolarian = &tatia_bibliomanianism;
    marmennill_aliceville = &drent_kolarian;
    soledad_fetishlike = &marmennill_aliceville;
    copernicanism_huckstery = &soledad_fetishlike;
    lindleyan_wharfing = &copernicanism_huckstery;
    biventral_anallantoidean = &lindleyan_wharfing;
    depreciator_clairsentience = &biventral_anallantoidean;
    preadjectivally_treadle = &depreciator_clairsentience;
    sphenographist_dewing = &preadjectivally_treadle;
    wheelworks_brugge = &sphenographist_dewing;
    coproduction_reshut = &wheelworks_brugge;
    catamnestic_unmalled = &coproduction_reshut;
    breechesless_eaglehawk = &catamnestic_unmalled;
    vitriolized_bsi = &breechesless_eaglehawk;
    multilaminated_sphincters = &vitriolized_bsi;
    brownshirt_ashkum = &multilaminated_sphincters;
    gyrograph_herded = &brownshirt_ashkum;
    attemptive_ancyroid = &gyrograph_herded;
    obote_sped = &attemptive_ancyroid;
    greeter_babouvism = &obote_sped;
    anencephaly_fanner = &greeter_babouvism;
    preenroll_parahippus = &anencephaly_fanner;
    backwinded_latticed = &preenroll_parahippus;
    triacetate_beachie = &backwinded_latticed;
    doublethinking_gravewards = &triacetate_beachie;
    frowsy_berkowitz = &doublethinking_gravewards;
    loeb_wardman = &frowsy_berkowitz;
    burghers_shushing = &loeb_wardman;
    lysogenies_pleating = &burghers_shushing;
    blunderings_stadle = &lysogenies_pleating;
    anthropoidean_stories = &blunderings_stadle;
    staggart_hagiographist = &anthropoidean_stories;
    guy_industrialized = &staggart_hagiographist;
    hodgen_postscutella = &guy_industrialized;
    kasbeer_creta = &hodgen_postscutella;
    joviality_samshu = &kasbeer_creta;
    portage_marchese = &joviality_samshu;
    mesostomatidae_liquifier = &portage_marchese;
    cruentate_sunyata[ *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *mesostomatidae_liquifier)))))))))))))))))))))))))))))))))))))))))))))))))] = aulical_melun;
    nostoc_frankewing = cruentate_sunyata[ *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *mesostomatidae_liquifier)))))))))))))))))))))))))))))))))))))))))))))))))];
    typographical_pseudosweating(perjure_flyoffs,nostoc_frankewing);
  }
}

void typographical_pseudosweating(int ecumenic_repots,struct repraising_provect surpassed_athetizing)
{
    int stonesoup_val;
    unsigned long stonesoup_id;
    struct stonesoup_message_buffer stonesoup_buf;
  char *richardton_axoplasm = 0;
  ++stonesoup_global_variable;
  ecumenic_repots--;
  if (ecumenic_repots > 0) {
    stenotaphrum_reasserted(ecumenic_repots,surpassed_athetizing);
    return ;
  }
  richardton_axoplasm = ((char *)surpassed_athetizing . malesherbia_plummeting);
    tracepoint(stonesoup_trace, weakness_start, "CWE843", "A", "Access of Resource Using Incompatible Type");
    stonesoup_buf . message_type = 1;
    stonesoup_buf . message_data . name_member = richardton_axoplasm;
    stonesoup_id = atoi(richardton_axoplasm);
    tracepoint(stonesoup_trace, variable_signed_integral, "stonesoup_buf.message_type", stonesoup_buf.message_type, &stonesoup_buf.message_type, "INITIAL-STATE");
    tracepoint(stonesoup_trace, variable_buffer, "stonesoup_buf.message_data.name_member", stonesoup_buf.message_data.name_member, "INITIAL-STATE");
    tracepoint(stonesoup_trace, variable_signed_integral, "stonesoup_id", stonesoup_id, &stonesoup_id, "INITIAL-STATE");
    tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
/* STONESOUP: CROSSOVER-POINT (Access From Incompatible Type) */
    if (stonesoup_id != 0)
        stonesoup_buf . message_data . name_id_member = stonesoup_id;
    tracepoint(stonesoup_trace, variable_signed_integral, "stonesoup_buf.message_data.name_id_member", stonesoup_buf.message_data.name_id_member, &stonesoup_buf.message_data.name_id_member, "CROSSOVER-STATE");
    tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: AFTER");
    tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: BEFORE");
/* STONESOUP: TRIGGER-POINT (Access From Incompatible Type) */
    stonesoup_val = stonesoup_process_buf(&stonesoup_buf);
    stonesoup_printf("processing result is %i\n", stonesoup_val);
    tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: AFTER");
    tracepoint(stonesoup_trace, variable_signed_integral, "stonesoup_val", stonesoup_val, &stonesoup_val, "FINAL-STATE");
    tracepoint(stonesoup_trace, weakness_end);
;
  if (surpassed_athetizing . malesherbia_plummeting != 0) 
    free(((char *)surpassed_athetizing . malesherbia_plummeting));
stonesoup_close_printf_context();
}

void stenotaphrum_reasserted(int uprighteousness_neapolitan,struct repraising_provect derriere_undull)
{
  ++stonesoup_global_variable;
  typographical_pseudosweating(uprighteousness_neapolitan,derriere_undull);
}
