#include <cstdlib>
void f() {
    enum E { ZERO };
    E *tab4 = (E *)malloc(20 * sizeof(E));
    tab4[20] = ZERO; // Array 'tab4[20]' accessed at index 20, which is out of bounds.
}
