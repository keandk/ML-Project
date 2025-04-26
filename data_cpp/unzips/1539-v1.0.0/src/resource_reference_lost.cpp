#include "resource_reference_lost.h"

// Constructor.
resource_reference_lost::resource_reference_lost(void)
{
}

// Destructor.
resource_reference_lost::~resource_reference_lost(void)
{
}

// Runs all tests.
void resource_reference_lost::runTests(bool mayCrash)
{
	v1();
}

// A memory block is leaked because of pointer reuse.
void resource_reference_lost::v1(void)
{
	struct S *p1, *p2;

    p1 = (struct S*)malloc(sizeof(struct S));
    p1->a = 0;
    p1->b = 0;
    for (int i = 0; i<100; i++)
    {
        p1->c[i] = 0;
    }

    p1 = (struct S*)malloc(sizeof(struct S)); // ERROR: p1 value is lost.
    p1->a = 0;
    p1->b = 1;
    for (int j = 0; j<100; j++)
    {
        p1->c[j] = 0;
    }

	free(p1);
}
