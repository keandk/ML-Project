#include "test.h"
#include <stdlib.h>

// A C++ array is not deleted correctly, which could lead to memory leaks.
class incorrect_cpp_array_deletion : public test
{
public:
	incorrect_cpp_array_deletion(void);
	~incorrect_cpp_array_deletion(void);
	void runTests(bool mayCrash);

	void v1(void);
};

// Helper class for incorrect_cpp_array_deletion.
class helper
{
private:
	S *p;
public:
	helper(void);
	~helper(void);
};
