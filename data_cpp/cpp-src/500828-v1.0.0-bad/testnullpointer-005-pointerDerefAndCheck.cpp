 void bar(int);
void foo(int *p)
{
    bar(*p); // Either the condition '!p' is redundant or there is possible null pointer dereference: p.
    if (!p)
        ;
}
