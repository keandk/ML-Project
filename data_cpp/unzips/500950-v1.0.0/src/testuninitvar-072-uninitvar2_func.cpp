typedef struct { int a; int b; } AB;
void a(AB *ab);
void b() {
    AB *ab;
    a(ab); // Uninitialized variable: ab
}
