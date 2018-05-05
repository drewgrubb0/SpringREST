package dg.springrest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdvertiserNotFoundException extends RuntimeException
{
    public AdvertiserNotFoundException(String e)
    {
        super(e);
    }
}
