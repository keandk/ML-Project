 struct my_type { int x; };
int foo()
{
  struct my_type* p;
  p = 0;
  return p->x; // Null pointer dereference: p
}
