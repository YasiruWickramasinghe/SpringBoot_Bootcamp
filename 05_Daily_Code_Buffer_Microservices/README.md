# Spring Boot Microservices

## How to Run 

* First Run -> service-registry 
* Second Run -> cloud-config-server
* Third Run -> cloud-gateway
* Fourth Run -> department-service
* Fifth Run -> user-service
* sixth -> start zipkin server
``` run
        download zipkin server -> https://zipkin.io/pages/quickstart.html -> java -> latest release
        run the server using command
        
        in cmd type -> java -jar zipkin-server-3.0.4-exec.jar
        
        then you will see port number and rin that in localhost - http://127.0.0.1:9411/zipkin/
 ```

## Introduction Microservices

### 1) service-registry

The Service Registry is a key component of our microservices architecture, utilizing Netflix Eureka for service discovery and registration. It enables microservices to find and communicate with each other dynamically, without needing hardcoded service locations

### 2) git-config-server

only a yml file contain global configuration 
The Git Config Server is responsible for fetching configuration properties from a Git repository. It pulls configuration data from a file named properties.yml in the repository, allowing centralized management of configuration properties used by various microservices

### 3) cloud-config-server

The Cloud Config Server integrates with the Git Config Server to provide a centralized configuration service for microservices. It serves the configuration data (e.g., from properties.yml) to the microservices at runtime, facilitating consistent configuration management across all services.

### 4) cloud-gateway

The Cloud Gateway acts as the entry point for all microservices, routing requests to the appropriate service. It provides a single, unified API endpoint for clients and includes features like routing, load balancing, and security.

### 5) department-service

The Department Microservice manages department-related data. It supports operations to add, update, and fetch department information, providing a RESTful API for interacting with department records.

### 6) user-service

The User Service handles user data management and integrates with the Department Microservice. It supports user registration, updates, and communication with the Department Microservice. It is equipped with Resilience4j for circuit breaking to handle service unavailability gracefully.

## Tool used in microservices

* Service Registry - Netflix eureka - Manages service discovery and registration with Netflix Eureka.
* Api Gateway - spring-cloud-routing gateway -Routes requests to microservices using Spring Cloud Gateway.
* circuit breaker - resilience4j - Handles service failures and fallback logic with Resilience4j.
* synchronous communication - RestTemplate - Facilitates direct communication between services using RestTemplate.
* cloud configuration - config-server & spring-cloud-starter-config - Centralizes configuration management with Config Server and Spring Cloud Config.
* Monitoring - Spring Actuator - Provides runtime metrics and health checks with Spring Actuator.
* logging and tracing - zipkin and spring opentelemetry
* Spring Cloud opentelemetry: Adds tracing to logs and propagates trace information across microservices.
* Zipkin: Collects and visualizes tracing data to monitor and troubleshoot requests in distributed systems.