void foo(char *s) {
  char * p = s + 20; // Pointer addition with NULL pointer.
}
void bar() { foo(0); }
