#include "division_by_zero.h"

// Constructor.
division_by_zero::division_by_zero(void)
{
}

// Destructor.
division_by_zero::~division_by_zero(void)
{
}

// Runs all tests.
void division_by_zero::runTests(bool mayCrash)
{
	if (mayCrash != 0)
	{
		v1();
	}
}

// v1: very simple case of division by zero.
void division_by_zero::v1(void)
{
	int a = 3;
    int b = 0;
    int c = a/b;
}
