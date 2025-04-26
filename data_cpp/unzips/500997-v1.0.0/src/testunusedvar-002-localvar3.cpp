void foo(int abc)
{
    int i;
    if ( abc )
        ;
    else i = 0; // Variable 'i' is assigned a value that is never used.
}
