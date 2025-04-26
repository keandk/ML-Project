void foo(char *s) {
  s += 20; // Pointer addition with NULL pointer.
}
void bar() { foo(0); }
