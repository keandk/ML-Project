 struct my_type { int x; };
void foo()
{
  struct my_type* p = 0;
  p->x = 0; // Null pointer dereference: p
}
