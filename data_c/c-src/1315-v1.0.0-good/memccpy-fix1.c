#include <stdio.h>
#include <string.h>

#define MAXSIZE 40

void test(char *str){
	char buf[MAXSIZE];
	for(int i=0; i<MAXSIZE; i++){
		buf[i] = 0;	
	}
	memccpy(buf, str, 0, MAXSIZE-1);
	printf("results: %s\n", buf);
}

int main(int argc, char **argv){
	char *userstr;
	if(argc > 1){
		userstr = argv[1];
		test(userstr);
	}
	return 0;
}
