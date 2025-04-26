int foo() {
    int a[2][2];
    return a[0][1]; // Uninitialized variable: a
}
