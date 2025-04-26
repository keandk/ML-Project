#include <cstdlib>
void foo()
{
    char *s; s = (char *)malloc(10);
    s[10] = 0; // Array 's[10]' accessed at index 10, which is out of bounds.
}
