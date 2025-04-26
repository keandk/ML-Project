 struct Foo { void* a; };
static void foo()
{
    int *p;
    delete p; // Uninitialized variable: p
}
