 struct ABC {int a;};
void f() {
    int x;
    if (1 == (3 & x)) { } // Uninitialized variable: x
}
