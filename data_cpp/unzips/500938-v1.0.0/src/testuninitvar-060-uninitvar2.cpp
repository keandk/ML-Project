 struct ABC {int a;};
void f(void) {
    int x;
    if (x) { } // Uninitialized variable: x
}
