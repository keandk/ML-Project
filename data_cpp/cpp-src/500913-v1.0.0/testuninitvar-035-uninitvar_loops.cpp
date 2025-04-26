 struct Entry { Entry *next; }; Entry *buckets[10];
void f(void)
{
    int* p;
    do
    {
        if (true) {;}
        else
        {
            return;
        }
        *p = 7; // Uninitialized variable: p
        p = new int(9);
    } while (*p != 8);
}
