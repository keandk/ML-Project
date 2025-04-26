void f()
{
    enum AB { a, b };
    AB ab;
    if (ab); // Uninitialized variable: ab
}
