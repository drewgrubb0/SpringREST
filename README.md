# SpringREST
A RESTful API built in Java ran by Spring boot

###Building
Using IntelliJ:
* Download Repository
* Place in a folder called SpringAdvertise
* Import using IntelliJ
* Wait for Gradle to synchronize
* Add Lombok IntelliJ Plugin (required for Lombok Annotations)
* File->Settings->Build,Execute,Deployment->Annotation Processing->Enable Annotation Processing
* Restart  IntelliJ

###Developing
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

###Running


###Testing
After Building, testing can be done by running AdvertiserControllerTest.

Creating API request tests is as simple as using the MockMvc to perform mock operations on the controller requests.

Testing can also be done manually using the Swagger UI