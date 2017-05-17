package io.apicur.innovationweek.server.data;

import java.util.Set;

import io.apicur.innovationweek.server.models.AddressBook;
import io.apicur.innovationweek.server.models.AddressBookWithAddresses;

public interface IDataService {

	public Set<AddressBook> listAddressBooks();

	public AddressBook createAddressBook(String name);

	public AddressBookWithAddresses getAddressBook(String addressBookId);

	public AddressBook deleteAddressBook(String addressBookId);

}
