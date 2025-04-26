#include "test.h"
#include <stdlib.h>

// A non-virtual destructor is never called.
class non_virtual_destructor_of_derived_class_not_called :	public test
{
public:
	non_virtual_destructor_of_derived_class_not_called(void);
	~non_virtual_destructor_of_derived_class_not_called(void);
	void runTests(bool mayCrash);

	void v1(void);
};

// Helper class.
class animal
{
protected:
	struct S *p;
public:
	animal(void);
	~animal(void);
};

// Helper class.
class madCow : public animal
{
public:
	madCow(void);
	~madCow(void);
};
