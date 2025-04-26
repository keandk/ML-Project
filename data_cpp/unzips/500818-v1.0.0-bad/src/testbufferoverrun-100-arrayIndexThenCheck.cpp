 volatile int y;
void f(const char s[]) {
    for (int i = 0; s[i] == 'x' && i < y; ++i) { // Array index 'i' is used before limits check.
    }}
