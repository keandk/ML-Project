#include "test.h"
#include <stdlib.h>

// A pointer to a local variable will go out of scope after the return.
class return_of_a_pointer_to_a_local_variable :	public test
{
public:
	return_of_a_pointer_to_a_local_variable(void);
	~return_of_a_pointer_to_a_local_variable(void);
	void runTests(bool mayCrash);

	int* v1(void);
	void v2(struct S3* in);
};