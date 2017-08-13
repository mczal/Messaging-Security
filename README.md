# Messaging-Security

This project was designed to (simple) securing data transaction for exchanging messages using Springboot (This was project assignment as a part of an interview test from Dana).
This project also used [Maven](https://maven.apache.org) as a project management tools.

List of tasks:
- [x] Create application using Java Spring -> Done using **Springboot**
- [x] JWT for user authorization -> Done applying **jjwt.io** in Spring Security
- [x] Use RDBMS -> Done using **MySQL** or **H2**
- [x] REST API with Json -> Done using spring rest controller

Bonus tasks:
- [x] Implement simple websocket -> Done using **STOMP** messaging and **SockJS** for client
-- In this example, websocket has been designed for authenticated user to be able to see their inbox message interactively. See endpoint **/ws-messaging**

Additional Feature:
- [x] Swagger documentation to see and test all API documentation -> See endpoint **/swagger-ui.html**

Information:
* Bootstraped user has been created in *com.test.dana.utils.JPABootstrap* 
```sh
username : mczal
email : mczal@dana.id
password : 123
```

-- [Mohamad Fahrizal Septrianto](https://www.linkedin.com/in/mczal/) --
