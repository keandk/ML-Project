 int maybe(); int dostuff();
void f() {
    int a[10];
    for (int i = 10; maybe(); dostuff()) {
        a[i] = 0; // Array 'a[10]' accessed at index 10, which is out of bounds.
    }
}
