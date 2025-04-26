void f()
{
    char data[8];
    for (int i = 19; i < 36; ++i) {
        data[i/2] = 0; // Array 'data[8]' accessed at index 17, which is out of bounds.
    }
}
