struct S { int m[9]; };
int f(S * s) {
    return s->m[sizeof(s->m)]; // Array 's->m[9]' accessed at index 36, which is out of bounds.
}
