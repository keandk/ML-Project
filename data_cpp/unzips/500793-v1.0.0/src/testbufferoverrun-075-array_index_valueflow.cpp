void f(int i) {
    char str[3];
    str[i] = 0; // Either the switch case 'case 10' is redundant or the array 'str[3]' is accessed at index 10, which is out of bounds.
    switch (i) {
    case 10: break;
    }
}
