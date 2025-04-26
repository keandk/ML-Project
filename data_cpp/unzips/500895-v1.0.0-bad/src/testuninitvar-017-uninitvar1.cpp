 void bar(int);
static void foo()
{
    int i;
    for (int x = 0; i < 10; x++); // Uninitialized variable: i
}
