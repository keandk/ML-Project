#include "reading_of_a_string_of_arbitrary_length_without_limit.h"

// Constructor.
reading_of_a_string_of_arbitrary_length_without_limit::reading_of_a_string_of_arbitrary_length_without_limit(void)
{
}

// Destructor
reading_of_a_string_of_arbitrary_length_without_limit::~reading_of_a_string_of_arbitrary_length_without_limit(void)
{
}

// Runs all tests.
void reading_of_a_string_of_arbitrary_length_without_limit::runTests(bool mayCrash)
{
	char string[20] = "0123456789012345678";

	v1(string);
}

// v1: simple case of overflow.
void reading_of_a_string_of_arbitrary_length_without_limit::v1(char* string)
{
	char buffer[10];

	strcpy(buffer, string); // ERROR: overflow on buffer.
}
