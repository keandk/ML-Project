void foo(int a[10]) {
    for (int i=0;i<50;++i) {
        a[i] = 0; // Array 'a[10]' accessed at index 49, which is out of bounds.
    }
}
