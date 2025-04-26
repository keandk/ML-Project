#include <cstdlib>
void f() {
    int *p = (int*)malloc(40);
    int var = *p; // Memory is allocated but not initialized: p
}
