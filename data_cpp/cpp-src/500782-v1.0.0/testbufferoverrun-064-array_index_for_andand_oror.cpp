 volatile int y;
void f() {
    char data[2];
    int x;
    for (x = 0; x < 10 && y; x++) {
        data[x] = 0; // Array 'data[2]' accessed at index 9, which is out of bounds.
    }
}
