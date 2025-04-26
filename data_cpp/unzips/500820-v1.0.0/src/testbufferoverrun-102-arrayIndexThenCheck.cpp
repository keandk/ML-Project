 volatile int y;
void f(const int a[], unsigned i) {
    if((a[i] < 2) && (42 >= i)) { // Array index 'i' is used before limits check.
    }
}
