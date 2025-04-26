 struct A {int x;};
void foo()
{
    int a[10];
    int x;
    a[0] = 0;
    x = a[0]; // Variable 'x' is assigned a value that is never used.
}
