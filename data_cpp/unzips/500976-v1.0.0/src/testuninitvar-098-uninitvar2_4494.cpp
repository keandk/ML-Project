class Fred {
public:
    void f1(char *p) { *p = 0; }
};
Fred fred;
void f(void) {
    char *p;
    fred.f1(p); // Uninitialized variable: p
}
