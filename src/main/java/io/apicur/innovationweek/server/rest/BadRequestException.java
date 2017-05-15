package io.apicur.innovationweek.server.rest;


public class BadRequestException extends AbstractRestException {

    private static final long serialVersionUID = 4663705773331595639L;

    /**
     * Constructor.
     * @param message the exception message
     */
    public BadRequestException(String message) {
        super(message);
    }

}
