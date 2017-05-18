package io.apicur.innovationweek.server.models.ws;

public class NavigationMessage extends Message {
	
	private String addressName;
	private String fieldName;

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}
