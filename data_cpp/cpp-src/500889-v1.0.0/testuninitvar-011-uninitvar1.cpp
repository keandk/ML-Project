 struct Foo { void* a; };
static void foo()
{
    int *x;
    int y = *x; // Uninitialized variable: x
}
