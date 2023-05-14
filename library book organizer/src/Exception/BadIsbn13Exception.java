package Exception;

public class BadIsbn13Exception extends Exception {
	public BadIsbn13Exception() {
		super("Error: bad ISBN13 number");
	}
	public BadIsbn13Exception(String message) {
		super(message);
	}
	
	
	
}