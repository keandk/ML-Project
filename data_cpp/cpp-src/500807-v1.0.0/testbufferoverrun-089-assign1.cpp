char str[3] = {'a', 'b', 'c'};

void foo()
{
    str[3] = 0; // Array 'str[3]' accessed at index 3, which is out of bounds.
}
