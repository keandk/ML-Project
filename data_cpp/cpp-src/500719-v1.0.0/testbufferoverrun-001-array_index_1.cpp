void f()
{
    char str[0x10] = {0};
    str[15] = 0;
    str[16] = 0; // Array 'str[16]' accessed at index 16, which is out of bounds.
}
