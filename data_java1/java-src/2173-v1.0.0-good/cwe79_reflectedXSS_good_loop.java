package servlets;
/*
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain. NIST assumes no responsibility whatsoever for its use by
 * other parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic.
 *
 * This reference program was developed in Jan 2010 as part of the Software
 * Assurance Metrics And Tool Evaluation (SAMATE) project.
 * We would appreciate acknowledgment if the software is used.
 * The SAMATE project website is: http://samate.nist.gov
 */

/*
 * This code (java servlet) has a FIX to Failure to Preserve Web Page
 * Structure 'Cross-site Scripting (XSS)' CWE-79 vulnerability.
 * http://cwe.mitre.org
 *
 * This code demos the Reflected XSS (or Non-Persistent) ==> "Loop Good Case".
 * The servlet reads the user input from the HTTP request and
 * reflects it back into the HTTP response after performing the
 * validate, filter, escape and encode of that user input data.
 *
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class cwe79_reflectedXSS_good_loop extends HttpServlet {

    public void doGet(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>" + "<head><title>CWE79 Reflected XSS Good</title></head>");

        out.println(
                "<body  bgcolor=\"#ffffff\">"
                + "<h4>Hello, my name is Test4XSS. What's yours?</h4>"
                + "<form method=\"get\">"
                + "<input type=\"text\" name=\"username\" size=\"50\">"
                + "<p></p>" + "<input type=\"submit\" value=\"Submit\">"
                + "<input type=\"reset\" value=\"Reset\">" + "</form>");

	for (int i = 1; i < 4; i++) {	/* Loop */
		out.println ("Loop # " + i);
        String username = request.getParameter("username");

		if (isValidateInput(username)) {
				String cleanedUsername = htmlEntityEncode (username);
				if ((cleanedUsername != null) && (cleanedUsername.length() > 0)) {
						out.println ("<h4>Hello, ");
						out.println (cleanedUsername);	/* OK - user input is being validated and encoded */
						out.println ("!</h4>");
				}
		}
		else if (username !=null)  {
			out.println ("<h4>Sorry, your input is invalid</h4>");
		}
	}
        out.println("</body></html>");
        out.close();
    }

	/*
	 * To prevent XSS, assume all input is malicious. This function is just
	 * an example and placeholder for demonstrating the need of performing proper
	 * output encoding, escaping and quoting. All references are used as example for
	 * demonstration only.They are neither for endorsement nor recommendation.
	 *
	 * One should decide to use existing Security API
	 * (e.g., OWASP Enterprise Security API (ESAPI) 2.0), or customized API, or
	 * customized functions for the same purpose.
	 *
	 * This test case used two functions for the purpose of protecting against XSS.
	 *   - isValudateInput - uses whitelist method to check the user input (example only)
	 *   - htmlEntityEncode - does the html encode (sourced by OWASP:
	 *              http://www.owasp.org/index.php/How_to_perform_HTML_entity_encoding_in_Java)
	 *
	 */
	private static final boolean isValidateInput (String uInput) {

		/*
		 * used as example with the whitelist to only allow alphnumeric, space and "'" as
		 * character set for name.  The length of the user input is also registrted within 100
		 *
		 */

		if (uInput == null)
			return false;

		if (uInput.matches ("[a-zA-Z0-9' ]*") && (uInput.length() <= 100))
			return true;
		else
			return false;
	}

    // From OWASP: Return StringBuilder and/or make Writer param and write to stream directly
    public static String htmlEntityEncode( String s )
        {
                int len = s.length();
                StringBuilder buf = new StringBuilder(len);

                for ( int i = 0; i < len; i++ )
                {
                        char c = s.charAt( i );
                        if ( c>='a' && c<='z' || c>='A' && c<='Z' || c>='0' && c<='9' )
                        {
                                buf.append( c );
                        }
                        else
                        {
                                buf.append("&#").append((int)c).append(";");
                        }
                }

                return buf.toString();
    }


    public String getServletInfo() {
        return "The Hello servlet says hello.(with CWE79 Reflected fixed XSS sample code).";
    }
}
