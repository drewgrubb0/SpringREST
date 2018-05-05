package dg.springrest.ui;

import lombok.Getter;
import lombok.ToString;

/**
 * A wrapper class for any request that requires text to be sent (such as errors or /info) so they return a JSON and not a String.
 */
@ToString
public class InfoWrapper
{
    @Getter private final String content;

    public InfoWrapper(String content)
    {
        this.content = content;
    }
}
