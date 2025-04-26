 struct Element{Element*Next();};
void f() {
    for (Element *ptr3 = ptr3->Next(); ptr3; ptr3 = ptr3->Next()) {} // Uninitialized variable: ptr3
}
