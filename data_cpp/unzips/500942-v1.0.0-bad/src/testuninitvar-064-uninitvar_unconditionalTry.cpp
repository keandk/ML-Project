int f() {
    int i;
    {
        return i; // Uninitialized variable: i
    }
}
