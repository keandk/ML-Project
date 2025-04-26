#include <cstdlib>
void f() {
    enum E { Size = 20 };
    E *tab4 = (E *)malloc(Size * 4);
    tab4[Size] = Size; // Array 'tab4[20]' accessed at index 20, which is out of bounds.
}
