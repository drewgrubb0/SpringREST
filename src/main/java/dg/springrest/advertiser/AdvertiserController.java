package dg.springrest.advertiser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * This class contains all of the request methods utilized by the Advertiser group.
 */
@RestController
public class AdvertiserController
{
    //Used to asynchronously generate new ID's when adding to the table
    private final AtomicLong numAccesses = new AtomicLong();

    @Autowired
    AdvertiserRepository repository;

    //TODO GET /credit validation


    //TODO POST new advertiser


    //TODO PUT update advertiser


    //TODO DELETE advertiser

    //TODO Swagger UI (probably here - look more into swagger ui)
}
