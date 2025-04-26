void f(int n) {
    int a[n];
    a[-1] = 0; // Array 'a[2147483648]' accessed at index -1, which is out of bounds.
}
