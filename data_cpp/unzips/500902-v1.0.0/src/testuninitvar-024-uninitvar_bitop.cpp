 int a; int c;
void foo() {
    int b;
    c = b | a; // Uninitialized variable: b
}
