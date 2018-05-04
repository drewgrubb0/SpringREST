package dg.springrest.core;

import dg.springrest.advertiser.AdvertiserRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring application kernel
 * This class contains the initial main method that starts the Spring application.
 * This class also contains
 */
@SpringBootApplication
@ComponentScan({"dg.springrest.advertiser"})
@MapperScan({"dg.springrest.advertiser"})
@RestController
public class SpringAdvertiseApplication
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdvertiserRepository repository;

	public static void main(String[] args)
    {
		SpringApplication.run(SpringAdvertiseApplication.class, args);
	}

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public InfoWrapper info()
    {
        //maybe Collections.singletonMap("tag", "string") ?
        return new InfoWrapper("TODO: info :]");
    }

	@Bean
    CommandLineRunner init()
    {
        return(event) ->
        {

        };
    }
}
