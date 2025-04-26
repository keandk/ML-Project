char * dostuff(char *p);
void f() {
    char a[4], *p=a;
    p = dostuff(p); // Variable 'p' is assigned a value that is never used.
}
