package src.main.java;

/**
 * Exception for unsupported or improperly formatted file types.
 */
public class UnknownFileTypeException extends RuntimeException {
    public UnknownFileTypeException(final String message) {
        super(message);
    }
}
