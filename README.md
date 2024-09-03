# Spring Boot Complete Bootcamp

Here Include 4 projects

## 1) Udemy Series
        i) Spring boot Core Concepts
        ii) Hibernate JPA CRUD
        iii) Rest Crud API
        iv) Rest API Security
        v) Spring MVC
        vi) Spring MVC CRUD
        vii) Spring MVC Security

## 2) PSR_Vlog Series
        i) Rest API
        ii) CRUD App
    
## 3) PSR_Vlog Microservice Series
        i) Create Microservices
        ii) Microservices Inter Communication - Synchronouse Communication
        iii) Microservice Asyncronouse Communication using Apache Kafka
        iv) Service Discovery With Netflix Eureka
        v) API Gateway With Spring CLoud
        ii) Microservice API Security using KeyCloak - Auth 2.0
        iii) Service Monitor using actuator and prometheus

## 4) Daily Code Buffer Series
        i) Spring Aspect-Oriented-Programming (AOP)
        ii) Spring Boot
            1) Craete CRUD APP
            2) Hibernate / Logger / Project Lombok / Exception Handling
            3) Unit Testing
            4) Config Properties File  / Multiple spring profile / Actuator
        iii) Spring Data JPA

            *** Class Diagram provided inside Folder called class_diagram ***
            *** Here We didn't create Rest Api Simply Test Repository and database Using JUnit and Mokito ***
            *** Run Test Cases to Check App ***

            1) Mapping Entities
            2) JPA Annotation
            3) JPA Relationship
            4) Paging and Sorting
        iv) Spring Security

            *** Complete Registration and Login Reference
                ****  https://www.baeldung.com/registration-with-spring-mvc-and-spring-security

            *** Complete OAuth 2.0 Reference
                ****  https://developer.okta.com/blog/2017/06/21/what-the-heck-is-oauth
                ****  https://www.oauth.com/playground/

            1) User registration and Login / email send for verify user / resend token / reset password / change password
            2) OAuth 2.0
            3) login functionality / authentication / authorization
            4) Spring Security config
            5) Resource Server Creation

## 4) Daily Code Buffer Series

### Hibernate Validation
        use Hibernate validation for Field Validation

``` add dependency 
      	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>      
 ```

### Add Loggers

    //Enable Loggers for Debugging Errors
    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    //Use Logger
    LOGGER.info("Inside saveDepartment of DepartmentController");

### Use Project Lombok
    using this we can remove all boiler plate code in our entity

#### i) Step - 1 - add dependency to pom.xml
``` add dependency 
      	<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>    
 ```

#### ii) Step - 2 - add plugin to pom.xml
``` add dependency 
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>  
 ```

#### iiI) Step - 3 - add plugin to pom.xml
    go to ide and check plugin section that lombok plugin installed

### Hibernate JPA Project

    *** Class Diagram provided inside Folder called class_diagram ***
    *** Here We didn't create Rest Api Simply Test Repository and database Using JUnit and Mokito ***
    *** Run Test Cases to Check App ***




## 3) PSR_Vlog Microservice Series

### 1) Microservice Asyncronouse Communication using Apache Kafka

#### Prerequisites java 

### Check Java Installed
java -version
javac -version

### Set and Check JAVA_HOME in Environment varible  - set environment variable both user variable and system variable
JAVA_HOME - C:\Java\jdk-17
echo %JAVA_HOME%

### Installing Kafka on Windows 

#### Step 1:Download the latest version of Apache Kafka 
go the official website https://kafka.apache.org.
click downloads -> Scala 2.13 - kafka_2.13.tgz 

#### Step 2:Extract the downloaded Kafka zip file to a directory of your choice. For example, you can extract it to "C:\kafka".
```javascript unzip file
$ tar -xzf kafka_2.13-3.8.0.tgz
```

#### Step 3:Set and Check KAFKA_HOME in Environment varible  - set environment variable both user variable and system variable
```javascript unzip file
KAFKA_HOME - C:\kafka
echo %KAFKA_HOME%
```
#### Step 4:Copy the path of the Kafka folder. Now go to config inside Kafka folder and open zookeeper.properties file. Copy the path against the field dataDir and add /zookeeper-data to the path.
```javascript zookeeper
dataDir=C:/Kafka/kafka/zookeeper-data
```

#### Step 5:Modify the config/server.properties file. Below is the change:
```javascript server
log.dirs=C:/Kafka/kafka/kafka_logs
```

### Run Kafka Server:

#### Step 1: Kafka requires Zookeeper to run. Basically, Kafka uses Zookeeper to manage the entire cluster and various brokers. Therefore, a running instance of Zookeeper is a prerequisite to Kafka.
To start Zookeeper, we can open a PowerShell prompt and execute the below command:
```javascript Start Zookeeper
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```
#### If the command is successful, Zookeeper will start on port 2181.

#### Step 2: Now open another command prompt and change the directory to the kafka folder. Run kafka server using the command:
```javascript Start Zookeeper
.\bin\windows\kafka-server-start.bat .\config\server.properties
```
#### Now your Kafka Server is up and running, you can create topics to store messages. Also, we can produce or consume data directly from the command prompt.

### Create Bootsrap-Server

#### Step 1: Create a Kafka Topic: Open a new command prompt and Run the following command:
```javascript Create topic
.\bin\windows\kafka-topics.bat --create --topic quickstart-events --bootstrap-server localhost:9092
```

#### Step 2: We can See newly created  Kafka Topic: Open a new command prompt and Run Run the following command:
```javascript Create topic
.\bin\windows\kafka-topics.bat --describe --topic quickstart-events --bootstrap-server localhost:9092
```

### Create Producer And Consumer
### If producer send message Quickly Consumer received that message using Event-Driven Architecture

#### Step 1: Creating Kafka Producer: Open a new command prompt and Run Run the following command:
```javascript Create topic
.\bin\windows\kafka-console-producer.bat --topic quickstart-events --bootstrap-server localhost:9092
```
### After this comman producer can send message

#### Step 2: Creating Kafka Consumer: Open a new command prompt and Run Run the following command:
```javascript Create topic
.\bin\windows\kafka-console-consumer.bat --topic quickstart-events --from-beginning --bootstrap-server localhost:9092
```
### After this comman Consumer Recieve all message send by producer

### Extra
#### List the all the topic to verify:
```javascript Create topic
.\bin\windows\kafka-topics.bat --bootstrap-server=localhost:9092 --list
```

## 2) Microservice API Security using KeyCloak - Auth 2.0

#### Prerequisites java

### Check Java Installed
java -version
javac -version

### Set and Check JAVA_HOME in Environment varible  - set environment variable both user variable and system variable
JAVA_HOME - C:\Java\jdk-17
echo %JAVA_HOME%

### Check Kafka Installed - Before Start KeyCloak Start Kafka Environment
```javascript 
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

### Installing keycloak on Windows

#### Step 1:Download the latest version of Apache Kafka
go the official website https://keycloak.org.
click get started -> Click OpenJDK - Download keycloak-25.0.4.zip

#### Step 2:Extract the downloaded Kafka zip file to a directory of your choice.

#### Step 3:Start New Terminal And Run Keycloak
```javascript 
bin\kc.bat start-dev
```

#### Step 4:Open new tab on Localhost
```javascript server
localhost://8080
```
#### Step 5:Create an Administrative User

## 3) Service Monitor using actuator and prometheus 

### Installing prometheus on Windows

#### Step 1:Download the latest version of prometheus
go the official website https://prometheus.io
click downloads

#### Step 2: run microservices and go to project and check prometheus.yml file
assign current running ports to yml file
example : localhost:58297

#### Step 3:Extract the downloaded prometheus zip file to a directory of your choice.

#### Step 4: go to downloaded directory and open new console and run below command
```javascript prometheus
.\prometheus --config.file=D:\PROGRAMMING\Code\SpringBoot_Bootcamp\03_PSR_Vlog_Microservices\RootLabs\prometheus\prometheus.yml
```
#### Step 5: go to new web browser tab
localhost://9090

## 3) Service Monitor using Grafana

### Installing grafana on Windows

#### Step 1: go to downloaded directory 
go to https://grafana.com/oss/ and click grafana then select windows and download

#### Step 2: Run Grafana
https://localhost:9090

## 1) Udemy Series

### Spring Boot 3 & Spring Framework 6
- Understand the core concepts and functionalities of these updated frameworks.

### Spring Boot 3 Core
- Delve into the heart of Spring Boot 3, exploring its configurations and annotations.

### Spring Boot 3 Java Configuration
- Master Java-based configuration, leaving XML behind.

### Spring Boot 3 and Spring MVC
- Build modern web applications with ease using Spring MVC.

### Spring Boot 3 Hibernate/JPA CRUD
- Implement efficient database interactions with JPA and Spring Data JPA.

### Spring Boot 3 Security
- Secure your applications with robust authentication and authorization mechanisms.

### Spring Boot 3 REST API
- Build and consume powerful RESTful APIs using Spring Boot.

### Maven
- Gain practical knowledge of managing dependencies with Maven.

## Real-Time Projects

Learning by doing is essential! This guide includes real-time projects to solidify your understanding:

### Spring Boot 3 REST API
- Develop a REST API with full CRUD functionality on a database.

### Spring Boot 3 REST API Security
- Implement password encryption for enhanced security.

### Spring Boot 3 with JPA & Spring Data JPA
- Build a comprehensive CRUD application using JPA and Spring Data JPA.

### Spring Boot 3 with Spring Data REST
- Experience building REST APIs directly from your data objects.

### Spring Boot 3 with Spring MVC & Thymeleaf
- Create web applications with server-side rendering and templating capabilities.

---