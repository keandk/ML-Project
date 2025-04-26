struct ABC
{
    char str[10];
};

static char f()
{
    struct ABC abc;
    return abc.str[10]; // Array 'abc.str[10]' accessed at index 10, which is out of bounds.
}
