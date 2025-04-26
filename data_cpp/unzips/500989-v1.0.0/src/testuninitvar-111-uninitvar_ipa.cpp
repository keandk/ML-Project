struct S {
  int x;
  int y;
};

void f() {
    struct S s1;
    struct S s2;
    int * x = &s1.x;
    s2.x = *x; // Uninitialized variable: *x
}
