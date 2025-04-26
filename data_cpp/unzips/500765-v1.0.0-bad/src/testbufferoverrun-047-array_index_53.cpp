double M[3][1];
 
void matrix()
{
    for (int i=0; i < 3; i++)
        for (int j = 0; j < 3; j++)
             M[i][j]=0.0; // Array 'M[3][1]' accessed at index M[*][2], which is out of bounds.
}
