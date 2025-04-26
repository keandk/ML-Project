void f() {
    int a[10];
    for (int i = 0; i < 20; ++i) {
        if (i==1) {
        }
        a[i] = 0; // Array 'a[10]' accessed at index 19, which is out of bounds.
    }
}
