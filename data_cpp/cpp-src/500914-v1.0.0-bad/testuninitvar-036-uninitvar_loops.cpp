 struct Entry { Entry *next; }; Entry *buckets[10];
void f(void)
{
    int* p;
    while (*p != 8) { // Uninitialized variable: p
        *p = 7;
        p = new int(9);
    }
}
