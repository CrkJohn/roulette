package technical.test.massiv.model.utils;

public enum ErrorMessage {

	USER_NO_NULL("The user Id's can not null"),

	ERROR_RANGE_MONEY("The range money is between 1 to 10000 USD");

	String message;

	ErrorMessage(String message) {

		this.message = message;
	}

	public String getMessage() {

		return message;
	}
}
