#include "call_of_free_with_an_invalid_pointer.h"

// Constructor.
call_of_free_with_an_invalid_pointer::call_of_free_with_an_invalid_pointer(void)
{
}

// Destructor.
call_of_free_with_an_invalid_pointer::~call_of_free_with_an_invalid_pointer(void)
{
}

// Runs all tests.
void call_of_free_with_an_invalid_pointer::runTests(bool mayCrash)
{
	if (mayCrash != 0)
	{
		v1();
	}
}

// v1: a bad pointer is passed to free().
void call_of_free_with_an_invalid_pointer::v1(void)
{
	int i;
	int *j;
    struct S *p;

    p = (struct S*)malloc(sizeof(struct S));
    j = &i;
	free(j); // ERROR: should be "free(p)".
	free(p); // To prevent the detection of a leak.
}
