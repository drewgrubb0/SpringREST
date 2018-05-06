# SpringREST
A RESTful API built in Java ran by Spring boot

## Building
Using IntelliJ:
* Download Repository
* Place in a folder called SpringAdvertise
* Import using IntelliJ
* Wait for Gradle to synchronize
* Add Lombok IntelliJ Plugin (required for Lombok Annotations)
* File->Settings->Build,Execute,Deployment->Annotation Processing->Enable Annotation Processing
* Restart  IntelliJ

## Developing
Creating API to Repo Requests:
*  Create a request method in the AdvertiserController class
* Call any Repo Requests with the corresponding Repository Method
* Handle errors by throwing a created exception

Creating Repo to Database Requests:
* In the repository interface, H2 requests can be created by annotating a command and recieving an object with those corresponding object names.

Creating new Repository:
* Create Repository Mapper
* Create Controller
* Create Schema to place in the repository

Handling Errors:
* Create exceptions with an error code
* Add exception to the ExceptionHandler class
* Exception can now be thrown cleanly

## Running
* I was unable to create an executable jar with gradle/spring boot dependencies due to a lack of a unix machine, so the only way to run this application is through an IDE or using unix.

## Testing
After Building, testing can be done by running AdvertiserControllerTest:
* Creating API request tests is as simple as using the MockMvc to perform mock operations on the controller requests.
* Testing can also be done manually using the Swagger UI

# Problems/Conclusion
Some things I was unable to complete:
* Creating an executable jar
* There's a problem with the applications where extra endpoints ("/api/advertiser/credit") do not work with the swagger ui. These endpoints work in an API Dev Environment like PostMan or even straight from the browser, but for some reason return "TypeError, Failed to Fetch" in the swagger UI. All attempts to remedy that have failed.

Some things I would like to do in the future:
* Add flyway integration so it is not an in-memory database (would require changing the way I assign advertiser ID's)
* Fixing the problems I was unable to solve when my knowledge of Spring Boot / REST has increased
* Adding more endpoints or repositories to keep track of more than just advertisers

This project gave me experience in a lot of things I had not utilized before. Gradle, Spring, Spring boot, Swagger, H2, Lombok, IntelliJ, Actuator, Spring-MVC and a variety of gradle plugins. In the future I hope to work more with these plugins and applications, and continuously grow my knowledge in Software Engineering.
