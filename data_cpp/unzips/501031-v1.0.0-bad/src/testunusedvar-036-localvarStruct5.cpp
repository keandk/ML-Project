struct A { int i; };
int foo() {
    A a = { 0 }; // Variable 'a' is assigned a value that is never used.
    return 0;
}
