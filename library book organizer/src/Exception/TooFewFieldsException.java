package Exception;

public class TooFewFieldsException extends Exception {
	public TooFewFieldsException() {
		super("Error: Too few fieds");
	}
	
	
	public TooFewFieldsException(String message) {
		super(message);
	}

}
