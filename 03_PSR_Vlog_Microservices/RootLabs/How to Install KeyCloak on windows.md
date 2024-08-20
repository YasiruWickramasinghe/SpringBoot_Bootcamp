#### Prerequisites java 

## Check Java Installed
java -version
javac -version

## Set and Check JAVA_HOME in Environment varible  - set environment variable both user variable and system variable
JAVA_HOME - C:\Java\jdk-17
echo %JAVA_HOME%

## Check Kafka Installed - Before Start KeyCloak Start Kafka Environment
```javascript 
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

# Installing keycloak on Windows 

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





