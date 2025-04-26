#include "test.h"

// Use of negative index when accessing an array or negative size when creating it.
class use_of_negative_array_index_or_size : public test
{
public:
	use_of_negative_array_index_or_size(void);
	~use_of_negative_array_index_or_size(void);
	void runTests(bool mayCrash);

	void v1(void);
	void v2(void);
	int h1(void);
};
