void f()
{
    int a[3];
    for (int i = 3; 0 <= i; i--)
        a[i] = i; // Array 'a[3]' accessed at index 3, which is out of bounds.
}
