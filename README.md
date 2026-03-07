# Client Management System

A full-stack web application for managing client information, built with Spring Boot backend and Angular frontend.

This application allows users to perform CRUD operations on client entries: add new clients, view existing ones, update client information, and remove clients as needed. The system includes filtering capabilities to quickly find clients, and features a clean, responsive UI built with Bootstrap.

This is a project made mainly for portfolio purpose.

**DISCLAIMER**: All the entries from the Database Seeder are made using AI. It isn't real information.

---

## Table of Contents

- [Features](#features)
- [Technologies and Apps Used](#technologies-and-apps-used)
- [Prerequisites](#prerequisites)
- [How to Run](#how-to-run)
- [How to Reset the Database](#how-to-reset-the-database)
- [API Endpoints](#api-endpoints)
- [Future Improvements](#future-improvements)

---

## Features

- **CRUD Operations**: Create, read, update, and delete client entries
- **Advanced Filtering**: Filter clients by status (ACTIVE/INACTIVE) and country
- **Client Status Management**: Easily toggle client status between active and inactive
- **Responsive Design**: Bootstrap-based UI that works on desktop and mobile devices
- **RESTful API**: Well-structured backend API following REST principles
- **Database Seeding**: Initial data population for testing and demonstration
- **Form Validation**: Client-side and server-side validation for data integrity

---

## Technologies and Apps Used

- **Backend**:
  - Java 21
  - Spring Boot 4.0.3
  - Spring Data JPA
  - PostgreSQL Database
  - Maven (build tool)

- **Frontend**:
  - Angular 21
  - TypeScript
  - Bootstrap 5
  - Node.js & npm

- **DevOps & Tools**:
  - IntelliJ IDEA
  - Docker
  - JUnit (testing)
  - Git & Github

---

## Prerequisites

- **Docker** installed on your system
- (Optional) Java 21 JDK and Maven for local backend development
- (Optional) Node.js 20+ and npm for local frontend development

---

## Configuration

### Database Credentials

The application uses PostgreSQL with default credentials configured in `docker-compose.yml` and `src/main/resources/application.properties`. If you need to change the database credentials:

1. Update the environment variables in `docker-compose.yml`:
   ```yaml
   postgres:
     environment:
       POSTGRES_DB: your_database_name
       POSTGRES_USER: your_username
       POSTGRES_PASSWORD: your_password
   ```

2. Update the corresponding values in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://postgres:5432/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

**Note**: If running locally without Docker, ensure your local PostgreSQL instance uses the same credentials.

---

## How to Run

### Using Docker (Recommended)

1. **Clone the repository**:
   ```bash
   git clone https://github.com/2011anlola/client-management.git
   cd client-management
   ```

2. **Build and run the application**:
   ```bash
   docker compose up --build
   ```

3. **Access the application**:
   - Frontend: http://localhost:4200
   - Backend API: http://localhost:8080
   - Database: localhost:5432 (if needed for direct access)

The application will automatically start all services (PostgreSQL database, Spring Boot backend, and Angular frontend) and set up the necessary connections.

### Local Development Setup

#### Backend Setup:
1. Ensure PostgreSQL is running locally or via Docker
2. Navigate to the project root
3. Run `./mvnw spring-boot:run`

#### Frontend Setup:
1. Navigate to `client-management-frontend/`
2. Install dependencies: `npm install`
3. Start development server: `npm start`
4. Access at http://localhost:4200

---

## How to Reset the Database

To reset the database and start with fresh data:

1. **Stop the application and force Database deletion**:
   ```bash
   docker compose down -v
   ```

2. **Restart the application**:
   ```bash
   docker compose up --build
   ```

This will recreate the database from scratch and run the database seeder.

---

## API Endpoints

The backend provides the following REST API endpoints:

- `GET /api/clients` - Get all clients (with optional filters)
- `GET /api/clients/{id}` - Get client by ID
- `POST /api/clients` - Create a new client
- `PUT /api/clients/{id}` - Update an existing client
- `DELETE /api/clients/{id}` - Delete a client

## Future Improvements

- Server-side pagination for handling large datasets efficiently
- Dashboard page with client statistics and analytics
- User authentication and authorization system
- Export functionality (CSV/PDF reports)
- Email notifications for client status changes
- Deploy as a single executable JAR with embedded frontend
- API documentation with Swagger/OpenAPI
- Unit and integration test coverage expansion