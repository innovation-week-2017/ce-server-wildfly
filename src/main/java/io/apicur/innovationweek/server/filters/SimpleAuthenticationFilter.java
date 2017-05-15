package io.apicur.innovationweek.server.filters;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;


/**
 * A very simple implementation of an authentication filter.
 */
public class SimpleAuthenticationFilter implements Filter {
	
	private static final String realm = "AddressBookManager";

    /**
     * Constructor.
     */
    public SimpleAuthenticationFilter() {
    }

    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String authHeader = req.getHeader("Authorization"); //$NON-NLS-1$
        if (authHeader == null) {
            sendAuthResponse((HttpServletResponse) response);
        } else if (authHeader.toUpperCase().startsWith("BASIC")) { //$NON-NLS-1$
            Creds credentials = parseAuthorizationBasic(authHeader);
            if  (credentials == null) {
                sendAuthResponse((HttpServletResponse) response);
            } else {
                doBasicAuth(credentials, req, (HttpServletResponse) response, chain);
            }
        } else {
            sendAuthResponse((HttpServletResponse) response);
        }
    }

    /**
     * Handle BASIC authentication.
     * @param credentials the credentials
     * @param request the http servlet request
     * @param response the http servlet respose
     * @param chain the filter chain
     * @throws IOException when I/O failure occurs in filter chain
     * @throws ServletException when servlet exception occurs during auth
     */
    protected void doBasicAuth(Creds credentials, HttpServletRequest request, HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {
    	if (credentials.username == null || !credentials.username.equals(credentials.password)) {
            sendAuthResponse((HttpServletResponse) response);
    	} else {
    		AuthPrincipal principal = new AuthPrincipal(credentials.username);
	        doFilterChain(request, response, chain, principal);
    	}
    }

    /**
     * Further process the filter chain.
     * @param request the request
     * @param response the response
     * @param chain the filter chain
     * @param principal the auth principal
     * @throws IOException when I/O failure occurs in filter chain
     * @throws ServletException when servlet exception occurs during auth
     */
    protected void doFilterChain(ServletRequest request, ServletResponse response, FilterChain chain,
            AuthPrincipal principal) throws IOException, ServletException {
        if (principal == null) {
            chain.doFilter(request, response);
        } else {
            HttpServletRequest hsr;
            hsr = wrapTheRequest(request, principal);
            chain.doFilter(hsr, response);
        }
    }

    /**
     * Wrap the request to provide the principal.
     * @param request the request
     * @param principal the principal
     */
    private HttpServletRequest wrapTheRequest(final ServletRequest request, final AuthPrincipal principal) {
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper((HttpServletRequest) request) {
            @Override
            public Principal getUserPrincipal() {
                return principal;
            }

            @Override
            public boolean isUserInRole(String role) {
            	return false;
            }

            @Override
            public String getRemoteUser() {
                return principal.getName();
            }
        };
        return wrapper;
    }

    /**
     * Parses the Authorization request header into a username and password.
     * @param authHeader the auth header
     */
    private Creds parseAuthorizationBasic(String authHeader) {
        String userpassEncoded = authHeader.substring(6);
        String data = StringUtils.newStringUtf8(Base64.decodeBase64(userpassEncoded));
        int sepIdx = data.indexOf(':');
        if (sepIdx > 0) {
            String username = data.substring(0, sepIdx);
            String password = data.substring(sepIdx + 1);
            return new Creds(username, password);
        } else {
            return new Creds(data, null);
        }
    }

    /**
     * Sends a response that tells the client that authentication is required.
     * @param response the response
     * @throws IOException when an error cannot be sent
     */
    private void sendAuthResponse(HttpServletResponse response) throws IOException {
        response.setHeader("WWW-Authenticate", String.format("Basic realm=\"%1$s\"", realm)); //$NON-NLS-1$ //$NON-NLS-2$
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    /**
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
    }

    /**
     * Models inbound basic auth credentials (user/password).
     * @author eric.wittmann@redhat.com
     */
    protected static class Creds {
        public String username;
        public String password;

        /**
         * Constructor.
         *
         * @param username the username
         * @param password the password
         */
        public Creds(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

}
