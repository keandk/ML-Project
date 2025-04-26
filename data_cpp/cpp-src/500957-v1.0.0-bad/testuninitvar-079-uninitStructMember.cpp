struct AB { int a; int b; };
void f(void) {
    AB ab;
    int a = ab.a; // Uninitialized struct member: ab.a
}
