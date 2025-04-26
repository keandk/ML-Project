static void foo(int x) {
    int y = 5 + *(int*)0; // Null pointer dereference: (int*)0
}
