package io.apicur.innovationweek.server.rest;


/**
 * Base class for all errors thrown by the API.
 */
public abstract class AbstractRestException extends Exception {
    
	private static final long serialVersionUID = -5028565388816852438L;

	/**
     * Constructor.
     */
    public AbstractRestException() {
    }
    
    /**
     * Constructor.
     * @param message the exception message
     */
    public AbstractRestException(String message) {
        super(message);
    }
    
    /**
     * Constructor.
     * @param cause the exception cause
     */
    public AbstractRestException(Throwable cause) {
        super(cause);
    }

}
