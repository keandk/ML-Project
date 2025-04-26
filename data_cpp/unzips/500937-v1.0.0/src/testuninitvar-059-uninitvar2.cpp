 struct ABC {int a;};
void f() {
    struct ABC *abc;
    abc->a = 0; // Uninitialized variable: abc
}
