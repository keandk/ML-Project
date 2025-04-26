#include <string>
 struct MyClass {int x;}; std::string foo();
void f() {
    std::string x = foo(); // Variable 'x' is assigned a value that is never used.
}
