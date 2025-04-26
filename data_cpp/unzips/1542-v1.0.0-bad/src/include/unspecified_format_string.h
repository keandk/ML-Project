#include "test.h"
#include <stdio.h>

// A string function is passed a value without format string.
// This can lead to a format string vulnerability if the value
// can be in control of a malicious user.
class unspecified_format_string : public test
{
public:
	unspecified_format_string(void);
	~unspecified_format_string(void);
	void runTests(bool mayCrash);

	void v1(char *string);
};
