# ATM information query service

The service provides the GET endpoint to access the ATM details for the Llyods bank using the open-api.

1. Create a java spring-boot microservice using gradle exposing one GET method requiring two values: This should return the identification value as well as full details of ATMs from the opendata-v2.2#get-atms-2.2 api.

         ``` 
         "url": "https://api.lloydsbank.com/open-banking/v2.2/atms",
         "identification":"30847300"
         ```

       

2. Create logging and demonstrate it by logging to the console.

![presenting logs on console](lloyds_tech_test_img_1.png)

3. Provide OpenAPI swagger specs functionality for your api.
    OpenAPI swagger specs can be found at below url : http://localhost:8850/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

4. Activate the actuator endpoint.
    * http://localhost:8050/actuator/
    * http://localhost:8050/actuator/health
    * http://localhost:8050/actuator/metrics/

5. Create a jar file which can be used to run as a docker image.
    run this command```./gradlew build```, then th jar file can be found at `/build/libs` folder.
