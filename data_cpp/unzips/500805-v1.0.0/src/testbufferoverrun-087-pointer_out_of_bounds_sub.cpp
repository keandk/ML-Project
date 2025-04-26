 void dostuff(char *);
char *f() {
    char x[10];
    return x-1; // Undefined behaviour, pointer arithmetic 'x-1' is out of bounds.
}
