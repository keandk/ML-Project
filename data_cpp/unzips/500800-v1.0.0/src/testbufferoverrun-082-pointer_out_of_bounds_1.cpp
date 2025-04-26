 void dostuff(char *);
void f() {
    char a[10];
    char *p = a + 100; // Undefined behaviour, pointer arithmetic 'a+100' is out of bounds.
}
