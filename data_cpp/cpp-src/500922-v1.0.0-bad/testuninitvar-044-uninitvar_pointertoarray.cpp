void draw_quad(float z)  {
    int i;
    float (*vertices)[2][4];
    vertices[0][0][0] = z; // Uninitialized variable: vertices
    vertices[0][0][1] = z;
    vertices[1][0][0] = z;
    vertices[1][0][1] = z;
    vertices[2][0][0] = z;
    vertices[2][0][1] = z;
    vertices[3][0][0] = z;
    vertices[3][0][1] = z;
    for (i = 0; i < 4; i++) {
        vertices[i][0][2] = z;
        vertices[i][0][3] = 1.0;
        vertices[i][1][0] = 2.0;
        vertices[i][1][1] = 3.0;
        vertices[i][1][2] = 4.0;
        vertices[i][1][3] = 5.0;
    }
}
