void f()
{
    int a[12];
    for (int i = 0; i < 12; i+=6)
        a[i+6] = i; // Array 'a[12]' accessed at index 12, which is out of bounds.
}
