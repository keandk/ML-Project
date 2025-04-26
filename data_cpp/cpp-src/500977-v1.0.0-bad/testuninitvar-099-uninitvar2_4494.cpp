class Fred {
public:
    class Wilma {
    public:
        class Barney {
        public:
            class Betty {
            public:
                void f1(char *p) { *p = 0; }
            };
            Betty betty;
        };
        Barney barney;
    };
    Wilma wilma;
};
Fred fred;
void f(void) {
    char *p;
    fred.wilma.barney.betty.f1(p); // Uninitialized variable: p
}
