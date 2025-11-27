# ESD Mini Project

This repository contains a complete **Placement History Viewer**, including a **Java Spring Boot** backend and a **React**-based frontend.

## 📂 Folder Structure

### 1. *backend* (Spring Boot)
- Built with **Java Spring Boot**.
- Handles all the business logic, API endpoints, and database connectivity.
- Includes:
  - Configuration files.
  - Controllers, Services, and Repositories for placement management.

### 2. *resources* (SQL Scripts)
- Contains the SQL files required to set up the database:
  - `create_table.sql`: Script to create necessary tables.
  - `alter_table.sql`: Script to alter existing tables (if needed).
  - `insert_data.sql`: Script to populate tables with sample data.

### 3. *frontend* (React)
- Built with **React**.
- Provides a user interface for interacting with the backend API.
- Includes components for managing placements, students, and search filters.

## 🚀 Setup Instructions

### Backend (Spring Boot)

#### Prerequisites
Ensure the following environment variables are set before running the backend:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/placement_db
spring.datasource.username=poojan
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

#### Steps to Run the Backend
1. Navigate to the *backend* folder:
   ```bash
   cd backend
   ```

2. Build and run the backend using Maven:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. Ensure the database is running and accessible. Update the environment variables with your database connection details.

---

### SQL Database
1. Set up the database using the provided SQL scripts:
   ```bash
   mysql -u <username> -p < database_name> < resources/create_table.sql
   mysql -u <username> -p < database_name> < resources/alter_table.sql
   mysql -u <username> -p < database_name> < resources/insert_data.sql
   ```

---

### Frontend (React)
1. Navigate to the *frontend* folder:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Run the development server:
   ```bash
   npm start
   ```

4. Open your browser and navigate to:
   ```
   http://localhost:3000
   ```

---

## 🛠️ Technologies Used
- **Backend**: Java Spring Boot, MySQL.
- **Frontend**: React, HTML/CSS, JavaScript.
- **Database**: MySQL (with .sql scripts for setup).

---

## 📌 Features
- **Role-Based Access Control (RBAC)**:
  - **Placement Coordinators**: Full access to detailed placement records and filtering.
  - **Students**: Restricted access (Dashboard statistics only).
- **Google OAuth 2.0 Integration**: Secure login using Google accounts.
- **Enhanced Sorting & Filtering**:
  - Sort by Name (A-Z, Z-A) and CTC.
  - Automatic filtering of unplaced students when sorting by CTC.
- **View all students**: Comprehensive placement history for placed and unplaced students.
- **Search and filter**: Based on organization, year, domain, and placement status.
- **Placement details**: Show all placement history along with alumni currently working in the selected organization.

---

## 📄 Environment Variable Details

| Variable Name                          | Description                           | Default Value                |
|----------------------------------------|---------------------------------------|------------------------------|
| SPRING_DATASOURCE_URL                | JDBC URL for the database             | jdbc:mysql://localhost:3306/placement_db |
| SPRING_DATASOURCE_USERNAME           | Database username                     | poojan              |
| SPRING_DATASOURCE_PASSWORD           | Database password                     | password             |
| SPRING_DATASOURCE_DRIVER_CLASS_NAME  | Database driver class name            | com.mysql.cj.jdbc.Driver |
| SPRING_JPA_HIBERNATE_DDL_AUTO        | Hibernate schema management strategy  | update                    |
| SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT | Hibernate dialect                   | org.hibernate.dialect.MySQL5Dialect |
| SPRING_JPA_SHOW_SQL                  | Show SQL queries in logs              | true                      |
| SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL | Format SQL queries in logs        | true                      |

---
