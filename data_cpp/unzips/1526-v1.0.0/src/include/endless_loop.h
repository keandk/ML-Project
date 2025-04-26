#include "test.h"

// The condition to exit the loop is never satisfied.
class endless_loop : public test
{
public:
	endless_loop(void);
	~endless_loop(void);
	void runTests(bool mayCrash);

	void v1(void);
	void v2(void);
};
