#include "integer_sign_lost_because_of_unsigned_cast.h"

// Constructor.
integer_sign_lost_because_of_unsigned_cast::integer_sign_lost_because_of_unsigned_cast(void)
{
}

// Destructor.
integer_sign_lost_because_of_unsigned_cast::~integer_sign_lost_because_of_unsigned_cast(void)
{
}

// Runs all tests.
void integer_sign_lost_because_of_unsigned_cast::runTests(bool mayCrash)
{
	v1();
}

// v1: simple case.
int integer_sign_lost_because_of_unsigned_cast::v1(void)
{
	int s;
	unsigned int u;

	s = -1;
	u = 1;
	if (u > s) // ERROR: implicit cast of s to unsigned int. 
	{
		return 1;
	}
	else
	{
		return 0; // Will return 0 instead of 1.
	}
}
