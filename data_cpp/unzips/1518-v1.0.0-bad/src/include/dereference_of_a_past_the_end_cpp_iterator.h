#include "test.h"
#include <list>

// A past-the-end c++ iterator is dereferenced.
class dereference_of_a_past_the_end_cpp_iterator : public test
{
public:
	dereference_of_a_past_the_end_cpp_iterator(void);
	~dereference_of_a_past_the_end_cpp_iterator(void);
	void runTests(bool mayCrash);

	void v1(void);
};
