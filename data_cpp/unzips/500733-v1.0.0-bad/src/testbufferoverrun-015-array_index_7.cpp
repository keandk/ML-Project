struct ABC
{
    char str[10];
};

static void f(struct ABC *abc)
{
    abc->str[10] = 0; // Array 'abc->str[10]' accessed at index 10, which is out of bounds.
}
