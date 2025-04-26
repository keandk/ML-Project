struct Fred { int i; };
void foo()
{
    Fred* ptr = new Fred(); // Variable 'ptr' is allocated memory that is never used.
    delete ptr;
}
