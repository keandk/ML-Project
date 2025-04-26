void f(unsigned int n) {
    int a[n];
    a[-1] = 0; // Array 'a[4294967296]' accessed at index -1, which is out of bounds.
}
