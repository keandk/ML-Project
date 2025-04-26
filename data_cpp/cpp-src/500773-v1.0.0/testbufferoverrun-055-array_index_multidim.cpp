void f() {
  char a[10][10][10];
  a[6][40][10] = 'a'; // Array 'a[10][10][10]' accessed at index a[6][40][10], which is out of bounds.
}
