struct Element {
    int v;
};
void test() {
    Element *element; (*element).v; // Uninitialized variable: element
}
