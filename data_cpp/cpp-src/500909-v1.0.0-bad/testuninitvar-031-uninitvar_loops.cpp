 void b(int);
void foo(void) {
    int a = 0;
    int x;

    for (;;) {
        if (!a || 12 < x) { // Uninitialized variable: x
            a = 1;
        }
    }
}
