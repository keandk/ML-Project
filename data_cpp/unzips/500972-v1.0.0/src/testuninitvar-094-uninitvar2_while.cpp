 int a;
void f() {
    int x;
    for (int i = 0; i < 10; i += x) { } // Uninitialized variable: x
}
