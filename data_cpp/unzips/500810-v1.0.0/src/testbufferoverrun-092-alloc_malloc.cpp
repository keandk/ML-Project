#include <cstdlib>
void f() {
    int *tab4 = (int *)malloc(20 * sizeof(int));
    tab4[20] = 0; // Array 'tab4[20]' accessed at index 20, which is out of bounds.
}
