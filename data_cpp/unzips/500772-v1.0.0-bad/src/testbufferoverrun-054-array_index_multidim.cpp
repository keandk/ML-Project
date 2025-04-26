void f()
{
  char a[10][10][10];
  a[2*3][4*3][2] = 'a'; // Array 'a[10][10][10]' accessed at index a[6][12][2], which is out of bounds.
}
