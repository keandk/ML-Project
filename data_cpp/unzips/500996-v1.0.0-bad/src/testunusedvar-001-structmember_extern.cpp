static struct AB
{
    int a; // struct member 'AB::a' is never used.
    int b;
} ab;

void foo()
{
    ab.b = 0;
}
