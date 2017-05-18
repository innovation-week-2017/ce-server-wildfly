package io.apicur.innovationweek.server.models.ws;

import io.apicur.innovationweek.server.models.Address;

public class UpdateMessage extends Message {
	
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
