 struct Foo { void* a; };
void f()
{
    int a;
    a = 5 + a; // Uninitialized variable: a
}
