# API_SurfingProject

## Table of contents
* [Contributors](#contributors)
* [General info](#general-info)
* [Description](#description)
* [Technologies](#technologies)

## Contributors
Adam Alaa Deeb  
Jesper Källqvist  
Sara Löfwander

## General info
A REST API containing the http methods GET, POST, PUT, DELETE, 
unit testing (JUnit & BDD), logging and database integration, 
using the Spring framework. 

## Description
An application primarily aimed towards surfers where they can 
keep themselves updated about weather conditions. We are using 
a weather API (https://www.visualcrossing.com) to collect 
weather data. The desired place is entered in the link below, 
seen here is the popular surfing beach Hossegor in France. We 
chose to present the current temperature from the gathered data.  

http://localhost:6005/api/weather/hossegor/  

Users can enter their own information in posts, such as 
quality of waves or warn others about sharks, however this 
feature is very basic as of yet. 

To make an intricate application out of this were there more 
time available, we would add elements such as detailed automated 
updates about weather, wind and currents saved to a database, 
using sorting algorithms to make predictions out of these statistics.
Log in, a feed, posts and comments would be added connected by 
many-to-many relations.


## Technologies
Project is created with:
* Java (v15)
* Spring Boot (v2.4.5)
* pgAdmin 4 (v5.2)
* PostgreSql (v42.2.20)
* Postman (v8.3.1)
* Swagger UI (v2.9.2)
* https://www.visualcrossing.com
* GitHub actions