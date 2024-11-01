# PROJECT

##### This project is a RESTful service provider built with the Javalin framework in Java.

##### It demonstrates various functionalities,

##### including data management using DAOs, API error handling, JPA persistence, and automated testing.

# Task 1: Build a REST Service Provider with Javalin


# Task 2: Exception Handling 
| HTTP method | REST Ressource            | Exceptions and status(es) |
|-------------|---------------------------|---------------------------|
| GET         | `/api/plants`             | Statuscode 204 / 400      |
| GET         | `/api/plants/{id}`        | Statuscode 404 / 400      |
| GET         | `/api/plants/type/{type}` | Statuscode 204 / 400      |
| POST        | `/api/plants`             | Statuscode   400          |

##### I  throw a API Exception with the statuscode 204, if the item is empty. 
##### Showing that the request was  successfully, but there’s no data to return
##### I throw a 404 status code to tell the file is not found
##### And the 400 is for the invalid client input. 
#####
{
"time of error": "2024-10-31 12:05:29",
"error message": {
"status": 400,
"message": "File not found, no Plants with id 1 was found"
}
}



# Task 3: Streams and Generis 
# Task 4: 
# Task 5:



# Task 6: Testing the Doctor API with REST Assured
