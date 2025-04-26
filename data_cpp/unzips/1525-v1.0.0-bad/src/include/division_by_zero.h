#include "test.h"

// A division by zero occurs.
class division_by_zero : public test
{
public:
	division_by_zero(void);
	~division_by_zero(void);
	void runTests(bool mayCrash);

	void v1(void);
};
