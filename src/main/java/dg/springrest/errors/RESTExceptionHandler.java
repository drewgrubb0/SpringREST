package dg.springrest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class RESTExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(InvalidRequestException.class)
    public final ResponseEntity<Error> handleInvalidRequestException(InvalidRequestException e, WebRequest request)
    {
        Error details = new Error(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AdvertiserNotFoundException.class)
    public final ResponseEntity<Error> handleAdvertiserNotFoundException(InvalidRequestException e, WebRequest request)
    {
        Error details = new Error(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }
}
