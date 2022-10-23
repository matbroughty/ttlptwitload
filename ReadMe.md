Summary
=============

A [Spring Boot Batch](https://spring.io/guides/gs/batch-processing/) App that reads the Listening 
Party data from the [Listening Party csv file]() 
(defined by file.input) and will insert every party into the LISTENING_PARTY table and 
populate the associated tweets for that party into the LISTENING_PARTY_TWEET table.

The twitter library used is [Twittered](https://github.com/redouane59/twittered).

The database is managed by [Liquibase](https://www.liquibase.org) and will be created/updated on 
start-up.

The database queries use [JOOQ](https://www.jooq.org) and the code schema generation for JOOQ 
is performed off of the Liquibase files via the [Maven plugin](https://www.jooq.org/doc/latest/manual/code-generation/codegen-maven/) 


Prerequisites
=============

You need to install the following tools if you want to run this application:

* [JDK 17](https://jdk.java.net/java-se-ri/17)
* [Maven](http://maven.apache.org/) 

Running the Application
=======================

Ensure the following properties or environment variables are set (see application.properties) to
define a database to connect to and the twitter api credentials

| ENV Variable Name                  | Property Name                |
|------------------------------------|------------------------------|
| DATA_SOURCE                        | spring.datasource.url        |
| DATA_SOURCE_PWD                    | spring.datasource.username   |
| DATA_SOURCE_USER                   | spring.datasource.password   |
| twitter4j_oauth_accessToken        | twitter4j.accessToken        | 
| twitter4j_oauth_accessTokenSecret  | twitter4j.accessTokenSecret  |     
| twitter4j_oauth_consumerKey        | twitter4j.consumerKey        |   
| twitter4j_oauth_consumerSecret     | twitter4j.consumerSecret     |




You can run the application by using Maven

Running the Application With Maven
----------------------------------

You can run the application by using the following command:

    mvn spring-boot:run 

To regenerate the JOOQ schema classes 

Loading Data
----------------------------------


The app runs as a web application and exposes one endpoint (by default server.port=8081)
**api/import** i.e. to call this:

### Navigate to a website
```bash
curl -v  -d "apiCallId=1" -X POST http://localhost:8081/api/import
```

The apiCallId should be passed and be unique if a new Job is to be run, otherwise the existing Job 
with that id will be re-run.

The twitter collection asscoiated with each listening party will be read and all the tweets within 
the collection loaded into the  LISTENING_PARTY_TWEET table 





