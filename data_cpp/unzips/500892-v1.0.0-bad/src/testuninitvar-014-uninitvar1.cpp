 struct Foo { void* a; };
void f()
{
    int a;
    a++; // Uninitialized variable: a
}
