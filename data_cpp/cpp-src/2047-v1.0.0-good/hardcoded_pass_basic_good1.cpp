
/* This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain. NIST assumes no responsibility whatsoever for its use by
 * other parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic.

 * We would appreciate acknowledgement if the software is used.
 * The SAMATE project website is: http://samate.nist.gov
*/


#include <iostream>
#include <string>
using namespace std;

bool verifyPassword(const string& pass) {
	
	// veryify password and do a query etc.
	// return true or false depending on verification
	return true;
}


bool user_ok(const string& userpass)
{
	if (verifyPassword(userpass))
		return true;
	return false;
}

int main()
{
	string userpass;
	cout << "Enter your password: " << endl;
	cin >> userpass;

	if (user_ok(userpass))
		cout << "You are now identified." << endl;
	else
		cout << "Your password is not valid, please reenter it." << endl;

	return 0;
}
