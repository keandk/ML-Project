--------------------------------------------------
Defense Research & Development Canada - Valcartier
April 2006
Frederic.Michaud@drdc-rddc.gc.ca
--------------------------------------------------

This set of synthetic tests was used to evaluate the performance
of automatic defect detection tools. It was specifically created for
the evaluation of PolySpace for C++, Coverity Prevent and Parasoft
Insure++. Each class (25 of them) tests for a specific kind of problem,
often in more than one way.

All files are integrated in a MS Visual Studio 2003 project, but VS is not
needed to run the tests. However, the #include files used for our evaluation
were those included with VS and from our experience, they are not exactly
the same for every compiler. These differences can have an impact on some
tests, especially those using the C++ STL.

----------------
Framework Design
----------------

All tests are derived from the "test" abstract class, which mandate
the implementation of the "runTests(bool mayCrash)" method. Some tests,
if executed (e.g. Insure), will crash the program. runTests() called
with mayCrash=0 will deactivate the tests that could crash the program.
However, this may not work properly, because the effects of some tests are
very dependent of the compiler and the configuration used.

The tests are implemented in v1, v2 [...] methods, called from runTests().
Some defects have interesting variants and many versions of the tests
were used when appropriate.

Because of the technique used by PolySpace (abstract interpretation), the
program had to be slightly modified for its verification. PolySpace is so
literal in its interpretation of the code that if a critical error is
found, the rest of the branch will not be verified. Since we wanted to run
all our tests in one verification pass, the code was modified so the
PolySpace model would allow the continuation of the verification after a
critical error is found. Conditional compilation with preprocessor
definitions is used to compile the program for a verification with PolySpace.

------------------------
Preprocessor Definitions
------------------------

mayCrash: if defined, tests that could crash the program are activated.

polyspace: if defined, the program is compiled for a verification with
           PolySpace for C++. If not, the program is compiled for a
           verification with Insure++ or Coverity.

--------
main.cpp
--------

All tests are executed from the main. There are 2 mains: one for
PolySpace and the other for Coverity and Insure. The PolySpace main
is not executable.

----------------------
How to Use These Tests
----------------------

These tests are simply small programs with bad behaviors. For a static
analysis tool, every code file (.cpp) should be compiled with the tool
and maycrash defined. For a dynamic analysis tool, just compile and link
with your favorite compiler without maycrash defined.

