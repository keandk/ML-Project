#include "incorrect_cpp_array_deletion.h"

// Constructor.
incorrect_cpp_array_deletion::incorrect_cpp_array_deletion(void)
{
}

// Destructor.
incorrect_cpp_array_deletion::~incorrect_cpp_array_deletion(void)
{
}

// Runs all tests.
void incorrect_cpp_array_deletion::runTests(bool mayCrash)
{
	if (mayCrash != 0)
	{
		v1();
	}
}

// v1: simple case with a small helper class.
void incorrect_cpp_array_deletion::v1(void)
{
	helper *buffer = new helper[10];
	delete buffer; // ERROR: should be delete[] buffer, destructors won't be called.
}


// Helper's constructor allocates memory.
helper::helper(void)
{
	p = (struct S*)malloc(sizeof(struct S));
}

// Helper's destructor frees memory.
helper::~helper(void)
{
	free(p);
}
