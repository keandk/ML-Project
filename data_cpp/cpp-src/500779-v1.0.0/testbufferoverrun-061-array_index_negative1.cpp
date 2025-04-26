void f()
{
    char data[8][4];
    data[5][-1] = 0; // Array 'data[8][4]' accessed at index data[*][-1], which is out of bounds.
}
