
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

string getDefaultPassword() {
	string pass;
	// get the system password or do a query etc.
	return pass;
}

bool user_ok(const string& userpass)
{
	if (userpass == getDefaultPassword())
		return true;
	return false;
}


int main()
{
	string userpass = "";
	unsigned int nbT=  0;
	cout << "Enter your password: " << endl;
	cin  >> userpass;
	while (!user_ok(userpass) && (++nbT < 4))
	{
		cout << "Your password is not valid, please reenter it." << endl;
		cin  >> userpass;
	}
	if (nbT == 4) return 0;
	cout << "You are now identified." << endl;

	return 0;
}
