#include "bit_shift_bigger_than_integral_type_or_negative.h"
#include "call_of_free_with_an_invalid_pointer.h"
#include "call_of_memcpy_with_overlapping_memory_regions.h"
#include "dereference_of_a_null_pointer.h"
#include "dereference_of_a_past_the_end_cpp_iterator.h"
#include "dereference_of_an_erased_cpp_iterator.h"
#include "division_by_zero.h"
#include "endless_loop.h"
#include "incorrect_cpp_array_deletion.h"
#include "incorrect_pointer_arithmetic.h"
#include "incorrect_size_parameter_to_a_buffer_function.h"
#include "integer_overflow_or_underflow.h"
#include "integer_precision_lost_because_of_bad_cast.h"
#include "integer_sign_lost_because_of_unsigned_cast.h"
#include "non_virtual_destructor_of_derived_class_not_called.h"
#include "overrun_or_underrun_of_an_array.h"
#include "reading_of_a_non_null_terminated_string.h"
#include "reading_of_a_string_of_arbitrary_length_without_limit.h"
#include "reading_of_freed_memory.h"
#include "reading_of_uninitialized_variable.h"
#include "resource_reference_lost.h"
#include "return_of_a_pointer_to_a_local_variable.h"
#include "underallocated_memory_for_a_given_type.h"
#include "unspecified_format_string.h"
#include "use_of_negative_array_index_or_size.h"

#ifdef polyspace

// PolySpace main - so the analysis continues when a red error is found.
// Note: this main is not executable!
int main(int argc, char *argv[])
{
	volatile int random = 0;
	bool mayCrash = 1;

	switch (random)
	{
	case 1: 
		{
		reading_of_freed_memory *t1 = new reading_of_freed_memory();
		t1->runTests(mayCrash);
		delete t1;
		break;
		}
	case 2:
		{
		underallocated_memory_for_a_given_type *t2 = new underallocated_memory_for_a_given_type();
		t2->runTests(mayCrash);
		delete t2;
		break;
		}
	case 3:
		{
		call_of_free_with_an_invalid_pointer *t3 = new call_of_free_with_an_invalid_pointer();
		t3->runTests(mayCrash);
		delete t3;
		break;
		}
	case 4:
		{
		incorrect_cpp_array_deletion *t4 = new incorrect_cpp_array_deletion();
		t4->runTests(mayCrash);
		delete t4;
		break;
		}
	case 5:
		{
		call_of_memcpy_with_overlapping_memory_regions *t5 = new call_of_memcpy_with_overlapping_memory_regions();
		t5->runTests(mayCrash);
		delete t5;
		break;
		}
	case 6:
		{
		reading_of_uninitialized_variable *t6 = new reading_of_uninitialized_variable();
		t6->runTests(mayCrash);
		delete t6;
		break;
		}
	case 7:
		{
		overrun_or_underrun_of_an_array *t7 = new overrun_or_underrun_of_an_array();
		t7->runTests(mayCrash);
		delete t7;
		break;
		}
	case 8:
		{
		dereference_of_a_past_the_end_cpp_iterator *t8 = new dereference_of_a_past_the_end_cpp_iterator();
		t8->runTests(mayCrash);
		delete t8;
		break;
		}
	case 9:
		{
		dereference_of_an_erased_cpp_iterator *t9 = new dereference_of_an_erased_cpp_iterator();
		t9->runTests(mayCrash);
		delete t9;
		break;
		}
	case 10:
		{
		incorrect_size_parameter_to_a_buffer_function *t10 = new incorrect_size_parameter_to_a_buffer_function();
		t10->runTests(mayCrash);
		delete t10;
		break;
		}
	case 11:
		{
		use_of_negative_array_index_or_size *t11 = new use_of_negative_array_index_or_size();
		t11->runTests(mayCrash);
		delete t11;
		break;
		}
	case 12:
		{
		reading_of_a_string_of_arbitrary_length_without_limit *t12 = new reading_of_a_string_of_arbitrary_length_without_limit();
		t12->runTests(mayCrash);
		delete t12;
		break;
		}
	case 13:
		{
		reading_of_a_non_null_terminated_string *t13 = new reading_of_a_non_null_terminated_string();
		t13->runTests(mayCrash);
		delete t13;
		break;
		}
	case 14:
		{
		return_of_a_pointer_to_a_local_variable *t14 = new return_of_a_pointer_to_a_local_variable();
		t14->runTests(mayCrash);
		delete t14;
		break;
		}
	case 15:
		{
		incorrect_pointer_arithmetic *t15 = new incorrect_pointer_arithmetic();
		t15->runTests(mayCrash);
		delete t15;
		break;
		}
	case 16:
		{
		dereference_of_a_null_pointer *t16 = new dereference_of_a_null_pointer();
		t16->runTests(mayCrash);
		delete t16;
		break;
		}
	case 17:
		{
		resource_reference_lost *t17 = new resource_reference_lost();
		t17->runTests(mayCrash);
		delete t17;
		break;
		}
	case 18:
		{
		division_by_zero *t18 = new division_by_zero();
		t18->runTests(mayCrash);
		delete t18;
		break;
		}
	case 19:
		{
		integer_overflow_or_underflow *t19 = new integer_overflow_or_underflow();
		t19->runTests(mayCrash);
		delete t19;
		break;
		}
	case 20:
		{
		bit_shift_bigger_than_integral_type_or_negative *t20 = new bit_shift_bigger_than_integral_type_or_negative();
		t20->runTests(mayCrash);
		delete t20;
		break;
		}
	case 21:
		{
		integer_sign_lost_because_of_unsigned_cast *t21 = new integer_sign_lost_because_of_unsigned_cast();
		t21->runTests(mayCrash);
		delete t21;
		break;
		}
	case 22:
		{
		integer_precision_lost_because_of_bad_cast *t22 = new integer_precision_lost_because_of_bad_cast();
		t22->runTests(mayCrash);
		delete t22;
		break;
		}
	case 23:
		{
		unspecified_format_string *t23 = new unspecified_format_string();
		t23->runTests(mayCrash);
		delete t23;
		break;
		}
	case 24:
		{
		endless_loop *t24 = new endless_loop();
		t24->runTests(mayCrash);
		delete t24;
		break;
		}
	case 25:
		{
		non_virtual_destructor_of_derived_class_not_called *t25 = new non_virtual_destructor_of_derived_class_not_called();
		t25->runTests(mayCrash);
		delete t25;
		break;
		}
	}
}

#else

// Main function that starts the tests, for Coverity and Insure.
int main(int argc, char *argv[])
{

#ifdef maycrash
	
	bool mayCrash = 1;

#else

	bool mayCrash = 0;

#endif
	
	reading_of_freed_memory *t1 = new reading_of_freed_memory();
	t1->runTests(mayCrash);
	delete t1;

	underallocated_memory_for_a_given_type *t2 = new underallocated_memory_for_a_given_type();
	t2->runTests(mayCrash);
	delete t2;

	call_of_free_with_an_invalid_pointer *t3 = new call_of_free_with_an_invalid_pointer();
	t3->runTests(mayCrash);
	delete t3;

	incorrect_cpp_array_deletion *t4 = new incorrect_cpp_array_deletion();
	t4->runTests(mayCrash);
	delete t4;

	call_of_memcpy_with_overlapping_memory_regions *t5 = new call_of_memcpy_with_overlapping_memory_regions();
	t5->runTests(mayCrash);
	delete t5;

	reading_of_uninitialized_variable *t6 = new reading_of_uninitialized_variable();
	t6->runTests(mayCrash);
	delete t6;

	overrun_or_underrun_of_an_array *t7 = new overrun_or_underrun_of_an_array();
	t7->runTests(mayCrash);
	delete t7;

	dereference_of_a_past_the_end_cpp_iterator *t8 = new dereference_of_a_past_the_end_cpp_iterator();
	t8->runTests(mayCrash);
	delete t8;

	dereference_of_an_erased_cpp_iterator *t9 = new dereference_of_an_erased_cpp_iterator();
	t9->runTests(mayCrash);
	delete t9;

	incorrect_size_parameter_to_a_buffer_function *t10 = new incorrect_size_parameter_to_a_buffer_function();
	t10->runTests(mayCrash);
	delete t10;

	use_of_negative_array_index_or_size *t11 = new use_of_negative_array_index_or_size();
	t11->runTests(mayCrash);
	delete t11;

	reading_of_a_string_of_arbitrary_length_without_limit *t12 = new reading_of_a_string_of_arbitrary_length_without_limit();
	t12->runTests(mayCrash);
	delete t12;

	reading_of_a_non_null_terminated_string *t13 = new reading_of_a_non_null_terminated_string();
	t13->runTests(mayCrash);
	delete t13;

	return_of_a_pointer_to_a_local_variable *t14 = new return_of_a_pointer_to_a_local_variable();
	t14->runTests(mayCrash);
	delete t14;

	incorrect_pointer_arithmetic *t15 = new incorrect_pointer_arithmetic();
	t15->runTests(mayCrash);
	delete t15;

	dereference_of_a_null_pointer *t16 = new dereference_of_a_null_pointer();
	t16->runTests(mayCrash);
	delete t16;

	resource_reference_lost *t17 = new resource_reference_lost();
	t17->runTests(mayCrash);
	delete t17;

    division_by_zero *t18 = new division_by_zero();
	t18->runTests(mayCrash);
	delete t18;

	integer_overflow_or_underflow *t19 = new integer_overflow_or_underflow();
	t19->runTests(mayCrash);
	delete t19;

	bit_shift_bigger_than_integral_type_or_negative *t20 = new bit_shift_bigger_than_integral_type_or_negative();
	t20->runTests(mayCrash);
	delete t20;

	integer_sign_lost_because_of_unsigned_cast *t21 = new integer_sign_lost_because_of_unsigned_cast();
	t21->runTests(mayCrash);
	delete t21;

	integer_precision_lost_because_of_bad_cast *t22 = new integer_precision_lost_because_of_bad_cast();
	t22->runTests(mayCrash);
	delete t22;

	unspecified_format_string *t23 = new unspecified_format_string();
	t23->runTests(mayCrash);
	delete t23;

	endless_loop *t24 = new endless_loop();
	t24->runTests(mayCrash);
	delete t24;

	non_virtual_destructor_of_derived_class_not_called *t25 = new non_virtual_destructor_of_derived_class_not_called();
	t25->runTests(mayCrash);
	delete t25;

	return 0;
}

#endif
