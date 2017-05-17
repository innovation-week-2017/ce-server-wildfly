package io.apicur.innovationweek.server.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {

    /**
     * Constructor.
     */
    public CorsFilter() {
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
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        if (isPreflightRequest(httpReq)) {
            httpResp.setHeader("Access-Control-Allow-Origin", httpReq.getHeader("Origin")); //$NON-NLS-1$ //$NON-NLS-2$
            httpResp.setHeader("Access-Control-Allow-Credentials", "true"); //$NON-NLS-1$ //$NON-NLS-2$
            httpResp.setHeader("Access-Control-Max-Age", "1800"); //$NON-NLS-1$ //$NON-NLS-2$
            httpResp.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,HEAD,DELETE"); //$NON-NLS-1$ //$NON-NLS-2$
            httpResp.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Content-Type,Accept,Origin,Authorization"); //$NON-NLS-1$ //$NON-NLS-2$
            httpResp.setHeader("Access-Control-Expose-Headers", "X-Apiman-Error"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            if (hasOriginHeader(httpReq)) {
                httpResp.setHeader("Access-Control-Allow-Origin", httpReq.getHeader("Origin")); //$NON-NLS-1$ //$NON-NLS-2$
                httpResp.setHeader("Access-Control-Allow-Credentials", "true"); //$NON-NLS-1$ //$NON-NLS-2$
                httpResp.setHeader("Access-Control-Expose-Headers", "X-Apiman-Error"); //$NON-NLS-1$ //$NON-NLS-2$
            }
            chain.doFilter(httpReq, httpResp);
        }
    }

    /**
     * Determines whether the request is a CORS preflight request.
     * @param httpReq the http servlet request
     * @return true if preflight, else false
     */
    private boolean isPreflightRequest(HttpServletRequest httpReq) {
        return isOptionsMethod(httpReq) && hasOriginHeader(httpReq);
    }

    /**
     * Returns true if it's an OPTIONS http request.
     * @param httpReq the http servlet request
     * @return true if options method, else false
     */
    private boolean isOptionsMethod(HttpServletRequest httpReq) {
        return "OPTIONS".equals(httpReq.getMethod()); //$NON-NLS-1$
    }

    /**
     * Returns true if the Origin request header is present.
     * @param httpReq the http servlet request
     * @return true if has origin header, else false
     */
    private boolean hasOriginHeader(HttpServletRequest httpReq) {
        String origin = httpReq.getHeader("Origin"); //$NON-NLS-1$
        return origin != null && origin.trim().length() > 0;
    }

    /**
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
    }

}
