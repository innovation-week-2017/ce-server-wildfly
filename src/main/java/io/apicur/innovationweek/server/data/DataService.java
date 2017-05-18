package io.apicur.innovationweek.server.data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.enterprise.context.ApplicationScoped;

import io.apicur.innovationweek.server.models.Address;
import io.apicur.innovationweek.server.models.AddressBook;
import io.apicur.innovationweek.server.models.AddressBookWithAddresses;
import io.apicur.innovationweek.server.util.ModelUtils;

@ApplicationScoped
public class DataService implements IDataService {
	
	private static Set<AddressBook> books = new TreeSet<>(new Comparator<AddressBook>() {
		@Override
		public int compare(AddressBook book1, AddressBook book2) {
			return book1.getName().compareToIgnoreCase(book2.getName());
		}
	});
	private static Map<String, Set<Address>> bookData = new HashMap<>();
	
	static {
		System.out.println("==============================");
		System.out.println("Initializing Address Book Data");
		System.out.println("==============================");
		AddressBook book = new AddressBook();
		book.setName("Personal");
		book.setId("personal");
		books.add(book);
		
		Set<Address> addresses = createEmptyBookData();
		
		Address address = new Address();
		address.setName("Eric Wittmann");
		address.setAddress1("14 Lake Rd");
		address.setCountry("USA");
		address.setCity("Newtown");
		address.setState("CT");
		address.setZip("06470");
		addresses.add(address);
		
		address = new Address();
		address.setName("Fred Johnson");
		address.setAddress1("22 Ziptie Ln");
		address.setCountry("USA");
		address.setCity("Erie");
		address.setState("PA");
		address.setZip("16505");
		addresses.add(address);
		
		address = new Address();
		address.setName("Dan Finkelstein");
		address.setAddress1("1736 Garden Blvd");
		address.setCountry("USA");
		address.setCity("Hatterstown");
		address.setState("NJ");
		address.setZip("23727");
		addresses.add(address);
		
		bookData.put(book.getId(), addresses);
		
		book = new AddressBook();
		book.setName("Work");
		book.setId("work");
		books.add(book);
	}

	private static TreeSet<Address> createEmptyBookData() {
		return new TreeSet<>(new Comparator<Address>() {
			@Override
			public int compare(Address o1, Address o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
	}
	
	public DataService() {
	}

	@Override
	public synchronized Set<AddressBook> listAddressBooks() {
		return books;
	}

	@Override
	public synchronized AddressBook createAddressBook(String name) {
		String id = ModelUtils.idFromName(name);
		if (this.getAddressBook(id) != null) {
			return null;
		}
		AddressBook book = new AddressBook();
		book.setId(id);
		book.setName(name);
		books.add(book);
		bookData.put(id, createEmptyBookData());
		return book;
	}

	@Override
	public synchronized AddressBookWithAddresses getAddressBook(String addressBookId) {
		Iterator<AddressBook> iterator = books.iterator();
		while (iterator.hasNext()) {
			AddressBook book = iterator.next();
			if (book.getId().equals(addressBookId)) {
				AddressBookWithAddresses rval = new AddressBookWithAddresses();
				rval.setId(book.getId());
				rval.setName(book.getName());
				rval.setAddresses(bookData.get(book.getId()));
				return rval;
			}
		}
		return null;
	}

	@Override
	public synchronized AddressBook deleteAddressBook(String addressBookId) {
		AddressBook book = getAddressBook(addressBookId);
		if (book != null) {
			books.remove(book);
		}
		return book;
	}

	@Override
	public AddressBookEditingSession editAddressBook(String addressBookId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
