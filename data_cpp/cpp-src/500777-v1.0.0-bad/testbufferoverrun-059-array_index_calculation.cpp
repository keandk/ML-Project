void f()
{
    char arr[5];
    for (int i = 0; i < 5; ++i) {
        arr[i + 7] = 0; // Array 'arr[5]' accessed at index 11, which is out of bounds.
    }
}
