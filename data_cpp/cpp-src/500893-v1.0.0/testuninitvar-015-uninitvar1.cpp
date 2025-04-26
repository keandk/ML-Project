 void bar(int);
void f()
{
    int a;
    bar(4 * a); // Uninitialized variable: a
}
