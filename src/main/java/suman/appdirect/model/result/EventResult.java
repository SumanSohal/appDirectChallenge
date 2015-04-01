// 

package suman.appdirect.model.result;

import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

// --------------------------------------------------------------------------------------------------------------------------------
@XmlRootElement(name = "result")
public class EventResult {
	private Boolean success;
	private String errorCode;
	private String message;
	private String accountIdentifier; // only applicable for 'create' events

	// --------------------------------------------------------------------------------------------------------------------------------
	public static EventResult error(EventResultException exception) {
		return error(exception.getError(), exception.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public static EventResult errorLogged(Error error, String message,
			Throwable exception) {
		// attach UUID to simplify tracking of logged exceptions
		UUID trackingId = UUID.randomUUID();
		message = String.format("%s [Tracking Id: %s]", message, trackingId);

		return error(error, message);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public static EventResult error(Error error, String message) {
		EventResult eventResult = new EventResult();
		eventResult.setSuccess(Boolean.FALSE);
		eventResult.setErrorCode(error.name());
		eventResult.setMessage(message);

		return eventResult;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public static EventResult success() {
		return success(null);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public static EventResult success(String accountIdentifier) {
		EventResult eventResult = new EventResult();
		eventResult.setSuccess(Boolean.TRUE);
		eventResult.setMessage("Operation completed successfully.");
		eventResult.setAccountIdentifier(accountIdentifier);

		return eventResult;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Boolean getSuccess() {
		return success;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getErrorCode() {
		return errorCode;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getMessage() {
		return message;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setMessage(String message) {
		this.message = message;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
}
