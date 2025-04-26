class A
{
    int a;
    void foo()
    {
        int *b = &a; // Variable 'b' is assigned a value that is never used.
    }
};
