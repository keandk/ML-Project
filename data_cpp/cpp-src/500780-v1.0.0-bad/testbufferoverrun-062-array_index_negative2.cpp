struct TEST { char a[10]; };
void foo() {
    TEST test;
    test.a[-1] = 3; // Array 'test.a[10]' accessed at index -1, which is out of bounds.
}
