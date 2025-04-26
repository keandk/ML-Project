#include "test.h"

// Incorrect pointer arithmetic to access a data structure.
class incorrect_pointer_arithmetic : public test
{
public:
	incorrect_pointer_arithmetic(void);
	~incorrect_pointer_arithmetic(void);
	void runTests(bool mayCrash);

	void v1(void);
};
