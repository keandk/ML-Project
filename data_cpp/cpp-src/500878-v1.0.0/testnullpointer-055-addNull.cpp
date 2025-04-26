#include <cstddef>
int* f10() { int *x = NULL; return x++; } // Pointer addition with NULL pointer.
