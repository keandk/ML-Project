/* This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain. NIST assumes no responsibility whatsoever for its use by
 * other parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic.

 * We would appreciate acknowledgement if the software is used.
 * The SAMATE project website is: http://samate.nist.gov
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_SIZE 8

int main(int argc, char *argv[])
{
	char str1[2][MAX_SIZE];
	
	if (fread(str1[1],sizeof(char),MAX_SIZE,stdin))
	{
		str[1][MAX_SIZE - 1] = '\0';
		printf("\n%s\nlength = %i\n", str1 + MAX_SIZE, strlen(str1 + MAX_SIZE));
	}
	return 0;
}




