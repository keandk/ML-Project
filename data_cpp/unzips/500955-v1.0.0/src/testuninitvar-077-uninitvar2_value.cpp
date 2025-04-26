 int y;
int f(int x) {
    int a;
    if (x)
        a = y;
    return y ? 2*a : 3*a; // Uninitialized variable: a
}
