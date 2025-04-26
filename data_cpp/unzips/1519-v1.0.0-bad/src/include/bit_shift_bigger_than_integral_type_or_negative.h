#include "test.h"

// The bit shift is bigger than the size of the integral type
// or is negative.
class bit_shift_bigger_than_integral_type_or_negative : public test
{
public:
	bit_shift_bigger_than_integral_type_or_negative(void);
	~bit_shift_bigger_than_integral_type_or_negative(void);
	void runTests(bool mayCrash);

	void v1(void);
	void v2(void);
};