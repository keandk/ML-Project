#include <memory>
 void dostuff(int);
std::shared_ptr<int> f() {
    return std::shared_ptr<int>(nullptr);
}
void g() {
    int a = *f(); // Null pointer dereference: f()
}

