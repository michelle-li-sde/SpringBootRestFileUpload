# SpringBootRestFileUpload
### Task Discription
Implemented a Restful API in Spring Boot Application to upload a file with a few meta-data fields. Persisted meta-data in memory DB (Derby) and stored the content on a file system. Performed unit testing using JUnit and Mockito.
### Run Application
I used Eclipse as IDE to develop this file uploading application.  
Open your Eclipse -> Import my application as Maven project -> Right click project -> Run as -> Spring Boot App.  
### API Documentation
|Method|Path|Parameters|Description|
|------|----|----------|-----------|
|POST|**/files**|Body->file|upload a new file|
|GET|**/files**||list all uploaded files metadata|
|GET|**/files/{fileId}**|fileId|find file metadata by fileId|
### Test API
I used Postman to test my Rest services.  
Open Postman -> Fire a request to http://localhost:8080/ by following my API documentation to test the service. For example:  
* POST http://localhost:8080/files and in Body you need to add **file** as Key (type is File) and **Choose Files** as Value.
* GET http://localhost:8080/files
* GET http://localhost:8080/files/1
### Run JUnit Tests
I used Eclipse to execute my tests.  
Right click project -> Run as -> JUnit Test.
