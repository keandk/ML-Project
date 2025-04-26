#include "test.h"
#include <stdlib.h>

// A pointer to allocated memory is used, even if the memory block was freed 
// before (the pointer was passed to free()).
class reading_of_freed_memory : public test
{
public:
	reading_of_freed_memory(void);
	~reading_of_freed_memory(void);
	void runTests(bool mayCrash);

	void v1(void);
	void v2(void);
	void v3a(void);
	int v3b(struct S *p);
};
