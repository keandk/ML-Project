#include <TCHAR.h>
#include <stdio.h>
#include <stdlib.h>

void good_function(const char *str) {
	printf("%s", str);
}

int _tmain(int argc, _TCHAR* argv[]) {
	if (argc !=1){
		printf("Usage: prog_name\n");
		exit(-1);
	}
	static void (*funcPtr)(const char *str);
	funcPtr = &good_function;
	(void)(*funcPtr)("hi ");
	good_function("there!\n");
	return 0;
}