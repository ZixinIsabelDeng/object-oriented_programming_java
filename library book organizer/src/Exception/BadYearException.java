package Exception;

public class BadYearException extends Exception {
	public BadYearException() {
		super("Error: bad Year");
	}
	public BadYearException(String message) {
		super(message);
	}
}
