void f() {
  char a[1][1][1];
  a[2][2][2] = 'a'; // Array 'a[1][1][1]' accessed at index a[2][2][2], which is out of bounds.
}
