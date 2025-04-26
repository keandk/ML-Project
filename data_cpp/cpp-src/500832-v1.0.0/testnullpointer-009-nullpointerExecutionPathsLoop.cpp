#include <cstdlib>
 int y[20];
void f() {
    char *p = 0;

    for (int x = 0; x < 3; ++x) {
        if (y[x] == 0) {
            p = (char *)malloc(10);
            break;
        }
    }

    *p = 0; // Possible null pointer dereference: p
}
