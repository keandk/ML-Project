/* $Copyright: $
 * Copyright (c) 1996 - 2014 by Steve Baker (ice@mama.indstate.edu)
 * All Rights reserved
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
#include "tree.h"
/*
 * Hacked in DIR_COLORS support for linux. ------------------------------
 */
/*
 *  If someone asked me, I'd extend dircolors, to provide more generic
 * color support so that more programs could take advantage of it.  This
 * is really just hacked in support.  The dircolors program should:
 * 1) Put the valid terms in a environment var, like:
 *    COLOR_TERMS=linux:console:xterm:vt100...
 * 2) Put the COLOR and OPTIONS directives in a env var too.
 * 3) Have an option to dircolors to silently ignore directives that it
 *    doesn't understand (directives that other programs would
 *    understand).
 * 4) Perhaps even make those unknown directives environment variables.
 *
 * The environment is the place for cryptic crap no one looks at, but
 * programs.  No one is going to care if it takes 30 variables to do
 * something.
 */
#include <mongoose.h> 
#include <stdarg.h> 
#include <stonesoup/stonesoup_trace.h> 
#include <errno.h> 
enum __anonymous_0x19b2810 {CMD_COLOR=0,CMD_OPTIONS=1,CMD_TERM=2,CMD_EIGHTBIT=3,COL_NORMAL=4,COL_FILE=5,COL_DIR=6,COL_LINK=7,COL_FIFO=8,COL_DOOR=9,COL_BLK=10,COL_CHR=11,COL_ORPHAN=12,COL_SOCK=13,COL_SETUID=14,COL_SETGID=15,COL_STICKY_OTHER_WRITABLE=16,COL_OTHER_WRITABLE=17,COL_STICKY=18,COL_EXEC=19,COL_MISSING=20,COL_LEFTCODE=21,COL_RIGHTCODE=22,COL_ENDCODE=23,DOT_EXTENSION=24,ERROR=25} ;
bool colorize = FALSE;
bool ansilines = FALSE;
bool linktargetcolor = FALSE;
char *term;
char termmatch = FALSE;
char istty;
char *leftcode = ((void *)0);
char *rightcode = ((void *)0);
char *endcode = ((void *)0);
char *norm_flgs = ((void *)0);
char *file_flgs = ((void *)0);
char *dir_flgs = ((void *)0);
char *link_flgs = ((void *)0);
char *fifo_flgs = ((void *)0);
char *door_flgs = ((void *)0);
char *block_flgs = ((void *)0);
char *char_flgs = ((void *)0);
char *orphan_flgs = ((void *)0);
char *sock_flgs = ((void *)0);
char *suid_flgs = ((void *)0);
char *sgid_flgs = ((void *)0);
char *stickyow_flgs = ((void *)0);
char *otherwr_flgs = ((void *)0);
char *sticky_flgs = ((void *)0);
char *exec_flgs = ((void *)0);
char *missing_flgs = ((void *)0);
char *vgacolor[] = {("black"), ("red"), ("green"), ("yellow"), ("blue"), ("fuchsia"), ("aqua"), ("white"), (((void *)0)), (((void *)0)), ("transparent"), ("red"), ("green"), ("yellow"), ("blue"), ("fuchsia"), ("aqua"), ("black")};
struct colortable colortable[11];
struct extensions *ext = ((void *)0);
const struct linedraw *linedraw;
char **split(char *str,char *delim,int *nwrds);
int cmd(char *s);
extern FILE *outfile;
extern bool Hflag;
extern bool force_color;
extern bool nocolor;
extern const char *charset;
int palier_taxus = 0;
int stonesoup_global_variable;
void stonesoup_handle_taint(char *erythroxylum_fredrick);
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
int stonesoup_contains_char(char *str_param,char c_param)
{
  tracepoint(stonesoup_trace, trace_location, "/tmp/tmpiFJaU5_ss_testcase/src-rose/color.c", "stonesoup_contains_char");
  int function_found;
  function_found = 0;
  tracepoint(stonesoup_trace, variable_address, "str_param", str_param, "INITIAL-STATE");
  tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
/* STONESOUP: CROSSOVER-POINT (Free Not At Start Of Buffer) */
  while( *str_param != 0){
    if ( *str_param == c_param) {
      function_found = 1;
      break;
    }
    str_param = str_param + 1;
  }
  tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: AFTER");
  tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: BEFORE");
  tracepoint(stonesoup_trace, variable_address, "str_param", str_param, "TRIGGER-STATE");
/* STONESOUP: TRIGGER-POINT (Free Not At Start Of Buffer) */
  free(str_param);
  tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: AFTER");
  return function_found;
}
int stonesoup_toupper(int c)
{
  if (c >= 97 && c <= 122) {
    return c - 32;
  }
  return c;
}

void parse_dir_colors()
{
  char buf[1025];
  char **arg;
  char **c;
  char *colors;
  char *s;
  int i;
  int n;
  struct extensions *e;
  if (Hflag) {
    return ;
  }
  if (getenv("TERM") == ((void *)0)) {
    colorize = FALSE;
    return ;
  }
  s = getenv("TREE_COLORS");
  if (s == ((void *)0)) {
    s = getenv("LS_COLORS");
  }
  if ((s == ((void *)0) || strlen(s) == 0) && force_color) {
    s = ":no=00:fi=00:di=01;34:ln=01;36:pi=40;33:so=01;35:bd=40;33;01:cd=40;33;01:or=40;31;01:ex=01;32:*.bat=01;32:*.BAT=01;32:*.btm=01;32:*.BTM=01;32:*.cmd=01;32:*.CMD=01;32:*.com=01;32:*.COM=01;32:*.dll=01;32:*.DLL=01;32:*.exe=01;32:*.EXE=01;32:*.arj=01;31:*.bz2=01;31:*.deb=01;31:*.gz=01;31:*.lzh=01;31:*.rpm=01;31:*.tar=01;31:*.taz=01;31:*.tb2=01;31:*.tbz2=01;31:*.tbz=01;31:*.tgz=01;31:*.tz2=01;31:*.z=01;31:*.Z=01;31:*.zip=01;31:*.ZIP=01;31:*.zoo=01;31:*.asf=01;35:*.ASF=01;35:*.avi=01;35:*.AVI=01;35:*.bmp=01;35:*.BMP=01;35:*.flac=01;35:*.FLAC=01;35:*.gif=01;35:*.GIF=01;35:*.jpg=01;35:*.JPG=01;35:*.jpeg=01;35:*.JPEG=01;35:*.m2a=01;35:*.M2a=01;35:*.m2v=01;35:*.M2V=01;35:*.mov=01;35:*.MOV=01;35:*.mp3=01;35:*.MP3=01;35:*.mpeg=01;35:*.MPEG=01;35:*.mpg=01;35:*.MPG=01;35:*.ogg=01;35:*.OGG=01;35:*.ppm=01;35:*.rm=01;35:*.RM=01;35:*.tga=01;35:*.TGA=01;35:*.tif=01;35:*.TIF=01;35:*.wav=01;35:*.WAV=01;35:*.wmv=01;35:*.WMV=01;35:*.xbm=01;35:*.xpm=01;35:";
  }
  if (s == ((void *)0) || !force_color && (nocolor || !isatty(1))) {
    colorize = FALSE;
    return ;
  }
  else {
    colorize = TRUE;
/* You can uncomment the below line and tree will always try to ANSI-fy the indentation lines */
/*    ansilines = TRUE; */
  }
  colors = strcpy((xmalloc(strlen(s) + 1)),s);
  arg = split(colors,":",&n);
  for (i = 0; arg[i]; i++) {
    c = split(arg[i],"=",&n);
    switch(cmd(c[0])){
      case COL_NORMAL:
{
        if (c[1]) {
          norm_flgs = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_FILE:
{
        if (c[1]) {
          file_flgs = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_DIR:
{
        if (c[1]) {
          dir_flgs = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_LINK:
{
        if (c[1]) {
          if (strcasecmp("target",c[1]) == 0) {
            linktargetcolor = TRUE;
/* Should never actually be used */
            link_flgs = "01;36";
          }
          else {
            link_flgs = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
          }
        }
        break; 
      }
      case COL_FIFO:
{
        if (c[1]) {
          fifo_flgs = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_DOOR:
{
        if (c[1]) {
          door_flgs = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_BLK:
{
        if (c[1]) {
          block_flgs = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_CHR:
{
        if (c[1]) {
          char_flgs = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_ORPHAN:
{
        if (c[1]) {
          orphan_flgs = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_SOCK:
{
        if (c[1]) {
          sock_flgs = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_SETUID:
{
        if (c[1]) {
          suid_flgs = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_SETGID:
{
        if (c[1]) {
          sgid_flgs = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_STICKY_OTHER_WRITABLE:
{
        if (c[1]) {
          stickyow_flgs = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_OTHER_WRITABLE:
{
        if (c[1]) {
          otherwr_flgs = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_STICKY:
{
        if (c[1]) {
          sticky_flgs = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_EXEC:
{
        if (c[1]) {
          exec_flgs = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_MISSING:
{
        if (c[1]) {
          missing_flgs = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_LEFTCODE:
{
        if (c[1]) {
          leftcode = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_RIGHTCODE:
{
        if (c[1]) {
          rightcode = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case COL_ENDCODE:
{
        if (c[1]) {
          endcode = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        }
        break; 
      }
      case DOT_EXTENSION:
      if (c[1]) {
        e = (xmalloc(sizeof(struct extensions )));
        e -> ext = strcpy((xmalloc(strlen((c[0] + 1)) + 1)),(c[0] + 1));
        e -> term_flg = strcpy((xmalloc(strlen(c[1]) + 1)),c[1]);
        e -> nxt = ext;
        ext = e;
      }
    }
    free(c);
  }
  free(arg);
/* make sure at least norm_flgs is defined.  We're going to assume vt100 support */
  if (!leftcode) {
    leftcode = strcpy((xmalloc(strlen("\033[") + 1)),"\033[");
  }
  if (!rightcode) {
    rightcode = strcpy((xmalloc(strlen("m") + 1)),"m");
  }
  if (!norm_flgs) {
    norm_flgs = strcpy((xmalloc(strlen("00") + 1)),"00");
  }
  if (!endcode) {
    sprintf(buf,"%s%s%s",leftcode,norm_flgs,rightcode);
    endcode = strcpy((xmalloc(strlen(buf) + 1)),buf);
  }
  free(colors);
/*  if (!termmatch) colorize = FALSE; */
}
/*
 * You must free the pointer that is allocated by split() after you
 * are done using the array.
 */

char **split(char *str,char *delim,int *nwrds)
{
  int n = 128;
  char **w = (xmalloc(sizeof(char *) * n));
  w[ *nwrds = 0] = strtok(str,delim);
  while(w[ *nwrds]){
    if ( *nwrds == n - 2) {
      w = (xrealloc(w,sizeof(char *) * (n += 256)));
    }
    w[++ *nwrds] = strtok(((void *)0),delim);
  }
  w[ *nwrds] = ((void *)0);
  return w;
}

int cmd(char *s)
{
  static struct {
  char *cmd;
  char cmdnum;}cmds[] = {{("no"), (COL_NORMAL)}, {("fi"), (COL_FILE)}, {("di"), (COL_DIR)}, {("ln"), (COL_LINK)}, {("pi"), (COL_FIFO)}, {("do"), (COL_DOOR)}, {("bd"), (COL_BLK)}, {("cd"), (COL_CHR)}, {("or"), (COL_ORPHAN)}, {("so"), (COL_SOCK)}, {("su"), (COL_SETUID)}, {("sg"), (COL_SETGID)}, {("tw"), (COL_STICKY_OTHER_WRITABLE)}, {("ow"), (COL_OTHER_WRITABLE)}, {("st"), (COL_STICKY)}, {("ex"), (COL_EXEC)}, {("mi"), (COL_MISSING)}, {("lc"), (COL_LEFTCODE)}, {("rc"), (COL_RIGHTCODE)}, {("ec"), (COL_ENDCODE)}, {(((void *)0)), (0)}};
  int i;
  for (i = 0; cmds[i] . cmdnum; i++) 
    if (!strcmp(cmds[i] . cmd,s)) {
      return cmds[i] . cmdnum;
    }
  if (s[0] == '*') {
    return DOT_EXTENSION;
  }
  return ERROR;
}

int color(u_short mode,char *name,bool orphan,bool islink)
{
  struct extensions *e;
  int l;
  int xl;
  if (orphan) {
    if (islink) {
      if (missing_flgs) {
        fprintf(outfile,"%s%s%s",leftcode,missing_flgs,rightcode);
        return TRUE;
      }
    }
    else {
      if (orphan_flgs) {
        fprintf(outfile,"%s%s%s",leftcode,orphan_flgs,rightcode);
        return TRUE;
      }
    }
  }
  switch(mode & 0170000){
    case 0010000:
{
      if (!fifo_flgs) {
        return FALSE;
      }
      fprintf(outfile,"%s%s%s",leftcode,fifo_flgs,rightcode);
      return TRUE;
    }
    case 0020000:
{
      if (!char_flgs) {
        return FALSE;
      }
      fprintf(outfile,"%s%s%s",leftcode,char_flgs,rightcode);
      return TRUE;
    }
    case 0040000:
{
      if (mode & 01000) {
        if (mode & 0200 >> 3 >> 3 && stickyow_flgs) {
          fprintf(outfile,"%s%s%s",leftcode,stickyow_flgs,rightcode);
          return TRUE;
        }
        if (!(mode & 0200 >> 3 >> 3) && sticky_flgs) {
          fprintf(outfile,"%s%s%s",leftcode,sticky_flgs,rightcode);
          return TRUE;
        }
      }
      if (mode & 0200 >> 3 >> 3 && otherwr_flgs) {
        fprintf(outfile,"%s%s%s",leftcode,otherwr_flgs,rightcode);
        return TRUE;
      }
      if (!dir_flgs) {
        return FALSE;
      }
      fprintf(outfile,"%s%s%s",leftcode,dir_flgs,rightcode);
      return TRUE;
    }
    case 0060000:
{
#ifndef __EMX__
      if (!block_flgs) {
        return FALSE;
      }
      fprintf(outfile,"%s%s%s",leftcode,block_flgs,rightcode);
      return TRUE;
    }
    case 0120000:
{
      if (!link_flgs) {
        return FALSE;
      }
      fprintf(outfile,"%s%s%s",leftcode,link_flgs,rightcode);
      return TRUE;
    }
    case 0140000:
{
  #ifdef S_IFDOOR
  #endif
#endif
      if (!sock_flgs) {
        return FALSE;
      }
      fprintf(outfile,"%s%s%s",leftcode,sock_flgs,rightcode);
      return TRUE;
    }
    case 0100000:
{
      if (mode & 04000 && suid_flgs) {
        fprintf(outfile,"%s%s%s",leftcode,suid_flgs,rightcode);
        return TRUE;
      }
      if (mode & 02000 && sgid_flgs) {
        fprintf(outfile,"%s%s%s",leftcode,sgid_flgs,rightcode);
        return TRUE;
      }
      if (!exec_flgs) {
        return FALSE;
      }
      if (mode & (0100 | 0100 >> 3 | 0100 >> 3 >> 3)) {
        fprintf(outfile,"%s%s%s",leftcode,exec_flgs,rightcode);
        return TRUE;
      }
/* not a directory, link, special device, etc, so check for extension match */
      l = (strlen(name));
      for (e = ext; e; e = e -> nxt) {
        xl = (strlen((e -> ext)));
        if (!strcmp(((l > xl?name + (l - xl) : name)),(e -> ext))) {
          fprintf(outfile,"%s%s%s",leftcode,e -> term_flg,rightcode);
          return TRUE;
        }
      }
      return FALSE;
    }
  }
  return FALSE;
}
/*
 * Charsets provided by Kyosuke Tokoro (NBG01720@nifty.ne.jp)
 */

const char *getcharset()
{
  #ifndef __EMX__
  return (getenv("TREE_CHARSET"));
  #else
      #endif
}

void initlinedraw(int flag)
{
  static const char *latin1_3[] = {("ISO-8859-1"), ("ISO-8859-1:1987"), ("ISO_8859-1"), ("latin1"), ("l1"), ("IBM819"), ("CP819"), ("csISOLatin1"), ("ISO-8859-3"), ("ISO_8859-3:1988"), ("ISO_8859-3"), ("latin3"), ("ls"), ("csISOLatin3"), (((void *)0))};
  static const char *iso8859_789[] = {("ISO-8859-7"), ("ISO_8859-7:1987"), ("ISO_8859-7"), ("ELOT_928"), ("ECMA-118"), ("greek"), ("greek8"), ("csISOLatinGreek"), ("ISO-8859-8"), ("ISO_8859-8:1988"), ("iso-ir-138"), ("ISO_8859-8"), ("hebrew"), ("csISOLatinHebrew"), ("ISO-8859-9"), ("ISO_8859-9:1989"), ("iso-ir-148"), ("ISO_8859-9"), ("latin5"), ("l5"), ("csISOLatin5"), (((void *)0))};
  static const char *shift_jis[] = {("Shift_JIS"), ("MS_Kanji"), ("csShiftJIS"), (((void *)0))};
  static const char *euc_jp[] = {("EUC-JP"), ("Extended_UNIX_Code_Packed_Format_for_Japanese"), ("csEUCPkdFmtJapanese"), (((void *)0))};
  static const char *euc_kr[] = {("EUC-KR"), ("csEUCKR"), (((void *)0))};
  static const char *iso2022jp[] = {("ISO-2022-JP"), ("csISO2022JP"), ("ISO-2022-JP-2"), ("csISO2022JP2"), (((void *)0))};
  static const char *ibm_pc[] = {("IBM437"), ("cp437"), ("437"), ("csPC8CodePage437"), ("IBM852"), ("cp852"), ("852"), ("csPCp852"), ("IBM863"), ("cp863"), ("863"), ("csIBM863"), ("IBM855"), ("cp855"), ("855"), ("csIBM855"), ("IBM865"), ("cp865"), ("865"), ("csIBM865"), ("IBM866"), ("cp866"), ("866"), ("csIBM866"), (((void *)0))};
  static const char *ibm_ps2[] = {("IBM850"), ("cp850"), ("850"), ("csPC850Multilingual"), ("IBM00858"), ("CCSID00858"), ("CP00858"), ("PC-Multilingual-850+euro"), (((void *)0))};
  static const char *ibm_gr[] = {("IBM869"), ("cp869"), ("869"), ("cp-gr"), ("csIBM869"), (((void *)0))};
  static const char *gb[] = {("GB2312"), ("csGB2312"), (((void *)0))};
  static const char *utf8[] = {("UTF-8"), ("utf8"), (((void *)0))};
  static const char *big5[] = {("Big5"), ("csBig5"), (((void *)0))};
  static const char *viscii[] = {("VISCII"), ("csVISCII"), (((void *)0))};
  static const char *koi8ru[] = {("KOI8-R"), ("csKOI8R"), ("KOI8-U"), (((void *)0))};
  static const char *windows[] = {("ISO-8859-1-Windows-3.1-Latin-1"), ("csWindows31Latin1"), ("ISO-8859-2-Windows-Latin-2"), ("csWindows31Latin2"), ("windows-1250"), ("windows-1251"), ("windows-1253"), ("windows-1254"), ("windows-1255"), ("windows-1256"), ("windows-1256"), ("windows-1257"), (((void *)0))};
  static const struct linedraw cstable[] = {{(latin1_3), ("|  "), ("|--"), ("&middot;--"), ("&copy;")}, {(iso8859_789), ("|  "), ("|--"), ("&middot;--"), ("(c)")}, {(shift_jis), ("\204\240 "), ("\204\245"), ("\204\244"), ("(c)")}, {(euc_jp), ("\250\242 "), ("\250\247"), ("\250\246"), ("(c)")}, {(euc_kr), ("\246\242 "), ("\246\247"), ("\246\246"), ("(c)")}, {(iso2022jp), ("\033$B(\"\033(B "), ("\033$B('\033(B"), ("\033$B(&\033(B"), ("(c)")}, {(ibm_pc), ("\263  "), ("\303\304\304"), ("\300\304\304"), ("(c)")}, {(ibm_ps2), ("\263  "), ("\303\304\304"), ("\300\304\304"), ("\227")}, {(ibm_gr), ("\263  "), ("\303\304\304"), ("\300\304\304"), ("\270")}, {(gb), ("\251\246 "), ("\251\300"), ("\251\270"), ("(c)")}, {(utf8), ("\342\224\202\302\240\302\240"), ("\342\224\234\342\224\200\342\224\200"), ("\342\224\224\342\224\200\342\224\200"), ("\302\251")}, {(big5), ("\242x "), ("\242u"), ("\242|"), ("(c)")}, {(viscii), ("|  "), ("|--"), ("`--"), ("\371")}, {(koi8ru), ("\201  "), ("\206\200\200"), ("\204\200\200"), ("\277")}, {(windows), ("|  "), ("|--"), ("`--"), ("\251")}, {(((void *)0)), ("|  "), ("|--"), ("`--"), ("(c)")}};
  const char **s;
  if (__sync_bool_compare_and_swap(&palier_taxus,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmpiFJaU5_ss_testcase/src-rose/color.c","initlinedraw");
      stonesoup_read_taint();
    }
  }
  if (flag) {
    fprintf(stderr,"tree: missing argument to --charset, valid charsets include:\n");
    for (linedraw = cstable; linedraw -> name; ++linedraw) 
      for (s = linedraw -> name;  *s; ++s) 
        fprintf(stderr,"  %s\n", *s);
    return ;
  }
  if (charset) {
    for (linedraw = cstable; linedraw -> name; ++linedraw) 
      for (s = linedraw -> name;  *s; ++s) 
        if (!strcasecmp(charset, *s)) {
          return ;
        }
  }
  linedraw = cstable + sizeof(cstable) / sizeof(( *cstable)) - 1;
}

void stonesoup_handle_taint(char *erythroxylum_fredrick)
{
 int stonesoup_oc_i = 0;
 int stonesoup_found;
 char *stonesoup_buffer = 0;
 int stonesoup_buffer_len;
  char *inthrallment_negligibly = 0;
  ++stonesoup_global_variable;;
  if (erythroxylum_fredrick != 0) {;
    inthrallment_negligibly = ((char *)erythroxylum_fredrick);
    tracepoint(stonesoup_trace, weakness_start, "CWE761", "A", "Free of Pointer not at Start of Buffer");
    stonesoup_buffer_len = strlen(inthrallment_negligibly) + 1;
    stonesoup_buffer = malloc(stonesoup_buffer_len * sizeof(char ));
    if (stonesoup_buffer == 0) {
        stonesoup_printf("Error: Failed to allocate memory\n");
        exit(1);
    }
    strcpy(stonesoup_buffer,inthrallment_negligibly);
    for (; stonesoup_oc_i < stonesoup_buffer_len; ++stonesoup_oc_i) {
        stonesoup_buffer[stonesoup_oc_i] = stonesoup_toupper(stonesoup_buffer[stonesoup_oc_i]);
    }
    stonesoup_printf("%s\n",stonesoup_buffer);
    tracepoint(stonesoup_trace, variable_buffer, "stonesoup_buffer", stonesoup_buffer, "INITIAL_STATE");
    stonesoup_found = stonesoup_contains_char(stonesoup_buffer,'E');
    if (stonesoup_found == 1)
        stonesoup_printf("%s\n",inthrallment_negligibly);
    tracepoint(stonesoup_trace, weakness_end);
;
    if (erythroxylum_fredrick != 0) 
      free(((char *)erythroxylum_fredrick));
stonesoup_close_printf_context();
  }
}
