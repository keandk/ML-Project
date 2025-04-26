void f(int x) {
    int i;
    if (!x) { }
    else i = 0;
    if (x || i>0) {} // Uninitialized variable: i
}
