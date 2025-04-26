 void do_something(int);
void f(void) {
   int x;
   for (;;) {
       int a = x+1; // Uninitialized variable: x
       do_something(a);
   }
}
