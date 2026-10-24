# GoMetro — Metro Ticket Booking System Backend

GoMetro is a full-stack Metro Ticket Booking System where passengers can register, search routes, purchase metro tickets, manage metro cards, recharge balances, and administrators can manage stations, routes, schedules, trains, fares, maintenance issues, and system operations.

---

## 🚀 Technology Stack

- **Language**: Java 21
- **Framework**: Spring Boot 3.3.x
- **Build Tool**: Maven 3.x
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA (Hibernate)
- **Security**: Spring Security
- **API Documentation**: Springdoc OpenAPI v2 / Swagger UI
- **Validation**: Jakarta Validation
- **Utilities**: Project Lombok
- **Dev Tools**: Spring Boot DevTools

---

## 📁 Project Structure

```text
gometro/
├── pom.xml
├── README.md
├── .gitignore
├── mvnw / mvnw.cmd
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/gometro/
    │   │       ├── GoMetroApplication.java
    │   │       ├── advice/
    │   │       │   └── GlobalExceptionHandler.java
    │   │       ├── config/
    │   │       │   ├── ApplicationConfig.java
    │   │       │   ├── MapperConfig.java
    │   │       │   └── OpenApiConfig.java
    │   │       ├── constants/
    │   │       │   └── AppConstants.java
    │   │       ├── controller/
    │   │       ├── dto/
    │   │       ├── entity/
    │   │       ├── enums/
    │   │       ├── exception/
    │   │       ├── mapper/
    │   │       ├── repository/
    │   │       ├── response/
    │   │       │   └── ApiResponse.java
    │   │       ├── security/
    │   │       │   └── SecurityConfig.java
    │   │       ├── service/
    │   │       │   └── impl/
    │   │       ├── util/
    │   │       └── validator/
    │   └── resources/
    │       └── application.yml
    └── test/
        └── java/
            └── com/gometro/
                └── GoMetroApplicationTests.java
```

---

## 📋 Prerequisites

- **Java Development Kit**: JDK 21+
- **Maven**: 3.8+ (or use included Maven Wrapper `mvnw`/`mvnw.cmd`)
- **Database**: PostgreSQL 12+

---

## ⚙️ Environment Variables

| Variable | Description | Default Value |
| :--- | :--- | :--- |
| `PORT` / `SERVER_PORT` | Application HTTP Server Port | `8080` |
| `SPRING_DATASOURCE_URL` | PostgreSQL JDBC Connection URL | `jdbc:postgresql://localhost:5432/gometro` |
| `SPRING_DATASOURCE_USERNAME` | PostgreSQL Database User | `postgres` |
| `SPRING_DATASOURCE_PASSWORD` | PostgreSQL Database Password | `postgres` |
| `SPRING_JPA_HIBERNATE_DDL_AUTO` | Hibernate DDL Auto strategy | `update` |

---

## 🏃 How to Run

### 1. Clone & Build
```bash
# Clone the repository
git clone <repository-url>
cd Metro_ip

# Build project with Maven Wrapper
./mvnw clean package -DskipTests
```

### 2. Configure Database
Ensure PostgreSQL is running locally or remotely and create the `gometro` database:
```sql
CREATE DATABASE gometro;
```

### 3. Run Application
```bash
# Run Spring Boot application
./mvnw spring-boot:run
```
Alternatively, run with environment variables:
```bash
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/gometro \
SPRING_DATASOURCE_USERNAME=postgres \
SPRING_DATASOURCE_PASSWORD=your_password \
./mvnw spring-boot:run
```

### 4. Access API Documentation (Swagger UI)
Once started, open your browser and navigate to:
- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON Docs**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---
