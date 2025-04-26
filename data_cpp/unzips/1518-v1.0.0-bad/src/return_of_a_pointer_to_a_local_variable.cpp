#include "return_of_a_pointer_to_a_local_variable.h"

// Constructor.
return_of_a_pointer_to_a_local_variable::return_of_a_pointer_to_a_local_variable(void)
{
}

// Destructor.
return_of_a_pointer_to_a_local_variable::~return_of_a_pointer_to_a_local_variable(void)
{
}

// Runs all tests.
void return_of_a_pointer_to_a_local_variable::runTests(bool mayCrash)
{
	struct S3 *p;
    int* i;
	int a;

    i = v1();
	a = *i;
	p = (struct S3*)malloc(sizeof(struct S3));
    v2(p);
	a = *(p->a);
    free(p);
}

// v1: simple case.
int* return_of_a_pointer_to_a_local_variable::v1(void)
{
	int a;
    int* b;

    b = &a;
    return b; // ERROR: return a pointer to a local variable.
}

// v2: indirect way via a data structure.
void return_of_a_pointer_to_a_local_variable::v2(struct S3* in)
{
	int a = 12;

    in->a = &a; // ERROR: the reference to "a" will be invalid.
}
