int foo(int x)
{
    if (int y = x % 2) // Variable 'y' is assigned a value that is never used.
        return 2;
    else
        return 1;
}
