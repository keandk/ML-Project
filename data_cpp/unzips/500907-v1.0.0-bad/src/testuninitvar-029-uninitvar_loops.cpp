 void b(int);
void f()
{
    for (int i = 0; i < 4; ++i) {
        int a;
        b(4*a); // Uninitialized variable: a
    }}
