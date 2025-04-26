void dostuff(int x);
void fun(int y, int c) {
  int x = 1;
  while (c) {
    dostuff(x);
    if (y) { x=10; break; } // Variable 'x' is assigned a value that is never used.
  }
}
