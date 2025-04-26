void f()
{
    int a[10];
    for (int i = 0; i < 10; i++)
        a[i-1] = a[i]; // Array 'a[10]' accessed at index -1, which is out of bounds.
}
