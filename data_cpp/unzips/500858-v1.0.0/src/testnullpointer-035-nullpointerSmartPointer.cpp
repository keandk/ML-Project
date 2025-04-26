#include <memory>
 void dostuff(int);
struct Fred { int x; };
void f() {
  std::shared_ptr<Fred> p(nullptr);
  dostuff(p->x); // Null pointer dereference: p
}
