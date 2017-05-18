package io.apicur.innovationweek.server.data;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

public class AddressBookEditingSession {
	
	protected final String addressBookId;
	private final Map<String, Session> participants = new ConcurrentHashMap<>();
	
	/**
	 * C'tor.
	 * @param addressBookId
	 */
	public AddressBookEditingSession(String addressBookId) {
		this.addressBookId = addressBookId;
	}
	
	/**
	 * Called to join a new participant to the editing session.
	 * @param session
	 */
	public void join(Session session) {
		String username = session.getPathParameters().get("username");
		debug(session, "User %s joining session %s", username, addressBookId);
		// Returns null if session is joining for the first time...
		if (this.participants.putIfAbsent(session.getId(), session) == null) {
			this.sendToOthers("User " + username + " has joined the session!", session);
		}
	}
	
	/**
	 * Called when a participant leaves the editing session.
	 * @param session
	 */
	public void leave(Session session) {
		String username = session.getPathParameters().get("username");
		debug(session, "User %s leaving session %s", username, addressBookId);
		this.participants.remove(session.getId());
		this.sendToOthers("User " + username + " left the session.", session);
		if (this.participants.isEmpty()) {
			debug(session, "All users have left the editing session for %s - closing session", addressBookId);
			this.close();
		}
	}
	
	/**
	 * Called to send a message to all participants except the one given.
	 * @param message
	 * @param excluding
	 */
	public void sendToOthers(String message, Session excluding) {
		debug(excluding, "**FROM** Sending test message: %s", message);
		for (Session session : this.participants.values()) {
			debug(session, "Iterating through participants.");
			if (!session.getId().equals(excluding.getId())) {
				try {
					debug(session, "**TO** Sending message: %s", message);
					session.getBasicRemote().sendText(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Called to send a message to all participants.
	 * @param message
	 * @param session
	 */
	public void sendToAll(String message) {
		System.out.println("[AddressBookEditingSession] Sending message (ALL): " + message);
		for (Session session : this.participants.values()) {
			try {
				debug(session, "**TO** Sending message: %s", message);
				session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Called to close the editing session and evict all participants (if any).
	 */
	public void close() {
	}

	private void debug(Session session, String message, Object ... params) {
		System.out.println("[AddressBookEditingSession] {" + session.getId() + "} : " + String.format(message, params));
	}
}
