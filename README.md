# P2P Payment Service

 A microservices-based peer-to-peer payment platform built with Java & Spring Boot.
 Designed to learn distributed systems concepts progressively — from REST APIs to Kafka, Docker, Kubernetes, and Strimzi.

---

## Architecture
```
[Client]
    ↓
[API Gateway :8080]
    ├── /api/users/**         →  user-service          :8081
    ├── /api/wallets/**       →  wallet-service        :8082
    └── /api/transactions/**  →  transaction-service   :8083
                              fraud-detection-service  :8084
                              notification-service     :8085
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
| Message Broker | Apache Kafka (Phase 2) |
| Containerization | Docker (Phase 3) |
| Orchestration | Kubernetes (Phase 4) |
| Kafka on K8s | Strimzi (Phase 5) |
| Observability | Prometheus + Grafana (Phase 6) |

---

## Services

| Service | Port | Status | Responsibility |
|---|---|---|---|
| user-service | 8081 | ✅ Done | Registration, profile management |
| wallet-service | 8082 | ✅ Done| Wallet creation, deposits, balance |
| transaction-service | 8083 | ✅ Done | P2P transfers, history |
| api-gateway | 8080 | ✅ Done | Routing, rate limiting |
| fraud-detection-service | 8084 | ⬜ Pending | Async fraud analysis |
| notification-service | 8085 | ⬜ Pending | Async notifications |

---

## Roadmap

- [x] Phase 1 — REST Microservices + OpenFeign + API Gateway
- [x] Phase 1.5 — JWT Security
- [ ] Phase 2 — Kafka + Fraud Detection + Notifications
- [ ] Phase 3 — Docker + Docker Compose
- [ ] Phase 4 — Kubernetes Manifests
- [ ] Phase 5 — Strimzi Kafka Operator
- [ ] Phase 6 — Prometheus + Grafana

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
Each service owns its database. No shared tables. No cross-service JPA relationships. Services communicate via REST (Phase 1) and Kafka (Phase 2).

**Synchronous vs Asynchronous**
User validation and balance checks are synchronous — the caller needs an immediate answer. Fraud detection and notifications are asynchronous — they don't block the payment flow. That's why Kafka enters in Phase 2.

**Why Strimzi?**
Running Kafka manually on Kubernetes is painful. Strimzi manages Kafka as a Kubernetes-native resource using CRDs — `Kafka`, `KafkaTopic`, `KafkaUser`. It's how production teams run Kafka on K8s.

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
  ├── api-gateway/
  └── README.md
```

---

## 👤 Author

**Karthik** — [GitHub](https://github.com/Karthikk-18)

---

## 📄 License

[MIT](LICENSE)
