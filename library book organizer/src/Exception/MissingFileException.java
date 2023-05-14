package Exception;

public class MissingFileException extends Exception {
	public MissingFileException() {
		super("Error: missing file");
	}
	
	public MissingFileException(String message) {
		super(message);
	}
}
