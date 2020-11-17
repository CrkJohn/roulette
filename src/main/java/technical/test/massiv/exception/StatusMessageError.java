package technical.test.massiv.exception;

import technical.test.massiv.model.utils.StateRequest;

/**
 * @author <a href="johnibanezt@gmail.com">John D. Ibanez</a>
 */
public class StatusMessageError {

	StateRequest stateRequest;

	String errorMessage;

	public StatusMessageError(StateRequest stateRequest, String errorMessage) {

		this.stateRequest = stateRequest;
		this.errorMessage = errorMessage;
	}

	public StatusMessageError() {


	}

	public StateRequest getStateRequest() {

		return stateRequest;
	}

	public void setStateRequest(StateRequest stateRequest) {

		this.stateRequest = stateRequest;
	}

	public String getErrorMessage() {

		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {

		this.errorMessage = errorMessage;
	}
}
