 int foo(int); int func(int);
void f(const int a[], unsigned i) {
    if (foo(a[i] + 3) < func(i) && i <= 42) { // Array index 'i' is used before limits check.
    }
}
