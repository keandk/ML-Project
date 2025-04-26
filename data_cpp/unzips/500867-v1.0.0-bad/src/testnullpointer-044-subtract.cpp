void foo(char *s) {
  s -= 20; // Overflow in pointer arithmetic, NULL pointer is subtracted.
}
void bar() { foo(0); }
