void f() {
    struct Struct { unsigned m_Var[1]; } s;
    s.m_Var[1] = 1; // Array 's.m_Var[1]' accessed at index 1, which is out of bounds.
}
