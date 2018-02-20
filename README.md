# Spring Boot Camel Mongo Embedded

This is a demo application to show how to build a backend application using

- Spring Boot
- Apache Camel
    - Rest routes - GET, POST etc
    - Path param & Query Param handling
    - Direct & Seda routes
    - Exception Handling
    - Response status handling
    - Multicast & Aggregation
- Spring Data
- Mongo DB (Embedded)
- Camel Swagger

## Running the application

Run using the included gradle wrapper

```
./gradlew bootRun
```

And then go to ```http://localhost:8080/camel/api-doc``` to access the Swagger documentation.

## cURL Commands

You can try the following API's once the server is running.

###### GET /person
``` curl http://localhost:8080/camel/person ```

###### POST /person
```  
curl -X POST \
      http://localhost:8080/camel/person \
      -H 'content-type: application/json' \
      -d '{
    	"firstName": "First",
    	"lastName": "Last",
    	"id": "1"
    }' 
```

###### GET /person?lastName=Last
``` curl http://localhost:8080/camel/person?lastName=Last ```

###### GET /person?firstName=First
``` curl http://localhost:8080/camel/person?firstName=First ```

###### GET /person/name?firstName=First&lastName=Last
``` curl "http://localhost:8080/camel/person/name?firstName=First&lastName=Last" ```