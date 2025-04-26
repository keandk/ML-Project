#include "incorrect_pointer_arithmetic.h"

// Constructor.
incorrect_pointer_arithmetic::incorrect_pointer_arithmetic(void)
{
}

// Destructor.
incorrect_pointer_arithmetic::~incorrect_pointer_arithmetic(void)
{
}

// Runs all tests.
void incorrect_pointer_arithmetic::runTests(bool mayCrash)
{
	v1();
}

// v1: simple case.
void incorrect_pointer_arithmetic::v1(void)
{
	struct S2 s;
    int* p;

    p = &(s.a);
    *p =  1;    // a
    *(p+1) = 2; // b
    *(p+2) = 3; // c
    *(p+3) = 4; // ERROR: overrun of structure S2.
}
