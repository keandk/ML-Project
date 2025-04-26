 void bar(int);
static void foo()
{
    int i;
    for (int x = 0; x < 10; i++); // Uninitialized variable: i
}
