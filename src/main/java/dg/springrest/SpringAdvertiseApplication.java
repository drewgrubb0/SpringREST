package dg.springrest;

import dg.springrest.ui.InfoWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring application kernel
 * This class contains the initial main method that starts the Spring application.
 * This class also contains
 */
@Configuration
@SpringBootApplication
@EnableSwagger2
@RestController
public class SpringAdvertiseApplication
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args)
    {
		SpringApplication.run(SpringAdvertiseApplication.class, args);
	}

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public InfoWrapper info()
    {
        return new InfoWrapper("Welcome to the RESTful API for Advertisers. " +
                "This application was created by Drew Grubb to keep track of Advertisers using a variety of resources." +
                " Some of these resources include Spring Boot, Swagger Springfox, H2, Actuator, MyBatis, " +
                "JUnit, Lombok, and Spring Boot Web. All actuator commands are on port 8090.");
    }
}
