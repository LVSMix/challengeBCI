#README: How to Test the Application
This guide explains how to set up, run, and test the application.

---
## Prerequisites

1. Java Development Kit (JDK): Ensure you have JDK 17 or higher installed.
2. Maven: Ensure Maven is installed and configured in your system.
3. Database: The application uses a database (e.g., H2, PostgreSQL, or MySQL). Ensure the database is running and properly configured in the application.properties file.
4. Postman or cURL: For testing the API endpoints.

---
### Steps to Run the Application

1. **Clone the Repository**:

```bash
git clone <repository-url>
cd <repository-folder>
```
2. Build the Project: Run the following command to build the project and download dependencies:

```bash
mvn clean install
```

3. Run the Application: Start the application using:

```bash
mvn spring-boot:run
```
The application will start on http://localhost:8080 by default.

### Testing the Application

1. Swagger Documentation
Open your browser and navigate to:
   http://localhost:8080/swagger-ui.html
Use the Swagger UI to explore and test the API endpoints.
2. API Endpoints
Base URL: http://localhost:8080/api/users


Register a New User:


Endpoint: POST /api/users
Request Body:
```json
{
    "name": "John Doe",
    "email": "john.doe@example.com",
    "password": "password123",
    "phones": [
            {
            "number": "123456789",
            "cityCode": "1",
            "countryCode": "57"
            }
    ]
}
```
Response:
201 Created: User successfully registered.
400 Bad Request: Email already registered.
Example cURL Command:

```bash
curl -X POST http://localhost:8080/api/users \
-H "Content-Type: application/json" \
-d '{
"name": "John Doe",
"email": "john.doe@example.com",
"password": "password123",
"phones": [
{
"number": "123456789",
"cityCode": "1",
"countryCode": "57"
}
]
}'
```
3. Database Verification
   If using an in-memory database (e.g., H2), access the H2 console at:
   http://localhost:8080/h2-console
   Use the credentials configured in application.properties to log in and verify the data.

---
Notes
Ensure the application.properties file is correctly configured for your database and environment.
If you encounter issues, check the logs for detailed error messages.
<hr></hr> This should help you set up and test the application effectively.