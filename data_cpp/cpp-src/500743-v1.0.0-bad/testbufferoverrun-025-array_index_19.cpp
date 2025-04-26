void f()
{
  char a[2];
  char *end = &(a[3]); // Array 'a[2]' accessed at index 3, which is out of bounds.
}
