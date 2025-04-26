#include <cstddef>
int* f9() { int *x = NULL; return x--; } // Overflow in pointer arithmetic, NULL pointer is subtracted.
