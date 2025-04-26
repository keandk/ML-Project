int f() {
    int i;
    try {
        return i; // Uninitialized variable: i
    } catch(...) {}
}
