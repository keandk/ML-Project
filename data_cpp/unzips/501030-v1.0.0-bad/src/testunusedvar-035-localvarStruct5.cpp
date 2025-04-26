struct A { int i; };
int foo() {
    A a;
    a.i = 0; // Variable 'a.i' is assigned a value that is never used.
    return 0;
}
