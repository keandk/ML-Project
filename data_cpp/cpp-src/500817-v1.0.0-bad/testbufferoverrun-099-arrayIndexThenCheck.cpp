 volatile int y;
void f(const char s[], int i) {
    if (s[i] == 'x' && i < y) { // Array index 'i' is used before limits check.
    }}
