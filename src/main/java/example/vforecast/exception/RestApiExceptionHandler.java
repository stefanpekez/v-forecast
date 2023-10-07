package example.vforecast.exception;

import example.vforecast.dto.exception.RestApiExceptionGetDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}
