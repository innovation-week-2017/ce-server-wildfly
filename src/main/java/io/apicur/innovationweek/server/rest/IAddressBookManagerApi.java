
package io.apicur.innovationweek.server.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.apicur.innovationweek.server.models.AddressBook;
import io.apicur.innovationweek.server.models.NewAddressBook;

/**
 * The interface for the Address Book Manager API. This API consists of the following
 * operations:
 * 
 *   /addressBooks 
 *     GET: Returns a list of address books.
 *     POST: Creates a new address book.
 *   /addressBooks/{addressBookId} 
 *     GET: Return a single address book by ID.
 *     DELETE: Delete a single address book by ID
 * 
 */
@Path("addressBooks")
public interface IAddressBookManagerApi {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AddressBook> listAddressBooks();
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AddressBook createAddressBook(NewAddressBook newBook) throws BadRequestException;

	@GET
	@Path("{addressBookId}")
	@Produces(MediaType.APPLICATION_JSON)
	public AddressBook getAddressBook(@PathParam("addressBookId") int addressBookId) throws NotFoundException;

	@DELETE
	@Path("{addressBookId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteAddressBook(@PathParam("addressBookId") int addressBookId) throws NotFoundException;

}
