#include "reading_of_a_non_null_terminated_string.h"

// Constructor.
reading_of_a_non_null_terminated_string::reading_of_a_non_null_terminated_string(void)
{
}

// Destructor.
reading_of_a_non_null_terminated_string::~reading_of_a_non_null_terminated_string(void)
{
}

// Runs all tests.
void reading_of_a_non_null_terminated_string::runTests(bool mayCrash)
{
	char s1[20] = "0123456789012345678";
	char s2[20] = "0123456789012345678";

	s1[19] = 1; // The null-termination zero at the end is removed.
	v1(s1);
}

// v1: simple case of overflow.
void reading_of_a_non_null_terminated_string::v1(char* string)
{
	char buffer[40];

	strncpy(buffer, string, 40); // ERROR: the string copied is 01234567890123456780123456789012345678.
}
