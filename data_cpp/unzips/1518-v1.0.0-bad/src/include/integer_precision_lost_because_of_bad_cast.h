#include "test.h"

// A value is casted into a type that can't represent it, 
// because it's too small.
class integer_precision_lost_because_of_bad_cast : public test
{
public:
	integer_precision_lost_because_of_bad_cast(void);
	~integer_precision_lost_because_of_bad_cast(void);
	void runTests(bool mayCrash);

	void v1(void);
};
