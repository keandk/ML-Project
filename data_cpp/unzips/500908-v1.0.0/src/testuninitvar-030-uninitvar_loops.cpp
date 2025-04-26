 void b(int);
void f() {
    int k;
    for (int i = 0; i < 4; ++i) {
        k = k + 2; // Uninitialized variable: k
    }
}
