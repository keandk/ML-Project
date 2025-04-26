struct foo
{
    char str[10];
};

void x()
{
    foo f;
    for ( unsigned int i = 0; i < 64; ++i )
        f.str[i] = 0; // Array 'f.str[10]' accessed at index 63, which is out of bounds.
}
