 volatile int y;
void f(const int a[], unsigned i) {
    if((a[i] < 2) && (i <= 42)) { // Array index 'i' is used before limits check.
    }
}
