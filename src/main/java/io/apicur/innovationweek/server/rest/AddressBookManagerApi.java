package io.apicur.innovationweek.server.rest;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.apicur.innovationweek.server.data.IDataService;
import io.apicur.innovationweek.server.models.AddressBook;
import io.apicur.innovationweek.server.models.AddressBookWithAddresses;
import io.apicur.innovationweek.server.models.NewAddressBook;

/**
 * Implementation of the JAX-RS Address Book Manager API interface.  This impl contains all of the
 * API logic.
 */
@ApplicationScoped
public class AddressBookManagerApi implements IAddressBookManagerApi {
	
	@Inject IDataService data;
	
	public AddressBookManagerApi() {
	}

	@Override
	public Set<AddressBook> listAddressBooks() {
		return this.data.listAddressBooks();
	}

	@Override
	public AddressBook createAddressBook(NewAddressBook newBook) throws BadRequestException {
		return this.data.createAddressBook(newBook.getName());
	}

	@Override
	public AddressBookWithAddresses getAddressBook(String addressBookId) throws NotFoundException {
		AddressBookWithAddresses book = this.data.getAddressBook(addressBookId);
		if (book == null) {
			throw new NotFoundException();
		}
		return book;
	}

	@Override
	public void deleteAddressBook(String addressBookId) throws NotFoundException {
		AddressBook book = this.data.deleteAddressBook(addressBookId);
		if (book == null) {
			throw new NotFoundException();
		}
	}

}
