#include "reading_of_freed_memory.h"

// Constructor.
reading_of_freed_memory::reading_of_freed_memory(void)
{
}

// Destructor.
reading_of_freed_memory::~reading_of_freed_memory(void)
{
}

// Runs all tests.
void reading_of_freed_memory::runTests(bool mayCrash)
{
	if (mayCrash == 0)
	{
		v1();
		v3a();
	}
	else
	{
		v1();
		v2();
		v3a();
	}
}

// v1: simple case of use after free.
void reading_of_freed_memory::v1(void)
{
	int i;
    struct S *p;

    p = (struct S*)malloc(sizeof(struct S));
    free(p);
    i = p->a; // ERROR: Use after free, dereferencing freed pointer "p".
}

// v2: double free.
void reading_of_freed_memory::v2(void)
{
	struct S *p;

    p = (struct S*)malloc(sizeof(struct S));
    free(p);
    free(p); // ERROR: double free of pointer "p".
}

// v3: interprocedural version.
void reading_of_freed_memory::v3a(void)
{
    int i;
	struct S *p;

    p = (struct S*)malloc(sizeof(struct S));
	p->a = 0;
    if(v3b(p) <= 0)
    {
        i = p->a; // ERROR: use after free, "p" previously freed in call to "v3b".
    }
}

// v3: interprocedural version.
int reading_of_freed_memory::v3b(struct S *p)
{
    if(p->a == 0)
    {
        free(p);
        return -1;
    }
    return 0;
}
