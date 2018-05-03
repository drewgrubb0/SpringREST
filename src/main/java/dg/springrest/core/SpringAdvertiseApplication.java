package dg.springrest.core;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Spring application kernel & main method
 */
@SpringBootApplication
public class SpringAdvertiseApplication
{
	public static void main(String[] args)
    {
		SpringApplication.run(SpringAdvertiseApplication.class, args);
	}

	@Bean
    CommandLineRunner init()
    {
        return(event) ->
        {
            System.out.println("Hello Spring!");
        };
    }
}
