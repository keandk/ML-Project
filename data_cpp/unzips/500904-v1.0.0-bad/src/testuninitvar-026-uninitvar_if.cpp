 struct Foo { void abcd(); };
static void foo(int x)
{
    int a;
    if (x==1);
    if (x==2);
    x = a; // Uninitialized variable: a
}
