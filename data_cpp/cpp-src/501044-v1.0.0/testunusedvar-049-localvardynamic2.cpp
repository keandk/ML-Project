#include <cstdlib>
struct Fred { int i; };
void foo()
{
    struct Fred* ptr = (Fred*)malloc(sizeof(Fred)); // Variable 'ptr' is allocated memory that is never used.
    free(ptr);
}
