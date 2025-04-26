struct Fred { char c[10]; };
char f()
{
    Fred *f; f = new Fred;
    return f->c[10]; // Array 'f->c[10]' accessed at index 10, which is out of bounds.
}
