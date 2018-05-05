package dg.springrest.advertiser;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This class holds the Schema for the Advertiser class that will be used within the H2 Database.
 * Advertiser holds onto the Advertiser Id, Name, Contact Name, and Credit Limit.
 */
@ToString
@Getter
@Setter
public class Advertiser
{
    private Long id;
    private String name;
    private String contactName;
    private double creditLimit;

    /**
     * Default constructor in case you're more privy to the factory method.
     * setName
     * setContactName
     * setCreditLimit
     */
    public Advertiser()
    {

    }

    /**
     * Constructor that, given all of the advertiser information,
     * will create a new Advertiser with that information.
     */
    public Advertiser(Long id, String name, String contactName, double creditLimit)
    {
        this.id = id;
        this.name = name;
        this.contactName = contactName;
        this.creditLimit = creditLimit;
    }
}
