#include "use_of_negative_array_index_or_size.h"

// Constructor.
use_of_negative_array_index_or_size::use_of_negative_array_index_or_size(void)
{
}

// Destructor.
use_of_negative_array_index_or_size::~use_of_negative_array_index_or_size(void)
{
}

// Runs all tests.
void use_of_negative_array_index_or_size::runTests(bool mayCrash)
{
#ifdef polyspace

	volatile int random = 0;
	switch (random)
	{
	case 1: v1();
		break;
	case 2: v2();
		break;
	}

#else	
	
	if (mayCrash != 0)
	{
		v1();
		v2();
	}

#endif
}

// v1: negative index.
void use_of_negative_array_index_or_size::v1(void)
{
	int buffer[100];
	int x;

	x = h1();
	buffer[x] = 0; // ERROR: use of negative index to access array (underrun).
}

// h1: Helper function to v1 that returns -1.
int use_of_negative_array_index_or_size::h1(void)
{
	return -1;
}

// v2: negative size.
void use_of_negative_array_index_or_size::v2(void)
{
	int *buffer;
	int size;
	size = h1();

	buffer = new int[size]; // ERROR: use of a negative array size.
	delete buffer;
}
