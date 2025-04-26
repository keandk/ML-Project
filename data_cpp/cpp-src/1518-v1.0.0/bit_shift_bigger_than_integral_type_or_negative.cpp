#include "bit_shift_bigger_than_integral_type_or_negative.h"

// Constructor.
bit_shift_bigger_than_integral_type_or_negative::bit_shift_bigger_than_integral_type_or_negative(void)
{
}

// Constructor.
bit_shift_bigger_than_integral_type_or_negative::~bit_shift_bigger_than_integral_type_or_negative(void)
{
}

// Runs all tests.
void bit_shift_bigger_than_integral_type_or_negative::runTests(bool mayCrash)
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

	v1();
	v2();

#endif
}

// v1: shift too big.
void bit_shift_bigger_than_integral_type_or_negative::v1(void)
{
	int i = 1;
	int j,k;
	int size = 34;

	j = i << size; // ERROR: bit shift bigger than integral type.
	k = i >> size; // ERROR: bit shift bigger than integral type.
}

// v2: negative shift.
void bit_shift_bigger_than_integral_type_or_negative::v2(void)
{
	int i = 1;
	int j,k;
	int size = -2;

	j = i << size; // ERROR: bit shift of negative value.
	k = i >> size; // ERROR: bit shift of negative value.
}
