struct ABC
{
    char str[1];
};

static void f()
{
    struct ABC x;
    x.str[1] = 0;} // Array 'x.str[1]' accessed at index 1, which is out of bounds.
