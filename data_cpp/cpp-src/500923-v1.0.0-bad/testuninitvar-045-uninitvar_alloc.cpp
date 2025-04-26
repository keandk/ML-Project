#include <cstring>
void f()
{
    char *s1 = new char[10];
    char *s2 = new char[strlen(s1)]; // Memory is allocated but not initialized: s1
};
