package dg.springrest.advertiser;

/**
 * This class holds the Schema for the Advertiser class that will be used within the H2 Database.
 * Advertiser holds onto the Advertiser Name, Contact Name, and Credit Limit.
 */
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
    public Advertiser(String name, String contactName, double creditLimit)
    {
        this.name = name;
        this.contactName = contactName;
        this.creditLimit = creditLimit;
    }

    //Getters/Setters

    public Long getId()
    {
        return id;
    }

    public void setId(Long tableID)
    {
        this.id = tableID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getContactName()
    {
        return contactName;
    }

    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    public double getCreditLimit()
    {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit)
    {
        this.creditLimit = creditLimit;
    }

    @Override
    public String toString()
    {
        return String.format("Advertiser [Id: %s, Name: %s, Contact Name: %s, Credit Limit: %s]", id, name, contactName, creditLimit);
    }
}
