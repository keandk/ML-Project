 typedef unsigned char UINT8;
void f() {
    UINT8 x[2];
    x[5] = 0; // Array 'x[2]' accessed at index 5, which is out of bounds.
}
