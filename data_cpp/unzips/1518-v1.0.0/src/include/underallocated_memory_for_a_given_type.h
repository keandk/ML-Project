#include "test.h"
#include <stdlib.h>

// The size passed to malloc is not big enough to represent the type.
class underallocated_memory_for_a_given_type : public test
{
public:
	underallocated_memory_for_a_given_type(void);
	~underallocated_memory_for_a_given_type(void);
	void runTests(bool mayCrash);

	void v1(void);
};
