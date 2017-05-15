package io.apicur.innovationweek.server.models;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {

	private String id;
	private List<Address> Addresses = new ArrayList<>();

	public List<Address> getAddresss() {
		return Addresses;
	}

	public void setAddresss(List<Address> Addresss) {
		this.Addresses = Addresss;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
