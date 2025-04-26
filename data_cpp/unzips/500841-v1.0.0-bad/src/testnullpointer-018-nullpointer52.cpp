struct A { int* x; };
int f(int a, int* b) {
    A c;
    c.x = nullptr;
    if(b) c.x = b;
    bool d = !c.x;
    if (!d) c.x = &a;
    return *c.x; // Possible null pointer dereference: c.x
}

