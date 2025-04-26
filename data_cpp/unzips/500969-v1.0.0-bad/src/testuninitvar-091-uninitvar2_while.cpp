 int a;
void f() {
    int x;
    do {
        x = x + 1; // Uninitialized variable: x
    } while (a);
}
