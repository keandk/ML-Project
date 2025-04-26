void foo(int a, int loop) {
  bool x = false; // Variable 'x' is assigned a value that is never used.
  while (loop) {
    if (a) {
      x = true;
      continue;
    }
  }
}
