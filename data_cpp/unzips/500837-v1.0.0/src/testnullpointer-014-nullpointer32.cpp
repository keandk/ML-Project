int f(int * ptr) {
  if(ptr)
  { return 0;}
  else{
    int *p1 = ptr;
    return *p1; // Either the condition 'ptr' is redundant or there is possible null pointer dereference: p1.
  }
}

