char f(void)
{
    char buf[10];
    for(int i = 0, j= 11; i < j; ++i)
       buf[i] = 0; // Array 'buf[10]' accessed at index 10, which is out of bounds.
    return buf[0];
}
