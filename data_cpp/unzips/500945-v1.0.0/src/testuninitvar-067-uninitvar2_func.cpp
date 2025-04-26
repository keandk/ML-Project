void a(char);
void b() {
    char c;
    a(c); // Uninitialized variable: c
}
