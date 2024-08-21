<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    <li>
      <a href="#about-the-project">Built With</a>
    <li>
      <a href="#about-the-project">Getting Started</a>
    <li>
      <a href="#about-the-project">Features/Usage</a>
    <li>
      <a href="#about-the-project">Sample Test</a>
    <li>
      <a href="#about-the-project">Sample Data Persisting DB</a>
    <li>
      <a href="#about-the-project">Contact</a>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

![MULTI-USER-MANAGEMENT-APPLICATION](endpoint_swagger.png)

[![EVENT BOOKING APPLICATION][SWAGGER-ENDPOINT-VIEW]](endpoint_swagger.png)

This project is a comprehensive Multi-User Task Management API designed to demonstrate backend development skills and proficiency. The API supports user management with role-based access control, allowing both regular users and admins to manage tasks efficiently. The project is built with the following key features:


### Built With

* Spring-Boot
* Spring Security
* Hibernate
* Spring Stater JPA
* Javax Mail and Javax Mail Activation
* Springdoc-Openapi
* Jason Web Toke(JWT)
* Lombok
* Spring-boot starter Validation
* Relational Database(Postgres)
* Spring-boot starter test


<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

This is a list of things you will need to use the software and how I got to work on this project
* Java 17
* Maven
* H2 database

### Installation

1. Clone the repo
   ```sh
   git clone https://oauth:glpat-whMJBguVQBgxFiiqfCyM@gitlab.com/sundayxyz/DEV_EVENT_BOOKING-493cd35a-b8f0-fd44-a544-778ff66c07cd.git
   ```
2. create pom.xml file to load maven
   ```sh
   add. 
    
   <?xml version="1.0" encoding="UTF-8"?>
     ```
     ```sh 
   <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd"> <modelVersion>4.0.0</modelVersion>
   ```
3. create src file and add dependencies
   ```sh
   src >>> main >>> java >>> dev.sunday
   
   src >>> main >>> resources >>> application.properties
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- USAGE EXAMPLES -->
## Features / Usage

- [ ] <span style="font-size: 20px; font-weight: bold;">User Registration / Sign-up : </span>
<span style="font-size: 20px; ">User's registers smartly with email and password</span>

  ![EVENT BOOKING APPLICATION](sign_up.png)
  ![EVENT BOOKING APPLICATION](response_signup.png)


- [ ] <span style="font-size: 20px; font-weight: bold;">User Login / JWT TOKEN : </span>
 <span style="font-size: 20px; ">On login exact role is authomatically determined and jwt token generated</span>

  ![EVENT BOOKING APPLICATION](login_request.png)
  ![EVENT BOOKING APPLICATION](login_response.png)

- [ ] <span style="font-size: 20px; font-weight: bold;">Admin creation and Admin creating admin</span>
  ![EVENT BOOKING APPLICATION](./admin_created.png)
  ![EVENT BOOKING APPLICATION](./admin_creating_admin.png)




<!-- TEST -->
## Sample Test

![EVENT BOOKING APPLICATION](passed_test.png)
![EVENT BOOKING APPLICATION](passed_test_multiple_assumptions.png)

<!-- DB -->
## Sample Data Persisting DB
<span style="font-size: 20px; font-weight: bold;">Persisting DB on login before full profile creation, showing password hashed and store to db</span>
![MULTI-USER-MANAGEMENT-APPLICATION](user_data_db.png)




<!-- CONTACT -->
## Contact
- [ ] <span style="font-size: 16px; font-weight: bold;">SUNDAY PETER</span>

- [ ] <span style="font-size: 16px; font-weight: bold;">[@linkedin](https://www.linkedin.com/in/sundaypeter1/)</span>

- [ ] <span style="font-size: 16px; font-weight: bold;">sundaypetersp12@gmail.com</span>

- [ ] <span style="font-size: 16px; font-weight: bold;">WhatsApp : 08186707807</span>

- [ ] <span style="font-size: 16px; font-weight: bold;">CALL : 08169036052</span>