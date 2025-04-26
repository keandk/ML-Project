 void dostuff(char *);
char *f() {
    char a[10];
    return a + 100; // Undefined behaviour, pointer arithmetic 'a+100' is out of bounds.
}
