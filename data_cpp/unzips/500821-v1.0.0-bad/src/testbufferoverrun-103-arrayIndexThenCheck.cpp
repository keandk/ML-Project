 int foo(int); int func(int);
void f(const int a[], unsigned i) {
    if(a[i] < func(i) && i <= 42) { // Array index 'i' is used before limits check.
    }
}
