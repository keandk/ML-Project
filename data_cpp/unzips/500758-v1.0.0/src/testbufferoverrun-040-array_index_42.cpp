#include <cstdlib>
void f()
{
  float *p; p = (float *)malloc(10 * sizeof(float));
  p[10] = 7; // Array 'p[10]' accessed at index 10, which is out of bounds.
  free(p);
}
