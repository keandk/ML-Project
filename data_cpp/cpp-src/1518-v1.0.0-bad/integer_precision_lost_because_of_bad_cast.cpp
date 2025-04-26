#include "integer_precision_lost_because_of_bad_cast.h"

// Constructor.
integer_precision_lost_because_of_bad_cast::integer_precision_lost_because_of_bad_cast(void)
{
}

// Destructor.
integer_precision_lost_because_of_bad_cast::~integer_precision_lost_because_of_bad_cast(void)
{
}
 
// Runs all tests.
void integer_precision_lost_because_of_bad_cast::runTests(bool mayCrash)
{
	v1();
}

// v1: simple case.
void integer_precision_lost_because_of_bad_cast::v1(void)
{
	int i;
	short s;

	i = 1000000;
	s = i - 50; // ERROR: 999 950 can't be represented on 16 bits.
}
