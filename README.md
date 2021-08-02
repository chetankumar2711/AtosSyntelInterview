RESTful API for a Sample Banking Application:-


What this project contains:

Business Functionality:
1. Controller for Customer related CRUD functions + check balance for a particular Account Type of a given Customer + make deposit or withdrawal to a particular Account Type of a given Customer + transfer balance between two account types of a given Customer.
2. Controller for Account related CRUD functions + perform transfer between accounts of two different customers.
3. For simplicity kept List of Accounts outside of Customer constructor, even though the relationship is of Aggregation.

Technical Specifications:
1. Spring Boot Project using Spring Boot starter Security (use basic authentication mechanism on Postman), user = 'user' password can be copied from logs written during Startup.
2. Spring Boot Starter Logging which uses Logback as default implementation, logs are configured to record IDs for all appropriate requests on Console. XML configuration is used to keep application.properties clean.
3. Centralized Exception handling using customized RuntimeExceptions in order to give Users an appropriate message for any failure.
4. In order to save time, ephemeral data is created using static lists during startup instead of persistent data using any databases.
5. Since there were neither any external API calls nor any Database calls , in order to save time JUnit Tests using Mockito were skipped. Unit Testing was directly performed using Swagger UI.
6. SpringFox Swagger Documentation and  Swagger UI configuration were integrated using Docket builder.
7. High level business validations scenarios were added including  ensuring Customer is present in system before proceeding any further for any Customer related HTTP requests. Similar check was written for Accounts when handling Accounts related HTTP requests. 





Swagger Links:
http://localhost:8080/swagger-ui
http://localhost:8080/v2/api-docs/


