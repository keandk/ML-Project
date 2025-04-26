 extern const int SIZE;
void f() {
    char a[SIZE+10];
    char c = *a; // Uninitialized variable: a
}
