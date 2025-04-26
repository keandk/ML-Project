void f()
{
    int a[10];
    for (int i = 0; i < 10; i++)
        a[10+i] = i; // Array 'a[10]' accessed at index 19, which is out of bounds.
}
