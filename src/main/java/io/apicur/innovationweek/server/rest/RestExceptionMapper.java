package io.apicur.innovationweek.server.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Provider that maps an error.
 */
@Provider
public class RestExceptionMapper implements ExceptionMapper<AbstractRestException> {

    /**
     * Constructor.
     */
    public RestExceptionMapper() {
    }

    /**
     * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
     */
    @Override
    public Response toResponse(AbstractRestException data) {
    	if (data instanceof NotFoundException) {
            return Response.status(404).build();
    	}
    	if (data instanceof BadRequestException) {
    		return Response.status(400).build();
    	}
		return Response.status(500).build();
    }

}
