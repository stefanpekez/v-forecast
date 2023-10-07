package example.vforecast.exception;

public class InvalidRequestedTimePeriodException extends RuntimeException {

    public InvalidRequestedTimePeriodException(String message) {
        super(message);
    }

    public InvalidRequestedTimePeriodException(String message, Throwable cause) {
        super(message, cause);
    }

}
