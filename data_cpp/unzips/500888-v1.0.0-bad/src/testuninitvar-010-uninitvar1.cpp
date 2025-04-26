 struct Foo { void* a; };
static void foo()
{
    int *p;
    p[0] = 135; // Uninitialized variable: p
}
