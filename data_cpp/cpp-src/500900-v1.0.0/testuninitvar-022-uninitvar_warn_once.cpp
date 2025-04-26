 int a; int b;
void f() {
  int x;
  a = x; // Uninitialized variable: x
  b = x;
}
