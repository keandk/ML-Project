 struct ABC { int a; int b; int x; };
void foo(struct ABC *abc)
{
    int a = abc->a; // Either the condition '!abc' is redundant or there is possible null pointer dereference: abc.
    if (!abc)
        ;
}
