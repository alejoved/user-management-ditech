
# User Management API

[![Build](https://github.com/alejoved/user-management-ditech/actions/workflows/ci.yml/badge.svg)](https://github.com/alejoved/user-management-ditech/actions)
[![License](https://img.shields.io/github/license/alejoved/user-management-ditech)](LICENSE)
[![Last Commit](https://img.shields.io/github/last-commit/alejoved/user-management-ditech)](https://github.com/alejoved/user-management-ditech/commits)
[![Java](https://img.shields.io/badge/java-17-blue.svg)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0+-brightgreen)](https://spring.io/projects/spring-boot)
[![Dockerized](https://img.shields.io/badge/docker-ready-blue)](#docker)

---

## 🚀 Main functionalities
  
### 🧑 User
- (`id`, `userName`, `email`, `active`)
- Read users
- Get user by Id
- Create users
- Delete user
---

## ⚙️ Technology Stack

| Category         | Technology                        |
|------------------|-----------------------------------|
| Language          | Java                              |
| Framework         | Spring Boot                       |
| ORM               | JPA                               |
| Base de datos     | H2                                |
| API Documentation | Swagger                           |
| Testing           | JUnit                             |
| Containers        | Docker, Minikube (K8s)            |

---

## 🗂️ Project Architecture

> 🧱 Based on Hexagonal Architecture / Clean Architecture principles

```
src/
├── application/        # Casos de uso y servicios de aplicación
├── domain/             # Modelos, interfaces y reglas de negocio
├── infrastructure/     # Adaptadores secundarios (DB, servicios externos)
├── interface/          # Adaptadores primarios (controllers REST)
├── shared/             # Utilidades, constantes, middlewares
└── main.java           # Punto de entrada principal
```

---

## 🧪 Execute and tests

### ▶️ Local Execution
```bash
# Install dependencies
./mvnw install

# Execute project
./mvnw spring-boot:run
```

### 🧪 Tests Execution
```bash
./mvnw test
./mvnw clean test jacoco:report
```

### 📘 Swagger Documentation
`http://localhost:8080/swagger-ui.html`

---

## 📦 Docker / Containers

### 🐳 Build & Run with Podman
```bash
podman build -t usermanagement-app:latest .
podman compose up
```

---

## ☸️ Deploy in Kubernetes with Minikube

### ✅ Requisitos
- [x] Install Minikube
- [x] Podman (o Docker)
- [x] Manifests `k8s/`

### 🚀 Deploy steps
```bash
minikube delete
minikube start
minikube addons enable metrics-server

# Create and export image
podman build -t usermanagement-app:latest .
podman save -o usermanagement-app.tar usermanagement-app:latest

# Load Image in Minikube
minikube image load usermanagement-app.tar

# Apply k8s manifests
kubectl apply -f k8s/

# Show logs and service
kubectl logs <pod-name>
minikube service usermanagement-service
```

## 👤 Autor

Developer by **Alejandro Aguirre**  
[LinkedIn](https://www.linkedin.com/in/jorge-alejandro-aguirre-gutierrez-1836a0187) • [GitHub](https://github.com/alejoved) • Backend Engineer

---

## 📄 License

This project with license [MIT License](LICENSE).