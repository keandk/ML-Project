#include <cstring>
void f() {
    char s[20];
    strchr(s, ' '); // Uninitialized variable: s
};
