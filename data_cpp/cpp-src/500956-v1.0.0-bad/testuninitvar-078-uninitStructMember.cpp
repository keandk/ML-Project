struct AB { int a; int b; };
void f(void) {
    struct AB ab;
    int a = ab.a; // Uninitialized struct member: ab.a
}
