A Spring Boot-based REST API for a job recruitment platform that connects job seekers and recruiters through a secure and structured backend system.

The project is designed to practice and demonstrate real-world backend development concepts including RESTful API design, CRUD operations, relational database management, DTOs, validation, authentication, authorization, search, filtering, pagination, and application management.

📌 Project Overview

The Job Portal provides separate capabilities for different types of users:

Candidates can create profiles, search for jobs, and apply for jobs.
Recruiters can create and manage job postings and review applications.
Admins can manage users and platform resources.

The backend follows a layered architecture:

Client
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
MySQL Database

This separation keeps the application maintainable and makes the business logic independent of the API layer.

✨ Features
👤 User Management
User registration
User login
User profiles
Role-based users
Candidate and recruiter accounts
Admin account

Supported roles:

CANDIDATE
RECRUITER
ADMIN
🔐 Authentication & Authorization

Authentication will be handled using Spring Security and JWT.

Users will authenticate using their credentials and receive a JWT token.

Login
  ↓
Credentials validation
  ↓
JWT generated
  ↓
Client sends JWT
  ↓
Spring Security validates token
  ↓
Request authorized

Role-based access will restrict sensitive operations.

For example:

CANDIDATE
├── Search jobs
├── View jobs
└── Apply for jobs

RECRUITER
├── Create jobs
├── Update jobs
├── Delete own jobs
└── View applications

ADMIN
├── Manage users
├── Manage jobs
└── Manage platform resources
💼 Job Management

Recruiters can create and manage job postings.

Each job can contain information such as:

Job
├── ID
├── Title
├── Description
├── Company
├── Location
├── Salary
├── Job Type
├── Required Skills
├── Created At
└── Recruiter
Job Operations
POST   /jobs
GET    /jobs
GET    /jobs/{id}
PUT    /jobs/{id}
DELETE /jobs/{id}
🔎 Job Search & Filtering

Candidates can search and filter available jobs.

Examples:

GET /jobs?keyword=Java
GET /jobs?location=Delhi
GET /jobs?jobType=REMOTE

Multiple filters can be combined:

GET /jobs?keyword=Java&location=Delhi&jobType=REMOTE

The API will also support:

Pagination
Sorting
Keyword search
Location filtering
Job type filtering
Salary filtering
Skill-based filtering

Example:

GET /jobs?page=0&size=10&sort=createdAt,desc
📝 Job Applications

Candidates can apply for available jobs.

Relationship:

Candidate
    │
    │ applies
    ↓
Application
    ↓
Job

Application statuses:

APPLIED
SHORTLISTED
INTERVIEW
REJECTED
HIRED
Application APIs
POST /jobs/{jobId}/apply
GET  /applications/my
GET  /jobs/{jobId}/applications
PATCH /applications/{id}/status

Recruiters can review applications for their jobs and update application status.

Candidates can view the status of their applications.

🗃️ Database Design

The application uses MySQL with Spring Data JPA / Hibernate.

Basic relationship structure:

                    User
                   /    \
                  /      \
         Candidate      Recruiter
              |             |
              |             |
              ↓             ↓
        Applications ←─── Jobs

A simplified relational structure:

users
 ├── id
 ├── name
 ├── email
 ├── password
 └── role

jobs
 ├── id
 ├── title
 ├── description
 ├── company
 ├── location
 ├── salary
 ├── job_type
 └── recruiter_id

applications
 ├── id
 ├── candidate_id
 ├── job_id
 ├── status
 └── applied_at
🏗️ Project Structure
src/main/java/com/shelfnotes/jobportal
│
├── config
│
├── controller
│
├── dto
│
├── entity
│
├── enums
│
├── exception
│
├── repository
│
├── service
│
└── JobPortalApplication.java
Package Responsibilities

controller

Contains REST controllers responsible for handling HTTP requests.

service

Contains business logic and application rules.

repository

Contains Spring Data JPA repositories responsible for database operations.

entity

Contains JPA entities representing database tables.

dto

Contains request and response DTOs to prevent exposing database entities directly through APIs.

enums

Contains fixed application values such as:

Role
JobType
ApplicationStatus

exception

Contains custom exceptions and global exception handling.

config

Contains Spring configuration classes, especially security configuration.

🛠️ Tech Stack
Backend
Java 21
Spring Boot
Spring Web
Spring Data JPA
Hibernate
Spring Security
JWT
Bean Validation
Database
MySQL
MySQL Workbench
Build Tool
Maven
Development Tools
IntelliJ IDEA
Postman
Git
GitHub
Deployment

Planned:

Docker
Render / AWS
🔄 API Architecture

The application follows a layered architecture.

For example, when creating a job:

POST /jobs
      │
      ↓
JobController
      │
      ↓
JobService
      │
      ↓
JobRepository
      │
      ↓
Hibernate / JPA
      │
      ↓
MySQL

The controller handles the HTTP request, while the service contains the actual business logic.

The repository communicates with the database.

✅ Validation

Incoming API requests will be validated using Jakarta Bean Validation.

For example:

@NotBlank
private String title;

@NotBlank
private String description;

Invalid requests will return an appropriate 400 Bad Request response.

⚠️ Exception Handling

The application uses centralized exception handling.

Examples:

JobNotFoundException
UserNotFoundException
ApplicationNotFoundException
UnauthorizedException

Instead of returning raw exceptions, the API will provide structured error responses.

Example:

{
  "status": 404,
  "message": "Job not found",
  "timestamp": "2026-09-25T12:30:00"
}
📄 Pagination & Sorting

Large datasets should not be returned in a single response.

Example:

GET /jobs?page=0&size=10

Sorting:

GET /jobs?sort=createdAt,desc

This allows the API to efficiently handle large numbers of jobs and applications.

🚀 Getting Started
Prerequisites

Make sure you have installed:

Java 21
Maven
MySQL
Git
IntelliJ IDEA
Postman
1. Clone the repository
git clone https://github.com/YOUR_USERNAME/job-portal.git
cd job-portal
2. Create the database

Open MySQL Workbench and run:

CREATE DATABASE jobportal;
3. Configure database connection

Update:

src/main/resources/application.properties

Example:

spring.datasource.url=jdbc:mysql://localhost:3306/jobportal
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Do not commit real passwords or JWT secrets to GitHub.

For production, use environment variables.

4. Run the application

Using Maven:

./mvnw spring-boot:run

On Windows:

mvnw.cmd spring-boot:run

Or run:

JobPortalApplication.java

directly from IntelliJ.

🧪 Testing

The APIs can be tested using Postman.

Example workflow:

1. Register user
        ↓
2. Login
        ↓
3. Receive JWT
        ↓
4. Add JWT to Authorization header
        ↓
5. Access protected endpoints

Authorization header:

Authorization: Bearer <JWT_TOKEN>
🗺️ Development Roadmap

The project will be developed progressively.

Phase 1 — Foundation
 Spring Boot setup
 Project structure
 MySQL configuration
 User entity
 Job entity
Phase 2 — Job CRUD
 Create job
 Get all jobs
 Get job by ID
 Update job
 Delete job
Phase 3 — Backend Quality
 DTOs
 Validation
 Global exception handling
 Proper HTTP status codes
 Pagination
 Sorting
Phase 4 — Search
 Keyword search
 Location filtering
 Job type filtering
 Salary filtering
 Skill filtering
 Combined filters
Phase 5 — Authentication
 User registration
 Login
 Password hashing
 JWT authentication
 Spring Security configuration
Phase 6 — Authorization
 Candidate role
 Recruiter role
 Admin role
 Endpoint authorization
 Ownership checks
Phase 7 — Applications
 Apply for job
 View applications
 Recruiter application management
 Application status
 Prevent duplicate applications
Phase 8 — Resume Management
 Resume upload
 Resume retrieval
 File validation
 External/object storage
Phase 9 — Testing
 Unit tests
 Service tests
 Controller tests
 Repository tests
 Integration tests
Phase 10 — Deployment
 Dockerize application
 Configure production environment
 Deploy backend
 Configure production database
 API documentation
🎯 Learning Objectives

This project is being built to develop practical backend engineering skills, particularly:

REST API design
Layered architecture
CRUD operations
Relational database design
JPA/Hibernate
Entity relationships
DTO-based API design
Input validation
Exception handling
Search and filtering
Pagination and sorting
Authentication
Authorization
JWT
Spring Security
File handling
API testing
Docker
Deployment
🔮 Future Improvements

Possible future additions:

Email notifications
Resume parsing
Job recommendations
Saved jobs
Recruiter dashboards
Candidate profiles
Company profiles
Advanced job search
Redis caching
Rate limiting
Asynchronous notifications
API documentation with Swagger/OpenAPI
CI/CD pipeline
👨‍💻 Author

Sagar

BTech CSE Student | Backend Development | Java | Spring Boot

📜 License

This project is intended for educational and portfolio purposes
