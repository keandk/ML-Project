#include <cstdlib>
void f()
{
  char *p; p = (char *)malloc(10);
  p[10] = 7; // Array 'p[10]' accessed at index 10, which is out of bounds.
  free(p);
}
