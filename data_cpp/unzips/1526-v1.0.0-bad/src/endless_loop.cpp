#include "endless_loop.h"

// Constructor.
endless_loop::endless_loop(void)
{
}

// Destructor.
endless_loop::~endless_loop(void)
{
}

// Runs all tests.
void endless_loop::runTests(bool mayCrash)
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
	}

#endif
}

// v1: simple case of endless loop.
void endless_loop::v1(void)
{
	unsigned int i = 0;
	while (i >= 0)
	{
		i++;
	}
}

// v2: esoteric case of endless loop.
// This is not a "true" endless loop, because "i" will overflow and be equal
// to zero in some point in time.
void endless_loop::v2(void)
{
	unsigned int i = 1;
	while (i > 0)
	{
		i++;
	}
}
