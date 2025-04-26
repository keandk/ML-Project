void f(int *p) {
   if(!p[0]) {} // Either the condition '!a' is redundant or there is possible null pointer dereference: p.
   const int *const a = p;
   if(!a){}
}
