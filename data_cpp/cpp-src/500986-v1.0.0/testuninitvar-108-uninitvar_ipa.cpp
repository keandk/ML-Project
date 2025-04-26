typedef struct
{
        int flags[3];
} someType_t;
void f(void) {
        someType_t gVar;
        if(gVar.flags[1] == 42){} // Uninitialized variable: flags
}
