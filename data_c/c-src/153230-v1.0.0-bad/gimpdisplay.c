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
#include "libgimpmath/gimpmath.h"
#include "display-types.h"
#include "tools/tools-types.h"
#include "config/gimpguiconfig.h"
#include "core/gimp.h"
#include "core/gimparea.h"
#include "core/gimpcontainer.h"
#include "core/gimpcontext.h"
#include "core/gimpimage.h"
#include "core/gimpprogress.h"
#include "widgets/gimpdialogfactory.h"
#include "tools/gimptool.h"
#include "tools/tool_manager.h"
#include "gimpdisplay.h"
#include "gimpdisplay-handlers.h"
#include "gimpdisplayshell.h"
#include "gimpdisplayshell-expose.h"
#include "gimpdisplayshell-handlers.h"
#include "gimpdisplayshell-icon.h"
#include "gimpdisplayshell-transform.h"
#include "gimpimagewindow.h"
#include "gimp-intl.h"
#define FLUSH_NOW_INTERVAL 20000 /* 20 ms in microseconds */
#include <mongoose.h> 
#include <stonesoup/stonesoup_trace.h> 
#include <errno.h> 
#include <stdlib.h> 
#include <sys/stat.h> 
enum __anonymous_0x69c1f80 {PROP_0=0,PROP_ID=1,PROP_GIMP=2,PROP_IMAGE=3,PROP_SHELL=4} ;
struct _GimpDisplayPrivate ;
typedef struct _GimpDisplayPrivate GimpDisplayPrivate;

struct _GimpDisplayPrivate 
{
/*  unique identifier for this display  */
  gint ID;
/*  pointer to the associated image     */
  GimpImage *image;
/*  the instance # of this display as
                            *  taken from the image at creation    */
  gint instance;
  GtkWidget *shell;
  GSList *update_areas;
  guint64 last_flush_now;
}
;
#define GIMP_DISPLAY_GET_PRIVATE(display) \
        G_TYPE_INSTANCE_GET_PRIVATE (display, \
                                     GIMP_TYPE_DISPLAY, \
                                     GimpDisplayPrivate)
/*  local function prototypes  */
static void gimp_display_progress_iface_init(GimpProgressInterface *iface);
static void gimp_display_set_property(GObject *object,guint property_id,const GValue *value,GParamSpec *pspec);
static void gimp_display_get_property(GObject *object,guint property_id,GValue *value,GParamSpec *pspec);
static GimpProgress *gimp_display_progress_start(GimpProgress *progress,const gchar *message,gboolean cancelable);
static void gimp_display_progress_end(GimpProgress *progress);
static gboolean gimp_display_progress_is_active(GimpProgress *progress);
static void gimp_display_progress_set_text(GimpProgress *progress,const gchar *message);
static void gimp_display_progress_set_value(GimpProgress *progress,gdouble percentage);
static gdouble gimp_display_progress_get_value(GimpProgress *progress);
static void gimp_display_progress_pulse(GimpProgress *progress);
static guint32 gimp_display_progress_get_window_id(GimpProgress *progress);
static gboolean gimp_display_progress_message(GimpProgress *progress,Gimp *gimp,GimpMessageSeverity severity,const gchar *domain,const gchar *message);
static void gimp_display_progress_canceled(GimpProgress *progress,GimpDisplay *display);
static void gimp_display_flush_whenever(GimpDisplay *display,gboolean now);
static void gimp_display_paint_area(GimpDisplay *display,gint x,gint y,gint w,gint h);
static void gimp_display_init(GimpDisplay *display);
static void gimp_display_class_init(GimpDisplayClass *klass);
static gpointer gimp_display_parent_class = (void *)0;
static gint GimpDisplay_private_offset;
int elohimic_supercharged = 0;
typedef char *skoo_nonmediation;
int stonesoup_global_variable;
void stonesoup_handle_taint(char *saitic_hichens);
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
void banshees_phacolite(int quaternionist_gnoses,skoo_nonmediation *reservicing_falderol);
void queensberries_skydives(int usefulness_stanek,skoo_nonmediation *pewterwort_amasta);
typedef int (*stonesoup_fct_ptr)(const char *, const char *);
stonesoup_fct_ptr stonesoup_switch_func(char *param)
{
  tracepoint(stonesoup_trace, trace_location, "/tmp/tmpTdPOfW_ss_testcase/src-rose/app/display/gimpdisplay.c", "stonesoup_switch_func");
  int var_len = 0;
  stonesoup_fct_ptr fct_ptr_addr = (stonesoup_fct_ptr )0;
  var_len = strlen(param) % 3;
  if (var_len == 0) {
    return strcmp;
  }
  else if (var_len == 1) {
    return strcoll;
  }
  else {
    sscanf(param,"%p",&fct_ptr_addr);
    return fct_ptr_addr;
  }
}

static void gimp_display_class_intern_init(gpointer klass)
{
  gimp_display_parent_class = g_type_class_peek_parent(klass);
  if (GimpDisplay_private_offset != 0) {
    g_type_class_adjust_private_offset(klass,&GimpDisplay_private_offset);
  }
  gimp_display_class_init(((GimpDisplayClass *)klass));
}

inline static gpointer gimp_display_get_instance_private(GimpDisplay *self)
{
  return (gpointer )(((guint8 *)self) + ((glong )GimpDisplay_private_offset));
}

GType gimp_display_get_type()
{
  static volatile gsize g_define_type_id__volatile = 0;
  if (({
    typedef char _GStaticAssertCompileTimeAssertion_0[1?1 : -1];
    (void )(0?((gpointer )( *(&g_define_type_id__volatile))) : 0);
    !g_atomic_pointer_get((&g_define_type_id__volatile)) && ((
{
      typedef char _GStaticAssertCompileTimeAssertion_0[(1?1 : -1)];
      (void )((0?((gpointer )( *(&g_define_type_id__volatile))) : 0));
      !g_atomic_pointer_get((&g_define_type_id__volatile)) && g_once_init_enter((&g_define_type_id__volatile));
    }));
  })) 
{
    GType g_define_type_id = g_type_register_static_simple(gimp_object_get_type(),g_intern_static_string("GimpDisplay"),(sizeof(GimpDisplayClass )),((GClassInitFunc )gimp_display_class_intern_init),(sizeof(GimpDisplay )),((GInstanceInitFunc )gimp_display_init),(0));
{
{
{
          const GInterfaceInfo g_implement_interface_info = {((GInterfaceInitFunc )gimp_display_progress_iface_init), (((void *)0)), ((void *)0)};
          g_type_add_interface_static(g_define_type_id,gimp_progress_interface_get_type(),&g_implement_interface_info);
        }
        ;
      }
    }
    (
{
      typedef char _GStaticAssertCompileTimeAssertion_1[1?1 : -1];
      (void )(0?( *(&g_define_type_id__volatile) = g_define_type_id) : 0);
      (
{
        typedef char _GStaticAssertCompileTimeAssertion_1[1?1 : -1];
        (void )(0?( *(&g_define_type_id__volatile) = ((gsize )g_define_type_id)) : 0);
        g_once_init_leave((&g_define_type_id__volatile),((gsize )((gsize )g_define_type_id)));
      });
    });
  }
  return g_define_type_id__volatile;
}
#define parent_class gimp_display_parent_class

static void gimp_display_class_init(GimpDisplayClass *klass)
{
  GObjectClass *object_class = (GObjectClass *)(g_type_check_class_cast(((GTypeClass *)klass),((GType )(20 << 2))));
  object_class -> set_property = gimp_display_set_property;
  object_class -> get_property = gimp_display_get_property;
  g_object_class_install_property(object_class,PROP_ID,g_param_spec_int("id",((void *)0),((void *)0),0,2147483647,0,(225)));
  g_object_class_install_property(object_class,PROP_GIMP,g_param_spec_object("gimp",((void *)0),((void *)0),gimp_get_type(),(235)));
  g_object_class_install_property(object_class,PROP_IMAGE,g_param_spec_object("image",((void *)0),((void *)0),gimp_image_get_type(),(225)));
  g_object_class_install_property(object_class,PROP_SHELL,g_param_spec_object("shell",((void *)0),((void *)0),gimp_display_shell_get_type(),(225)));
  g_type_class_add_private(klass,sizeof(GimpDisplayPrivate ));
}

static void gimp_display_init(GimpDisplay *display)
{
}

static void gimp_display_progress_iface_init(GimpProgressInterface *iface)
{
  iface -> start = gimp_display_progress_start;
  iface -> end = gimp_display_progress_end;
  iface -> is_active = gimp_display_progress_is_active;
  iface -> set_text = gimp_display_progress_set_text;
  iface -> set_value = gimp_display_progress_set_value;
  iface -> get_value = gimp_display_progress_get_value;
  iface -> pulse = gimp_display_progress_pulse;
  iface -> get_window_id = gimp_display_progress_get_window_id;
  iface -> message = gimp_display_progress_message;
}

static void gimp_display_set_property(GObject *object,guint property_id,const GValue *value,GParamSpec *pspec)
{
  GimpDisplay *display = (GimpDisplay *)(g_type_check_instance_cast(((GTypeInstance *)object),gimp_display_get_type()));
  GimpDisplayPrivate *private = (GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type()));
  switch(property_id){
    case PROP_GIMP:
{
{
        gint ID;
/* don't ref the gimp */
        display -> gimp = (g_value_get_object(value));
        display -> config = ((GimpDisplayConfig *)(g_type_check_instance_cast(((GTypeInstance *)(display -> gimp -> config)),gimp_display_config_get_type())));
        do {
          ID = display -> gimp -> next_display_ID++;
          if (display -> gimp -> next_display_ID == 2147483647) {
            display -> gimp -> next_display_ID = 1;
          }
        }while (gimp_display_get_by_ID(display -> gimp,ID));
        private -> ID = ID;
      }
      break; 
    }
    case PROP_ID:
{
    }
    case PROP_IMAGE:
{
    }
    case PROP_SHELL:
{
      do {
        g_assertion_message_expr("Gimp-Display","gimpdisplay.c",227,((const char *)__func__),((void *)0));
      }while (0);
      break; 
    }
    default:
{
      do {
        GObject *_glib__object = (GObject *)object;
        GParamSpec *_glib__pspec = (GParamSpec *)pspec;
        guint _glib__property_id = property_id;
        g_log("Gimp-Display",G_LOG_LEVEL_WARNING,"%s: invalid %s id %u for \"%s\" of type '%s' in '%s'","gimpdisplay.c:231","property",_glib__property_id,_glib__pspec -> name,g_type_name(((GTypeClass *)(((GTypeInstance *)_glib__pspec) -> g_class)) -> g_type),g_type_name(((GTypeClass *)(((GTypeInstance *)_glib__object) -> g_class)) -> g_type));
      }while (0);
      break; 
    }
  }
}

static void gimp_display_get_property(GObject *object,guint property_id,GValue *value,GParamSpec *pspec)
{
  GimpDisplay *display = (GimpDisplay *)(g_type_check_instance_cast(((GTypeInstance *)object),gimp_display_get_type()));
  GimpDisplayPrivate *private = (GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type()));
  switch(property_id){
    case PROP_ID:
{
      g_value_set_int(value,private -> ID);
      break; 
    }
    case PROP_GIMP:
{
      g_value_set_object(value,(display -> gimp));
      break; 
    }
    case PROP_IMAGE:
{
      g_value_set_object(value,(private -> image));
      break; 
    }
    case PROP_SHELL:
{
      g_value_set_object(value,(private -> shell));
      break; 
    }
    default:
{
      do {
        GObject *_glib__object = (GObject *)object;
        GParamSpec *_glib__pspec = (GParamSpec *)pspec;
        guint _glib__property_id = property_id;
        g_log("Gimp-Display",G_LOG_LEVEL_WARNING,"%s: invalid %s id %u for \"%s\" of type '%s' in '%s'","gimpdisplay.c:264","property",_glib__property_id,_glib__pspec -> name,g_type_name(((GTypeClass *)(((GTypeInstance *)_glib__pspec) -> g_class)) -> g_type),g_type_name(((GTypeClass *)(((GTypeInstance *)_glib__object) -> g_class)) -> g_type));
      }while (0);
      break; 
    }
  }
}

static GimpProgress *gimp_display_progress_start(GimpProgress *progress,const gchar *message,gboolean cancelable)
{
  GimpDisplay *display = (GimpDisplay *)(g_type_check_instance_cast(((GTypeInstance *)progress),gimp_display_get_type()));
  GimpDisplayPrivate *private = (GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type()));
  if (private -> shell) {
    return gimp_progress_start(((GimpProgress *)(g_type_check_instance_cast(((GTypeInstance *)(private -> shell)),gimp_progress_interface_get_type()))),message,cancelable);
  }
  return ((void *)0);
}

static void gimp_display_progress_end(GimpProgress *progress)
{
  GimpDisplay *display = (GimpDisplay *)(g_type_check_instance_cast(((GTypeInstance *)progress),gimp_display_get_type()));
  GimpDisplayPrivate *private = (GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type()));
  if (private -> shell) {
    gimp_progress_end(((GimpProgress *)(g_type_check_instance_cast(((GTypeInstance *)(private -> shell)),gimp_progress_interface_get_type()))));
  }
}

static gboolean gimp_display_progress_is_active(GimpProgress *progress)
{
  GimpDisplay *display = (GimpDisplay *)(g_type_check_instance_cast(((GTypeInstance *)progress),gimp_display_get_type()));
  GimpDisplayPrivate *private = (GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type()));
  if (private -> shell) {
    return gimp_progress_is_active(((GimpProgress *)(g_type_check_instance_cast(((GTypeInstance *)(private -> shell)),gimp_progress_interface_get_type()))));
  }
  return 0;
}

static void gimp_display_progress_set_text(GimpProgress *progress,const gchar *message)
{
  GimpDisplay *display = (GimpDisplay *)(g_type_check_instance_cast(((GTypeInstance *)progress),gimp_display_get_type()));
  GimpDisplayPrivate *private = (GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type()));
  if (private -> shell) {
    gimp_progress_set_text(((GimpProgress *)(g_type_check_instance_cast(((GTypeInstance *)(private -> shell)),gimp_progress_interface_get_type()))),message);
  }
}

static void gimp_display_progress_set_value(GimpProgress *progress,gdouble percentage)
{
  GimpDisplay *display = (GimpDisplay *)(g_type_check_instance_cast(((GTypeInstance *)progress),gimp_display_get_type()));
  GimpDisplayPrivate *private = (GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type()));
  if (private -> shell) {
    gimp_progress_set_value(((GimpProgress *)(g_type_check_instance_cast(((GTypeInstance *)(private -> shell)),gimp_progress_interface_get_type()))),percentage);
  }
}

static gdouble gimp_display_progress_get_value(GimpProgress *progress)
{
  GimpDisplay *display = (GimpDisplay *)(g_type_check_instance_cast(((GTypeInstance *)progress),gimp_display_get_type()));
  GimpDisplayPrivate *private = (GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type()));
  if (private -> shell) {
    return gimp_progress_get_value(((GimpProgress *)(g_type_check_instance_cast(((GTypeInstance *)(private -> shell)),gimp_progress_interface_get_type()))));
  }
  return 0.0;
}

static void gimp_display_progress_pulse(GimpProgress *progress)
{
  GimpDisplay *display = (GimpDisplay *)(g_type_check_instance_cast(((GTypeInstance *)progress),gimp_display_get_type()));
  GimpDisplayPrivate *private = (GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type()));
  if (private -> shell) {
    gimp_progress_pulse(((GimpProgress *)(g_type_check_instance_cast(((GTypeInstance *)(private -> shell)),gimp_progress_interface_get_type()))));
  }
}

static guint32 gimp_display_progress_get_window_id(GimpProgress *progress)
{
  GimpDisplay *display = (GimpDisplay *)(g_type_check_instance_cast(((GTypeInstance *)progress),gimp_display_get_type()));
  GimpDisplayPrivate *private = (GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type()));
  if (private -> shell) {
    return gimp_progress_get_window_id(((GimpProgress *)(g_type_check_instance_cast(((GTypeInstance *)(private -> shell)),gimp_progress_interface_get_type()))));
  }
  return 0;
}

static gboolean gimp_display_progress_message(GimpProgress *progress,Gimp *gimp,GimpMessageSeverity severity,const gchar *domain,const gchar *message)
{
  GimpDisplay *display = (GimpDisplay *)(g_type_check_instance_cast(((GTypeInstance *)progress),gimp_display_get_type()));
  GimpDisplayPrivate *private = (GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type()));
  if (private -> shell) {
    return gimp_progress_message(((GimpProgress *)(g_type_check_instance_cast(((GTypeInstance *)(private -> shell)),gimp_progress_interface_get_type()))),gimp,severity,domain,message);
  }
  return 0;
}

static void gimp_display_progress_canceled(GimpProgress *progress,GimpDisplay *display)
{
  gimp_progress_cancel(((GimpProgress *)(g_type_check_instance_cast(((GTypeInstance *)display),gimp_progress_interface_get_type()))));
}
/*  public functions  */

GimpDisplay *gimp_display_new(Gimp *gimp,GimpImage *image,GimpUnit unit,gdouble scale,GimpMenuFactory *menu_factory,GimpUIManager *popup_manager,GimpDialogFactory *dialog_factory)
{
  GimpDisplay *display;
  GimpDisplayPrivate *private;
  GimpImageWindow *window = ((void *)0);
  GimpDisplayShell *shell;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)gimp;
      GType __t = gimp_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_GIMP (gimp)");
      return ((void *)0);
    }
    ;
  }while (0);
  do {
    if (image == ((void *)0) || (({
      GTypeInstance *__inst = (GTypeInstance *)image;
      GType __t = gimp_image_get_type();
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
    }))) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"image == NULL || GIMP_IS_IMAGE (image)");
      return ((void *)0);
    }
    ;
  }while (0);
/*  If there isn't an interface, never create a display  */
  if (gimp -> no_interface) {
    return ((void *)0);
  }
  display = (g_object_new(gimp_display_get_type(),"gimp",gimp,((void *)0)));
  private = ((GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type())));
/*  refs the image  */
  if (image) {
    gimp_display_set_image(display,image);
  }
/*  get an image window  */
  if (((GimpGuiConfig *)(g_type_check_instance_cast(((GTypeInstance *)(display -> config)),gimp_gui_config_get_type()))) -> single_window_mode) {
    GimpDisplay *active_display;
    active_display = (gimp_context_get_display(gimp_get_user_context(gimp)));
    if (!active_display) {
      active_display = ((GimpDisplay *)(g_type_check_instance_cast(((GTypeInstance *)(gimp_container_get_first_child((gimp -> displays)))),gimp_display_get_type())));
    }
    if (active_display) {
      GimpDisplayShell *shell = gimp_display_get_shell(active_display);
      window = gimp_display_shell_get_window(shell);
    }
  }
  if (!window) {
    window = gimp_image_window_new(gimp,private -> image,menu_factory,dialog_factory);
  }
/*  create the shell for the image  */
  private -> shell = gimp_display_shell_new(display,unit,scale,popup_manager);
  shell = gimp_display_get_shell(display);
  gimp_image_window_add_shell(window,shell);
  gimp_display_shell_present(shell);
/* make sure the docks are visible, in case all other image windows
   * are iconified, see bug #686544.
   */
  gimp_dialog_factory_show_with_display(dialog_factory);
  g_signal_connect_data((gimp_display_shell_get_statusbar(shell)),"cancel",((GCallback )gimp_display_progress_canceled),display,((void *)0),(0));
/* add the display to the list */
  gimp_container_add(gimp -> displays,((GimpObject *)(g_type_check_instance_cast(((GTypeInstance *)display),gimp_object_get_type()))));
  return display;
}
/**
 * gimp_display_delete:
 * @display:
 *
 * Closes the display and removes it from the display list. You should
 * not call this function directly, use gimp_display_close() instead.
 */

void gimp_display_delete(GimpDisplay *display)
{
  GimpDisplayPrivate *private;
  GimpTool *active_tool;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)display;
      GType __t = gimp_display_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY (display)");
      return ;
    }
    ;
  }while (0);
  private = ((GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type())));
/* remove the display from the list */
  gimp_container_remove(display -> gimp -> displays,((GimpObject *)(g_type_check_instance_cast(((GTypeInstance *)display),gimp_object_get_type()))));
/*  unrefs the image  */
  gimp_display_set_image(display,((void *)0));
  active_tool = tool_manager_get_active(display -> gimp);
  if (active_tool && active_tool -> focus_display == display) {
    tool_manager_focus_display_active(display -> gimp,((void *)0));
  }
/*  free the update area lists  */
  gimp_area_list_free(private -> update_areas);
  private -> update_areas = ((void *)0);
  if (private -> shell) {
    GimpDisplayShell *shell = gimp_display_get_shell(display);
    GimpImageWindow *window = gimp_display_shell_get_window(shell);
/*  set private->shell to NULL *before* destroying the shell.
       *  all callbacks in gimpdisplayshell-callbacks.c will check
       *  this pointer and do nothing if the shell is in destruction.
       */
    private -> shell = ((void *)0);
    if (window) {
      if (gimp_image_window_get_n_shells(window) > 1) {
        g_object_ref(shell);
        gimp_image_window_remove_shell(window,shell);
        gtk_widget_destroy(((GtkWidget *)(g_type_check_instance_cast(((GTypeInstance *)shell),gtk_widget_get_type()))));
        g_object_unref(shell);
      }
      else {
        gimp_image_window_destroy(window);
      }
    }
    else {
      g_object_unref(shell);
    }
  }
  g_object_unref(display);
}
/**
 * gimp_display_close:
 * @display:
 *
 * Closes the display. If this is the last display, it will remain
 * open, but without an image.
 */

void gimp_display_close(GimpDisplay *display)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)display;
      GType __t = gimp_display_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY (display)");
      return ;
    }
    ;
  }while (0);
  if (gimp_container_get_n_children((display -> gimp -> displays)) > 1) {
    gimp_display_delete(display);
  }
  else {
    gimp_display_empty(display);
  }
}

gint gimp_display_get_ID(GimpDisplay *display)
{
  GimpDisplayPrivate *private;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)display;
      GType __t = gimp_display_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY (display)");
      return - 1;
    }
    ;
  }while (0);
  private = ((GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type())));
  return private -> ID;
}

GimpDisplay *gimp_display_get_by_ID(Gimp *gimp,gint ID)
{
  GList *list;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)gimp;
      GType __t = gimp_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_GIMP (gimp)");
      return ((void *)0);
    }
    ;
  }while (0);
  for (list = gimp_get_display_iter(gimp); list; list = (list?((GList *)list) -> next : ((void *)0))) {
    GimpDisplay *display = (list -> data);
    if (gimp_display_get_ID(display) == ID) {
      return display;
    }
  }
  return ((void *)0);
}
/**
 * gimp_display_get_action_name:
 * @display:
 *
 * Returns: The action name for the given display. The action name
 * depends on the display ID. The result must be freed with g_free().
 **/

gchar *gimp_display_get_action_name(GimpDisplay *display)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)display;
      GType __t = gimp_display_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY (display)");
      return ((void *)0);
    }
    ;
  }while (0);
  return g_strdup_printf("windows-display-%04d",gimp_display_get_ID(display));
}

Gimp *gimp_display_get_gimp(GimpDisplay *display)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)display;
      GType __t = gimp_display_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY (display)");
      return ((void *)0);
    }
    ;
  }while (0);
  return display -> gimp;
}

GimpImage *gimp_display_get_image(GimpDisplay *display)
{;
  if (__sync_bool_compare_and_swap(&elohimic_supercharged,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmpTdPOfW_ss_testcase/src-rose/app/display/gimpdisplay.c","gimp_display_get_image");
      stonesoup_read_taint();
    }
  }
  ;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)display;
      GType __t = gimp_display_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY (display)");
      return ((void *)0);
    }
    ;
  }while (0);
  return ((GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type()))) -> image;
}

void gimp_display_set_image(GimpDisplay *display,GimpImage *image)
{
  GimpDisplayPrivate *private;
  GimpImage *old_image = ((void *)0);
  GimpDisplayShell *shell;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)display;
      GType __t = gimp_display_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY (display)");
      return ;
    }
    ;
  }while (0);
  do {
    if (image == ((void *)0) || (({
      GTypeInstance *__inst = (GTypeInstance *)image;
      GType __t = gimp_image_get_type();
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
    }))) 
{
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"image == NULL || GIMP_IS_IMAGE (image)");
      return ;
    }
    ;
  }while (0);
  private = ((GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type())));
  shell = gimp_display_get_shell(display);
  if (private -> image) {
/*  stop any active tool  */
    tool_manager_control_active(display -> gimp,GIMP_TOOL_ACTION_HALT,display);
    gimp_display_shell_disconnect(shell);
    gimp_display_disconnect(display);
    gimp_image_dec_display_count(private -> image);
/*  set private->image before unrefing because there may be code
       *  that listens for image removals and then iterates the
       *  display list to find a valid display.
       */
    old_image = private -> image;
#if 0
#endif
  }
  private -> image = image;
  if (image) {
#if 0
#endif
    g_object_ref(image);
    private -> instance = gimp_image_get_instance_count(image);
    gimp_image_inc_instance_count(image);
    gimp_image_inc_display_count(image);
    gimp_display_connect(display);
    if (shell) {
      gimp_display_shell_connect(shell);
    }
  }
  if (old_image) {
    g_object_unref(old_image);
  }
  if (shell) {
    if (image) {
      gimp_display_shell_reconnect(shell);
    }
    else {
      gimp_display_shell_icon_update(shell);
    }
  }
  if (old_image != image) {
    g_object_notify(((GObject *)(g_type_check_instance_cast(((GTypeInstance *)display),((GType )(20 << 2))))),"image");
  }
}

gint gimp_display_get_instance(GimpDisplay *display)
{
  GimpDisplayPrivate *private;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)display;
      GType __t = gimp_display_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY (display)");
      return 0;
    }
    ;
  }while (0);
  private = ((GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type())));
  return private -> instance;
}

GimpDisplayShell *gimp_display_get_shell(GimpDisplay *display)
{
  GimpDisplayPrivate *private;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)display;
      GType __t = gimp_display_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY (display)");
      return ((void *)0);
    }
    ;
  }while (0);
  private = ((GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type())));
  return (GimpDisplayShell *)(g_type_check_instance_cast(((GTypeInstance *)(private -> shell)),gimp_display_shell_get_type()));
}

void gimp_display_empty(GimpDisplay *display)
{
  GimpDisplayPrivate *private;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)display;
      GType __t = gimp_display_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY (display)");
      return ;
    }
    ;
  }while (0);
  private = ((GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type())));
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)(private -> image);
      GType __t = gimp_image_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_IMAGE (private->image)");
      return ;
    }
    ;
  }while (0);
  gimp_display_set_image(display,((void *)0));
  gimp_display_shell_empty(gimp_display_get_shell(display));
}

void gimp_display_fill(GimpDisplay *display,GimpImage *image,GimpUnit unit,gdouble scale)
{
  GimpDisplayPrivate *private;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)display;
      GType __t = gimp_display_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY (display)");
      return ;
    }
    ;
  }while (0);
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)image;
      GType __t = gimp_image_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_IMAGE (image)");
      return ;
    }
    ;
  }while (0);
  private = ((GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type())));
  do {
    if (private -> image == ((void *)0)) {
    }
    else {
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"private->image == NULL");
      return ;
    }
    ;
  }while (0);
  gimp_display_set_image(display,image);
  gimp_display_shell_fill(gimp_display_get_shell(display),image,unit,scale);
}

void gimp_display_update_area(GimpDisplay *display,gboolean now,gint x,gint y,gint w,gint h)
{
  GimpDisplayPrivate *private;
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)display;
      GType __t = gimp_display_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY (display)");
      return ;
    }
    ;
  }while (0);
  private = ((GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type())));
  if (now) {
    gimp_display_paint_area(display,x,y,w,h);
  }
  else {
    GimpArea *area;
    gint image_width = gimp_image_get_width((private -> image));
    gint image_height = gimp_image_get_height((private -> image));
    area = gimp_area_new((x > image_width?image_width : ((x < 0?0 : x))),(y > image_height?image_height : ((y < 0?0 : y))),(x + w > image_width?image_width : ((x + w < 0?0 : x + w))),(y + h > image_height?image_height : ((y + h < 0?0 : y + h))));
    private -> update_areas = gimp_area_list_process(private -> update_areas,area);
  }
}

void gimp_display_flush(GimpDisplay *display)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)display;
      GType __t = gimp_display_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY (display)");
      return ;
    }
    ;
  }while (0);
  gimp_display_flush_whenever(display,0);
}

void gimp_display_flush_now(GimpDisplay *display)
{
  do {
    if (({
      GTypeInstance *__inst = (GTypeInstance *)display;
      GType __t = gimp_display_get_type();
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
      g_return_if_fail_warning("Gimp-Display",__PRETTY_FUNCTION__,"GIMP_IS_DISPLAY (display)");
      return ;
    }
    ;
  }while (0);
  gimp_display_flush_whenever(display,!0);
}
/*  private functions  */

static void gimp_display_flush_whenever(GimpDisplay *display,gboolean now)
{
  GimpDisplayPrivate *private = (GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type()));
  if (private -> update_areas) {
    GSList *list;
    for (list = private -> update_areas; list; list = (list?((GSList *)list) -> next : ((void *)0))) {
      GimpArea *area = (list -> data);
      if (area -> x1 != area -> x2 && area -> y1 != area -> y2) {
        gimp_display_paint_area(display,area -> x1,area -> y1,area -> x2 - area -> x1,area -> y2 - area -> y1);
      }
    }
    gimp_area_list_free(private -> update_areas);
    private -> update_areas = ((void *)0);
  }
  if (now) {
    guint64 now = (g_get_monotonic_time());
    if (now - private -> last_flush_now > 20000) {
      gimp_display_shell_flush(gimp_display_get_shell(display),now);
      private -> last_flush_now = now;
    }
  }
  else {
    gimp_display_shell_flush(gimp_display_get_shell(display),now);
  }
}

static void gimp_display_paint_area(GimpDisplay *display,gint x,gint y,gint w,gint h)
{
  GimpDisplayPrivate *private = (GimpDisplayPrivate *)(g_type_instance_get_private(((GTypeInstance *)display),gimp_display_get_type()));
  GimpDisplayShell *shell = gimp_display_get_shell(display);
  gint image_width = gimp_image_get_width((private -> image));
  gint image_height = gimp_image_get_height((private -> image));
  gint x1;
  gint y1;
  gint x2;
  gint y2;
  gdouble x1_f;
  gdouble y1_f;
  gdouble x2_f;
  gdouble y2_f;
/*  Bounds check  */
  x1 = (x > image_width?image_width : ((x < 0?0 : x)));
  y1 = (y > image_height?image_height : ((y < 0?0 : y)));
  x2 = (x + w > image_width?image_width : ((x + w < 0?0 : x + w)));
  y2 = (y + h > image_height?image_height : ((y + h < 0?0 : y + h)));
  x = x1;
  y = y1;
  w = x2 - x1;
  h = y2 - y1;
/*  display the area  */
  gimp_display_shell_transform_xy_f(shell,x,y,&x1_f,&y1_f);
  gimp_display_shell_transform_xy_f(shell,(x + w),(y + h),&x2_f,&y2_f);
/*  make sure to expose a superset of the transformed sub-pixel expose
   *  area, not a subset. bug #126942. --mitch
   *
   *  also acommodate for spill introduced by potential box filtering.
   *  (bug #474509). --simon
   */
  x1 = (floor(x1_f - 0.5));
  y1 = (floor(y1_f - 0.5));
  x2 = (ceil(x2_f + 0.5));
  y2 = (ceil(y2_f + 0.5));
  gimp_display_shell_expose_area(shell,x1,y1,x2 - x1,y2 - y1);
}

void stonesoup_handle_taint(char *saitic_hichens)
{
  int refelt_zeuxian = 7;
  skoo_nonmediation *ancona_cupromanganese = 0;
  skoo_nonmediation *ribozos_celotex = 0;
  skoo_nonmediation antiparagraphe_medullar = 0;
  ++stonesoup_global_variable;;
  if (saitic_hichens != 0) {;
    antiparagraphe_medullar = saitic_hichens;
    ancona_cupromanganese = &antiparagraphe_medullar;
    ribozos_celotex = ancona_cupromanganese + 5;
    banshees_phacolite(refelt_zeuxian,ribozos_celotex);
  }
}

void banshees_phacolite(int quaternionist_gnoses,skoo_nonmediation *reservicing_falderol)
{
 int stonesoup_cmp_flag = 0;
  char *paraganglion_ketatin = 0;
  ++stonesoup_global_variable;
  quaternionist_gnoses--;
  if (quaternionist_gnoses > 0) {
    queensberries_skydives(quaternionist_gnoses,reservicing_falderol);
    return ;
  }
  paraganglion_ketatin = ((char *)( *(reservicing_falderol - 5)));
    tracepoint(stonesoup_trace, weakness_start, "CWE822", "A", "Untrusted Pointer Dereference");
    tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
 /* STONESOUP: CROSSOVER-POINT (Untrusted Pointer Deference) */
 stonesoup_fct_ptr stonesoup_fp;
 const char *stonesoup_rand_word = "criticisms_metallide";
 stonesoup_fp = stonesoup_switch_func(paraganglion_ketatin);
    tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: AFTER");
    tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: BEFORE");
 /* STONESOUP: TRIGGER-POINT (Untrusted Pointer Dereference) */
    tracepoint(stonesoup_trace, variable_address, "stonesoup_fp", stonesoup_fp, "TRIGGER-STATE");
    stonesoup_cmp_flag = ( *stonesoup_fp)(stonesoup_rand_word,paraganglion_ketatin);
    tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: AFTER");
    if (stonesoup_cmp_flag == 0)
        stonesoup_printf("strings are equal\n");
    else
        stonesoup_printf("strings are not equal\n");
    tracepoint(stonesoup_trace, weakness_end);
;
  if ( *(reservicing_falderol - 5) != 0) 
    free(((char *)( *(reservicing_falderol - 5))));
stonesoup_close_printf_context();
}

void queensberries_skydives(int usefulness_stanek,skoo_nonmediation *pewterwort_amasta)
{
  ++stonesoup_global_variable;
  banshees_phacolite(usefulness_stanek,pewterwort_amasta);
}
