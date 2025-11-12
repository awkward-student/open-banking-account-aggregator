# 🏦 Open Banking Account Aggregator

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen)
![Kafka](https://img.shields.io/badge/Apache%20Kafka-Event--Driven-yellow)
![Docker](https://img.shields.io/badge/Docker-Containerized-blue)
![Microservices](https://img.shields.io/badge/Architecture-Microservices-indigo)
![License](https://img.shields.io/badge/License-MIT-green)
![Status](https://img.shields.io/badge/Status-Stable%20%26%20Working-success)

---

## 📘 Overview

**Open Banking Account Aggregator** is a **secure backend microservices platform** that acts as a **trusted intermediary** between:

- 🏦 **Banks / FIPs (Financial Information Providers)**
- 📊 **Financial Apps / FIUs (Financial Information Users)**
- 👤 **Users (who own and consent to share their financial data)**

This system simulates how real-world Account Aggregator (AA) frameworks (like **India’s AA under RBI**, or **PSD2 in the EU**) securely manage **user consent, authorization, and data aggregation** — without any frontend dependency.

It’s fully **Dockerized**, **Kafka-driven**, and built on **Spring Boot microservices**, with support for **JWT authentication**, **asynchronous messaging**, and **modular architecture**.

---

## 🧩 Key Features

✅ **JWT-based Authentication & Authorization** (Spring Security)  
✅ **User Consent Management** with lifecycle (GRANTED / REVOKED / EXPIRED)  
✅ **Kafka Event-Driven Communication** between services  
✅ **Data Aggregation Service** to consume & process financial data  
✅ **Mock Bank APIs** to simulate real FIPs (Bank A / Bank B)  
✅ **H2 Database** / Postgres ready - persistence  
✅ **Fully Dockerized Microservices Setup** (Compose)  
✅ **Scalable & Modular Architecture**  
✅ **Auditing & Observability Ready**  

---

## 🧠 Why This Project?

Today, financial data is **fragmented** across multiple banks.  

Users and fintech apps face these issues:

❌ Inconsistent APIs  
❌ No standardized secure data sharing  
❌ No user-controlled consent mechanism  
❌ Privacy & compliance challenges

This project solves that through:

💡 **User-centric consented data sharing**  
💡 **Standardized aggregation APIs**  
💡 **Secure inter-service communication**  
💡 **Open Banking–style backend framework**

---

## 🏗️ Architecture Overview

           ┌──────────────────────────┐
           │        User / App        │
           │ (FIU or Fintech Client)  │
           └────────────┬─────────────┘
                        │
                        ▼
           ┌──────────────────────────┐
           │       Auth Service       │
           │ (Login, Register, JWT)   │
           └────────────┬─────────────┘
                        │
                        ▼
           ┌──────────────────────────┐
           │     Consent Service      │
           │ (Manages user consents)  │
           └────────────┬─────────────┘
                        │   Kafka: consent-events
                        ▼
           ┌──────────────────────────┐
           │   Aggregator Service     │
           │ (Consumes & processes    │
           │  consent-based data)     │
           └────────────┬─────────────┘
                        │
        ┌───────────────┼────────────────┐
        ▼               ▼                ▼

    ┌──────────────┐ ┌──────────────┐ ┌──────────────┐
    │ Mock Bank A │  │ Mock Bank B │  │  Other FIPs │
    │ (Data APIs) │  │ (Data APIs) │  │ (Simulated) │
    └──────────────┘ └──────────────┘ └──────────────┘


---

## ⚙️ Microservices Overview

### 1️⃣ Auth Service
- Manages **user registration** and **login**
- Issues **JWT tokens**
- Provides `/register`, `/login` endpoints
- Used for **secure API access**

---

### 2️⃣ Consent Service
- Handles **consent creation, approval, revocation, expiry**
- Publishes **ConsentEvent** messages to Kafka topic: `consent-events`
- Listens to and validates **user tokens**
- Database: H2 / PostgreSQL-ready

**Endpoints**

- POST /api/consents → Create new consent
- GET /api/consents/{id} → Fetch consent by ID
- GET /api/consents/customer/{id} → Get all consents by customer


---

### 3️⃣ Aggregator Service
- Consumes `ConsentEvent` messages from Kafka
- Processes and aggregates user financial data
- Acts as **FIU (Financial Information User)** in the flow
- Future extension: fetches from bank adapters

**Kafka Consumer Group:** `aggregator-service`  
**Topic:** `consent-events`

---

### 4️⃣ Mock Bank Adapters (A & B)
- Represent **FIPs (banks or data providers)**
- Provide dummy APIs for:
  - Account details
  - Transactions
  - Balance
- Used to simulate multi-bank data aggregation.

---

## 🧵 Inter-Service Flow

1️⃣ **User Registration/Login**  
→ `Auth Service` issues JWT token.

2️⃣ **Consent Creation**  
→ `Consent Service` validates JWT, stores consent, and publishes Kafka event.

3️⃣ **Consent Event Propagation**  
→ Kafka topic `consent-events` carries serialized event.

4️⃣ **Aggregator Consumption**  
→ `Aggregator Service` listens for consent events and processes them.

5️⃣ **Bank Data Fetch (Simulated)**  
→ Aggregator calls Mock Bank A / B adapters.

6️⃣ **Data Aggregation (Future Scope)**  
→ Aggregator consolidates and standardizes financial data.

---

## 🧰 Tech Stack

| Layer | Technology |
|-------|-------------|
| **Language** | Java 21 |
| **Framework** | Spring Boot 3.5.7 |
| **Architecture** | Microservices |
| **Messaging** | Apache Kafka 7.6 |
| **Security** | Spring Security + JWT |
| **Database** | H2 (PostgreSQL Ready) |
| **Containerization** | Docker & Docker Compose |
| **Build Tool** | Maven |
| **Logging** | SLF4J + Logback |
| **Testing** | JUnit + Mockito |
| **Observability** | Spring Actuator |

---

## 🧩 Kafka Configuration

| Property | Producer | Consumer |
|-----------|-----------|----------|
| `bootstrap.servers` | kafka:29092 | kafka:29092 |
| `key.serializer` | StringSerializer | StringDeserializer |
| `value.serializer` | JsonSerializer | JsonDeserializer |
| `group.id` | — | aggregator-service |
| `topic` | consent-events | consent-events |

---

## 🐳 Dockerized Setup

### Directory Structure

    account-aggregator/
    │
    ├── auth-service/
    │ └── Dockerfile
    │
    ├── consent-service/
    │ └── Dockerfile
    │
    ├── aggregator-service/
    │ └── Dockerfile
    │
    ├── bank-adapter-mockbank-a/
    │ └── Dockerfile
    │
    ├── bank-adapter-mockbank-b/
    │ └── Dockerfile
    │
    └── docker-compose.yml



### Compose File Includes:
- ✅ Zookeeper
- ✅ Kafka
- ✅ Auth Service
- ✅ Consent Service
- ✅ Aggregator Service
- ✅ Bank A / Bank B Adapters

---

## 🧪 Testing the System

1️⃣ Start everything  
```bash
docker compose up --build
```

2️⃣ Register a user
```bash
POST http://localhost:8081/api/auth/register
```

3️⃣ Login to get JWT
```bash
POST http://localhost:8081/api/auth/login
```

4️⃣ Create a consent
```bash
POST http://localhost:8082/api/consents
Authorization: Bearer <your_token>
Content-Type: application/json
{
  "consentId": "12345CS1",
  "customerId": "ankit",
  "status": "GRANTED"
}
```

5️⃣ Observe Kafka + Aggregator logs

You’ll see consent propagation and event processing in Docker logs.

---

## 🧩 Future Enhancements

 - 📊 Integrate external financial APIs (mock -> real)

 - 🧠 Add Machine Learning-based insights

 - 🧾 Implement consent expiry scheduler

---

## 📜 License

This project is licensed under the MIT License.

---

## 👨‍💻 Author

Vishwas Karode - @vishwasio - Backend Developer.
Open to collaboration on distributed systems & secure architectures.
