 int a; int c;
void foo() {
    int b;
    c = a | b; // Uninitialized variable: b
}
