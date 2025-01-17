package technical.test.massiv.exception;

import org.slf4j.helpers.MessageFormatter;

/**
 * @author <a href="johnibanezt@gmail.com">John D. Ibanez</a>
 */
public class NotFoundRouletteException extends Exception {

	public NotFoundRouletteException(String message, String identifier) {

		super(MessageFormatter.format(message, identifier).getMessage());
	}

	public NotFoundRouletteException(ErrorMessage notFoundRoulette, String idRoulette) {

		this(notFoundRoulette.message, idRoulette);
	}
}