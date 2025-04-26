struct AB { int a; int b; };
void f(void) {
    struct AB ab;
    ab.a = ab.a + 1; // Uninitialized struct member: ab.a
}
