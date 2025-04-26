 int b; int c;
void foo() {
    int a;
    b = a - c; // Uninitialized variable: a
}
