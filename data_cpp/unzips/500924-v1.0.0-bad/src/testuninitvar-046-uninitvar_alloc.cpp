#include <cstdlib>
void f()
{
    char *p = (char*)malloc(64);
    int x = p[0]; // Memory is allocated but not initialized: p
}
