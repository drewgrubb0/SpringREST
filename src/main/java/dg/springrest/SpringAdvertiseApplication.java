package dg.springrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring application kernel
 * This class contains the initial main method that starts the Spring application.
 */
@SpringBootApplication
public class SpringAdvertiseApplication
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args)
    {
		SpringApplication.run(SpringAdvertiseApplication.class, args);
	}
}
