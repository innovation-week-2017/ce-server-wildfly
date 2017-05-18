package io.apicur.innovationweek.server.models.ws;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageEncoder implements Encoder.Text<Message> {
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void init(EndpointConfig ec) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public String encode(Message msg) throws EncodeException {
		try {
			return mapper.writeValueAsString(msg);
		} catch (JsonProcessingException e) {
			throw new EncodeException(msg, e.getMessage());
		}
	}
}
