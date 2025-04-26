#include "test.h"
#include <list>

// An erased c++ iterator is dereferenced.
class dereference_of_an_erased_cpp_iterator : public test
{
public:
	dereference_of_an_erased_cpp_iterator(void);
	~dereference_of_an_erased_cpp_iterator(void);
	void runTests(bool mayCrash);

	void v1(void);
};