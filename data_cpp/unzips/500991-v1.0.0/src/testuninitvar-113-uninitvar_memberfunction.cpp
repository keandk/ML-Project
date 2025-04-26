struct C {
    int x();
};
void f() {
    C *c;
    if (c->x() == 4) {} // Uninitialized variable: c
}
