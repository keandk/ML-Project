void foo(char *s) {
  if (!s) {}
  char * p = 20 + s; // Either the condition '!s' is redundant or there is pointer arithmetic with NULL pointer.
}
