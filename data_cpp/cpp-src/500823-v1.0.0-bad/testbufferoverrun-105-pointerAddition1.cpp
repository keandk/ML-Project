void f() {
    char arr[10];
    char *p = arr + 20; // Undefined behaviour, pointer arithmetic 'arr+20' is out of bounds.
}
