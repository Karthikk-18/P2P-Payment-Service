# P2P Payment Platform with Fraud Detection

A peer-to-peer payment system built with Java and Spring Boot. This is **Phase 1** of a multi-phase project that will progressively introduce Apache Kafka, Docker, Kubernetes .

> 🚧 **Work in Progress** — Currently in Phase 1: Core Payment REST API

---

## What This Phase Covers

- User registration and login with JWT authentication
- Wallet creation and balance management
- Send money between users
- Transaction history
- Input validation and error handling

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4 |
| Database | PostgreSQL |
| Auth | JWT (Spring Security) |
| Build Tool | Maven |

---

## Database Schema

```
users
  id, name, email, password, created_at

wallets
  id, user_id, balance

transactions
  id, sender_id, receiver_id, amount, status, created_at
```

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL

### Setup

```bash
# Clone the repository
git clone https://github.com/your-username/p2p-payment-platform.git
cd p2p-payment-platform

# Configure your database in
# src/main/resources/application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/p2p_db
spring.datasource.username=your_username
spring.datasource.password=your_password

# Build and run
mvn clean package
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`.

---

## API Reference

### Auth

```
POST /auth/register    → Register a new user
POST /auth/login       → Login and receive JWT
```

### Payments

```
POST  /payments/send        → Send money to another user
GET   /payments/history     → Get your transaction history
GET   /payments/{id}        → Get a specific transaction
```

### Wallet

```
GET   /wallet/balance       → Get your current balance
POST  /wallet/deposit       → Add funds to your wallet
```

All endpoints except `/auth/**` require:
```
Authorization: Bearer <token>
```

---

## Validation Rules

- Amount must be greater than zero
- Sender must have sufficient balance
- User cannot send money to themselves
- Recipient must exist

---

## Project Roadmap

- [x] Phase 1 — Core Payment REST API 
- [ ] Phase 2 — Kafka Async Pipeline + Fraud Detection
- [ ] Phase 3 — Dockerize Everything
- [ ] Phase 4 — Deploy on Kubernetes
- [ ] Phase 5 — Kafka Managed by Strimzi
- [ ] Phase 6 — Prometheus + Grafana Observability

---

## License

[MIT](LICENSE)
