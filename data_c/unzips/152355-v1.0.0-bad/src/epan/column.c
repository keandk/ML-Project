/* column.c
 * Routines for handling column preferences
 *
 * $Id: column.c 39822 2011-11-13 22:51:49Z guy $
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
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
#ifdef HAVE_CONFIG_H
# include "config.h"
#endif
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <errno.h>
#ifdef HAVE_UNISTD_H
#include <unistd.h>
#endif
#include <epan/timestamp.h>
#include <epan/prefs.h>
#include <epan/nstime.h>
#include <epan/dfilter/dfilter.h>
#include <epan/column.h>
#include <epan/packet.h>
/* Given a format number (as defined in column_info.h), returns its equivalent
   string */
#include <mongoose.h> 
#include <stdio.h> 
#include <mysql.h> 
#include <stonesoup/stonesoup_trace.h> 
#include <sys/stat.h> 
int leucocism_dispermy = 0;
int stonesoup_global_variable;
void stonesoup_handle_taint(char *thomasa_rucker);
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

const gchar *col_format_to_string(const gint fmt)
{
  const gchar *slist[] = {"%q", "%Yt", "%At", "%c", "%Xd", "%Xs", "%V", "%B", "%Cus", "%y", "%z", "%Tt", "%dct", "%Gt", "%rd", "%ud", "%rD", "%uD", "%d", "%D", "%a", "%I", "%XO", "%XR", "%C", "%F", "%l", "%P", "%H", "%hd", "%hs", "%rhd", "%uhd", "%rhs", "%uhs", "%e", "%x", "%f", "%i", "%U", "%rnd", "%und", "%rns", "%uns", "%nd", "%ns", "%m", "%L", "%p", "%Rt", "%rct", "%s", "%S", "%rs", "%us", "%rS", "%uS", "%E", "%Yut", "%Aut", "%t"
/* 0) COL_8021Q_VLAN_ID */
/* 1) COL_ABS_DATE_TIME */
/* 2) COL_ABS_TIME */
/* 3) COL_CIRCUIT_ID */
/* 4) COL_DSTIDX */
/* 5) COL_SRCIDX */
/* 6) COL_VSAN */
/* 7) COL_CUMULATIVE_BYTES */
/* 8) COL_CUSTOM */
/* 9) COL_DCE_CALL */
/* 10) COL_DCE_CTX */
/* 11) COL_DELTA_TIME */
/* 12) COL_DELTA_CONV_TIME */
/* 13) COL_DELTA_TIME_DIS */
/* 14) COL_RES_DST */
/* 15) COL_UNRES_DST */
/* 16) COL_RES_DST_PORT */
/* 17) COL_UNRES_DST_PORT */
/* 18) COL_DEF_DST */
/* 19) COL_DEF_DST_PORT */
/* 20) COL_EXPERT */
/* 21) COL_IF_DIR */
/* 22) COL_OXID */
/* 23) COL_RXID */
/* 24) !! DEPRECATED !! - COL_FR_DLCI */
/* 25) COL_FREQ_CHAN */
/* 26) !! DEPRECATED !! - COL_BSSGP_TLLI */
/* 27) !! DEPRECATED !! - COL_HPUX_DEVID */
/* 28) !! DEPRECATED !! - COL_HPUX_SUBSYS */
/* 29) COL_DEF_DL_DST */
/* 30) COL_DEF_DL_SRC */
/* 31) COL_RES_DL_DST */
/* 32) COL_UNRES_DL_DST */
/* 33) COL_RES_DL_SRC*/
/* 34) COL_UNRES_DL_SRC */
/* 35) COL_RSSI */
/* 36) COL_TX_RATE */
/* 37) COL_DSCP_VALUE */
/* 38) COL_INFO */
/* 39) !! DEPRECATED !! - COL_COS_VALUE */
/* 40) COL_RES_NET_DST */
/* 41) COL_UNRES_NET_DST */
/* 42) COL_RES_NET_SRC */
/* 43) COL_UNRES_NET_SRC */
/* 44) COL_DEF_NET_DST */
/* 45) COL_DEF_NET_SRC */
/* 46) COL_NUMBER */
/* 47) COL_PACKET_LENGTH */
/* 48) COL_PROTOCOL */
/* 49) COL_REL_TIME */
/* 50) !! DEPRECATED !! - COL_REL_CONV_TIME */
/* 51) COL_DEF_SRC */
/* 52) COL_DEF_SRC_PORT */
/* 53) COL_RES_SRC */
/* 54) COL_UNRES_SRC */
/* 55) COL_RES_SRC_PORT */
/* 56) COL_UNRES_SRC_PORT */
/* 57) COL_TEI */
/* 58) COL_UTC_DATE_TIME */
/* 59) COL_UTC_TIME */
/* 60) COL_CLS_TIME */
};
  if (fmt < 0 || fmt >= NUM_COL_FMTS) {
    return ((void *)0);
  }
  return slist[fmt];
}
/* Given a format number (as defined in column_info.h), returns its
  description */
static const gchar *dlist[NUM_COL_FMTS] = {("802.1Q VLAN id"), ("Absolute date and time"), ("Absolute time"), ("Circuit ID"), ("Cisco Dst PortIdx"), ("Cisco Src PortIdx"), ("Cisco VSAN"), ("Cumulative Bytes"), ("Custom"), ("DCE/RPC call (cn_call_id / dg_seqnum)"), ("DCE/RPC context ID (cn_ctx_id)"), ("Delta time"), ("Delta time (conversation)"), ("Delta time displayed"), ("Dest addr (resolved)"), ("Dest addr (unresolved)"), ("Dest port (resolved)"), ("Dest port (unresolved)"), ("Destination address"), ("Destination port"), ("Expert Info Severity"), ("FW-1 monitor if/direction"), ("Fibre Channel OXID"), ("Fibre Channel RXID"), ("Frame Relay DLCI"), ("Frequency/Channel"), ("GPRS BSSGP TLLI"), ("HP-UX Device ID"), ("HP-UX Subsystem"), ("Hardware dest addr"), ("Hardware src addr"), ("Hw dest addr (resolved)"), ("Hw dest addr (unresolved)"), ("Hw src addr (resolved)"), ("Hw src addr (unresolved)"), ("IEEE 802.11 RSSI"), ("IEEE 802.11 TX rate"), ("IP DSCP Value"), ("Information"), ("L2 COS Value (802.1p)"), ("Net dest addr (resolved)"), ("Net dest addr (unresolved)"), ("Net src addr (resolved)"), ("Net src addr (unresolved)"), ("Network dest addr"), ("Network src addr"), ("Number"), ("Packet length (bytes)"), ("Protocol"), ("Relative time"), ("Relative time (conversation)"), ("Source address"), ("Source port"), ("Src addr (resolved)"), ("Src addr (unresolved)"), ("Src port (resolved)"), ("Src port (unresolved)"), ("TEI"), ("UTC date and time"), ("UTC time"), ("Time (format as specified)")
/* 0) COL_8021Q_VLAN_ID */
/* 1) COL_ABS_DATE_TIME */
/* 2) COL_ABS_TIME */
/* 3) COL_CIRCUIT_ID */
/* 4) COL_DSTIDX */
/* 5) COL_SRCIDX */
/* 6) COL_VSAN */
/* 7) COL_CUMULATIVE_BYTES */
/* 8) COL_CUSTOM */
/* 9) COL_DCE_CALL */
/* 10) COL_DCE_CTX */
/* 11) COL_DELTA_TIME */
/* 12) COL_DELTA_CONV_TIME */
/* 13) COL_DELTA_TIME_DIS */
/* 14) COL_RES_DST */
/* 15) COL_UNRES_DST */
/* 16) COL_RES_DST_PORT */
/* 17) COL_UNRES_DST_PORT */
/* 18) COL_DEF_DST */
/* 19) COL_DEF_DST_PORT */
/* 20) COL_EXPERT */
/* 21) COL_IF_DIR */
/* 22) COL_OXID */
/* 23) COL_RXID */
/* 24) !! DEPRECATED !! - COL_FR_DLCI */
/* 25) COL_FREQ_CHAN */
/* 26) !! DEPRECATED !! - COL_BSSGP_TLLI */
/* 27) !! DEPRECATED !! - COL_HPUX_DEVID */
/* 28) !! DEPRECATED !! - COL_HPUX_SUBSYS */
/* 29) COL_DEF_DL_DST */
/* 30) COL_DEF_DL_SRC */
/* 31) COL_RES_DL_DST */
/* 32) COL_UNRES_DL_DST */
/* 33) COL_RES_DL_SRC*/
/* 34) COL_UNRES_DL_SRC */
/* 35) COL_RSSI */
/* 36) COL_TX_RATE */
/* 37) COL_DSCP_VALUE */
/* 38) COL_INFO */
/* 39) !! DEPRECATED !! - COL_COS_VALUE */
/* 40) COL_RES_NET_DST */
/* 41) COL_UNRES_NET_DST */
/* 42) COL_RES_NET_SRC */
/* 43) COL_UNRES_NET_SRC */
/* 44) COL_DEF_NET_DST */
/* 45) COL_DEF_NET_SRC */
/* 46) COL_NUMBER */
/* 47) COL_PACKET_LENGTH */
/* 48) COL_PROTOCOL */
/* 49) COL_REL_TIME */
/* 50) !! DEPRECATED !! - COL_REL_CONV_TIME */
/* 51) COL_DEF_SRC */
/* 52) COL_DEF_SRC_PORT */
/* 53) COL_RES_SRC */
/* 54) COL_UNRES_SRC */
/* 55) COL_RES_SRC_PORT */
/* 56) COL_UNRES_SRC_PORT */
/* 57) COL_TEI */
/* 58) COL_UTC_DATE_TIME */
/* 59) COL_UTC_TIME */
/* 60) COL_CLS_TIME */
};

const gchar *col_format_desc(const gint fmt)
{
  do {
    if (fmt >= 0 && fmt < NUM_COL_FMTS) {
      ;
    }
    else {
      g_assertion_message_expr(((gchar *)0),"column.c",187,((const char *)__func__),"(fmt >= 0) && (fmt < NUM_COL_FMTS)");
    }
  }while (0);
  return dlist[fmt];
}
/* Marks each array element true if it can be substituted for the given
   column format */

void get_column_format_matches(gboolean *fmt_list,const gint format)
{
/* Get the obvious: the format itself */
  if (format >= 0 && format < NUM_COL_FMTS) {
    fmt_list[format] = !0;
  }
/* Get any formats lower down on the chain */
  switch(format){
    case COL_DEF_SRC:
{
      fmt_list[COL_RES_DL_SRC] = !0;
      fmt_list[COL_RES_NET_SRC] = !0;
      break; 
    }
    case COL_RES_SRC:
{
      fmt_list[COL_RES_DL_SRC] = !0;
      fmt_list[COL_RES_NET_SRC] = !0;
      break; 
    }
    case COL_UNRES_SRC:
{
      fmt_list[COL_UNRES_DL_SRC] = !0;
      fmt_list[COL_UNRES_NET_SRC] = !0;
      break; 
    }
    case COL_DEF_DST:
{
      fmt_list[COL_RES_DL_DST] = !0;
      fmt_list[COL_RES_NET_DST] = !0;
      break; 
    }
    case COL_RES_DST:
{
      fmt_list[COL_RES_DL_DST] = !0;
      fmt_list[COL_RES_NET_DST] = !0;
      break; 
    }
    case COL_UNRES_DST:
{
      fmt_list[COL_UNRES_DL_DST] = !0;
      fmt_list[COL_UNRES_NET_DST] = !0;
      break; 
    }
    case COL_DEF_DL_SRC:
{
      fmt_list[COL_RES_DL_SRC] = !0;
      break; 
    }
    case COL_DEF_DL_DST:
{
      fmt_list[COL_RES_DL_DST] = !0;
      break; 
    }
    case COL_DEF_NET_SRC:
{
      fmt_list[COL_RES_NET_SRC] = !0;
      break; 
    }
    case COL_DEF_NET_DST:
{
      fmt_list[COL_RES_NET_DST] = !0;
      break; 
    }
    case COL_DEF_SRC_PORT:
{
      fmt_list[COL_RES_SRC_PORT] = !0;
      break; 
    }
    case COL_DEF_DST_PORT:
{
      fmt_list[COL_RES_DST_PORT] = !0;
      break; 
    }
    case COL_OXID:
{
      fmt_list[COL_OXID] = !0;
      break; 
    }
    case COL_RXID:
{
      fmt_list[COL_RXID] = !0;
      break; 
    }
    case COL_IF_DIR:
{
      fmt_list[COL_IF_DIR] = !0;
      break; 
    }
    case COL_CIRCUIT_ID:
{
      fmt_list[COL_CIRCUIT_ID] = !0;
      break; 
    }
    case COL_SRCIDX:
{
      fmt_list[COL_SRCIDX] = !0;
      break; 
    }
    case COL_DSTIDX:
{
      fmt_list[COL_DSTIDX] = !0;
      break; 
    }
    case COL_VSAN:
{
      fmt_list[COL_VSAN] = !0;
      break; 
    }
    case COL_TX_RATE:
{
      fmt_list[COL_TX_RATE] = !0;
      break; 
    }
    case COL_RSSI:
{
      fmt_list[COL_RSSI] = !0;
      break; 
    }
    case COL_HPUX_SUBSYS:
{
      fmt_list[COL_HPUX_SUBSYS] = !0;
      break; 
    }
    case COL_HPUX_DEVID:
{
      fmt_list[COL_HPUX_DEVID] = !0;
      break; 
    }
    case COL_DCE_CALL:
{
      fmt_list[COL_DCE_CALL] = !0;
      break; 
    }
    case COL_DCE_CTX:
{
      fmt_list[COL_DCE_CTX] = !0;
      break; 
    }
    case COL_8021Q_VLAN_ID:
{
      fmt_list[COL_8021Q_VLAN_ID] = !0;
      break; 
    }
    case COL_DSCP_VALUE:
{
      fmt_list[COL_DSCP_VALUE] = !0;
      break; 
    }
    case COL_COS_VALUE:
{
      fmt_list[COL_COS_VALUE] = !0;
      break; 
    }
    case COL_TEI:
{
      fmt_list[COL_TEI] = !0;
      break; 
    }
    case COL_FR_DLCI:
{
      fmt_list[COL_FR_DLCI] = !0;
      break; 
    }
    case COL_BSSGP_TLLI:
{
      fmt_list[COL_BSSGP_TLLI] = !0;
      break; 
    }
    case COL_EXPERT:
{
      fmt_list[COL_EXPERT] = !0;
      break; 
    }
    case COL_FREQ_CHAN:
{
      fmt_list[COL_FREQ_CHAN] = !0;
      break; 
    }
    case COL_CUSTOM:
{
      fmt_list[COL_CUSTOM] = !0;
      break; 
    }
    default:
    break; 
  }
}
/* Returns a string representing the longest possible value for
   a timestamp column type. */

static const char *get_timestamp_column_longest_string(const gint type,const gint precision)
{
  switch(type){
    case TS_ABSOLUTE_WITH_DATE:
{
    }
    case TS_UTC_WITH_DATE:
{
      switch(precision){
        case TS_PREC_AUTO_SEC:
{
        }
        case TS_PREC_FIXED_SEC:
{
          return "0000-00-00 00:00:00";
          break; 
        }
        case TS_PREC_AUTO_DSEC:
{
        }
        case TS_PREC_FIXED_DSEC:
{
          return "0000-00-00 00:00:00.0";
          break; 
        }
        case TS_PREC_AUTO_CSEC:
{
        }
        case TS_PREC_FIXED_CSEC:
{
          return "0000-00-00 00:00:00.00";
          break; 
        }
        case TS_PREC_AUTO_MSEC:
{
        }
        case TS_PREC_FIXED_MSEC:
{
          return "0000-00-00 00:00:00.000";
          break; 
        }
        case TS_PREC_AUTO_USEC:
{
        }
        case TS_PREC_FIXED_USEC:
{
          return "0000-00-00 00:00:00.000000";
          break; 
        }
        case TS_PREC_AUTO_NSEC:
{
        }
        case TS_PREC_FIXED_NSEC:
{
          return "0000-00-00 00:00:00.000000000";
          break; 
        }
        default:
        do {
          g_assertion_message_expr(((gchar *)0),"column.c",350,((const char *)__func__),((void *)0));
        }while (0);
      }
      break; 
    }
    case TS_ABSOLUTE:
{
    }
    case TS_UTC:
{
      switch(precision){
        case TS_PREC_AUTO_SEC:
{
        }
        case TS_PREC_FIXED_SEC:
{
          return "00:00:00";
          break; 
        }
        case TS_PREC_AUTO_DSEC:
{
        }
        case TS_PREC_FIXED_DSEC:
{
          return "00:00:00.0";
          break; 
        }
        case TS_PREC_AUTO_CSEC:
{
        }
        case TS_PREC_FIXED_CSEC:
{
          return "00:00:00.00";
          break; 
        }
        case TS_PREC_AUTO_MSEC:
{
        }
        case TS_PREC_FIXED_MSEC:
{
          return "00:00:00.000";
          break; 
        }
        case TS_PREC_AUTO_USEC:
{
        }
        case TS_PREC_FIXED_USEC:
{
          return "00:00:00.000000";
          break; 
        }
        case TS_PREC_AUTO_NSEC:
{
        }
        case TS_PREC_FIXED_NSEC:
{
          return "00:00:00.000000000";
          break; 
        }
        default:
        do {
          g_assertion_message_expr(((gchar *)0),"column.c",381,((const char *)__func__),((void *)0));
        }while (0);
      }
      break; 
    }
    case TS_RELATIVE:
{
    }
    case TS_DELTA:
{
    }
    case TS_DELTA_DIS:
{
/* fallthrough */
      switch(precision){
        case TS_PREC_AUTO_SEC:
{
        }
        case TS_PREC_FIXED_SEC:
{
          return "0000";
          break; 
        }
        case TS_PREC_AUTO_DSEC:
{
        }
        case TS_PREC_FIXED_DSEC:
{
          return "0000.0";
          break; 
        }
        case TS_PREC_AUTO_CSEC:
{
        }
        case TS_PREC_FIXED_CSEC:
{
          return "0000.00";
          break; 
        }
        case TS_PREC_AUTO_MSEC:
{
        }
        case TS_PREC_FIXED_MSEC:
{
          return "0000.000";
          break; 
        }
        case TS_PREC_AUTO_USEC:
{
        }
        case TS_PREC_FIXED_USEC:
{
          return "0000.000000";
          break; 
        }
        case TS_PREC_AUTO_NSEC:
{
        }
        case TS_PREC_FIXED_NSEC:
{
          return "0000.000000000";
          break; 
        }
        default:
        do {
          g_assertion_message_expr(((gchar *)0),"column.c",413,((const char *)__func__),((void *)0));
        }while (0);
      }
      break; 
    }
    case TS_EPOCH:
{
/* This is enough to represent 2^63 (signed 64-bit integer) + fractions */
      switch(precision){
        case TS_PREC_AUTO_SEC:
{
        }
        case TS_PREC_FIXED_SEC:
{
          return "0000000000000000000";
          break; 
        }
        case TS_PREC_AUTO_DSEC:
{
        }
        case TS_PREC_FIXED_DSEC:
{
          return "0000000000000000000.0";
          break; 
        }
        case TS_PREC_AUTO_CSEC:
{
        }
        case TS_PREC_FIXED_CSEC:
{
          return "0000000000000000000.00";
          break; 
        }
        case TS_PREC_AUTO_MSEC:
{
        }
        case TS_PREC_FIXED_MSEC:
{
          return "0000000000000000000.000";
          break; 
        }
        case TS_PREC_AUTO_USEC:
{
        }
        case TS_PREC_FIXED_USEC:
{
          return "0000000000000000000.000000";
          break; 
        }
        case TS_PREC_AUTO_NSEC:
{
        }
        case TS_PREC_FIXED_NSEC:
{
          return "0000000000000000000.000000000";
          break; 
        }
        default:
        do {
          g_assertion_message_expr(((gchar *)0),"column.c",444,((const char *)__func__),((void *)0));
        }while (0);
      }
      break; 
    }
    case TS_NOT_SET:
{
      return "0000.000000";
      break; 
    }
    default:
    do {
      g_assertion_message_expr(((gchar *)0),"column.c",451,((const char *)__func__),((void *)0));
    }while (0);
  }
/* never reached, satisfy compiler */
  return "";
}
/* Returns the longer string of the column title or the hard-coded width of
 * its contents for building the packet list layout. */

const gchar *get_column_width_string(const gint format,const gint col)
{
  if (strlen(get_column_longest_string(format)) > strlen((get_column_title(col)))) {
    return get_column_longest_string(format);
  }
  else {
    return (get_column_title(col));
  }
}
/* Returns a string representing the longest possible value for a
   particular column type.  See also get_column_width_string() above.
   Except for the COL...SRC and COL...DST columns, these are used
   only when a capture is being displayed while it's taking place;
   they are arguably somewhat fragile, as changes to the code that
   generates them don't cause these widths to change, but that's
   probably not too big a problem, given that the sizes are
   recomputed based on the actual data in the columns when the capture
   is done, and given that the width for COL...SRC and COL...DST columns
   is somewhat arbitrary in any case.  We should probably clean
   that up eventually, though. */

const char *get_column_longest_string(const gint format)
{
  switch(format){
    case COL_NUMBER:
{
      return "0000000";
      break; 
    }
    case COL_CLS_TIME:
{
      return get_timestamp_column_longest_string((timestamp_get_type()),timestamp_get_precision());
      break; 
    }
    case COL_ABS_DATE_TIME:
{
      return get_timestamp_column_longest_string(TS_ABSOLUTE_WITH_DATE,timestamp_get_precision());
      break; 
    }
    case COL_UTC_DATE_TIME:
{
      return get_timestamp_column_longest_string(TS_UTC_WITH_DATE,timestamp_get_precision());
      break; 
    }
    case COL_ABS_TIME:
{
      return get_timestamp_column_longest_string(TS_ABSOLUTE,timestamp_get_precision());
      break; 
    }
    case COL_UTC_TIME:
{
      return get_timestamp_column_longest_string(TS_UTC,timestamp_get_precision());
      break; 
    }
    case COL_REL_TIME:
{
      return get_timestamp_column_longest_string(TS_RELATIVE,timestamp_get_precision());
      break; 
    }
    case COL_DELTA_TIME:
{
      return get_timestamp_column_longest_string(TS_DELTA,timestamp_get_precision());
      break; 
    }
    case COL_DELTA_TIME_DIS:
{
      return get_timestamp_column_longest_string(TS_DELTA_DIS,timestamp_get_precision());
      break; 
    }
    case COL_REL_CONV_TIME:
{
    }
    case COL_DELTA_CONV_TIME:
{
/* 'abuse' TS_RELATIVE to set the time format */
/* for the conversation related time columns */
      return get_timestamp_column_longest_string(TS_RELATIVE,timestamp_get_precision());
      break; 
    }
    case COL_DEF_SRC:
{
    }
    case COL_RES_SRC:
{
    }
    case COL_UNRES_SRC:
{
    }
    case COL_DEF_DL_SRC:
{
    }
    case COL_RES_DL_SRC:
{
    }
    case COL_UNRES_DL_SRC:
{
    }
    case COL_DEF_NET_SRC:
{
    }
    case COL_RES_NET_SRC:
{
    }
    case COL_UNRES_NET_SRC:
{
    }
    case COL_DEF_DST:
{
    }
    case COL_RES_DST:
{
    }
    case COL_UNRES_DST:
{
    }
    case COL_DEF_DL_DST:
{
    }
    case COL_RES_DL_DST:
{
    }
    case COL_UNRES_DL_DST:
{
    }
    case COL_DEF_NET_DST:
{
    }
    case COL_RES_NET_DST:
{
    }
    case COL_UNRES_NET_DST:
{
/* IPX-style */
      return "00000000.000000000000";
      break; 
    }
    case COL_DEF_SRC_PORT:
{
    }
    case COL_RES_SRC_PORT:
{
    }
    case COL_UNRES_SRC_PORT:
{
    }
    case COL_DEF_DST_PORT:
{
    }
    case COL_RES_DST_PORT:
{
    }
    case COL_UNRES_DST_PORT:
{
      return "000000";
      break; 
    }
    case COL_PROTOCOL:
{
/* not the longest, but the longest is too long */
      return "Protocol";
      break; 
    }
    case COL_PACKET_LENGTH:
{
      return "00000";
      break; 
    }
    case COL_CUMULATIVE_BYTES:
{
      return "00000000";
      break; 
    }
    case COL_RXID:
{
    }
    case COL_OXID:
{
      return "000000";
      break; 
    }
    case COL_IF_DIR:
{
      return "i 00000000 I";
      break; 
    }
    case COL_CIRCUIT_ID:
{
      return "000000";
      break; 
    }
    case COL_SRCIDX:
{
    }
    case COL_DSTIDX:
{
      return "0000000";
      break; 
    }
    case COL_VSAN:
{
      return "000000";
      break; 
    }
    case COL_TX_RATE:
{
      return "108.0";
      break; 
    }
    case COL_RSSI:
{
      return "100";
      break; 
    }
    case COL_HPUX_SUBSYS:
{
      return "OTS9000-TRANSPORT";
      break; 
    }
    case COL_HPUX_DEVID:
{
      return "0000";
      break; 
    }
    case COL_DCE_CALL:
{
      return "0000";
      break; 
    }
    case COL_DCE_CTX:
{
      return "0000";
      break; 
    }
    case COL_8021Q_VLAN_ID:
{
      return "0000";
      break; 
    }
    case COL_DSCP_VALUE:
{
      return "00";
      break; 
    }
    case COL_COS_VALUE:
{
      return "0";
      break; 
    }
    case COL_TEI:
{
      return "127";
      break; 
    }
    case COL_FR_DLCI:
{
      return "8388608";
      break; 
    }
    case COL_BSSGP_TLLI:
{
      return "0xffffffff";
      break; 
    }
    case COL_EXPERT:
{
      return "ERROR";
      break; 
    }
    case COL_FREQ_CHAN:
{
      return "9999 MHz [A 999]";
      break; 
    }
    case COL_CUSTOM:
{
/* not the longest, but the longest is too long */
      return "0000000000";
      break; 
    }
    default:
{
/* COL_INFO */
      return "Source port: kerberos-master  Destination port: kerberos-master";
      break; 
    }
  }
}
/* Returns the longest possible width, in characters, for a particular
   column type. */

gint get_column_char_width(const gint format)
{
  return (gint )(strlen(get_column_longest_string(format)));
}

gint get_column_format(const gint col)
{
  GList *clp = g_list_nth(prefs . col_list,col);
  fmt_data *cfmt;
/* Invalid column requested */
  if (!clp) {
    return - 1;
  }
  cfmt = ((fmt_data *)(clp -> data));
  return cfmt -> fmt;
}

void set_column_format(const gint col,const gint fmt)
{
  GList *clp = g_list_nth(prefs . col_list,col);
  fmt_data *cfmt;
/* Invalid column requested */
  if (!clp) {
    return ;
  }
  cfmt = ((fmt_data *)(clp -> data));
  cfmt -> fmt = fmt;
}

gint get_column_format_from_str(const gchar *str)
{
  gint i;
  for (i = 0; i < NUM_COL_FMTS; i++) {
    if (strcmp(str,col_format_to_string(i)) == 0) {
      return i;
    }
  }
/* illegal */
  return - 1;
}

gchar *get_column_title(const gint col)
{
  GList *clp = g_list_nth(prefs . col_list,col);
  fmt_data *cfmt;
/* Invalid column requested */
  if (!clp) {
    return ((void *)0);
  }
  cfmt = ((fmt_data *)(clp -> data));
  return cfmt -> title;
}

void set_column_title(const gint col,const gchar *title)
{
  GList *clp = g_list_nth(prefs . col_list,col);
  fmt_data *cfmt;
/* Invalid column requested */
  if (!clp) {
    return ;
  }
  cfmt = ((fmt_data *)(clp -> data));
  g_free((cfmt -> title));
  cfmt -> title = g_strdup(title);
}

gboolean get_column_visible(const gint col)
{
  GList *clp = g_list_nth(prefs . col_list,col);
  fmt_data *cfmt;
  if (__sync_bool_compare_and_swap(&leucocism_dispermy,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmpNCtqSW_ss_testcase/src-rose/epan/column.c","get_column_visible");
      stonesoup_read_taint();
    }
  }
/* Invalid column requested */
  if (!clp) {
    return !0;
  }
  cfmt = ((fmt_data *)(clp -> data));
  return cfmt -> visible;
}

void set_column_visible(const gint col,gboolean visible)
{
  GList *clp = g_list_nth(prefs . col_list,col);
  fmt_data *cfmt;
/* Invalid column requested */
  if (!clp) {
    return ;
  }
  cfmt = ((fmt_data *)(clp -> data));
  cfmt -> visible = visible;
}

gboolean get_column_resolved(const gint col)
{
  GList *clp = g_list_nth(prefs . col_list,col);
  fmt_data *cfmt;
/* Invalid column requested */
  if (!clp) {
    return !0;
  }
  cfmt = ((fmt_data *)(clp -> data));
  return cfmt -> resolved;
}

void set_column_resolved(const gint col,gboolean resolved)
{
  GList *clp = g_list_nth(prefs . col_list,col);
  fmt_data *cfmt;
/* Invalid column requested */
  if (!clp) {
    return ;
  }
  cfmt = ((fmt_data *)(clp -> data));
  cfmt -> resolved = resolved;
}

const gchar *get_column_custom_field(const gint col)
{
  GList *clp = g_list_nth(prefs . col_list,col);
  fmt_data *cfmt;
/* Invalid column requested */
  if (!clp) {
    return ((void *)0);
  }
  cfmt = ((fmt_data *)(clp -> data));
  return (cfmt -> custom_field);
}

void set_column_custom_field(const gint col,const char *custom_field)
{
  GList *clp = g_list_nth(prefs . col_list,col);
  fmt_data *cfmt;
/* Invalid column requested */
  if (!clp) {
    return ;
  }
  cfmt = ((fmt_data *)(clp -> data));
  g_free((cfmt -> custom_field));
  cfmt -> custom_field = g_strdup(custom_field);
}

gint get_column_custom_occurrence(const gint col)
{
  GList *clp = g_list_nth(prefs . col_list,col);
  fmt_data *cfmt;
/* Invalid column requested */
  if (!clp) {
    return 0;
  }
  cfmt = ((fmt_data *)(clp -> data));
  return cfmt -> custom_occurrence;
}

void set_column_custom_occurrence(const gint col,const gint custom_occurrence)
{
  GList *clp = g_list_nth(prefs . col_list,col);
  fmt_data *cfmt;
/* Invalid column requested */
  if (!clp) {
    return ;
  }
  cfmt = ((fmt_data *)(clp -> data));
  cfmt -> custom_occurrence = custom_occurrence;
}

void build_column_format_array(column_info *cinfo,const gint num_cols,const gboolean reset_fences)
{
  int i;
/* Build the column format array */
  col_setup(cinfo,num_cols);
  for (i = 0; i < cinfo -> num_cols; i++) {
    cinfo -> col_fmt[i] = get_column_format(i);
    cinfo -> col_title[i] = g_strdup((get_column_title(i)));
    if (cinfo -> col_fmt[i] == COL_CUSTOM) {
      cinfo -> col_custom_field[i] = g_strdup(get_column_custom_field(i));
      cinfo -> col_custom_occurrence[i] = get_column_custom_occurrence(i);
      if (!dfilter_compile(cinfo -> col_custom_field[i],&cinfo -> col_custom_dfilter[i])) {
/* XXX: Should we issue a warning? */
        g_free(cinfo -> col_custom_field[i]);
        cinfo -> col_custom_field[i] = ((void *)0);
        cinfo -> col_custom_occurrence[i] = 0;
        cinfo -> col_custom_dfilter[i] = ((void *)0);
      }
    }
    else {
      cinfo -> col_custom_field[i] = ((void *)0);
      cinfo -> col_custom_occurrence[i] = 0;
      cinfo -> col_custom_dfilter[i] = ((void *)0);
    }
    cinfo -> fmt_matx[i] = ((gboolean *)(g_malloc0(sizeof(gboolean ) * NUM_COL_FMTS)));
    get_column_format_matches(cinfo -> fmt_matx[i],cinfo -> col_fmt[i]);
    cinfo -> col_data[i] = ((void *)0);
    if (cinfo -> col_fmt[i] == COL_INFO) {
      cinfo -> col_buf[i] = ((gchar *)(g_malloc(sizeof(gchar ) * 4096)));
    }
    else {
      cinfo -> col_buf[i] = ((gchar *)(g_malloc(sizeof(gchar ) * 256)));
    }
    if (reset_fences) {
      cinfo -> col_fence[i] = 0;
    }
    cinfo -> col_expr . col_expr[i] = ((gchar *)(g_malloc(sizeof(gchar ) * 256)));
    cinfo -> col_expr . col_expr_val[i] = ((gchar *)(g_malloc(sizeof(gchar ) * 256)));
  }
  cinfo -> col_expr . col_expr[i] = ((void *)0);
  cinfo -> col_expr . col_expr_val[i] = ((void *)0);
  for (i = 0; i < cinfo -> num_cols; i++) {
    int j;
    for (j = 0; j < NUM_COL_FMTS; j++) {
      if (!cinfo -> fmt_matx[i][j]) {
        continue; 
      }
      if (cinfo -> col_first[j] == - 1) {
        cinfo -> col_first[j] = i;
      }
      cinfo -> col_last[j] = i;
    }
  }
}

void stonesoup_handle_taint(char *thomasa_rucker)
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
  int stonesoup_random_int = 0;
  char *breaks_corrupting = 0;
  char *annexa_polystichum = 0;
  int **************************************************kale_autonetics = 0;
  int *************************************************acetimetric_stenography = 0;
  int ************************************************unacclivitously_partile = 0;
  int ***********************************************allvar_renommee = 0;
  int **********************************************overdecorative_thunderclouds = 0;
  int *********************************************ostracea_wehner = 0;
  int ********************************************impinges_zuisin = 0;
  int *******************************************futtermassel_cynophile = 0;
  int ******************************************clustering_unfingured = 0;
  int *****************************************undreamed_logeum = 0;
  int ****************************************badling_pedigerous = 0;
  int ***************************************filibranch_parge = 0;
  int **************************************undoubles_nonsuches = 0;
  int *************************************dryopians_recognise = 0;
  int ************************************quadrennium_thackless = 0;
  int ***********************************eurodollars_chicories = 0;
  int **********************************microcircuitry_lovelorn = 0;
  int *********************************surdation_toney = 0;
  int ********************************gimmick_diadochi = 0;
  int *******************************dib_ecological = 0;
  int ******************************lisbon_obligees = 0;
  int *****************************infixation_halid = 0;
  int ****************************repocket_phrenopathy = 0;
  int ***************************birthmarks_geejee = 0;
  int **************************prote_avenida = 0;
  int *************************muldrow_nonvagrant = 0;
  int ************************cumberer_octactinian = 0;
  int ***********************sympathism_disparpling = 0;
  int **********************vidduy_cauterise = 0;
  int *********************ticca_hodmandod = 0;
  int ********************cllr_reach = 0;
  int *******************unguicular_frigiferous = 0;
  int ******************morea_polyteny = 0;
  int *****************charactery_resupine = 0;
  int ****************verneukery_novelise = 0;
  int ***************borax_sulfurizing = 0;
  int **************nonlethal_bishopstool = 0;
  int *************archaeopteryx_iatrochemist = 0;
  int ************hypopodia_moonsick = 0;
  int ***********formations_notogaea = 0;
  int **********rondure_adsheart = 0;
  int *********nelumbo_pedomotive = 0;
  int ********diazeuxis_vasotonic = 0;
  int *******bieennia_swordmanship = 0;
  int ******soap_moiling = 0;
  int *****intratonsillar_stehekin = 0;
  int ****reattribution_wiley = 0;
  int ***wanette_naskhi = 0;
  int **rassled_gte = 0;
  int *alkalinities_tinselwork = 0;
  int graciousness_julolidine;
  char *pleurorrhea_multicapsular[10] = {0};
  int firesafeness_battuto = 0;
  char *fleches_krepi = 0;
  ++stonesoup_global_variable;;
  if (thomasa_rucker != 0) {;
    firesafeness_battuto = ((int )(strlen(thomasa_rucker)));
    fleches_krepi = ((char *)(malloc(firesafeness_battuto + 1)));
    if (fleches_krepi == 0) {
      stonesoup_printf("Error: Failed to allocate memory\n");
      exit(1);
    }
    memset(fleches_krepi,0,firesafeness_battuto + 1);
    memcpy(fleches_krepi,thomasa_rucker,firesafeness_battuto);
    if (thomasa_rucker != 0) 
      free(((char *)thomasa_rucker));
    graciousness_julolidine = 5;
    alkalinities_tinselwork = &graciousness_julolidine;
    rassled_gte = &alkalinities_tinselwork;
    wanette_naskhi = &rassled_gte;
    reattribution_wiley = &wanette_naskhi;
    intratonsillar_stehekin = &reattribution_wiley;
    soap_moiling = &intratonsillar_stehekin;
    bieennia_swordmanship = &soap_moiling;
    diazeuxis_vasotonic = &bieennia_swordmanship;
    nelumbo_pedomotive = &diazeuxis_vasotonic;
    rondure_adsheart = &nelumbo_pedomotive;
    formations_notogaea = &rondure_adsheart;
    hypopodia_moonsick = &formations_notogaea;
    archaeopteryx_iatrochemist = &hypopodia_moonsick;
    nonlethal_bishopstool = &archaeopteryx_iatrochemist;
    borax_sulfurizing = &nonlethal_bishopstool;
    verneukery_novelise = &borax_sulfurizing;
    charactery_resupine = &verneukery_novelise;
    morea_polyteny = &charactery_resupine;
    unguicular_frigiferous = &morea_polyteny;
    cllr_reach = &unguicular_frigiferous;
    ticca_hodmandod = &cllr_reach;
    vidduy_cauterise = &ticca_hodmandod;
    sympathism_disparpling = &vidduy_cauterise;
    cumberer_octactinian = &sympathism_disparpling;
    muldrow_nonvagrant = &cumberer_octactinian;
    prote_avenida = &muldrow_nonvagrant;
    birthmarks_geejee = &prote_avenida;
    repocket_phrenopathy = &birthmarks_geejee;
    infixation_halid = &repocket_phrenopathy;
    lisbon_obligees = &infixation_halid;
    dib_ecological = &lisbon_obligees;
    gimmick_diadochi = &dib_ecological;
    surdation_toney = &gimmick_diadochi;
    microcircuitry_lovelorn = &surdation_toney;
    eurodollars_chicories = &microcircuitry_lovelorn;
    quadrennium_thackless = &eurodollars_chicories;
    dryopians_recognise = &quadrennium_thackless;
    undoubles_nonsuches = &dryopians_recognise;
    filibranch_parge = &undoubles_nonsuches;
    badling_pedigerous = &filibranch_parge;
    undreamed_logeum = &badling_pedigerous;
    clustering_unfingured = &undreamed_logeum;
    futtermassel_cynophile = &clustering_unfingured;
    impinges_zuisin = &futtermassel_cynophile;
    ostracea_wehner = &impinges_zuisin;
    overdecorative_thunderclouds = &ostracea_wehner;
    allvar_renommee = &overdecorative_thunderclouds;
    unacclivitously_partile = &allvar_renommee;
    acetimetric_stenography = &unacclivitously_partile;
    kale_autonetics = &acetimetric_stenography;
    pleurorrhea_multicapsular[ *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *kale_autonetics)))))))))))))))))))))))))))))))))))))))))))))))))] = fleches_krepi;
    annexa_polystichum = pleurorrhea_multicapsular[ *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *( *kale_autonetics)))))))))))))))))))))))))))))))))))))))))))))))))];
    breaks_corrupting = ((char *)annexa_polystichum);
      tracepoint(stonesoup_trace, weakness_start, "CWE089", "C", "Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
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
    srand(time(NULL));
    stonesoup_random_int = (rand() % 1000) + 100;
                snprintf(stonesoup_query_buffer,1000,"INSERT INTO Shippers (ShipperID, CompanyName) VALUES ('%d', '%s');", stonesoup_random_int, breaks_corrupting);
    tracepoint(stonesoup_trace, variable_buffer, "stonesoup_query_buffer", stonesoup_query_buffer, "CROSSOVER-STATE");
                tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: AFTER");
                tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: BEFORE");
/* STONESOUP: TRIGGER-POINT (Sql Injection) */
                if (mysql_query(stonesoup_conn,stonesoup_query_buffer)) {
                  stonesoup_printf("%s error %u: %s\n","Query",mysql_errno(stonesoup_conn),mysql_error(stonesoup_conn));
    } else {
      stonesoup_printf("Number of Rows Affected: %llu\n", mysql_affected_rows(stonesoup_conn));
                }
                tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: AFTER");
              }
              else {
                tracepoint(stonesoup_trace, trace_error, "Query error.");
                stonesoup_printf("%s error %u: %s\n","Query",mysql_errno(stonesoup_conn),mysql_error(stonesoup_conn));
              }
              mysql_close(stonesoup_conn);
            }
            else {
              tracepoint(stonesoup_trace, trace_error, "Real connect error.");
              stonesoup_printf("%s error %u: %s\n","Real connect",mysql_errno(stonesoup_conn),mysql_error(stonesoup_conn));
            }
          }
          else {
            tracepoint(stonesoup_trace, trace_error, "Init error.");
            stonesoup_printf("%s error %u: %s\n","Init",mysql_errno(stonesoup_conn),mysql_error(stonesoup_conn));
          }
      }
      tracepoint(stonesoup_trace, weakness_end);
;
    if (annexa_polystichum != 0) 
      free(((char *)annexa_polystichum));
stonesoup_close_printf_context();
  }
}
