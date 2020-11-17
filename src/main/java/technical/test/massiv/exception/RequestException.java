package technical.test.massiv.exception;

import org.slf4j.helpers.MessageFormatter;

/**
 * This class represents all the exceptions that can be generated
 * 	respect the parameters of the request
 *
 * @author <a href="johnibanezt@gmail.com">John D. Ibanez</a>
 */
public class RequestException extends Exception {

	public RequestException() {

		super();
	}

	public RequestException(String message) {

		super(message);
	}

	public RequestException(String message, Integer identifier) {

		super(MessageFormatter.format(message, identifier).getMessage());
	}

	public RequestException(ErrorMessage exception) {

		super(exception.getMessage());
	}

	public RequestException(String message, Exception exception) {

		super(message, exception);
	}

}