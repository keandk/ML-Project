 int a(); void b(const char *);
void foo()
{
    char buf[8]; // Unused variable: buf
    char *srcdata;
    char vdata[8];
    if (a()) {
        srcdata = buf;
    }
    srcdata = vdata;
    b(srcdata);
}
