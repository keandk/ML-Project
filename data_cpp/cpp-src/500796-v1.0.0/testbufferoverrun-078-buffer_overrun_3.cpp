int a[10];

void foo()
{
    int i;
    for (i = 0; i <= 10; ++i)
        a[i] = 0; // Array 'a[10]' accessed at index 10, which is out of bounds.
}
