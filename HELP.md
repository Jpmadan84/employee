# Getting Started

### Reference Documentation
To run the project follow the following steps
1. Install jdk in your local machine
2. download the file target/employee-0.0.1-SNAPSHOT.jar to the local machine in some folder
3. open command prompt and go to the folder where you downloaded the file mentioned in previous step and run the following command
    java -jar employee-0.0.1-SNAPSHOT.jar
    
    
The application will start running

Note: Please make sure that port 8080 is not being used by other application in the local machine, otherwise this application will not start. 

To know what all rest endpoints are available and details of the endpoint please open following link from any browser when application is running
http://localhost:8080/swagger-ui/

There is already some default data embedded which you can refer from employee/src/main/resources/data.sql
So you can run the endpoints without any further setup

You may import the postman collection from ./Employee.postman_collection.json to your local postman and play around with the endpoints
