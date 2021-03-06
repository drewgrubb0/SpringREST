package dg.springrest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * To be thrown when the user has requested with an incorrect input or when a header/body is missing
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException
{
    public InvalidRequestException(String e)
    {
        super(e);
    }
}
