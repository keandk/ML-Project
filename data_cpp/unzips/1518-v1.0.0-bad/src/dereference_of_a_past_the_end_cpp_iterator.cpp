#include "dereference_of_a_past_the_end_cpp_iterator.h"

using std::list;

// Constructor.
dereference_of_a_past_the_end_cpp_iterator::dereference_of_a_past_the_end_cpp_iterator(void)
{
}

// Destructor.
dereference_of_a_past_the_end_cpp_iterator::~dereference_of_a_past_the_end_cpp_iterator(void)
{
}

// Runs all tests.
void dereference_of_a_past_the_end_cpp_iterator::runTests(bool mayCrash)
{
	v1();
}

// v1: simple case.
void dereference_of_a_past_the_end_cpp_iterator::v1(void)
{
	list<int> *li = new list<int>();
	li->push_back(1);
	li->push_back(2);
    list<int>::iterator i = li->end();
    int x = *i; // ERROR: dereferencing past-the-end C++ iterator.
	delete li;
}
