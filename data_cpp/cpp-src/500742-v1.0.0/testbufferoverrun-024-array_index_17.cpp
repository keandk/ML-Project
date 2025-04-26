void f() {
    int a[2];
    for (int i = 0; i < 4; i+=2)
        a[i] = 0; // Array 'a[2]' accessed at index 2, which is out of bounds.
}
