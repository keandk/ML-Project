void a(char c);
void b() {
    char c;
    a(c); // Uninitialized variable: c
}
