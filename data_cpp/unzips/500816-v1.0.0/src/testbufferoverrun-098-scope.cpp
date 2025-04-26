class A {
public:
    struct X { char buf[10]; };
};

void f()
{
    A::X x;
    x.buf[10] = 0; // Array 'x.buf[10]' accessed at index 10, which is out of bounds.
}
