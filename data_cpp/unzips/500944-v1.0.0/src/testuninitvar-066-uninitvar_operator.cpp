struct Source { Source& operator>>(int& i) { i = 0; return *this; } };
struct Sink { int v; };
Source foo;
void uninit() {
  Sink s;
  int n = 1 >> s.v; // Uninitialized struct member: s.v
};
void notUninit() {
  Sink s1;
  foo >> s1.v;
  Sink s2;
  int n;
  foo >> s2.v >> n;
}
