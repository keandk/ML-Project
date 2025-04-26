struct S {
    S& rIo;
    S(S&);
    void Write();
};
void foo(bool b, struct S &io) {
    S* p;
    if (b)
        p = new S(io);
    p->Write(); // Uninitialized variable: p
}
