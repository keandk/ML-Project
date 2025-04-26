union { char a[1]; int b; } ab;
void f() {
    ab.a[2] = 0; // Array 'ab.a[1]' accessed at index 2, which is out of bounds.
}
