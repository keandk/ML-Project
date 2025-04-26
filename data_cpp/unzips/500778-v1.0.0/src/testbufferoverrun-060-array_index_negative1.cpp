void f()
{
    char data[8];
    data[-1] = 0; // Array 'data[8]' accessed at index -1, which is out of bounds.
}
