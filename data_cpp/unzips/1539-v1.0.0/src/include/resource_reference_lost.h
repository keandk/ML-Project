#include "test.h"
#include <stdlib.h>

// A reference to an allocated resource is lost because of 
// pointer reuse.
class resource_reference_lost :	public test
{
public:
	resource_reference_lost(void);
	~resource_reference_lost(void);
	void runTests(bool mayCrash);

	void v1(void);
};
