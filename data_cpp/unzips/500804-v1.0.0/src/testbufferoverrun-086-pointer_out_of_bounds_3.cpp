struct S { int a[10]; };
void f(struct S *s) {
    int *p = s->a + 100; // Undefined behaviour, pointer arithmetic 's->a+100' is out of bounds.
}
