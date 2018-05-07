package dg.springrest.ui;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class integrates the Swagger Springfox User Interface with this REST API.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(info())
                .select()
                .apis(RequestHandlerSelectors.basePackage("dg.springrest"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo info()
    {
        return new ApiInfoBuilder()
                .title("Drew Grubb Advertiser API")
                .description("A RESTful API for handling advertisers")
                .version("0.1.6")
                .contact(new Contact("Drew Grubb", "https://linkedin.com/in/drew-grubb", ""))
                .license("Github Repository")
                .licenseUrl("https://github.com/drewgrubb0/SpringREST")
                .build();
    }
}
