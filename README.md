# Cinema_Room_REST_Service
HyperSkill Project #21

Source Code Directory: Cinema_Room_REST_Service/Cinema Room REST Service/task/src/cinema/

https://hyperskill.org/projects/189

This project is my first Spring application which attempts to replicate requests sent to and performed to a Cinema. It contains 1 GET request (to obtain the list of seats) and recieves the output as a formatted JSON object. The 3 POST requests are for purchasing a ticket and generating a unique Ticket object (unique UUID), refunding a ticket and returning it to the available pool of available seats using the UUID, and a third internal object of the Theatre class to obtain certain metrics. The last POST request requires a password and returns information about the Cinema (Income, Available Seats and Sold Seats). The project contains some error handling with custom exceptions and descriptions of the errors. The project is divided into 5 sections, a Configuration package to handle the initialization of the Theatre object, a Controller package to handle the web requests, an Exception package for the custom exceptions created for the project, a Model package for the different types of objects that are used and passed around the Spring Framework, and a Service package to handle the business logic of the application.
