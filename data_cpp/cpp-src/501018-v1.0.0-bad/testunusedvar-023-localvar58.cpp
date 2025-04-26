void f() {
    int x = 0;
    if (x-- > 0) {} // Variable 'x' is assigned a value that is never used.
}
