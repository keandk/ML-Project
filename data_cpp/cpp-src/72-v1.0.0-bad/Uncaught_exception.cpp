#include <iostream.h>
#include <new>
#include <stdlib.h>
int
main(){
char input[100];
int i, n;
long *l;
cout << "How many numbers do you want to type in? ";
cin.getline(input, 100);
i = atoi(input);
//here we are purposly not checking to see if this call to
//new works
//try {
	l = new long [i];
//}
//catch (bad_alloc & ba) {
// cout << "Exception:" << endl;
//}
if (l == NULL)
	exit(1);
for (n = 0; n < i; n++) {
	cout << "Enter number: ";
	cin.getline(input, 100);
	l[n] = atol(input);
}
cout << "You have entered: ";
for (n = 0; n < i; n++)
	cout << l[n] << ", ";
delete[] l;
return 0;
}
