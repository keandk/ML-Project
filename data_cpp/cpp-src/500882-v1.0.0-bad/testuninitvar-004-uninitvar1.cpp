 struct Foo { void abcd(); };
static void foo()
{
    Foo *p;
    p->abcd(); // Uninitialized variable: p
}
