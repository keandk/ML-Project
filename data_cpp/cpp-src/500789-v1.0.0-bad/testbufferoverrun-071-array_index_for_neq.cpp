void f() {
    int a[5];
    for (int i = 0; i != 10; ++i) {
        a[i] = 0; // Array 'a[5]' accessed at index 9, which is out of bounds.
    }
}
