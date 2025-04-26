 extern const int PORT_LEARN_DISABLE;
void foo() {
    int learn;
    for (int index = 0; index < 10; index++) {
        if (!(learn & PORT_LEARN_DISABLE)) // Uninitialized variable: learn
            learn = 123;
    }
}
