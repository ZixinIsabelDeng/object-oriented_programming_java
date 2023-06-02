package Exception;

public class TooManyFiedsException extends Exception {
	//default constructor
	public TooManyFiedsException() {
		super("Error: Too many fields");
	}
	
	public TooManyFiedsException(String message) {
		super(message);
	}
	
	

}
