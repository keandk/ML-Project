void f()
{
    int val[50];
    int i, sum=0;
    for (i = 1; i < 100; i++)
        sum += val[i]; // Array 'val[50]' accessed at index 99, which is out of bounds.
}
