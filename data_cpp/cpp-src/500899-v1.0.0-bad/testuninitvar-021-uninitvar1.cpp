 void bar(int);
void f() {
    int a;
    int b = 1;
    (b += a) = 1; // Uninitialized variable: a
}
