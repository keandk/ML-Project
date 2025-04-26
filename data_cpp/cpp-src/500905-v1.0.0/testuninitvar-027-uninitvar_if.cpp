 struct Foo { void abcd(); };
static void foo(int v) {
    int x;
    x = v <= 0 ? -1 : x; // Uninitialized variable: x
}
