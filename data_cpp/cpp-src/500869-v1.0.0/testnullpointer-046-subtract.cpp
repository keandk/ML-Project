#include <cstddef>
int* f8() { int *x = NULL; return --x; } // Overflow in pointer arithmetic, NULL pointer is subtracted.
