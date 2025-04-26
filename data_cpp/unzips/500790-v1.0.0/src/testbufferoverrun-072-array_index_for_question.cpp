void f() {
    int a[10];
    for (int i = 0; i != 10; ++i) {
        i==0 ? 0 : a[i-1];
        a[i-1] = 0; // Array 'a[10]' accessed at index -1, which is out of bounds.
    }
}
