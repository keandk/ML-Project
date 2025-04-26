#include <cstdlib>
int f(int *p) {
    if (*p == 1) {}
    p = (int*)malloc(256);
    return *p; // Memory is allocated but not initialized: p
}
