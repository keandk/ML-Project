#include <cstdlib>
char f() {
    char *p = (char*)malloc(64);
    return p[0]; // Memory is allocated but not initialized: p
}
