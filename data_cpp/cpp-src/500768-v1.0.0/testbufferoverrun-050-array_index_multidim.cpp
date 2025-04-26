void f()
{
  char a[2][2][2];
  a[2][1][1] = 'a'; // Array 'a[2][2][2]' accessed at index a[2][1][1], which is out of bounds.
}
