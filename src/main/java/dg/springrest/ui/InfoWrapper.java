package dg.springrest.ui;

import lombok.Getter;
import lombok.ToString;

/**
 * A wrapper class for the /info Request so that /info returns a JSON and not a String.
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
