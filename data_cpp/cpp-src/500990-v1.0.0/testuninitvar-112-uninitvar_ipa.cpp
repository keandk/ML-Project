#include <cstddef>
static void g(bool * result, int num, int num2, size_t * buflen) {
  if (*result && *buflen >= 5) {} // Uninitialized variable: *buflen
}
void f() {
  size_t bytesCopied;
  bool copied_all = true;
  g(&copied_all, 5, 6, &bytesCopied);
}
