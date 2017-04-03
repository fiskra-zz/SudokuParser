# SudokuParser

### How to run
deploy the ear file

### Sudoku Solver
This application aims to solve given numbers by user and check it is possible to make a sudoku 
or return an error. 

### Technical Infrastructure

#### Language
Java 8

#### Runtime
JDK 8

#### Deploy
The application is deployable on Glassfish(4.1) application server. 

#### Default port
8080

#### How to call

http://localhost:8080/SudokuParser-web/rest/solve?input=(sudokuPattern)
* sudokuPattern should be like this: input=x,6,3,4,9,x,x,x,1,x,x,x,x,x,x,7,x,9,x,1,9,x,x,x,x,x,x,x,x,1,x,x,2,9,3,x,9,x,x,1,x,7,x,x,2,x,7,8,9,x,x,4,x,x,x,x,x,x,x,x,8,2,x,3,x,6,x,x,x,x,x,x,4,x,x,x,2,9,1,7,x 

#### Build
Maven is the preferential build manager. 

#### Additional  

The implementation is provided as a web service architecture. There are many advantages to support 

web service when developing applications. Java provides restful and other(SOAP) based web services libraries

Rest services are more readable and testable besides, it is lightweight and we don't have to put

additional envelope stuffs like in the others we do. But of course, architecture should be defined 

in order to requirements and current technical situation we have.

In java to develop rest services, JAX-RS api is used. Jersey is one of the reference implementation

of JAX-RS and it is open source. For data readability, JSON is preferred. To provide JSON support 

Genson library is added to maven dependencies. It is a data-binding and streaming library for JSON and java.   

#### Security 

As the requirements for this task is so simple, we don't need to implement authentication to make
secure our rest services but in real, we should think about at least authentication and 
authorization part. In addition, there are some methods to apply on our application in compliance
with best practices:
 - Use HTTPS protocol(it needs to keystore file on server) 
 - Validate input parameters (implemented)
 - Protect http requests(put,post,delete) from CSRF attack(Jersey includes csrf protection filter)
  
### Implementation Details

#### What is sudoku 
Sudoku is a puzzle in numbers one to nine into a grid consisting of nine squares subdivided into 
a further nine smaller squares in such a way that every number appears once in each horizontal line, 
vertical line, and square.  

#### The requirements
 - We need 9 x 9 grid 
 - Each cell can have distinct numbers in 1 - 9 range
 - Each 3 x 3 boxes, vertical and horizontal line can have distinct numbers in 1 - 9 range
 
#### Implementation
To find a valid solution, dept-first search algorithm used. Dept-first search tends to be recursive.
Because of the our constraints are not so deep(9 x 9 matrix) dept-first search approach would be a
good candidate to find an optimal solution. Recursive approach would cause overflow error if input
parameters are so big but in this case we don't need to worry about.

I strived to separate tasks as possible as I can. 

#### Big O analysis
Dept-first search analyze O(mxn) for matrix in our application O(n^2) 

Validation of input parameters was implemented. 


P.S : I'm not good at java8(but I am willing to be good at), maybe there would be a way to parse given input better than I implemented
via stream operations.    

#### Exception Handling 
ExceptionMapper interface offers build custom exception handlers. ExceptionMapper is a contract for a provider 
that maps Java exceptions to Response object, toResponse() method will be triggered when an exception occurs. 
I implemented custom exception handler class, it supports the exception is more understandable and clean.


#### Logging 
To log exceptions and some details we want to write application log, log4j implementation added. The exceptions occured
during the runtime will be logged.

### Test

There are some useful methods were implemented to test analyzing the possible case. 

I just implemented unit test cases but some scenario based cases should be considered in real.

Integration test is very helpful if your application is distributed. 

For unit test Jersey Test Framework is implemented and it is relatively easy. We just need to override

configure() method and we simply investigate the response namely the status code returned by the service.
