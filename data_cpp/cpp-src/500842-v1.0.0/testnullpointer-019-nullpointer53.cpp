void f(int nParams, int* params) {
  for (int n=1; n<nParams+10; ++n) {
    params[n]=42; // Possible null pointer dereference: params
  }
}
void bar() {
  f(0, 0);
}
