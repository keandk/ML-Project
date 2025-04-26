struct Element {
    void f() { }
};
void test() {
    Element *element; (*element).f(); // Uninitialized variable: element
}
