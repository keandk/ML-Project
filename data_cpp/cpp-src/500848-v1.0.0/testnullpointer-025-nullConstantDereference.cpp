#include <cstddef>
int * f() {
    return NULL;
}
int main() {
  return *f(); // Null pointer dereference: f()
}
