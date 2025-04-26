#include <cstdlib>
 int b; int c;
void foo() {
    int *p;
    realloc(p,10); // Uninitialized variable: p
}
