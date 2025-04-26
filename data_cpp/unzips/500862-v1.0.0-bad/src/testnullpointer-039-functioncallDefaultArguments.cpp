#include <iostream>
void f(int *p = 0) {
    std::cout << p ? *p : 0; // Possible null pointer dereference if the default parameter value is used: p
}
