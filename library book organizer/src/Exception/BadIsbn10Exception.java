package Exception;

public class BadIsbn10Exception extends Exception {
	public BadIsbn10Exception() {
		super("Error: bad ISBN number");
	}
	public BadIsbn10Exception(String message) {
		super(message);
	}
	
	
	
}
