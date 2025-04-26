struct AB { int a; int b; };
void f(void) {
    AB ab1;
    AB ab2 = { ab1.a, 0 }; // Uninitialized struct member: ab1.a
}
