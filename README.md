# A Spring Boot Tax Calculation Application

## Overview

This is a Spring Boot application that allows us with the use of the Strategy pattern to calculate the final price of a product based on the country of sale. 
It includes a REST API for managing products and calculating final prices with taxes.


## Clone the Repository

## Build the project
I've used maven, so for building the project you should use : 

```
mvn clean install
```
## Run the project
```
mvn spring-boot:run
```
## Access the Application

Base URL: http://localhost:8080

## API Endpoints
--------------------------
Add Product

URL: ```/products```

Method: POST

Request Body:
```
{
  "name": "Product Name",
  "price": 100.00,
  "country": "US"
}
```
---------------------------
Get Product by ID

URL: ```/products/{id}```

Method: GET

---------------------------
Get Final Price

URL: ```/products/{id}/price```

Method: GET

## Swagger Documentation
```http://localhost:8080/swagger-ui/index.html```

## Running tests
```mvn test```


