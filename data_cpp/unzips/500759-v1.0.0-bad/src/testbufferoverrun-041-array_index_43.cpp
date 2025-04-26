int f( )
{
  struct {
    int arr[ 3 ];
  } var[ 1 ];
   int y;
   var[ 0 ].arr[ 0 ] = 0;
   var[ 0 ].arr[ 1 ] = 1;
   var[ 0 ].arr[ 2 ] = 2;
   y = var[ 0 ].arr[ 3 ]; // Array 'var[0].arr[3]' accessed at index 3, which is out of bounds.
   return y;
}
