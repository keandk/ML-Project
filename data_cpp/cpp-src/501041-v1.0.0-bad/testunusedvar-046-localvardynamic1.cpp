void foo()
{
    char* ptr = new char[16]; // Variable 'ptr' is allocated memory that is never used.
    delete[] ptr;
}
