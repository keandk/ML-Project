#include "test.h"
#include <string.h>

// memcopy() is used to copy memory from one region to another, 
// but the two regions overlap, which is not supported by memcopy().
// NOTE: the implementation in vstudio seems to handle overlapping
// regions correctly, even if the doc says otherwise.
class call_of_memcpy_with_overlapping_memory_regions : public test
{
public:
	call_of_memcpy_with_overlapping_memory_regions(void);
	~call_of_memcpy_with_overlapping_memory_regions(void);
	void runTests(bool mayCrash);

	void v1(void);
};
