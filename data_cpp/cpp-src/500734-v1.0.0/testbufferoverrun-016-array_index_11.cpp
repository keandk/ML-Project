class ABC
{
public:
    ABC();
    char *str[10];
    struct ABC *next();
};

static void f(ABC *abc1)
{
    for ( ABC *abc = abc1; abc; abc = abc->next() )
    {
        abc->str[10] = 0; // Array 'abc->str[10]' accessed at index 10, which is out of bounds.
    }
}
