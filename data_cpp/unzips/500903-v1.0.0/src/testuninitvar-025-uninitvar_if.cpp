 struct Foo { void abcd(); };
static void foo(int x)
{
    Foo *p;
    if (x)
        p = new Foo;
    p->abcd(); // Uninitialized variable: p
}
