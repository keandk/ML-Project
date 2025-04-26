#include "call_of_memcpy_with_overlapping_memory_regions.h"

// Constructor.
call_of_memcpy_with_overlapping_memory_regions::call_of_memcpy_with_overlapping_memory_regions(void)
{
}

// Destructor.
call_of_memcpy_with_overlapping_memory_regions::~call_of_memcpy_with_overlapping_memory_regions(void)
{
}

// Runs all tests.
void call_of_memcpy_with_overlapping_memory_regions::runTests(bool mayCrash)
{
	v1();
}

// v1: very simple case.
void call_of_memcpy_with_overlapping_memory_regions::v1(void)
{
	char a[100];

	for (int i = 0; i < 100; i++)
	{
		a[i] = i;
	}

    memcpy(a + 2, a, 10); // ERROR: range 0-9 is copied on 2-11.
}
