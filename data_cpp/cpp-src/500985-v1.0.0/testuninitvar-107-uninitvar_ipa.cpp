typedef struct  {
    int flags;
} someType_t;
void bar(const someType_t * const p)  {
    if( (p->flags & 0xF000) == 0xF000){} // Uninitialized variable: flags
}
void f(void) {
    someType_t gVar;
    bar(&gVar);
}
