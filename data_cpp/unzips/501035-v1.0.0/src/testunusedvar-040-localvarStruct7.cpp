 void dostuff(int*);
struct ARG {
  int a;
  int b;
};

void fun() {
  ARG aatt;
  int *p = &aatt.b;
  aatt.a = 123; // Variable 'aatt.a' is assigned a value that is never used.
  dostuff(p);
}
