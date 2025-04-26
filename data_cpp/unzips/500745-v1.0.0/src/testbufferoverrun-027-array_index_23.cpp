void foo()
{
    char c[10];
    c[1<<23]='a'; // Array 'c[10]' accessed at index 8388608, which is out of bounds.
}
