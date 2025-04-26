#include "integer_overflow_or_underflow.h"

// Constructor.
integer_overflow_or_underflow::integer_overflow_or_underflow(void)
{
}

// Destructor.
integer_overflow_or_underflow::~integer_overflow_or_underflow(void)
{
}

// Runs all tests.
void integer_overflow_or_underflow::runTests(bool mayCrash)
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

	v1();
	v2();

#endif
}

// v1: overflow on a char.
void integer_overflow_or_underflow::v1(void)
{
	char value = 127;

	value++; // ERROR: 127 is the maximum value for a signed char.
}

// v2: underflow on a char.
void integer_overflow_or_underflow::v2(void)
{
	char value = -128;

	value--; // ERROR: -128 is the minimum value for a signed char.
}
