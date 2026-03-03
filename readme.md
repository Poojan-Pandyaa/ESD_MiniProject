# 🚀 ESD Mini Project – Placement History Viewer

This repository contains a complete **Placement History Viewer**, including a **Java Spring Boot backend** and a **React-based frontend**.

The project demonstrates both:

* ✅ Full-stack application development
* ✅ Production-style containerized deployment with CI/CD automation

---

# 📂 Project Structure

```
ESD_MiniProject/
│
├── ESD_Backend/        # Spring Boot backend
├── ESD_Frontend/       # React frontend
├── resources/          # SQL scripts
└── Jenkinsfile         # CI/CD pipeline definition
```

---

# 🖥 Application Overview

## 1️⃣ Backend (Spring Boot)

* Built with **Java 17 + Spring Boot**
* Handles business logic, API endpoints, and database connectivity
* Implements:

  * Controllers
  * Services
  * Repositories
  * Google OAuth2 Authentication
  * Role-Based Access Control (RBAC)

---

## 2️⃣ Frontend (React)

* Built with **React**
* Provides UI for:

  * Placement history viewing
  * Sorting and filtering
  * Role-based dashboard access

---

## 3️⃣ Database (MySQL)

SQL scripts available in `/resources`:

* `create_table.sql`
* `alter_table.sql`
* `insert_data.sql`

---

# 🚀 Local Development Setup (Without Docker)

## Backend

### Environment Configuration

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

### Run Backend

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

---

## Frontend

```bash
cd frontend
npm install
npm start
```

Access:

```
http://localhost:3000
```

---

# 🛠 Technologies Used

### Application

* Java Spring Boot
* React
* MySQL
* Google OAuth 2.0

### DevOps & Infrastructure

* Docker
* Docker Compose
* Nginx (Reverse Proxy)
* Jenkins (CI/CD)
* GitHub Webhooks
* Ubuntu Linux
* SSH Jumpbox Access

---

# 🚀 Production Deployment & DevOps Architecture

This project was extended beyond local development into a **production-style containerized multi-service deployment**.

---

## 🏗 Architecture Overview

### Application Flow

```
Browser (localhost:8081)
        ↓
Nginx (Reverse Proxy Container)
        ├── Serves React Static Build
        └── Routes /api → Spring Boot (Port 9191)
                               ↓
                             MySQL (Port 3306)
```

### CI/CD Flow

```
GitHub Push
     ↓
Jenkins Pipeline
     ├── Maven Build (Backend)
     ├── Docker Image Build
     ├── Docker Compose Deployment
     └── Service Restart
```

---

# 🐳 Containerized Deployment

The application runs as multi-container architecture:

| Service | Purpose                              |
| ------- | ------------------------------------ |
| nginx   | Serves frontend & routes API traffic |
| backend | Spring Boot REST API                 |
| mysql   | Database                             |
| jenkins | CI/CD automation                     |

All services communicate through Docker internal networking.

---

## ▶ Run Using Docker Compose

```bash
cd ESD_Backend/docker
docker compose up --build -d
```

Access application:

```
http://localhost:8081
```

---

# 🔁 CI/CD Pipeline (Jenkins)

Jenkins automates:

1. Checkout from GitHub
2. Maven build (`mvn clean package`)
3. Docker image build
4. Container deployment using Docker Compose
5. Automatic service restart on commit

This ensures:

* Automated deployments
* Consistent build environment
* Reduced manual errors
* Faster development cycle

---

# 🔐 Authentication & Security

* Google OAuth2 integration
* Role-Based Access Control (RBAC)
* Backend not publicly exposed (only accessible via Nginx)
* Secure deployment on Ubuntu VM
* SSH jumpbox-controlled access

---

# 📌 Features

* Placement history tracking
* Role-based dashboards
* Sorting by Name & CTC
* Organization/year/domain filtering
* Google OAuth login
* Alumni visibility by organization
* Student vs Coordinator access separation

---

# 🧠 DevOps Concepts Demonstrated

* Multi-container orchestration
* Reverse proxy configuration
* Internal Docker networking
* CI/CD automation
* Environment variable management
* Service health checks
* Secure VM deployment
* OAuth integration in containerized setup

---

# 📈 Why This Project Is Significant

Unlike a basic CRUD deployment, this project demonstrates:

* Full-stack system design
* Production-ready reverse proxy setup
* Automated CI/CD pipeline
* Secure remote deployment architecture
* Container-based infrastructure management

---

# 👨‍💻 Author

Poojan Pandya
Full-Stack Developer | DevOps Enthusiast
