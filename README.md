# 🚀 CodeSync Backend

<p align="center">
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-green?style=for-the-badge&logo=springboot" />
  <img src="https://img.shields.io/badge/Spring_Cloud-Microservices-blue?style=for-the-badge&logo=spring" />
  <img src="https://img.shields.io/badge/MySQL-Database-orange?style=for-the-badge&logo=mysql" />
  <img src="https://img.shields.io/badge/RabbitMQ-Async_Messaging-FF6600?style=for-the-badge&logo=rabbitmq" />
  <img src="https://img.shields.io/badge/Redis-Caching-red?style=for-the-badge&logo=redis" />
  <img src="https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge&logo=docker&logoColor=white" />
</p>

<p align="center">
  <img src="https://img.shields.io/github/license/your-repo/codesync?style=flat-square" />
  <img src="https://img.shields.io/github/stars/your-repo/codesync?style=flat-square" />
  <img src="https://img.shields.io/github/issues/your-repo/codesync?style=flat-square" />
</p>

---

# 📌 Overview

**CodeSync Backend** is a production-style distributed microservices backend designed for a collaborative cloud coding platform.
The system is built using **Spring Boot**, **Spring Cloud**, and an event-driven architecture to support authentication, collaboration, execution pipelines, notifications, and scalable service communication.

This backend powers:

* Real-time collaboration
* Distributed code execution
* Snapshot/version management
* JWT/OAuth2 authentication
* API gateway routing
* Event-driven notifications
* Review/comment workflows

---

# ✨ Core Features

## 🔐 Authentication & Authorization

* JWT Authentication
* OAuth2 Login Support
* Secure Role-Based Access
* Token Validation
* Gateway Authorization Filters

---

## 📁 Project & File Management

* Project CRUD Operations
* Folder & File Management
* Organized Workspace Structure
* Persistent Storage Support

---

## 💻 Execution Pipeline

* Remote Code Execution
* Execution Queue Handling
* Multi-language Support
* Async Processing with RabbitMQ
* Execution Status Tracking

---

## 👥 Collaboration System

* Real-time Collaboration Sessions
* WebSocket Communication
* Live Event Broadcasting
* Shared Workspace Synchronization

---

## 🧠 Version & Review System

* Snapshot Creation
* Version History
* Snapshot Comparison
* Review Comments
* Diff Management

---

## 📢 Notification System

* Broadcast Notifications
* User Alerts
* Event-driven Notifications
* Real-time Updates

---

# 🏛️ High-Level Architecture

```text
Frontend → API Gateway → Microservices → Database / Redis / RabbitMQ
```

### Architecture Components

* Angular Frontend
* Spring Cloud Gateway
* Eureka Service Discovery
* Independent Microservices
* RabbitMQ Messaging
* Redis Caching
* MySQL Storage
* WebSocket Communication

---

# 🏗️ System Architecture

```text
                           ┌─────────────────────┐
                           │   Angular Frontend  │
                           │     CodeSync UI     │
                           └──────────┬──────────┘
                                      │
                                      ▼
                         ┌─────────────────────────┐
                         │      API Gateway        │
                         │       Port : 8080       │
                         └──────────┬──────────────┘
                                    │
      ┌──────────────┬──────────────┼──────────────┬──────────────┐
      ▼              ▼              ▼              ▼              ▼

┌────────────┐ ┌────────────┐ ┌────────────┐ ┌────────────┐ ┌────────────┐
│ Auth       │ │ Project    │ │ File       │ │ Collab     │ │ Execution  │
│ Service    │ │ Service    │ │ Service    │ │ Service    │ │ Service    │
│ :8081      │ │ :8082      │ │ :8083      │ │ :8084      │ │ :8085      │
└─────┬──────┘ └─────┬──────┘ └─────┬──────┘ └─────┬──────┘ └─────┬──────┘
      │              │              │              │              │
      └──────────────┴──────────────┴──────────────┴──────────────┘
                                      │
                                      ▼

                    ┌────────────────────────────────┐
                    │       Shared Infrastructure     │
                    ├────────────────────────────────┤
                    │ MySQL Database                 │
                    │ Redis Cache                    │
                    │ RabbitMQ Messaging             │
                    │ Eureka Service Discovery       │
                    └────────────────────────────────┘
```

---

# ⚡ Event-Driven Communication

RabbitMQ enables asynchronous communication between services.

### Event Examples

* Collaboration Events
* Notification Broadcasting
* Execution Queue Processing
* Snapshot Events
* Review Comment Events
* Real-time Updates

---

# 🧰 Tech Stack

| Technology           | Purpose                        |
| -------------------- | ------------------------------ |
| Spring Boot          | Microservices Framework        |
| Spring Cloud         | Distributed System Support     |
| Eureka Server        | Service Discovery              |
| Spring Cloud Gateway | API Gateway                    |
| Spring Security      | Authentication & Authorization |
| JWT                  | Token-based Security           |
| OAuth2               | Social Login                   |
| MySQL                | Relational Database            |
| Redis                | Caching & Session Storage      |
| RabbitMQ             | Async Messaging                |
| WebSocket            | Real-time Communication        |
| Docker               | Containerization               |
| Maven                | Dependency Management          |

---

# 📂 Repository Structure

```bash
CodeSync/
│
├── ApiGatewayService-CodeSync/
├── AuthService-CodeSync/
├── ProjectService-CodeSync/
├── FileService-CodeSync/
├── CollabService-CodeSync/
├── ExecutionService-CodeSync/
├── VersionService-CodeSync/
├── CommentService-CodeSync/
├── NotificationService-CodeSync/
├── EurekaService-CodeSync/
│
├── docker-compose.yml
├── init-db.sql
├── env.example
├── run-backend-sonar.ps1
└── README.md
```

---

# 🔌 Microservices & Ports

| Service              |   Port | Responsibility          |
| -------------------- | -----: | ----------------------- |
| Eureka Server        | `8761` | Service Discovery       |
| API Gateway          | `8080` | Request Routing         |
| Auth Service         | `8081` | Authentication          |
| Project Service      | `8082` | Project Management      |
| File Service         | `8083` | File Operations         |
| Collab Service       | `8084` | Real-time Collaboration |
| Execution Service    | `8085` | Code Execution          |
| Version Service      | `8086` | Snapshot Management     |
| Comment Service      | `8087` | Review Comments         |
| Notification Service | `8088` | Notifications           |

---

# ⚙️ Prerequisites

Before running the backend, install:

## ✅ Required Software

* Java 21
* Maven 3.9+
* MySQL 8
* Redis 7
* RabbitMQ 3
* Docker Desktop (Optional)

---

# 🔧 Installation

## 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/codesync-backend.git
```

---

## 2️⃣ Navigate to Backend

```bash
cd CodeSync
```

---

## 3️⃣ Configure Environment

Create `.env` from:

```bash
env.example
```

---

## 4️⃣ Install Dependencies

```bash
mvn clean install
```

---

# 🌍 Environment Variables

| Variable      | Description        |
| ------------- | ------------------ |
| DB_URL        | MySQL Database URL |
| DB_USERNAME   | Database Username  |
| DB_PASSWORD   | Database Password  |
| JWT_SECRET    | JWT Secret Key     |
| REDIS_HOST    | Redis Host         |
| REDIS_PORT    | Redis Port         |
| RABBITMQ_HOST | RabbitMQ Host      |
| RABBITMQ_PORT | RabbitMQ Port      |
| MAIL_USERNAME | SMTP Username      |
| MAIL_PASSWORD | SMTP Password      |

---

# 🐳 Docker Quick Start

## Start Infrastructure

```bash
docker compose up -d
```

---

## Verify Running Containers

```bash
docker ps
```

---

## Stop Containers

```bash
docker compose down
```

---

# 🗄️ Database Setup

## Create Database

```sql
CREATE DATABASE codesync;
```

---

## Run Initialization Script

```bash
mysql -u root -p < init-db.sql
```

---

# ▶️ Service Startup Order

Start services in the following order:

```text
1. Eureka Service
2. API Gateway
3. Auth Service
4. Project Service
5. File Service
6. Collab Service
7. Execution Service
8. Version Service
9. Comment Service
10. Notification Service
```

---

# ▶️ Run Services

Inside each service:

```bash
mvn spring-boot:run
```

---

# 🌐 API Gateway Routes

| Route                      | Service              |
| -------------------------- | -------------------- |
| `/api/v1/auth/**`          | Auth Service         |
| `/api/v1/projects/**`      | Project Service      |
| `/api/v1/files/**`         | File Service         |
| `/api/v1/sessions/**`      | Collab Service       |
| `/api/v1/executions/**`    | Execution Service    |
| `/api/v1/versions/**`      | Version Service      |
| `/api/v1/comments/**`      | Comment Service      |
| `/api/v1/notifications/**` | Notification Service |

---

# 📚 Swagger Documentation

| Service              | Swagger URL                             |
| -------------------- | --------------------------------------- |
| Auth Service         | `http://localhost:8081/swagger-ui.html` |
| Project Service      | `http://localhost:8082/swagger-ui.html` |
| File Service         | `http://localhost:8083/swagger-ui.html` |
| Collab Service       | `http://localhost:8084/swagger-ui.html` |
| Execution Service    | `http://localhost:8085/swagger-ui.html` |
| Version Service      | `http://localhost:8086/swagger-ui.html` |
| Comment Service      | `http://localhost:8087/swagger-ui.html` |
| Notification Service | `http://localhost:8088/swagger-ui.html` |

---

# 🧪 Testing & Code Quality

## Run Unit Tests

```bash
mvn test
```

---

## Generate JaCoCo Coverage Report

```bash
mvn clean test jacoco:report
```

---

## SonarQube Scan

```bash
mvn clean verify sonar:sonar \
-Dsonar.projectKey=codesync-backend \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.token=YOUR_TOKEN
```

---

# 🔒 Security Features

* JWT Authentication
* OAuth2 Login
* API Gateway Filters
* Secure Route Authorization
* Service Isolation
* Environment Variable Protection
* Token Validation
* Request Authentication

---

# ❗ Troubleshooting

## ⚠️ Port Already in Use

Stop conflicting processes or update `SERVER_PORT`.

---

## ⚠️ Database Connection Failure

Verify:

* MySQL is running
* Correct credentials
* Proper DB URL

---

## ⚠️ RabbitMQ Authentication Failed

Verify:

* RabbitMQ username/password
* Virtual host configuration

---

## ⚠️ Execution Stuck in QUEUED

Verify:

* Execution Service logs
* RabbitMQ connectivity
* Executor configuration

---

## ⚠️ Eureka Registration Failure

Verify:

* Eureka server is running
* Correct discovery URL configured

---

# 🚀 Future Enhancements

* Kubernetes Deployment
* OpenShift Deployment
* AI Code Suggestions
* Distributed Logging
* Prometheus Monitoring
* Grafana Dashboards
* CI/CD Pipelines
* Cloud Deployment

---

# 👨‍💻 Contributors

| Name          | Role                 |
| ------------- | -------------------- |
| Madhav Sharma | Full Stack Developer |

---

# 📄 License

This project is intended for:

* Academic Use
* Learning Purposes
* Internal Demonstrations

---

# ⭐ Support

If you like this project:

* ⭐ Star the repository
* 🍴 Fork the project
* 🛠️ Contribute improvements

---

<p align="center">
  Built with ❤️ using Spring Boot Microservices
</p>
