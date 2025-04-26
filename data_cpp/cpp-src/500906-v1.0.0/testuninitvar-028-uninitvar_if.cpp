 struct Foo { void abcd(); };
void foo(int a, int b)
{
    int x; x = (a<b) ? 1 : 0;
    int y = y; // Uninitialized variable: y
}
