 struct OBJECT { int ySize; };
void foo( OBJECT *obj )
{
    int x;
    x = obj->ySize / 8; // Variable 'x' is assigned a value that is never used.
}
