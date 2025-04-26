 template<class T> struct Foo { void abcd(); };
static void foo()
{
    Foo<int> *p;
    p->abcd(); // Uninitialized variable: p
}
