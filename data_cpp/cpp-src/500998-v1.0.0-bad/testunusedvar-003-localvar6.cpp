void foo() {
    int b[10];
    for (int i=0;i<10;++i)
        b[i] = 0; // Variable 'b[i]' is assigned a value that is never used.
}
