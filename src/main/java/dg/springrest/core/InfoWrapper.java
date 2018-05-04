package dg.springrest.core;

/**
 * A wrapper class for the /info Request so that /info returns a JSON and not a String.
 */
public class InfoWrapper
{
    private final String content;

    public InfoWrapper(String content)
    {
        this.content = content;
    }

    //Getters/Setters

    public String getContent()
    {
        return content;
    }
}
