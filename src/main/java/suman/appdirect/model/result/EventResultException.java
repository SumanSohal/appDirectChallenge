// 

package suman.appdirect.model.result;

//--------------------------------------------------------------------------------------------------------------------------------
public class EventResultException extends RuntimeException {
	private static final long serialVersionUID = -1180793868788523116L;

	private Error error;
	private String message;

	// --------------------------------------------------------------------------------------------------------------------------------
	public EventResultException(Error error, String message) {
		setError(error);
		setMessage(message);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Error getError() {
		return error;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setError(Error error) {
		this.error = error;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getMessage() {
		return message;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setMessage(String message) {
		this.message = message;
	}
}
