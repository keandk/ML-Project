 extern const int SIZE;
void f() {
    char a[10];
    *a += 10; // Uninitialized variable: a
}
