package technical.test.massiv.exception;

/**
 * ErrorMessage is used to represent
 * the possible errors that can happen in the application
 *
 * @author <a href="johnibanezt@gmail.com">John D. Ibanez</a>
 */
public enum ErrorMessage {

	ERROR_RANGE_MONEY("The range money is between 1 to 10000 USD"),

	ERROR_POSITION_BET("The range position is between 0 to 36"),

	ERROR_OPEN_ROULETTE("the roulette is already opened"),

	ERROR_CLOSE_ROULETTE("the roulette is not  opened"),

	NOT_FOUND_ROULETTE("the roulette with id [{}] does not exist"),

	NOT_POSSIBLE_BET("It does not possible bet, the roulette is closed");
	String message;

	ErrorMessage(String message) {

		this.message = message;
	}

	public String getMessage() {

		return message;
	}
}
