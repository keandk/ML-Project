struct tt {
    char name[21];
} ;
void doswitch(struct tt *x)
{
    struct tt *tt=x;
    tt->name[22] = 123; // Array 'tt->name[21]' accessed at index 22, which is out of bounds.
}
