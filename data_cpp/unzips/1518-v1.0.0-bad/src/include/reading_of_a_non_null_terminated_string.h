#include "test.h"
#include <string.h>

// A non null-terminated string is read.
class reading_of_a_non_null_terminated_string : public test
{
public:
	reading_of_a_non_null_terminated_string(void);
	~reading_of_a_non_null_terminated_string(void);
	void runTests(bool mayCrash);

	void v1(char* string);
};
