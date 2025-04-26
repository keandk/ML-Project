void f()
{
    char data[8];
    for (int i = 10; i > 0; --i) {
        data[i] = 0; // Array 'data[8]' accessed at index 10, which is out of bounds.
    }
}
