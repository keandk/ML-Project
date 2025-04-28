package goodCodeComplexity;
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

/*
 * This servlet implements a Cross-Site Scripting vulnerability (XSS)
 * Parameters:
 *   - data: source of the vulnerability
 * Example:
 *   - url: http://server_address/path_to_servlet/CrossSiteScripting_080?data=<script>alert('XSS');</script>
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrossSiteScripting_good_Loop_080 extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public CrossSiteScripting_good_Loop_080()
    {
        super();
    }

    // Method which will be called to handle HTTP GET requests
	protected void doGet( HttpServletRequest req, HttpServletResponse resp )
		throws ServletException, IOException
	{
		// Prepare the output data that will be sent back to the client
		resp.setContentType( "text/html" );
		ServletOutputStream out = resp.getOutputStream();
		
		// Write the HTML document to the output stream.
		// Note that the data provided by the client in
		// the field "data" is encoded so there is no more XSS
		out.println( "<html><body><blockquote><pre>" );
		for (int i = 0; i < 3; i++)
		{
			String s =  req.getParameter( "data" );
			
			if ((s != null) && (s.length() > 0))
			{
				String s_encode = htmlEntityEncode( s );
				if (s_encode != null)
					out.println(  s_encode );
			}
		}
		out.println( "</pre></blockquote></body></html>" );
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
	}
}
