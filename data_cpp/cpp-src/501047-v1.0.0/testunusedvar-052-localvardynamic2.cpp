#include <cstdlib>
class Fred { public: int i; };
void foo()
{
    Fred* ptr = (Fred*)malloc(sizeof(Fred)); // Variable 'ptr' is allocated memory that is never used.
    free(ptr);
}
