/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filter.authentication;

import com.encdyc.AESencrp;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author girish
 */
public class AccessFilter implements Filter {

	private static final boolean debug = true;
	private FilterConfig filterConfig = null;
	private static final String AUTHENTICATION_PROPERTY = "Authentication";
	private static final String AUTHENTICATION_SCHEME = "Basic";
	private static final String ACCESS_DENIED = "Access denied / Authentication Failed for this resource due to 401";


	public AccessFilter() {
	}

	/**
	 *
	 * @param request The servlet request we are processing
	 * @param response The servlet response we are creating
	 * @param chain The filter chain we are processing
	 *
	 * @exception IOException if an input/output error occurs
	 * @exception ServletException if a servlet error occurs
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException { try {
				//Authentication: GIRISH MARTHAM
				HttpServletResponse res = (HttpServletResponse) response;
				HttpServletRequest req = (HttpServletRequest) request;
				String username = "";
				String password = "";
				
				//code to allow cross domain requests for direct ajax calls
				res.setHeader("Access-Control-Allow-Origin", "*");
				res.setHeader("Access-Control-Allow-Credentials", "true");
				res.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
				res.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization,X-Custom-Header");
				System.out.println("Authentication.11........." + req.getHeader(AUTHENTICATION_PROPERTY));
				 String authentication ="";
				 boolean err = false;
				try{
				 authentication = req.getHeader(AUTHENTICATION_PROPERTY);//AESencrp.decrypt(req.getHeader(AUTHENTICATION_PROPERTY));
                                 System.out.println("authentication.dec222........." + authentication);
				
				}
				catch(Exception e){
					e.printStackTrace();
					authentication = "";
				}
				
				System.out.println("authentication.22222........." + authentication);
				
				if (authentication == null || authentication.isEmpty()) {
					//return ACCESS_DENIED;
					err = true;
					
				} else {
					//Get encoded username and password
					final String encodedUserPassword = authentication.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
					System.out.println("encodedUserPassword111.........." + encodedUserPassword);
					String usernameAndPassword;
					usernameAndPassword = encodedUserPassword;
					System.out.println("usernameAndPassword222.........." + usernameAndPassword);
					//Split username and password tokens
					StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, " ");
					System.out.println("tokenizer.........." + tokenizer.toString());
					username = tokenizer.nextToken();
					System.out.println("username.........." + username);
					password = tokenizer.nextToken();
					System.out.println("password.........." + password);
				}
				
				Set<String> rolesSet = new HashSet<String>();
				rolesSet.add("ADMIN");
				if (!isUserAllowed(username, password, rolesSet)) {
					err = true;
					//return ACCESS_DENIED;
				}
				//}
				
				System.out.println("errerr.....111..........." + err);
				
				if (err) {
					PrintWriter out = response.getWriter();
					res.setStatus(401);// setting that the request is un authorized due to authentication fail
					out.println(ACCESS_DENIED);
				} else {
					chain.doFilter(request, response);
				}
		} catch (Exception ex) {
			Logger.getLogger(AccessFilter.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}

	private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet) {
		boolean isAllowed = false;

		//Step 1. Fetch password from database and match with password in argument
		//If both match then get the defined role for user from database and continue; else return isAllowed [false]
		//Access the database and do this part yourself
		//String userRole = userMgr.getUserRole(username);
		String userRole = "ADMIN";
		String username1 = "GIRISH";
		String password1 = "MARTHAM";

		//Step 2. Verify user role
		if (rolesSet.contains(userRole) && (username.equals(username1) && password1.equals(password))) {
			isAllowed = true;
		}
		return isAllowed;
	}

	/**
	 * Return the filter configuration object for this filter.
	 */
	public FilterConfig getFilterConfig() {
		return (this.filterConfig);
	}

	/**
	 * Set the filter configuration object for this filter.
	 *
	 * @param filterConfig The filter configuration object
	 */
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	/**
	 * Destroy method for this filter
	 */
	public void destroy() {
	}

	/**
	 * Init method for this filter
	 */
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
		if (filterConfig != null) {
			if (debug) {
				log("AccessFilter:Initializing filter");
			}
		}
	}

	/**
	 * Return a String representation of this object.
	 */
	@Override
	public String toString() {
		if (filterConfig == null) {
			return ("AccessFilter()");
		}
		StringBuffer sb = new StringBuffer("AccessFilter(");
		sb.append(filterConfig);
		sb.append(")");
		return (sb.toString());
	}

//	private void sendProcessingError(Throwable t, ServletResponse response) {
//		String stackTrace = getStackTrace(t);
//
//		if (stackTrace != null && !stackTrace.equals("")) {
//			try {
//				response.setContentType("text/html");
//				PrintStream ps = new PrintStream(response.getOutputStream());
//				PrintWriter pw = new PrintWriter(ps);
//				pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N
//
//				// PENDING! Localize this for next official release
//				pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
//				pw.print(stackTrace);
//				pw.print("</pre></body>\n</html>"); //NOI18N
//				pw.close();
//				ps.close();
//				response.getOutputStream().close();
//			} catch (Exception ex) {
//			}
//		} else {
//			try {
//				PrintStream ps = new PrintStream(response.getOutputStream());
//				t.printStackTrace(ps);
//				ps.close();
//				response.getOutputStream().close();
//			} catch (Exception ex) {
//			}
//		}
//	}
//
//	public static String getStackTrace(Throwable t) {
//		String stackTrace = null;
//		try {
//			StringWriter sw = new StringWriter();
//			PrintWriter pw = new PrintWriter(sw);
//			t.printStackTrace(pw);
//			pw.close();
//			sw.close();
//			stackTrace = sw.getBuffer().toString();
//		} catch (Exception ex) {
//		}
//		return stackTrace;
//	}
	public void log(String msg) {
		filterConfig.getServletContext().log(msg);
	}

}
