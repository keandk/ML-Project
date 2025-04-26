void f(int *p, int n) {
    int *q = 0;
    if(n > 10) q = p;
    *p +=2;
    if(n < 120) *q+=12; // Possible null pointer dereference: q
}
