package technical.test.massiv.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;
import technical.test.massiv.model.utils.StateRequest;

/**
 * This class intercepts the message and defines the status and message of the http response
 *
 * @author <a href="john.ibanez@payulatam.com">John D. Ibanez</a>
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlerRoulette {

	@ExceptionHandler({
			NotFoundRouletteException.class,
			NotPossibleActionException.class,
			RequestException.class
	})
	@Nullable
	public final ResponseEntity<?> handleException(Exception ex, WebRequest request) {

		HttpHeaders headers = new HttpHeaders();
		log.info("Handling {} due to {} ", ex.getClass().getSimpleName(), ex.getMessage());
		HttpStatus status;
		String messageError = ex.getMessage();
		StatusMessageError statusMessageError = new StatusMessageError();
		statusMessageError.setStateRequest(StateRequest.REJECTED);
		statusMessageError.setErrorMessage(messageError);
		return createHandleException(ex, request, headers, statusMessageError);
	}

	private ResponseEntity<?> createHandleException(Exception ex, WebRequest request, HttpHeaders headers,
													StatusMessageError statusMessageError) {

		HttpStatus status;
		if (ex instanceof NotFoundRouletteException) {
			status = HttpStatus.NOT_FOUND;
			NotFoundRouletteException notFoundRouletteException = (NotFoundRouletteException) ex;
			return handleInternalException(notFoundRouletteException, statusMessageError, headers, status, request);
		}else if (ex instanceof NotPossibleActionException) {
			status = HttpStatus.NOT_ACCEPTABLE;
			NotPossibleActionException notPossibleActionException = (NotPossibleActionException) ex;
			return handleInternalException(notPossibleActionException, statusMessageError, headers, status, request);
		}else if (ex instanceof RequestException) {

			status = HttpStatus.BAD_REQUEST;
			RequestException notPossibleActionException = (RequestException) ex;
			return handleInternalException(notPossibleActionException, statusMessageError, headers, status, request);
		} else {
			if (log.isWarnEnabled()) {
				log.warn("Unknown exception type: {}", ex.getClass().getName());
			}
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return handleInternalException(ex, statusMessageError, headers, status, request);
		}
	}

	private ResponseEntity<?> handleInternalException(Exception ex, @Nullable StatusMessageError body, HttpHeaders headers,
													  HttpStatus status, WebRequest request) {

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}

		return new ResponseEntity<>(body, headers, status);
	}

}
