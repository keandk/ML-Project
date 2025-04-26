void foo()
{
    struct ABC { int a, b, c; };
    struct ABC abc = { 1, 2, 3 }; // Variable 'abc' is assigned a value that is never used.
}
