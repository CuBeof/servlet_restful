package nissan.procurement.filter;

import nissan.procurement.utils.Constant;
import nissan.procurement.utils.Authentication;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/api/v1/auth/*")
public class AuthenticationFilter implements Filter {
	private static final int STATUS_CODE_UNAUTHORIZED = 401;
    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        String jwt = httpRequest.getHeader(Constant.HEADER_STRING);
        if (Authentication.isAuthenticated(jwt)) {
        	System.out.println("JwtAuthentication: Valid token. Go to other filters");
        	chain.doFilter(request, response);
        } else {
        	System.out.println( "JwtAuthentication: Failed logging in with security token");
	        HttpServletResponse httpResponse = (HttpServletResponse) response;
	        httpResponse.setContentLength( 0 );
	        httpResponse.setStatus( STATUS_CODE_UNAUTHORIZED );
        }
		// pass the request along the filter chain
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
