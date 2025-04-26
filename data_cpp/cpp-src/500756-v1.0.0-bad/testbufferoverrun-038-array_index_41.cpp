void a() {
    struct Fred { char data[6]; } fred;
    fred.data[4] = 0;
}

void b() {
    struct Fred { char data[3]; } fred;
    fred.data[4] = 0; // Array 'fred.data[3]' accessed at index 4, which is out of bounds.
}
