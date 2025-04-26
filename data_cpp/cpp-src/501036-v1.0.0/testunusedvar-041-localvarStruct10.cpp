#include <cstring>
struct S { int x; };

void foo(const struct S s2) {
    struct S s;
    s.x = 3; // Variable 's.x' is assigned a value that is never used.
    memcpy (&s, &s2, sizeof (S));
}
