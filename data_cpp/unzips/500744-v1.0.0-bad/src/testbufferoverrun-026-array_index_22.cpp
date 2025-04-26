#include <cstddef>
int main() {
  size_t indices[2];
  int b = indices[2]; // Array 'indices[2]' accessed at index 2, which is out of bounds.
}
