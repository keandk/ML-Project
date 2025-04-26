struct AB { int a; int b; } ab;
void foo()
{
    int * a = &ab.a; // Variable 'a' is assigned a value that is never used.
}
