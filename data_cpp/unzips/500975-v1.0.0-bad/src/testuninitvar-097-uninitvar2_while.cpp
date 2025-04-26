 int a;
void f() {
  for (int i;;i++) // Uninitialized variable: i
    a=i;
}
