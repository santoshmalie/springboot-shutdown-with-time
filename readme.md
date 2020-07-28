############################################################################
                    BUDGET-MANAGEMENT-SERVICE 
############################################################################ 

TECHNOLOGIES : 
    Springboot : REST API implementation
    Java 8 : Programming language
    Maven :  Dependency management
    Lombok : Boilerplate code generation

Use-cases :
  1 - Create the project with Name and budget given at startup
  2 - Initialize the Shutdown timer
  3 - Approve the spend request if budget available otherwise deny
  4 - Log each approved request and reset the shutdown timer
  5 - If no requests approved in Shutdown timer frequency (90 Secs) then shutdown the application

Approach : 
  1 - Configured the initial command line arguments to Java class 
      e.g. -Dproject.name="ESG-IT" -Dproject.budget="20000"
  2 - Used Timer class from Java to initiate the Shutdown timer
  3 - If budget exhausted then shutdown the application immediately
  4 - Process the request if budget is available
  5 - Approve/Deny the request   based upon spend requst amount
  6 - If Request approved then log into the file with project name 
      e.g. ESG-IT.txt
  7 - Once shutdown triggered either Scheduled/adhoc, do the following activity
      - Create the project folder in archive
      - Copy the file content from project file to archive file, this contains the approved request and final project status

Execution steps :
 cd to pom.xml
 mvn clean install
 java -jar Budget-Manager-Service-0.0.1-SNAPSHOT.jar -Dproject.name="ESG-IT" -Dproject.budget="20000"

Endpoint :
/budget/spend : Process Spend request
    URL : http://localhost:8081/budget/spend
    Type : POST
    Request :
       {
        "teammateId" : "10002",
        "amount" : "15000",
        "description" : "Dinner"
       }
     Response : 
     {
         "responseStatus": "approved"
     }

/actuator/health : Check Health 
    URL:  http://localhost:8081/actuator/health
    Type:  POST
    
P.S. : I think we should use the distributed cache or external database if this application used on multiple instance
 Auto Shutdown will not work as expected if used in multiple instance environment
 Also, I could not complete the Unit testing for this project