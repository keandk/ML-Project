void f()
{
  char a[2][2][2];
  a[1][1][2] = 'a'; // Array 'a[2][2][2]' accessed at index a[1][1][2], which is out of bounds.
}
