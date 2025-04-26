 struct ABC { int a; int b; int x; };
void f(ABC *abc) {
    if (abc->x == 0) { // Either the condition '!abc' is redundant or there is possible null pointer dereference: abc.
        return;
    }
    if (!abc);
}
