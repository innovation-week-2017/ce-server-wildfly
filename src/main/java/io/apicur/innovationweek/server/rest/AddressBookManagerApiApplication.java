package io.apicur.innovationweek.server.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


/**
 * The JAX-RS application Address Book Manager API. 
 */
@ApplicationPath("/")
public class AddressBookManagerApiApplication extends Application {

    private Set<Object> singletons = new HashSet<>();
    private Set<Class<?>> classes = new HashSet<>();

    /**
     * Constructor.
     */
    public AddressBookManagerApiApplication() {
        classes.add(AddressBookManagerApi.class);
        classes.add(RestExceptionMapper.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
