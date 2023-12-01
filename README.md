# Users API
Basically there are 5 methods:
- Get User By Id
- Get User By Email
- Post User, for create a new one
- Put User, to modify an existing one
- Delete User

I'm attaching the postman collection, to test the 5 methods.
To start the application run:

    mvn spring-boot:run

1. Get User By Id:
   ```
    localhost:8080/smartjob/users/3ba492ba-f719-4d93-a790-6593884ac642
   ```

   You can pass a random non-persisted UUID to check that we have an 404 HTTP code.

2. Get User By Email:
   ```
    localhost:8080/smartjob/users/email/juan@rodriguez.org
   ```
    
    Same as above, you can pass a non-persisted email, to check that we have an 404.

3. Post User:
    
    ```
   localhost:8080/smartjob/users
   ```

   ```
   {
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "PicoStationM2HP*",
    "phones": [
        {
            "number": "1234567",
            "cityCode": "1",
            "countryCode": "57"
        }
    ]
   }
   ```

   Constraints:
   - Regexp used for email:
   ```
   Pattern.compile("^\\S+@\\S+\\.\\S+$")
   ```
   - Regexp used for password:
   ```
   Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
   ```

   Source: https://uibakery.io/regex-library

4. Put User:
   ```
   localhost:8080/smartjob/users
   ```

   ```
   {
    "id": "902e9ae8-64bd-4acf-b72f-a337d680774d",
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "created": "2023-11-30T21:41:25.21341",
    "modified": "2023-11-30T21:41:25.213483",
    "password": "Meet1reqs*",
    "phones": [
        {
            "number": "1234567",
            "cityCode": "1",
            "countryCode": "57"
        }
    ]
   }
   ```
   
   Also, in this method, we take care of the pattern for email, password, and also we check if
   the new password to update doesn't belong to an existing user.
   
5. Delete User:
   ```
   localhost:8080/smartjob/users/9e009e79-ecdb-48f4-824a-6312181438e0
   ```
   It deletes the user by the id

H2 Web Client, to check data persisted:
http://localhost:8080/h2-ui/login.jsp

Also Swagger has been added, you can found it here:

http://localhost:8080/swagger-ui/index.html#/

Next iteration, adding spring security, plus JWT token.