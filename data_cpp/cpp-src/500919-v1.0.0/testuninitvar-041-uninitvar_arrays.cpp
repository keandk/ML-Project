void f(int i);
void foo()
{
    int a[10];
    f(a[0]); // Uninitialized variable: a
}
