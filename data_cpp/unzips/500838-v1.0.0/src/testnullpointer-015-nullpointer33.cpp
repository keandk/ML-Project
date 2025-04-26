void f(int * x) {
    if (x != nullptr)
        *x = 2;
    else
        *x = 3; // Either the condition 'x!=nullptr' is redundant or there is possible null pointer dereference: x.
}

