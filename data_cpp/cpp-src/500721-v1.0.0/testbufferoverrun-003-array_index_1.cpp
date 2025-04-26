int f()
{
   int x[ 3 ] = { 0, 1, 2 };
   int y;
   y = x[ 4 ]; // Array 'x[3]' accessed at index 4, which is out of bounds.
   return y;
}
