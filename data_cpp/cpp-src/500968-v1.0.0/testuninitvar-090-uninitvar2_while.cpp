 int a;
void f() {
    int x;
    while (a) {
        x = x + 1; // Uninitialized variable: x
    }
}
