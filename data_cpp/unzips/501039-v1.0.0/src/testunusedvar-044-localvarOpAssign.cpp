#include <utility>
void f(std::pair<int,int> x) {
  std::pair<int,int> fred;
  fred = x; // Variable 'fred' is assigned a value that is never used.
}
