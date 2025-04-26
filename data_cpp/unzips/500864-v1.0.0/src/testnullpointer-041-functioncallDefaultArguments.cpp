void foo(int x, int *p = 0) {
    int var1 = x ? *p : 5; // Possible null pointer dereference if the default parameter value is used: p
}
