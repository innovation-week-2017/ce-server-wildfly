package io.apicur.innovationweek.server.ws;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/editAddressBook/{addressBookId}")
public class EditAddressBookEndpoint {

	@OnMessage
	public String onMessage(String message) {
		System.out.println("MESSAGE: " + message);
		return message;
	}

	@OnOpen
	public void myOnOpen(Session session) {
		System.out.println("WebSocket opened: " + session.getId());
	}

	@OnClose
	public void myOnClose(CloseReason reason) {
		System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
	}
}
