#include "unspecified_format_string.h"

// Constructor.
unspecified_format_string::unspecified_format_string(void)
{
}

// Destructor.
unspecified_format_string::~unspecified_format_string(void)
{
}

// Runs all tests.
void unspecified_format_string::runTests(bool mayCrash)
{
	v1("hello");
}

// v1: simple case of unspecified format string.
void unspecified_format_string::v1(char *string)
{
	printf(string); // ERROR: the format string is not specified.
}
