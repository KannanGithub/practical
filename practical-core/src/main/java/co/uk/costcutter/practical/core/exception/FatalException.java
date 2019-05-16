package co.uk.costcutter.practical.core.exception;

public class FatalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FatalException(String message) {
        super(message);
    }

}