package io.apicur.innovationweek.server.models.ws;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
@JsonSubTypes({
	@Type(value = JoinMessage.class, name = "join"),
	@Type(value = LeaveMessage.class, name = "leave"),
	@Type(value = CreateMessage.class, name = "create"),
	@Type(value = DeleteMessage.class, name = "delete"),
	@Type(value = UpdateMessage.class, name = "update"),
	@Type(value = NavigationMessage.class, name = "nav")})
public abstract class Message {
	
	public Message() {
	}
	
	public Message(String from) {
		setFrom(from);
	}

	private String from;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
