package painter.exception;

/**
 * Represents an application specific exception for the Painter program.
 */
public class PainterException extends Exception {
    /**
     * Constructs a PainterException with the specified error message.
     *
     * @param description A description of the error.
     */
    public PainterException(String description) {
        super(description);
    }
}
