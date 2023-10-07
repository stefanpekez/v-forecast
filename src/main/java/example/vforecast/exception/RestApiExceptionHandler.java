package example.vforecast.exception;

import example.vforecast.dto.exception.RestApiExceptionGetDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(InvalidRequestedTimePeriodException.class)
    protected RestApiExceptionGetDto handleInvalidRequestedTimePeriod(RuntimeException e) {
        return new RestApiExceptionGetDto(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected RestApiExceptionGetDto handleResourceNotFoundException(RuntimeException e) {
        return new RestApiExceptionGetDto(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(DateTimeParseException.class)
    protected RestApiExceptionGetDto handleDateTimeParseException(DateTimeParseException e) {
        return new RestApiExceptionGetDto(HttpStatus.BAD_REQUEST, "Please use the yyyy-MM-dd'T'HH:mm format for dates");
    }

    @ExceptionHandler(InvalidSortOrderTypeException.class)
    protected RestApiExceptionGetDto handleInvalidSortOrderTypeException(InvalidSortOrderTypeException e) {
        return new RestApiExceptionGetDto(HttpStatus.BAD_REQUEST, "Invalid sort order type, please use 'asc' for ascending or 'desc' for descending order");
    }

}
