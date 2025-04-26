#include "overrun_or_underrun_of_an_array.h"

// Constructor.
overrun_or_underrun_of_an_array::overrun_or_underrun_of_an_array(void)
{
}

// Destructor.
overrun_or_underrun_of_an_array::~overrun_or_underrun_of_an_array(void)
{
}

// Runs all tests.
void overrun_or_underrun_of_an_array::runTests(bool mayCrash)
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
	
	if (mayCrash == 0)
	{
		v2();
	}
	else
	{
		v1();
		v2();
	}

#endif
}

// v1: overrun of a dynamically allocated array.
void overrun_or_underrun_of_an_array::v1()
{
	// allocates 40 bytes (10 * 4 bytes ints)
    int *buffer = (int *) malloc(10 * sizeof(int));
    // valid indices are buffer[0] to buffer[9]
    int i;
    
    for(i = 0; i <= 10; i++)
    {
        buffer[i] = i; // ERROR: overruns memory by writing buffer[10]
    }
	free(buffer);
}

// v2: overrun of a statically allocated array.
void overrun_or_underrun_of_an_array::v2()
{
	int buf[10];        // buff has 10 elements, index 0 to 9
    int *x = &buf[1];   // x now points to buff[1]
    x[9] = 0;           // ERROR: x[9] is equivalent to buff[10], which is out of bounds
}
