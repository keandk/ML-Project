#include "test.h"
#include <string.h>

// A buffer function is called with a destination size too big.
class incorrect_size_parameter_to_a_buffer_function : public test
{
public:
	incorrect_size_parameter_to_a_buffer_function(void);
	~incorrect_size_parameter_to_a_buffer_function(void);
	void runTests(bool mayCrash);

	void v1(void);
};