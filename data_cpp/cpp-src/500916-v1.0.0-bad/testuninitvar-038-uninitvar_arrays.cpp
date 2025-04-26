void f(int x) {
    char a[10], c;
    c = *(x?a:0); // Uninitialized variable: a
}
