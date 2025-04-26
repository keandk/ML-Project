#include <cstdlib>
void f() {
    int *x, i;
    x = (int *)malloc(10 * sizeof(int));
    x[10] = 0; // Array 'x[10]' accessed at index 10, which is out of bounds.
}
