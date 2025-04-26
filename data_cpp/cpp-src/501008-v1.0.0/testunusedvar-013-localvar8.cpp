 struct A {int x;};
void foo()
{
    int i = 0;
    int &j = i; // Variable 'j' is assigned a value that is never used.
}
