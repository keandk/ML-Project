 void bar(int);
void foo(char *p)
{
    if (*p == 0) { } // Either the condition '!p' is redundant or there is possible null pointer dereference: p.
    if (!p) { }
}
