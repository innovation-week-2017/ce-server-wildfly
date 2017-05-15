package io.apicur.innovationweek.server.filters;

import java.security.Principal;

/**
 * A simple implementation of a principal;
 */
public class AuthPrincipal implements Principal {
    
    private String username;
    
    /**
     * Constructor.
     * 
     * @param username the username
     */
    public AuthPrincipal(String username) {
        this.username = username;
    }

    /**
     * @see java.security.Principal#getName()
     */
    @Override
    public String getName() {
        return username;
    }
}
