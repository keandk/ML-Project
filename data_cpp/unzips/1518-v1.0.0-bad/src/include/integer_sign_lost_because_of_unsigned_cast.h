#include "test.h"

// A signed integer looses its sign when implicitly casted to an unsigned integer.
class integer_sign_lost_because_of_unsigned_cast : public test
{
public:
	integer_sign_lost_because_of_unsigned_cast(void);
	~integer_sign_lost_because_of_unsigned_cast(void);
	void runTests(bool mayCrash);

	int v1(void);
};