package io.apicur.innovationweek.server.filters;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * {@link Filter} to disable all caching.
 */
public class DisableCachingFilter implements Filter {


    static void disableHttpCaching(HttpServletResponse httpResponse) {
        Date now = new Date();
        httpResponse.setDateHeader("Date", now.getTime()); //$NON-NLS-1$
        httpResponse.setDateHeader("Expires", expiredSinceYesterday(now)); //$NON-NLS-1$
        httpResponse.setHeader("Pragma", "no-cache"); //$NON-NLS-1$ //$NON-NLS-2$
        httpResponse.setHeader("Cache-control", "no-cache, no-store, must-revalidate"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    private static long expiredSinceYesterday(Date now) {
        return now.getTime() - 86400000L;
    }
    
    /**
     * C'tor
     */
    public DisableCachingFilter() {
    }

    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig config) throws ServletException {
    }

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	DisableCachingFilter.disableHttpCaching((HttpServletResponse) response);
        chain.doFilter(request, response);
    }

    /**
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {
    }
}
