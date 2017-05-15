package io.apicur.innovationweek.server.rest;

import java.util.ArrayList;
import java.util.List;

import io.apicur.innovationweek.server.models.AddressBook;
import io.apicur.innovationweek.server.models.NewAddressBook;

/**
 * Implementation of the JAX-RS Address Book Manager API interface.  This impl contains all of the
 * API logic.
 */
public class AddressBookManagerApi implements IAddressBookManagerApi {
	
	public AddressBookManagerApi() {
	}

	@Override
	public List<AddressBook> listAddressBooks() {
		return new ArrayList<>();
	}

	@Override
	public AddressBook createAddressBook(NewAddressBook newBook) throws BadRequestException {
		return new AddressBook();
	}

	@Override
	public AddressBook getAddressBook(int addressBookId) throws NotFoundException {
		return new AddressBook();
	}

	@Override
	public void deleteAddressBook(int addressBookId) throws NotFoundException {
	}

}
