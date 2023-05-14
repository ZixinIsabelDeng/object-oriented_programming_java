package Exception;

public class BadPriceException extends Exception {
	public BadPriceException() {
		super("Error: bad Price");
	}
	public BadPriceException(String message) {
		super(message);
	}
}
