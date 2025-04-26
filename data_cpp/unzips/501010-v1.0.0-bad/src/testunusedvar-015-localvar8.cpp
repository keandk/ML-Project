 int f();
void foo()
{
    int a, b, c;
    a = b = c = f(); // Variable 'a' is assigned a value that is never used.
}
