#include <memory>
 void dostuff(int);
struct Fred { int x; };
void f(std::shared_ptr<Fred> p) {
  Fred * pp = nullptr;
  p.reset(pp);
  dostuff(p->x); // Null pointer dereference: p
}
