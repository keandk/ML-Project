void f()
{
    int a[10];
    for (int i = 0; i < 10; i++)
        a[i+1] = i; // Array 'a[10]' accessed at index 10, which is out of bounds.
}
