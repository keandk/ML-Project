class Fred {char c;};
class A : public Fred { int i; };
int foo() {
    A a; // Unused variable: a
    return 0;
}
