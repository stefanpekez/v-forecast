package example.vforecast.exception;

public class InvalidSortOrderTypeException extends RuntimeException {

    public InvalidSortOrderTypeException() {
    }

    public InvalidSortOrderTypeException(String message) {
        super(message);
    }

    public InvalidSortOrderTypeException(String message, Throwable cause) {
        super(message, cause);
    }

}
