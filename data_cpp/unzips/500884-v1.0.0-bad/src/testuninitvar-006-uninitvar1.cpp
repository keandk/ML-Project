#include <cstdlib>
 struct Foo { void* a; };
void f(Foo *p)
{
    int a;
    p->a = malloc(4 * a); // Uninitialized variable: a
}
