class Fred
{
private:
    char str[10];
public:
    Fred();
};
Fred::Fred()
{
    str[10] = 0; // Array 'str[10]' accessed at index 10, which is out of bounds.
}
