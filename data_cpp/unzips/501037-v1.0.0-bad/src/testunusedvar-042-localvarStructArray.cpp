 struct X {int a;};
void f() {
    struct X x[10];
    x[0].a = 5; // Variable 'x[0].a' is assigned a value that is never used.
}
