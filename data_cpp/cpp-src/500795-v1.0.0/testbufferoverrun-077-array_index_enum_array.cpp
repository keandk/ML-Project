enum E : unsigned int { e1, e2 };
void f() {
    E arrE[] = { e1, e2 };
    arrE[sizeof(arrE)] = e1; // Array 'arrE[2]' accessed at index 8, which is out of bounds.
}
