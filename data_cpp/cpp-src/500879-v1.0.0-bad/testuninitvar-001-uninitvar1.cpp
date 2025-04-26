 int b; int c;
void foo() {
    int a;
    b = c - a; // Uninitialized variable: a
}
