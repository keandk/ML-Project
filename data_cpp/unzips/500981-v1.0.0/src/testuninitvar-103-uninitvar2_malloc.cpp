#include <cstdlib>
struct AB { int a; int b; };
int f(struct AB *ab) {
    if (ab->a == 1) {}
    ab = (AB*)malloc(sizeof(struct AB));
    return ab->a; // Uninitialized struct member: ab.a
}
