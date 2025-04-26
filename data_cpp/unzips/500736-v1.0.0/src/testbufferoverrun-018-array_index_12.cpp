class Fred
{
private:
    char str[10];
public:
    char c();
};
char Fred::c()
{
    return str[10]; // Array 'str[10]' accessed at index 10, which is out of bounds.
}
