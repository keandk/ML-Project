#include <string>
#include <vector>
 struct MyClass {int x;}; std::string foo();
void f() {
    std::vector<int> x(100); // Variable 'x' is assigned a value that is never used.
}
