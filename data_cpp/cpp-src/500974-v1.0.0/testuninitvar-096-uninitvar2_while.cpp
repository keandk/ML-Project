#include <cstdlib>
 void do_something(int);
void f() {
  char *p = (char *)malloc(256);
  while(*p && *p == '_') // Memory is allocated but not initialized: *p
    p++;
}
