/*
 *
 * Copyright (c) 2005 Carnegie Mellon University.
 * All rights reserved.
 * Permission to use this software and its documentation for any purpose is hereby granted,
 * provided that the above copyright notice appear and that both that copyright notice and
 * this permission notice appear in supporting documentation, and that the name of CMU not
 * be used in advertising or publicity pertaining to distribution of the software without
 * specific, written prior permission.
 *
 * CMU DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE, INCLUDING ALL IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS, IN NO EVENT SHALL CMU BE LIABLE FOR ANY SPECIAL, INDIRECT OR
 * CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS,
 * WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, RISING OUT OF OR IN
 * CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

#include <sys/stat.h>  /* for stat(...) and struct stat */
#include <sys/types.h> 
#include <stdio.h>    
#include <stdlib.h>    /* for exit(...)  */
#include <err.h>       /* for err(...)   */
#include <fcntl.h>     /* for open(...)  */
#include <unistd.h>    /* for close(...) */

#define MAX_FILE_SIZE  1048576   /* max size == 1 MB */

/*
 * Figure 7-4
 */
int main(int argc, char *argv[])  
{
  struct stat statbuf;
  int fd;
  
  if (argc != 2) {
    printf("Usage: prog_name <file_to_test>\n");
    exit(-1);
  } 

  if (stat(argv[1], &statbuf) == -1) {
    err(1, "stat");
  }
  
  if (statbuf.st_size >= MAX_FILE_SIZE) {
    err(2, "file size");
  }
  
  if ((fd=open(argv[1], O_RDONLY)) == -1) {
    err(3, "open - %s",argv[1]);
  }

  printf("Processing %s ...\n",argv[1]);
  
  close(fd);
  
  return 0;
}
