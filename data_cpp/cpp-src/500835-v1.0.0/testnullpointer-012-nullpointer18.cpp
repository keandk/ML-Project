#include <cstddef>
void f ()
{
  int i=0;
  char *str=NULL;
  while (str[i]) // Null pointer dereference: str
  {
    i++;
  };
}
