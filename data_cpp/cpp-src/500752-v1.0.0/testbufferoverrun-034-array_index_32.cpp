class X
{
    public:
    X()
    {
       m_x[0] = 0;
       m_x[1] = 0; // Array 'm_x[1]' accessed at index 1, which is out of bounds.
    }
    int m_x[1];
};
