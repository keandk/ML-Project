void foo(char *s) {
  if (!s) {}
  char *p = s - 20; // Either the condition '!s' is redundant or there is overflow in pointer subtraction.
}
