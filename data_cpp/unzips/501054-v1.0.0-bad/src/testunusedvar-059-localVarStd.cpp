#include <string>
#include <vector>
 struct MyClass {int x;}; std::string foo();
void f() {
    std::vector<MyClass> x; // Unused variable: x
}
