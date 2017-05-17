package io.apicur.innovationweek.server.models;

import java.util.Set;

public class AddressBookWithAddresses extends AddressBook {

	private Set<Address> addresses;

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	
}
