package dg.springrest.advertiser;

import dg.springrest.errors.AdvertiserNotFoundException;
import dg.springrest.errors.InvalidRequestException;
import dg.springrest.ui.InfoWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class contains all of the RESTful request methods utilized by the Advertiser group.
 */
@RestController
@RequestMapping("/api/advertiser")
public class AdvertiserController
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //Used to synchronously generate new ID's when adding to the table
    private final AtomicLong numAccesses = new AtomicLong();

    @Autowired
    AdvertiserRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public Advertiser getAdvertiser(@RequestHeader(value = "id") String id)
    {
        if(!isLong(id))
            throw new InvalidRequestException("ID must be of type long");

        Long idToFetch = Long.parseLong(id);
        Advertiser temp = repository.findById(idToFetch);

        if(temp == null)
            throw new AdvertiserNotFoundException("An advertiser with that id does not exist");

        return temp;
    }

    @RequestMapping(value = "/credit", method = RequestMethod.GET)
    public InfoWrapper getCreditValidation(@RequestHeader(value = "id") String id,
                                           @RequestHeader(value="transactionCost") String transactionCost)
    {
        if(!isLong(id))
            throw new InvalidRequestException("ID must be of type long");
        if(!isDouble(transactionCost) || Double.parseDouble(transactionCost) <= 0)
            throw new InvalidRequestException("Transaction cost must be a double > 0");

        Long idToFetch = Long.parseLong(id);
        Advertiser temp = repository.findById(idToFetch);

        if(temp == null)
            throw new AdvertiserNotFoundException("An advertiser with id=" + idToFetch + " does not exist");

        if(Double.parseDouble(transactionCost) > temp.getCreditLimit())
           throw new InvalidRequestException("Sorry! You cannot afford a transaction of that size!");

        return new InfoWrapper("You can afford a transaction of that size");
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Advertiser> getAll()
    {
        return repository.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public InfoWrapper addAdvertiser(String name, String contactName, String creditLimit, HttpServletResponse response)
    {
        if(name == null || contactName == null || creditLimit == null)
            throw new InvalidRequestException("Please enter name, contactname, and creditlimit fields");
        if(!isDouble(creditLimit))
            throw new InvalidRequestException("creditlimit must be a real number > 0");

        double credit = Double.parseDouble(creditLimit);

        if(credit <= 0)
            throw new InvalidRequestException("creditlimit must be a real number > 0");

        repository.add(new Advertiser(numAccesses.incrementAndGet(), name, contactName, credit));

        response.setStatus(HttpServletResponse.SC_CREATED);
        return new InfoWrapper("POST successful!");
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Advertiser updateAdvertiser(String id, String name, String contactName, String creditLimit)
    {
        if(id == null)
            throw new InvalidRequestException("Id must be entered");
        if(!isLong(id))
            throw new InvalidRequestException("ID must be of type long");
        if(name == null && contactName == null && creditLimit == null)
            throw new InvalidRequestException("need to input new name, contactName, or creditLimit value");
        if(creditLimit != null && (!isDouble(creditLimit) || Double.parseDouble(creditLimit) <= 0))
            throw new InvalidRequestException("Transaction cost must be a double > 0");

        Advertiser temp = repository.findById(Long.parseLong(id));

        if(temp == null)
            throw new AdvertiserNotFoundException("Advertiser with that ID does not exist");

        if(name != null)
            temp.setName(name);
        if(contactName != null)
            temp.setContactName(contactName);
        if(creditLimit != null)
            temp.setCreditLimit(Double.parseDouble(creditLimit));

        repository.update(temp);
        return temp;
    }

    @RequestMapping(value = "/deduct", method = RequestMethod.POST)
    public Advertiser deductCredits(String id, String creditsToDeduct)
    {
        if(id == null || creditsToDeduct == null)
            throw new InvalidRequestException("id and creditsToDeduct must be entered");
        if(!isLong(id))
            throw new InvalidRequestException("id must be of type long");
        if(!isDouble(creditsToDeduct) || Double.parseDouble(creditsToDeduct) <= 0)
            throw new InvalidRequestException("creditsToDeduct must be a real number >0");

        Advertiser temp = repository.findById(Long.parseLong(id));

        if(temp == null)
            throw new AdvertiserNotFoundException("Advertiser with that ID does not exist");

        if(temp.getCreditLimit() - Double.parseDouble(creditsToDeduct) < 0)
            temp.setCreditLimit(0);
        else
            temp.setCreditLimit(temp.getCreditLimit() - Double.parseDouble(creditsToDeduct));

        repository.update(temp);
        return temp;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public InfoWrapper deleteAdvertiser(String id, HttpServletResponse response)
    {
        if(!isLong(id))
            throw new InvalidRequestException("Id must be of type Long");

        int success = repository.deleteById(Long.parseLong(id));
        if(success == 0)
            throw new AdvertiserNotFoundException("Advertiser does not exist in the database");

        return new InfoWrapper("Advertiser has been deleted");
    }


    private boolean isDouble(String number)
    {
        try{
            Double.parseDouble(number);
        } catch(Exception e) {
            return false;
        }
        return true;
    }

    private boolean isLong(String number)
    {
        try{
            Long.parseLong(number);
        } catch(Exception e) {
            return false;
        }
        return true;
    }
}
