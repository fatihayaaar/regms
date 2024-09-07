# REGMS

This project is a Social Media System designed with Microservice Architecture, developed using Spring Cloud and Angular.

## Introduction

In this project, I developed an advanced social media system using a microservices architecture. Here are the key components and technologies used in this project:

#### Authentication and Authorization

- **Keycloak**: Used as the authentication server to manage user identities and access. Security is ensured using JWT tokens. A custom login screen was designed for Keycloak.
- **OpenLDAP**: Configured as a user provider for Keycloak. LDAP settings were adjusted to fit the project requirements.
- **Vault**: Used to securely manage sensitive information, such as LDAP admin user passwords.

#### Microservices Architecture

- **Keycloak** acts as the **Auth Server**, while other microservices function as **Resource Servers**.
- **Docker**: Custom Dockerfile files were created for each service, and the project is deployed using Docker Compose.

#### Spring Framework

- **Spring Cloud**: The project was developed as a Spring Cloud project, structured under a root project with multiple sub-modules.
- **Spring Config Server**: Used to manage configuration files from a central service.
- **Spring Eureka Server**: Provided service discovery and registration integration for microservices.
- **Spring Cloud Gateway**: Used as a frontend gateway to route requests to other services.
- **CQRS**: Designed RESTful APIs by separating command and query responsibilities.

#### Service Applications

- **User Service**: Designed as a user management service interacting with LDAP and accessible via RESTful API.
- **Profile and Post Services**: Worked with Cassandra database. The Post Service simplified endpoint management by handling API requests with GraphQL.
- **Like, Comment, and Follow Services**: Managed relationship data effectively using GraphDB and reduced query complexity.
- **Inter-Service Communication**: Used gRPC and RESTful protocols for data exchange between services.

#### Observability and Interface

- **Zipkin Server**: Performed logging and tracing to monitor inter-service communication and debug issues.
- **Angular**: An interactive user interface was developed for the project.

This project integrates modern technologies and tools to create a robust and scalable social media system, effectively managing data and service orchestration.

## Key Features

- **Easy Registration and Login**: Users can access the app through straightforward registration and login options.
- **Customizable User Profile**: Users can personalize their profile information, photos, and settings according to their preferences.
- **Follow Feature**: Users can follow other users they are interested in.
- **User Search**: Users can search for and find other users by name and username.
- **Content Sharing**: Users can share updates and thoughts through text and photo posts.
- **Likes and Comments**: Users can like and comment on other users' posts, allowing for interaction.
- **View Posts**: Users can view, like, and comment on posts from users they follow.
- **View Followers and Following**: Users can see who follows them and who they are following.
- **App Settings**: Users can personalize various app settings (privacy, notifications, etc.) to optimize their experience.

## Installation

#### With Docker (Linux, MacOS)

- Use this command to start the server in the project directory.

```
run.sh
```

Navigate to `http://localhost:4200/` to access the web UI.

*To ensure your project runs locally, add keycloak to your localhost DNS settings.*

## Projects Pages

### Login Page

<img width="1440" alt="login" src="https://github.com/user-attachments/assets/a590a071-cec0-4d79-950b-13dd85a59948">

------

### Register Page

<img width="1440" alt="register" src="https://github.com/user-attachments/assets/2ba3d2c7-a13a-4b2f-9187-cd7c15d2f1ba">

-------

### Home Page

<img width="1440" alt="home" src="https://github.com/user-attachments/assets/5af648da-7077-48c3-bc9c-a809f5f4e2d7">

-------

### Profile Page

<img width="1440" alt="profile" src="https://github.com/user-attachments/assets/1d1c332f-61bc-48c2-9f71-99675bf75b23">

-------

### Post Detail Page

<img width="1440" alt="comments" src="https://github.com/user-attachments/assets/2e6cfd2a-ab42-48cb-b642-aa01ea0e27c6">

-------

### Followers page

<img width="1438" alt="follower" src="https://github.com/user-attachments/assets/b51f4ec8-21a1-4691-9eae-8980f88caafc">

--------

### Settings Page

<img width="1440" alt="settings" src="https://github.com/user-attachments/assets/d94f774e-8bf1-4091-9c15-c9b08b271cd6">