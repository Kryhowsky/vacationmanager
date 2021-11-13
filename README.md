# Vacation Manager
Simple program allowing to manage holidays of workers.

## General Information
System allows users to create Vacation request.
There are four types of leave:
- Leave
- On Demand (four days per year from the pool for that year)
- Occasional (two days per year outside the pool for that year)
- On Child (two days per year outside the pool for that year)


Each user may have 20 days of leave when the work tenure is up to 10 years and 26 days when the work tenure is over 10 years.

Annual leave is available until the end of September of the following year, and then it is forfeited.

The user can request for a vacation, then vacation request comes in pending status and waits for acceptance.

Administrator can accept or cancel the user's vacation request.

## Technologies Used
- Spring
- Spring Boot
- Spring Data JPA
- Spring MVC
- MySql
- Lombok

## Features
- Request Vacation
- Accept Vacation
- Reject Vacation
- See all Vacations
- Check if request can be placed

## Project Status
Project is: _in progress_

## Room for Improvement
To do:
- Implement AdviceController to manage Exceptions
- Prepare DTO objects and implement Mapstruct
- Implement Flyway to automatically create two roles (Admin, User) in DB
- Implement validation of fields
- Implement Security (JWT)
- Implement Audit (hibernate) possibility to track created by, created date, last updated by, last updated date
- Create frontend app in Angular

## Contact
Created by [@Kryhowsky](https://www.linkedin.com/in/kryhowsky/) - feel free to contact me!
