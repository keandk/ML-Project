#include <cstdlib>
void foo() {
    char *p = (char *)malloc(10);
    free(p);
    p = (char *)malloc(10);
    p[10] = 0; // Array 'p[10]' accessed at index 10, which is out of bounds.
}
