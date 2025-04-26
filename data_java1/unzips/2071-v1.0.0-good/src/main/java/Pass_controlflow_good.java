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

import java.io.*;

public class Pass_controlflow_good
{
	public static void main (String[]args)
	{
		try
		{
		BufferedReader read = new BufferedReader(new FileReader("Passwords.txt"));
		String adminPass = read.readLine();
		if (args[0].equals(adminPass))
			System.out.println("Access Granted");
		else
			System.out.println("Your password is not valid, please reenter it.");
		}
		catch(IOException e){
		System.out.println("io error");}
		return;
	}

}