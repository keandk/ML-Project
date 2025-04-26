 int maybe(); int *g();
int foo() {
    int* iVal = 0;
    if(maybe()) iVal = g();
    return iVal[0]; // Possible null pointer dereference: iVal
}
