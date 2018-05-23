# SpringREST

This is a RESTful API built using Spring Boot and a variety of tools currently utilized in the Development-Operations industry. The goal of this project was to build a Spring Boot API in Java that could keep track of advertising information in a MySQL database.

To run this project, simply download the .jar file and run it using the Java Virtual Machine (8+).

To see the project in action after running it, visit http://localhost:8080/swagger-ui.html. Actuator endpoints can be accessed via http://localhost:8090/actuator

### Tools Used
- Java 8
- Spring Boot Web
- Gradle
- Swagger Springfox
- JSON
- H2
- Actuator
- Mybatis
- JUnit
- Lombok

Below are some useful tips for development, should you attempt to fork/add to this project.

## Building a Jar
In IntelliJ:
* Download Repository
* Place in a folder called SpringAdvertise
* Import using IntelliJ
* Wait for Gradle to synchronize
* Add Lombok IntelliJ Plugin (required for Lombok Annotations)
* File->Settings->Build,Execute,Deployment->Annotation Processing->Enable Annotation Processing
* Restart  IntelliJ

Building a new jar requires gradle, java jdk, the jdk path being in %PATH%, and the jdk path being in gradle.properties

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

## Testing
After Building, testing can be done by running AdvertiserControllerTest:
* Creating API request tests is as simple as using the MockMvc to perform mock operations on the controller requests.
* Testing can also be done manually using the Swagger UI
