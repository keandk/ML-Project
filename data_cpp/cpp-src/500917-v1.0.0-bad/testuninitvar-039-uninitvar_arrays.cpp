#include <cstring>
void f(char *s2) {
    char s[20];
    strcpy(s2, s); // Uninitialized variable: s
};
