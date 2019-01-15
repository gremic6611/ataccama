package mgm.devtask.dbview.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConnectionServiceDetailMissingException extends RuntimeException {

	public ConnectionServiceDetailMissingException(String message) {
		super(message);
	}
	
}
