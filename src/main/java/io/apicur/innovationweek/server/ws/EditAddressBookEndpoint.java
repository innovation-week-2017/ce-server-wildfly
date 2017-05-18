package io.apicur.innovationweek.server.ws;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import io.apicur.innovationweek.server.data.AddressBookEditingSession;
import io.apicur.innovationweek.server.data.IDataService;

@ServerEndpoint("/editAddressBook/{addressBookId}/{username}")
@ApplicationScoped
public class EditAddressBookEndpoint {

	@Inject IDataService data;
	
	private static Map<String, AddressBookEditingSession> sessions = new ConcurrentHashMap<>();
	
	@OnOpen
	public void onOpenSession(Session session) {
		String bookId = session.getPathParameters().get("addressBookId");
		String username = session.getPathParameters().get("username");

		System.out.println("WebSocket opened: " + session.getId());
		System.out.println("\tuser:" + username);
		System.out.println("\turi:" + session.getRequestURI());
		System.out.println("\taddressBookId:" + bookId);
		
		AddressBookEditingSession newSession = new AddressBookEditingSession(bookId);
		AddressBookEditingSession esession = sessions.putIfAbsent(bookId, newSession);
		if (esession == null) { esession = newSession; }
		
		esession.join(session);
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		String bookId = session.getPathParameters().get("addressBookId");
//		String username = session.getPathParameters().get("username");

		AddressBookEditingSession esession = sessions.get(bookId);
		if (esession != null) {
			esession.sendToOthers(message, session);
		}
	}

	@OnClose
	public void onCloseSession(Session session, CloseReason reason) {
		String bookId = session.getPathParameters().get("addressBookId");
		String username = session.getPathParameters().get("username");

		System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
		System.out.println("\tuser:" + username);
		System.out.println("\turi:" + session.getRequestURI());
		System.out.println("\taddressBookId:" + bookId);
		
		AddressBookEditingSession esession = sessions.get(bookId);
		if (esession != null) {
			esession.leave(session);
		}
	}
}
