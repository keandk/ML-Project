void foo(char *s) {
  if (!s) {}
  s += 20; // Either the condition '!s' is redundant or there is pointer arithmetic with NULL pointer.
}
