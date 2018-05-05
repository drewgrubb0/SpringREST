package dg.springrest.errors;

import java.util.Date;

public class Error
{
    private Date timestamp;
    private String responseMessage;
    private String details;

    public Error(Date timestamp, String responseMessage, String details)
    {
        this.timestamp = timestamp;
        this.responseMessage = responseMessage;
        this.details = details;
    }
}
