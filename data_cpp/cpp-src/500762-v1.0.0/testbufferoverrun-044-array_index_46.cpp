void f() {
    int buffer[9];
    long int i;
    for(i=10; i--;) {
        buffer[i] = i; // Array 'buffer[9]' accessed at index 9, which is out of bounds.
    }
}
