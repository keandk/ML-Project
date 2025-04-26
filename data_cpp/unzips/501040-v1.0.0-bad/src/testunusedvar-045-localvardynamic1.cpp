#include <cstdlib>
void foo()
{
    void* ptr = malloc(16); // Variable 'ptr' is allocated memory that is never used.
    free(ptr);
}
