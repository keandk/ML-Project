void foo(int x)
{
    int a = 0;
    if (x == 1)
        a = 123;
    else if (x == 2)
        a = 456;
    else
        return;
    x = a; // Variable 'x' is assigned a value that is never used.
}
