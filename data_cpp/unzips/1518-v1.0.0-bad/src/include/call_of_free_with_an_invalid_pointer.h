#include "test.h"
#include <stdlib.h>

// free() is given a pointer to something else than an allocated memory block.
class call_of_free_with_an_invalid_pointer : public test
{
public:
	call_of_free_with_an_invalid_pointer(void);
	~call_of_free_with_an_invalid_pointer(void);
	void runTests(bool mayCrash);

	void v1(void);
};