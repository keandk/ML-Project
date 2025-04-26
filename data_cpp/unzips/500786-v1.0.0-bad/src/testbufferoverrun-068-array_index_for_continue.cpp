 int maybe();
void f() {
    int a[2];
    for (int i = 0; i < 2; ++i) {
        if (maybe()) {
            continue;
        }
        a[i - 1] = 0; // Array 'a[2]' accessed at index -1, which is out of bounds.
    }
}
