void foo()
{
    const char *p[2];
    for (int i = 0; i < 8; ++i)
        p[i] = 0; // Array 'p[2]' accessed at index 7, which is out of bounds.
}
