#include "test.h"
#include <stdlib.h>

// An array is overrun.
class overrun_or_underrun_of_an_array : public test
{
public:
	overrun_or_underrun_of_an_array(void);
	~overrun_or_underrun_of_an_array(void);
	void runTests(bool mayCrash);

	void v1(void);
	void v2(void);
};
