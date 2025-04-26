 void dostuff(char *);
void f(int i) {
    char x[10];
    if (i == -1) {}
    dostuff(x+i); // Undefined behaviour, when 'i' is -1 the pointer arithmetic 'x+i' is out of bounds.
}
