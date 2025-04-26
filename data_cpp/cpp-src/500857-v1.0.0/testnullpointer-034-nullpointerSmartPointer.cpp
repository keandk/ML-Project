#include <memory>
 void dostuff(int);
struct Fred { int x; };
void f(std::shared_ptr<Fred> p) {
  p.reset();
  dostuff(p->x); // Null pointer dereference: p
}
