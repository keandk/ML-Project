void f(void){
    int k=0, dd, d[1U] = {1};
    for (dd=d[k]; k<10; dd=d[++k]){;} // Array 'd[1]' accessed at index 1, which is out of bounds.
}
