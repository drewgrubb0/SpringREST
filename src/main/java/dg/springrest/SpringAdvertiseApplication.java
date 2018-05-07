package dg.springrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
