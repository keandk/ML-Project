void foo(char *s) {
  char * p = 20 + s; // Pointer addition with NULL pointer.
}
void bar() { foo(0); }
