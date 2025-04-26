static void foo() {
    int &r = *(int*)0; // Null pointer dereference: (int*)0
}
