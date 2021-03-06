package dg.springrest.advertiser;

import dg.springrest.errors.AdvertiserNotFoundException;
import dg.springrest.errors.InvalidRequestException;
import dg.springrest.ui.InfoWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class contains all of the RESTful request methods utilized by the Advertiser group.
 */
@RestController
@RequestMapping("/api/advertising")
public class AdvertiserController
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //Used to generate new ID's when adding to the table
    Random rand = new Random();

    @Autowired
    AdvertiserRepository repository;

    @GetMapping
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

    @GetMapping(value = "/credit")
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

    @GetMapping(value = "/all")
    public List<Advertiser> getAll()
    {
        return repository.getAll();
    }

    @PostMapping
    public Advertiser addAdvertiser(String name, String contactName, String creditLimit, HttpServletResponse response)
    {
        if(name == null || contactName == null || creditLimit == null)
            throw new InvalidRequestException("Please enter name, contactname, and creditlimit fields");
        if(!isDouble(creditLimit))
            throw new InvalidRequestException("creditlimit must be a real number > 0");

        double credit = Double.parseDouble(creditLimit);

        if(credit <= 0)
            throw new InvalidRequestException("creditlimit must be a real number > 0");

        long newID = (long) rand.nextInt(900000) + 100000;

        //Possible problem: More than 899,999 advertisers
        //Solution: Pad beginning of existing ID's with zeroes to increase space
        while(repository.findById(newID) != null)
        {
            newID = (long) rand.nextInt(900000) + 100000;
        }

        Advertiser temp = new Advertiser(newID, name, contactName, credit);
        repository.add(temp);

        response.setStatus(HttpServletResponse.SC_CREATED);
        return temp;
    }

    @PutMapping
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

    @PostMapping(value = "/deduct")
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

    @DeleteMapping
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
