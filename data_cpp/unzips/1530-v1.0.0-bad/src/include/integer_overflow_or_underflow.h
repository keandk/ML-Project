#include "test.h"

// The value exceeds the representation capacity of the type.
class integer_overflow_or_underflow : public test
{
public:
	integer_overflow_or_underflow(void);
	~integer_overflow_or_underflow(void);
	void runTests(bool mayCrash);

	void v1(void);
	void v2(void);
};
