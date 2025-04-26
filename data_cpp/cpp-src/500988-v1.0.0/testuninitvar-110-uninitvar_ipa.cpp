struct S {
  int x;
  int y;
};

void f() {
    struct S s1;
    int * x = &s1.x;
    struct S s2 = {*x, 0}; // Uninitialized variable: *x
}
