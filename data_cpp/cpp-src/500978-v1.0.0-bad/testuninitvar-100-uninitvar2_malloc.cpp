#include <cstdlib>
int f() {
    int *p = (int*)malloc(40);
    return *p; // Memory is allocated but not initialized: p
}
