package Exception;

public class UnknownGenreException extends Exception {
	public UnknownGenreException() {
		super("Error: Unknown Genre");
	}
	
	public UnknownGenreException(String message) {
		super(message);
	}
	
	
	
}
