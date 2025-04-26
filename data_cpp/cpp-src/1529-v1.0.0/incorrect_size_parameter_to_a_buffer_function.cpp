#include "incorrect_size_parameter_to_a_buffer_function.h"

// Constructor.
incorrect_size_parameter_to_a_buffer_function::incorrect_size_parameter_to_a_buffer_function(void)
{
}

// Destructor.
incorrect_size_parameter_to_a_buffer_function::~incorrect_size_parameter_to_a_buffer_function(void)
{
}

// Runs all tests.
void incorrect_size_parameter_to_a_buffer_function::runTests(bool mayCrash)
{
	v1();
}

// v1: very simple case.
void incorrect_size_parameter_to_a_buffer_function::v1(void)
{
	char source[200];
	char destination[100];

	strncpy(destination, source, 200); // ERROR: destination is overrun.
}
