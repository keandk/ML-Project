void foo(char *s) {
  char *p = s - 20; // Overflow in pointer arithmetic, NULL pointer is subtracted.
}
void bar() { foo(0); }
