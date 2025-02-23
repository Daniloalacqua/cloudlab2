package hello;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventNotFoundException extends RuntimeException {
	
	public EventNotFoundException() {
		super("Event not found");
	}
}
