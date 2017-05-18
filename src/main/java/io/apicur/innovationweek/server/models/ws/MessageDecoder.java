package io.apicur.innovationweek.server.models.ws;

import java.io.IOException;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageDecoder implements Decoder.Text<Message> {

	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void init(EndpointConfig ec) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public Message decode(String value) throws DecodeException {
		try {
			return mapper.readValue(value, Message.class);
		} catch (IOException e) {
			throw new DecodeException(value, e.getMessage());
		}
	}

	@Override
	public boolean willDecode(String string) {
		return true;
	}
}
