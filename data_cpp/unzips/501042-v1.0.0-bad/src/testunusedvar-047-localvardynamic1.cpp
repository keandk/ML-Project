void foo()
{
    char* ptr = new char; // Variable 'ptr' is allocated memory that is never used.
    delete ptr;
}
