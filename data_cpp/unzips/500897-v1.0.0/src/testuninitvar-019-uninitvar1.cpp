 void bar(int);
static void foo(int x)
{
    int i;
    if (x)
        i = 0;
    i++; // Uninitialized variable: i
}
