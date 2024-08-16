#### Prerequisites java 

## Check Java Installed
java -version
javac -version

## Set and Check JAVA_HOME in Environment varible  - set environment variable both user variable and system variable
JAVA_HOME - C:\Java\jdk-17
echo %JAVA_HOME%

# Installing Kafka on Windows 

#### Step 1:Download the latest version of Apache Kafka 
go the official website https://kafka.apache.org.
click downloads -> Scala 2.13 - kafka_2.13.tgz 

#### Step 2:Extract the downloaded Kafka zip file to a directory of your choice. For example, you can extract it to "C:\kafka".
```javascript unzip file
$ tar -xzf kafka_2.13-3.8.0.tgz
```

## Step 3:Set and Check KAFKA_HOME in Environment varible  - set environment variable both user variable and system variable
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

## Run Kafka Server:

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

## Create Bootsrap-Server

#### Step 1: Create a Kafka Topic: Open a new command prompt and Run the following command:
```javascript Create topic
.\bin\windows\kafka-topics.bat --create --topic quickstart-events --bootstrap-server localhost:9092
```

#### Step 2: We can See newly created  Kafka Topic: Open a new command prompt and Run Run the following command:
```javascript Create topic
.\bin\windows\kafka-topics.bat --describe --topic quickstart-events --bootstrap-server localhost:9092
```

## Create Producer And Consumer
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

## Extra
#### List the all the topic to verify:
```javascript Create topic
.\bin\windows\kafka-topics.bat --bootstrap-server=localhost:9092 --list
```




