 int a(); void b(const char *);
void foo()
{
    char buf[8];
    char *srcdata;
    char vdata[8]; // Unused variable: vdata
    if (a()) {
        srcdata = vdata;
    }
    srcdata = buf;
    b(srcdata);
}
