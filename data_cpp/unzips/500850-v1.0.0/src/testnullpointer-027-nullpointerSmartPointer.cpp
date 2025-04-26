#include <memory>
 void dostuff(int);
struct Fred { int x; };
void f(std::shared_ptr<Fred> p) {
  if (p) {}
  dostuff(p->x); // Either the condition 'p' is redundant or there is possible null pointer dereference: p.
}
