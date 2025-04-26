#include "underallocated_memory_for_a_given_type.h"

// Constructor.
underallocated_memory_for_a_given_type::underallocated_memory_for_a_given_type(void)
{
}

// Destructor.
underallocated_memory_for_a_given_type::~underallocated_memory_for_a_given_type(void)
{
}

// Runs all tests.
void underallocated_memory_for_a_given_type::runTests(bool mayCrash)
{
	if (mayCrash != 0)
	{
		v1();
	}
}

// v1: reading and writing to underallocated memory block.
void underallocated_memory_for_a_given_type::v1(void)
{
	int i;
	struct S *p;

    p = (struct S*)malloc(sizeof(p)); // ERROR: allocation too small, sizeof(*p) was intended.
    p->a = 0;
	p->b = 0; // Will overwrite memory.
	i = p->b; // Will read from out of bounds memory.
	free(p);
}
