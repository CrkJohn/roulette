package technical.test.massiv.exception;

import org.slf4j.helpers.MessageFormatter;

/**
 * @author <a href="johnibanezt@gmail.com">John D. Ibanez</a>
 */
public class NotPossibleActionException extends Exception {

	public NotPossibleActionException(ErrorMessage errorOpenRoulette) {

		super(errorOpenRoulette.message);
	}

	public NotPossibleActionException() {

		super();
	}

	public NotPossibleActionException(String message) {

		super(message);
	}

	public NotPossibleActionException(String message, Integer identifier) {

		super(MessageFormatter.format(message, identifier).getMessage());
	}

	public NotPossibleActionException(Exception exception) {

		super(exception);
	}

	public NotPossibleActionException(String message, Exception exception) {

		super(message, exception);
	}
	
}
