void f( ) {
struct S{
    int var[ 3 ];
} ;
S var[2];
var[0].var[ 0 ] = 0;
var[0].var[ 1 ] = 1;
var[0].var[ 2 ] = 2;
var[0].var[ 4 ] = 4; // Array 'var[0].var[3]' accessed at index 4, which is out of bounds.
}
