# P2P Payment Service

 A microservices-based peer-to-peer payment platform built with Java & Spring Boot.
 Designed to learn distributed systems concepts progressively — from REST APIs to Kafka, Docker, and Kubernetes.

---

## Architecture
```
[Client]
    ↓
[API Gateway:8080]
    ├── /api/users/**         →  user-service          :8081
    ├── /api/wallets/**       →  wallet-service        :8082
    └── /api/transactions/**  →  transaction-service   :8083
                              fraud-detection-service  :8084
                              notification-service    :8085
```

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.0.* |
| Service Communication | OpenFeign |
| API Gateway | Spring Cloud Gateway |
| Database | PostgreSQL (Supabase) |
| Message Broker | Apache Kafka |
| Containerization | Docker |
| Orchestration | Kubernetes |

---

## Services

| Service | Port | Responsibility |
|---|---|---|
| user-service | 8081 | Registration, profile management |
| wallet-service | 8082 | Wallet creation, deposits, balance |
| transaction-service | 8083 | P2P transfers, history |
| api-gateway | 8080 | Routing, rate limiting |
| fraud-detection-service | 8084 | Async fraud analysis |
| notification-service | 8085 | Async notifications |

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL or Supabase account

### Run Locally
```bash
# Clone the repo
git clone https://github.com/Karthikk-18/P2P-Payment-Service.git
cd P2P-Payment-Service
```

Create `application-local.yml` inside each service under `src/main/resources/`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://your-host:5432/postgres
    username: your-username
    password: your-password
```
```bash
# Run user-service
cd user-service
mvn spring-boot:run

# Run wallet-service (new terminal)
cd wallet-service
mvn spring-boot:run
```

---

## 📡 API Reference

### user-service — :8081
```
POST    /api/users          →  Register user
GET     /api/users/{id}     →  Get user by ID
GET     /api/users          →  Get all users
DELETE  /api/users/{id}     →  Delete user
```

### wallet-service — :8082
```
POST    /api/wallets                →  Create wallet
GET     /api/wallets/{id}/balance   →  Get balance
POST    /api/wallets/{id}/deposit   →  Deposit funds
```

### transaction-service — :8083
```
POST    /api/transactions/send      →  Send money
GET     /api/transactions/history   →  Transaction history
GET     /api/transactions/{id}      →  Get transaction
```

---

## Key Design Decisions

**Database per service**
Each service owns its database. No shared tables. No cross-service JPA relationships. Services communicate via REST and Kafka.

**Synchronous vs Asynchronous**
User validation and balance checks are synchronous — the caller needs an immediate answer. Fraud detection and notifications are asynchronous — they don't block the payment flow. That's why Kafka enters in Phase 2.

---

## 📁 Project Structure
```
P2P-Payment-Service/
  ├── user-service/
  │   ├── src/
  │   ├── pom.xml
  │   └── README.md
  ├── wallet-service/
  │   ├── src/
  │   ├── pom.xml
  │   └── README.md
  ├── transaction-service/
  │   ├── src/
  │   ├── pom.xml
  │   └── README.md
  ├── api-gateway/
  └── README.md
```

---

## 📄 License

[MIT](LICENSE)
