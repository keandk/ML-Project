 int a(); void b(const char *);
void foo(char *vdata)
{
    char buf[8]; // Unused variable: buf
    char *srcdata;
    if (a()) {
        srcdata = buf;
    }
    srcdata = vdata;
    b(srcdata);
}
