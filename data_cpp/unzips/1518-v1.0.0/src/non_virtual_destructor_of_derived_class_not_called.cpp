#include "non_virtual_destructor_of_derived_class_not_called.h"

// Constructor.
non_virtual_destructor_of_derived_class_not_called::non_virtual_destructor_of_derived_class_not_called(void)
{
}

// Destructor.
non_virtual_destructor_of_derived_class_not_called::~non_virtual_destructor_of_derived_class_not_called(void)
{
}

// Runs all tests.
void non_virtual_destructor_of_derived_class_not_called::runTests(bool mayCrash)
{
	v1();
}

// v1: simple case.
void non_virtual_destructor_of_derived_class_not_called::v1(void)
{
	animal *p = new madCow();
	delete p;
}

// Helper's constructor. 
animal::animal(void)
{
}

// Helper's destructor.
animal::~animal(void)
{
}

// Helper's constructor. 
madCow::madCow(void)
{
	p = (struct S*)malloc(sizeof(struct S));
}

// Helper's destructor.
madCow::~madCow(void)
{
	free(p); // ERROR: will never be called.
}
