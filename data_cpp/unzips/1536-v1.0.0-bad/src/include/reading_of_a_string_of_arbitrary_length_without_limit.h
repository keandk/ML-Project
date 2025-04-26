#include "test.h"
#include <string.h>

// Use of gets(), strcpy() and similar functions, where there is no way to 
// limit the size of the read string (no destination size parameter).
class reading_of_a_string_of_arbitrary_length_without_limit : public test
{
public:
	reading_of_a_string_of_arbitrary_length_without_limit(void);
	~reading_of_a_string_of_arbitrary_length_without_limit(void);
	void runTests(bool mayCrash);

	void v1(char* string);
};
