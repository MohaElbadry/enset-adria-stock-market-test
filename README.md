<p align="center">
  <img src="https://img.icons8.com/?size=100&id=NdeIzskkIm2U&format=png&color=000000" alt="Digital Banking" width="120" height="120">
</p>

<h1 align="center">ENSET ADRIA Stock Market</h1>

<p align="center">
  <strong>Modern Stock Market Solutions</strong><br>
  A comprehensive financial management system built with Spring Boot
</p>

<div align="center">
  
  ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-brightgreen?style=flat-square&logo=spring)
  ![Java](https://img.shields.io/badge/Java-17+-orange?style=flat-square&logo=java)
  ![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)
  
</div>

---

## Core Features

- Secure Authentication System with OAuth2
- Customer Management
- Account Operations
- Transaction Processing
- Role-Based Access Control
- RESTful API Documentation with Swagger/OpenAPI

## Technology Stack

### Backend Stack

```yaml
Runtime: Java 21
Framework: Spring Boot 3.4.5
Security: Spring Security + OAuth2 Resource Server
Database: MySQL / H2
ORM: Spring Data JPA
Documentation: SpringDoc OpenAPI 2.1.0
Build: Maven
```

### Key Dependencies

```yaml
Spring Boot Starter:
  - Web
  - Data JPA
  - DevTools
  - OAuth2 Resource Server
  - Security Test
Database:
  - MySQL Connector
  - H2 Database
Tools:
  - Lombok
  - SpringDoc OpenAPI
```

---

## Quick Start Guide

### Prerequisites

- Java 21
- Maven 3.6+
- MySQL (optional, H2 is included for development)

### Setup

```bash
# Clone & Navigate
git clone https://github.com/yourusername/digital-banking-jee.git
cd digital-banking-jee

# Run the application
mvn spring-boot:run
```

### Access Points

| Service     | URL                                   | Purpose          |
| ----------- | ------------------------------------- | ---------------- |
| Application | http://localhost:8080                 | Main Application |
| H2 Console  | http://localhost:8080/h2-console      | Database Admin   |
| API Docs    | http://localhost:8080/swagger-ui.html | Interactive API  |

---

## Project Structure

```
src/main/java/ma/enset/digitalbanking/
├── config/          # Configuration classes
├── dtos/           # Data Transfer Objects
├── entities/       # Domain entities
├── enums/          # Enumeration types
├── exceptions/     # Custom exceptions
├── mappers/        # Object mappers
├── repositories/   # Data access layer
├── security/       # Security configuration
├── services/       # Business logic
└── web/           # REST controllers
```

## API Documentation

### Core Endpoints

#### Authentication APIs

```http
POST   /api/auth/login     # User authentication
POST   /api/auth/logout    # Session termination
GET    /api/auth/profile   # User profile data
```

#### Customer Management APIs

```http
GET    /api/customers              # List all customers
POST   /api/customers              # Create new customer
GET    /api/customers/{id}         # Get customer details
PUT    /api/customers/{id}         # Update customer
DELETE /api/customers/{id}         # Remove customer
```

#### Account Management APIs

```http
GET    /api/accounts               # List all accounts
POST   /api/accounts               # Create new account
GET    /api/accounts/{id}          # Account details
PUT    /api/accounts/{id}          # Update account
DELETE /api/accounts/{id}          # Close account
```

#### Transaction APIs

```http
POST   /api/operations/debit       # Debit transaction
POST   /api/operations/credit      # Credit transaction
POST   /api/operations/transfer    # Transfer funds
GET    /api/operations/{id}        # Transaction details
GET    /api/operations/history     # Transaction history
```

---

## Configuration

### Environment Variables

```bash
curl -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6..." http://localhost:8085/customers
```

## 📦 Dependencies

The project uses the following main dependencies:

```xml
<!-- Spring Boot Starter Web -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Spring Boot Starter Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Spring Boot Starter Security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<!-- Spring Boot OAuth2 Resource Server -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>

<!-- H2 Database -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- MySQL Connector -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- Lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>

<!-- Spring Boot DevTools -->
<dependency>****
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>********
    <optional>true</optional>
</dependency>
```

## 📝 API Endpoints

### Authentication

- `POST /auth/login`: Authenticate user and get JWT token
- `GET /auth/profile`: Get authenticated user profile

### Customers

- `GET /customers`: Get all customers
- `GET /customers/search?keyword=`: Search customers by name
- `GET /customers/{id}`: Get customer by ID
- `POST /customers`: Create new customer
- `PUT /customers/{id}`: Update customer
- `DELETE /customers/{id}`: Delete customer

### Accounts

- `GET /accounts`: Get all accounts
- `GET /accounts/{id}`: Get account by ID
- `GET /customers/{customerId}/accounts`: Get accounts by customer ID
- `POST /customers/{customerId}/current-accounts`: Create current account
- `POST /customers/{customerId}/saving-accounts`: Create saving account

### Operations

- `GET /accounts/{accountId}/operations`: Get operations for account
- `POST /accounts/debit`: Debit operation
- `POST /accounts/credit`: Credit operation
- `POST /accounts/transfer`: Transfer between accounts

### Database Configuration

```yaml
# application.yml
spring:
  datasource:
    url: jdbc:h2:mem:banking_db
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:<****
    hibernate:
      ddl-auto: create-drop
    show-sql: true
```

### UML

```mermaid
classDiagram
    class Customer {
        +Long id
        +String name
        +String email
        +List~BankAccount~ bankAccounts
    }

    class BankAccount {
        +String id
        +double balance
        +Date createDate
        +AccountStatus status
        +Customer customer
        +List~AccountOperation~ accountOperations
    }

    class CurrentAccount {
        +double overDraft
    }

    class SavingAccount {
        +double interestRate
    }

    class AccountOperation {
        +Long id
        +Date operationDate
        +double amount
        +OperationType type
        +BankAccount bankAccount
        +String description
    }

    class OperationType {
        <<enumeration>>
        CREDIT
        DEBIT
    }

    class AccountStatus {
        <<enumeration>>
        CREATED
        ACTIVATED
        SUSPENDED
    }

    Customer "1" --o "many" BankAccount
    BankAccount "1" --o "many" AccountOperation
    BankAccount <|-- CurrentAccount
    BankAccount <|-- SavingAccount
    AccountOperation --> OperationType
    BankAccount --> AccountStatus
```

### Flux

```mermaid
sequenceDiagram
    participant U as Utilisateur
    participant F as Frontend
    participant B as Backend
    participant DB as Database

    U->>F: Saisie login/password
    F->>F: Validation formulaire
    F->>B: POST /auth/login
    B->>DB: Vérification credentials
    DB-->>B: Utilisateur trouvé
    B->>B: Génération JWT token
    B-->>F: Token + rôles
    F->>F: Stockage token
    F->>F: Redirection dashboard
    F->>U: Interface selon rôle
```

---

## Future Roadmap

### Phase 2 - Enhanced Features

- [ ] Responsive design for Mobile
- [ ] Analytics dashboard
- [ ] Multi-currency support
- [ ] AI-powered Chat-Bot

### Phase 3 - Enterprise Features

- [ ] Microservices migration
- [ ] Kubernetes deployment
- [ ] Third-party integrations

---

<div align="center">

### Star This Project

_If you find this banking platform valuable, please give it a star!_

---

**Digital Banking Platform - Modern Banking Solutions**

</div>

=> you gave me a good repport but i want you to make it better style

# Rapport de Projet — Système de Gestion des Cotations Boursières (Microservices Spring Cloud)

Ce document présente la conception, l’implémentation et le déploiement d’un système distribué de gestion d’entreprises et de leurs cotations boursières, basé sur Spring Cloud. Il constitue un support d’entretien technique et un rapport de synthèse.

---

## 1. Contexte et objectifs

- Contexte: Architecture microservices Spring Cloud pour gérer des entreprises cotées et leurs cotations.
- Objectifs:
  - Architecture robuste et évolutive: Discovery, Gateway, services métiers, tolérance aux pannes.
  - Communication inter-services (OpenFeign) avec résilience (Resilience4J).
  - APIs REST + outils MCP pour un agent AI/chatbot.
  - Infrastructure DevOps (Docker, Compose, Jenkins, Kubernetes) et sécurité (Keycloak/OIDC/JWT) prévue.

---

## 2. Périmètre fonctionnel

- Company-Service: CRUD Entreprises (id, name, listingDate, currentStockPrice, sector).
- Stock-Service: CRUD Cotations (id, date, open/high/low/close, volume, companyId) + mise à jour du prix courant de l’entreprise via la dernière closeValue.
- Chatbot-Service: Outils MCP exposant les opérations Company/Stock pour un agent AI.
- Services techniques: Discovery (Eureka) et Gateway (Spring Cloud Gateway).

---

## 3. Architecture globale

```text
Clients (Web React / Mobile Flutter / MCP Agent)
          │  REST + JWT
          ▼
   Spring Cloud Gateway (8888) ───────────────┐
          │           ▲                       │
          │  Get Registry                     │
          ▼           │                       │
     Eureka Server (8761) <── Register ── Company-Service (8081)
                                       └─(OpenFeign)
                                         ▲
                                         └────────── Stock-Service (8082)

Keycloak (JWT/OIDC) – prévu pour la sécurité via Gateway (propagation du token)
```

Illustration (schéma): `docs/architecture.png` (placeholder).

---

## 4. Organisation du dépôt

```text
enset-adria-stock-market-test/
├── pom.xml (parent multi-modules)
├── discovery-service/
├── gateway-service/
├── company-service/
├── stock-service/
├── chatbot-service/
├── docker-compose.yml
├── k8s/ (manifests Kubernetes)
├── Jenkinsfile
├── README.md
└── REPORT.md (ce document)
```

---

## 5. Stack technique

- Java 17, Spring Boot 3.5.x
- Spring Cloud 2025.x: Eureka Server/Client, Spring Cloud Gateway, OpenFeign, LoadBalancer
- JPA + H2 (dev)
- Resilience4J (circuit breaker)
- Keycloak/OAuth2/OIDC/JWT (prévu)
- Spring AI MCP Server WebMVC (chatbot-service)
- Docker, Docker Compose, Jenkins, Kubernetes

---

## 6. Services et API

### 6.1 Discovery-Service (Eureka)

- Code: `discovery-service/src/main/java/.../DiscoveryServiceApplication.java`
- Propriétés: `server.port=8761`, `eureka.client.register-with-eureka=false`, `fetch-registry=false`
- Rôle: Registre de services pour l’auto-découverte.

### 6.2 Gateway-Service

- Code: `gateway-service/src/main/java/.../GatewayServiceApplication.java`
- Config YAML: `gateway-service/src/main/resources/application.yml`
  - Discovery locator activé + routes explicites:
    - `/api/companies/**` → `lb://company-service`
    - `/api/stocks/**` → `lb://stock-service`

### 6.3 Company-Service

- Dossier: `company-service/`
- Entité: `entities/Company.java`
- DTO: `dto/CompanyRequestDTO.java`, `dto/CompanyResponseDTO.java`
- Repository: `repositories/CompanyRepository.java`
- Service: `services/CompanyService.java`, `services/CompanyServiceImpl.java`
- Controller: `web/CompanyRestController.java`
- Seed (dev): `CompanyServiceApplication` → `CommandLineRunner`
- Persistence: H2 (console activée), `spring.jpa.hibernate.ddl-auto=update`

Endpoints REST:

```http
POST   /api/companies
GET    /api/companies
GET    /api/companies/{id}
PUT    /api/companies/{id}/price?value=DOUBLE
DELETE /api/companies/{id}
GET    /api/companies/sector/{sector}
```

Exemple payload (création):

```json
{
  "name": "AdriaTech",
  "listingDate": "2024-05-12",
  "currentStockPrice": 125.5,
  "sector": "IT"
}
```

### 6.4 Stock-Service

- Dossier: `stock-service/`
- Entité: `entities/StockMarket.java`
- DTO: `dto/StockRequestDTO.java`, `dto/StockResponseDTO.java`
- Mapper: `mappers/StockMapper.java`
- Repository: `repositories/StockRepository.java`
  - Inclut `findTopByCompanyIdOrderByDateDesc`
- Feign Client: `clients/CompanyRestClient.java`
- Service: `services/StockService.java`, `services/StockServiceImpl.java`
  - Création d’une cotation → mise à jour du prix courant de l’entreprise avec la `closeValue` de la dernière cotation (via Feign)
  - CircuitBreaker Resilience4J pour robustesse si Company-Service down
- Controller: `web/StockRestController.java`
- Seed (dev): `StockServiceApplication` → `CommandLineRunner`
- Persistence: H2 + JPA

Endpoints REST:

```http
POST   /api/stocks
GET    /api/stocks
GET    /api/stocks/{id}
DELETE /api/stocks/{id}
GET    /api/stocks/company/{companyId}
PUT    /api/stocks/company/{companyId}/update-company-price
```

Exemple payload (création de cotation):

```json
{
  "date": "2025-11-17T10:00:00",
  "openValue": 100.0,
  "highValue": 120.0,
  "lowValue": 95.0,
  "closeValue": 115.0,
  "volume": 5000,
  "companyId": 1
}
```

### 6.5 Chatbot-Service (MCP)

- Dossier: `chatbot-service/`
- Dépendances: Spring AI BOM + `spring-ai-starter-mcp-server-webmvc`, OpenFeign, Eureka client
- Feign Clients: `clients/CompanyClient.java`, `clients/StockClient.java`
- MCP Tools: `tools/McpTools.java`
  - Outils: `get_companies`, `get_company_by_id`, `get_companies_by_sector`,
    `create_company`, `update_company_price`, `get_stocks`, `get_stock_by_id`,
    `get_stocks_by_company`, `create_stock`, `recalculate_company_price`
- Config: `application.properties` (port 8090, Eureka, config off)
- À étendre: intégration Telegram/LLM

---

## 7. Sécurité (prévue)

- Keycloak (realm: `stock-market-realm`)
- Clients: `gateway`, `company-service`, `stock-service` (resource-server)
- Rôles: `USER` (lecture), `ADMIN` (CRUD)
- Flux: Le Gateway valide le JWT et propage l’en-tête Authorization vers les services.
- À implémenter: starters `spring-boot-starter-oauth2-resource-server`, `Issuer URI`,
  `JwtAuthenticationConverter`, filtres Gateway.

---

## 8. DevOps — Architecture et outillage

### 8.1 Architecture DevOps (vue d’ensemble)

```text
Source Repo (GitHub) → CI (Jenkins) → Images Docker → (Compose pour dev) / (K8s pour prod)
                               │
                             Tests
```

### 8.2 Dockerisation

- Dockerfiles présents dans chaque module (Java 17 JRE). Exemple:

```dockerfile
FROM eclipse-temurin:17-jre
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
```

- `.dockerignore` ajouté pour accélérer les builds.

### 8.3 Docker Compose (local)

Fichier: `docker-compose.yml`

- Services: discovery, gateway, company, stock, chatbot, (optionnel) keycloak
- Réseau: `stock-market-net`
- Variables d’env: `EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE=http://discovery-service:8761/eureka`

Commandes:

```bash
mvn -q -DskipTests clean package
docker compose build
docker compose up -d
```

Accès:

- Eureka: <http://localhost:8761>
- Gateway: <http://localhost:8888>

### 8.4 CI/CD – Jenkins

Fichier: `Jenkinsfile`

- Étapes: Checkout → Maven Build → Build images Docker → (optionnel) Compose Up
- Paramètre: `DEPLOY_DEV=true` pour lancer `docker compose up -d` après build

### 8.5 Kubernetes (optionnel)

Manifests dans `k8s/`:

- `discovery.yaml` (ClusterIP)
- `company.yaml` (ClusterIP)
- `stock.yaml` (ClusterIP)
- `gateway.yaml` (NodePort 30088)
- `chatbot.yaml` (ClusterIP)

Déploiement:

```bash
kubectl apply -f k8s/discovery.yaml
kubectl apply -f k8s/company.yaml
kubectl apply -f k8s/stock.yaml
kubectl apply -f k8s/gateway.yaml
kubectl apply -f k8s/chatbot.yaml
```

Pré-requis: Pousser les images dans un registre accessible au cluster et adapter `image:` si besoin.

Sujets d’amélioration:

- Probes liveness/readiness (Actuator)
- Ingress au lieu de NodePort
- ConfigMaps/Secrets pour Keycloak/DB
- Observabilité: Prometheus/Grafana

---

## 9. Démonstrations (exemples via Gateway)

Lister les entreprises:

```bash
curl -s http://localhost:8888/api/companies
```

Créer une entreprise:

```bash
curl -s -X POST http://localhost:8888/api/companies \
  -H 'Content-Type: application/json' \
  -d '{"name":"TestCorp","listingDate":"2024-01-10","currentStockPrice":99.5,"sector":"IT"}'
```

Créer une cotation et mettre à jour le prix courant:

```bash
curl -s -X POST http://localhost:8888/api/stocks \
  -H 'Content-Type: application/json' \
  -d '{"date":"2025-11-17T12:30:00","openValue":120,"highValue":130,"lowValue":118,"closeValue":127,"volume":7000,"companyId":1}'

curl -s -X PUT http://localhost:8888/api/stocks/company/1/update-company-price
```

---

## 10. Captures & snippets (placeholders)

- Schéma global: `docs/architecture.png`
- Eureka Dashboard: `screenshots/eureka-dashboard.png`
- Gateway routes: `screenshots/gateway-routes.png`
- Tests Postman Company: `screenshots/company-postman.png`
- Tests Postman Stock: `screenshots/stock-postman.png`
- Console H2 Company: `screenshots/company-h2.png`
- CI Jenkins: `screenshots/jenkins-pipeline.png`

Snippets clés (références):

- `company-service/web/CompanyRestController.java`
- `stock-service/web/StockRestController.java`
- `stock-service/services/StockServiceImpl.java` (Feign + CircuitBreaker)
- `chatbot-service/tools/McpTools.java` (exposition MCP tools)

---

## 11. Bilan et pistes futures

- Réalisé: Architecture microservices, Discovery, Gateway, Company, Stock (Feign + Resilience4J), Chatbot MCP, Docker/Compose, Jenkins, manifests K8s.
- À finaliser: Sécurité Keycloak/JWT, Frontend React/Angular, Mobile Flutter, Monitoring et traces, Ingress K8s, tests complets (unitaires/intégration/end-to-end).

---

## 12. Annexes

- Versions:
  - Java 17, Spring Boot 3.5.x, Spring Cloud 2025.x
  - Keycloak 23.x (optionnel)
- Rappels: Seeds (Company/Stock) activés pour dev (H2). En prod: DB (MySQL/PostgreSQL), migrations (Flyway/Liquibase), données initiales contrôlées.

# Rapport de Projet — Système de Gestion des Cotations Boursières (Microservices Spring Cloud)

Ce document présente la conception, l’implémentation et le déploiement d’un système distribué de gestion d’entreprises et de leurs cotations boursières, basé sur Spring Cloud. Il constitue un support d’entretien technique et un rapport de synthèse.

---

## 1. Contexte et objectifs

- Contexte: Architecture microservices Spring Cloud pour gérer des entreprises cotées et leurs cotations.
- Objectifs:
  - Architecture robuste et évolutive: Discovery, Gateway, services métiers, tolérance aux pannes.
  - Communication inter-services (OpenFeign) avec résilience (Resilience4J).
  - APIs REST + outils MCP pour un agent AI/chatbot.
  - Infrastructure DevOps (Docker, Compose, Jenkins, Kubernetes) et sécurité (Keycloak/OIDC/JWT) prévue.

---

## 2. Périmètre fonctionnel

- Company-Service: CRUD Entreprises (id, name, listingDate, currentStockPrice, sector).
- Stock-Service: CRUD Cotations (id, date, open/high/low/close, volume, companyId) + mise à jour du prix courant de l’entreprise via la dernière closeValue.
- Chatbot-Service: Outils MCP exposant les opérations Company/Stock pour un agent AI.
- Services techniques: Discovery (Eureka) et Gateway (Spring Cloud Gateway).

---

## 3. Architecture globale

```mermaid
flowchart LR
  subgraph Clients
    W["Web UI (React/Angular)"]
    M["Mobile App (Flutter)"]
    C["Chatbot / MCP Client"]
  end

  subgraph Edge[API Gateway]
    G["Spring Cloud Gateway\n(port 8888)"]
  end

  subgraph Discovery[Service Discovery]
    E["Eureka Server\n(port 8761)"]
  end

  subgraph Biz[Business Microservices]
    CS["company-service\n(port 8081)"]
    SS["stock-service\n(port 8082)"]
    CHS["chatbot-service\n(port 8090)"]
  end

  subgraph Security[Identity Provider]
    K["Keycloak (OIDC/JWT)"]
  end

  W -->|REST + JWT| G
  M -->|REST + JWT| G
  C -->|MCP HTTP| CHS

  G -->|Service Discovery| E
  CS -->|Register| E
  SS -->|Register| E
  CHS -->|Register| E

  G -->|/api/companies/**| CS
  G -->|/api/stocks/**| SS

  SS -->|OpenFeign + CB| CS

  G -->|JWT Validation| K
  CS -->|Resource Server (prévu)| K
  SS -->|Resource Server (prévu)| K
  CHS -->|Resource Server (optionnel)| K
```

Illustration (rendu Mermaid): architecture globale microservices + sécurité.

---

## 4. Organisation du dépôt

```text
enset-adria-stock-market-test/
├── pom.xml (parent multi-modules)
├── discovery-service/
├── gateway-service/
├── company-service/
├── stock-service/
├── chatbot-service/
├── docker-compose.yml
├── k8s/ (manifests Kubernetes)
├── Jenkinsfile
├── README.md
└── REPORT.md (ce document)
```

---

## 5. Stack technique

- Java 17, Spring Boot 3.5.x
- Spring Cloud 2025.x: Eureka Server/Client, Spring Cloud Gateway, OpenFeign, LoadBalancer
- JPA + H2 (dev)
- Resilience4J (circuit breaker)
- Keycloak/OAuth2/OIDC/JWT (prévu)
- Spring AI MCP Server WebMVC (chatbot-service)
- Docker, Docker Compose, Jenkins, Kubernetes

---

## 6. Services et API

### 6.1 Discovery-Service (Eureka)

- Code: `discovery-service/src/main/java/.../DiscoveryServiceApplication.java`
- Propriétés: `server.port=8761`, `eureka.client.register-with-eureka=false`, `fetch-registry=false`
- Rôle: Registre de services pour l’auto-découverte.

### 6.2 Gateway-Service

- Code: `gateway-service/src/main/java/.../GatewayServiceApplication.java`
- Config YAML: `gateway-service/src/main/resources/application.yml`
  - Discovery locator activé + routes explicites:
    - `/api/companies/**` → `lb://company-service`
    - `/api/stocks/**` → `lb://stock-service`

### 6.3 Company-Service

- Dossier: `company-service/`
- Entité: `entities/Company.java`
- DTO: `dto/CompanyRequestDTO.java`, `dto/CompanyResponseDTO.java`
- Repository: `repositories/CompanyRepository.java`
- Service: `services/CompanyService.java`, `services/CompanyServiceImpl.java`
- Controller: `web/CompanyRestController.java`
- Seed (dev): `CompanyServiceApplication` → `CommandLineRunner`
- Persistence: H2 (console activée), `spring.jpa.hibernate.ddl-auto=update`

Endpoints REST:

```http
POST   /api/companies
GET    /api/companies
GET    /api/companies/{id}
PUT    /api/companies/{id}/price?value=DOUBLE
DELETE /api/companies/{id}
GET    /api/companies/sector/{sector}
```

Exemple payload (création):

```json
{
  "name": "AdriaTech",
  "listingDate": "2024-05-12",
  "currentStockPrice": 125.5,
  "sector": "IT"
}
```

### 6.4 Stock-Service

- Dossier: `stock-service/`
- Entité: `entities/StockMarket.java`
- DTO: `dto/StockRequestDTO.java`, `dto/StockResponseDTO.java`
- Mapper: `mappers/StockMapper.java`
- Repository: `repositories/StockRepository.java`
  - Inclut `findTopByCompanyIdOrderByDateDesc`
- Feign Client: `clients/CompanyRestClient.java`
- Service: `services/StockService.java`, `services/StockServiceImpl.java`
  - Création d’une cotation → mise à jour du prix courant de l’entreprise avec la `closeValue` de la dernière cotation (via Feign)
  - CircuitBreaker Resilience4J pour robustesse si Company-Service down
- Controller: `web/StockRestController.java`
- Seed (dev): `StockServiceApplication` → `CommandLineRunner`
- Persistence: H2 + JPA

Endpoints REST:

```http
POST   /api/stocks
GET    /api/stocks
GET    /api/stocks/{id}
DELETE /api/stocks/{id}
GET    /api/stocks/company/{companyId}
PUT    /api/stocks/company/{companyId}/update-company-price
```

Exemple payload (création de cotation):

```json
{
  "date": "2025-11-17T10:00:00",
  "openValue": 100.0,
  "highValue": 120.0,
  "lowValue": 95.0,
  "closeValue": 115.0,
  "volume": 5000,
  "companyId": 1
}
```

### 6.5 Chatbot-Service (MCP)

- Dossier: `chatbot-service/`
- Dépendances: Spring AI BOM + `spring-ai-starter-mcp-server-webmvc`, OpenFeign, Eureka client
- Feign Clients: `clients/CompanyClient.java`, `clients/StockClient.java`
- MCP Tools: `tools/McpTools.java`
  - Outils: `get_companies`, `get_company_by_id`, `get_companies_by_sector`,
    `create_company`, `update_company_price`, `get_stocks`, `get_stock_by_id`,
    `get_stocks_by_company`, `create_stock`, `recalculate_company_price`
- Config: `application.properties` (port 8090, Eureka, config off)
- À étendre: intégration Telegram/LLM

---

## 7. Sécurité (prévue)

- Keycloak (realm: `stock-market-realm`)
- Clients: `gateway`, `company-service`, `stock-service` (resource-server)
- Rôles: `USER` (lecture), `ADMIN` (CRUD)
- Flux: Le Gateway valide le JWT et propage l’en-tête Authorization vers les services.
- À implémenter: starters `spring-boot-starter-oauth2-resource-server`, `Issuer URI`,
  `JwtAuthenticationConverter`, filtres Gateway.

---

## 8. DevOps — Architecture et outillage

### 8.1 Architecture DevOps (vue d’ensemble)

```mermaid
flowchart LR
  Dev[(Developer)] --> PR["Git Commit / PR\n(GitHub)"]
  PR --> CI["Jenkins CI Pipeline"]

  subgraph Build[Build & Test]
    CI --> MVN["Maven Build\n(unit/integration tests)"]
    MVN --> IMG["Docker Build\n(one image per service)"]
  end

  subgraph Registry[Container Registry]
    IMG --> REG["Docker Registry\n(Docker Hub / Private)"]
  end

  subgraph EnvDev[Dev Environment]
    DC["docker-compose\n(local)"]
  end

  subgraph EnvProd[Prod / Preprod]
    K8S["Kubernetes Cluster"]
  end

  REG --> DC
  REG --> K8S

  CI -->|optionnel: deploy dev| DC
  CI -->|CD pipeline| K8S
```

Cette vue montre le cycle complet: code → CI Jenkins → images Docker → exécution via Docker Compose ou Kubernetes.

### 8.2 Dockerisation

- Dockerfiles présents dans chaque module (Java 17 JRE). Exemple:

```dockerfile
FROM eclipse-temurin:17-jre
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
```

- `.dockerignore` ajouté pour accélérer les builds.

### 8.3 Docker Compose (local)

Fichier: `docker-compose.yml`

- Services: discovery, gateway, company, stock, chatbot, (optionnel) keycloak
- Réseau: `stock-market-net`
- Variables d’env: `EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE=http://discovery-service:8761/eureka`

Commandes:

```bash
mvn -q -DskipTests clean package
docker compose build
docker compose up -d
```

Accès:

- Eureka: <http://localhost:8761>
- Gateway: <http://localhost:8888>

### 8.4 CI/CD – Jenkins

Fichier: `Jenkinsfile`

- Étapes: Checkout → Maven Build → Build images Docker → (optionnel) Compose Up
- Paramètre: `DEPLOY_DEV=true` pour lancer `docker compose up -d` après build

### 8.5 Kubernetes (optionnel)

Manifests dans `k8s/`:

- `discovery.yaml` (ClusterIP)
- `company.yaml` (ClusterIP)
- `stock.yaml` (ClusterIP)
- `gateway.yaml` (NodePort 30088)
- `chatbot.yaml` (ClusterIP)

Déploiement:

```bash
kubectl apply -f k8s/discovery.yaml
kubectl apply -f k8s/company.yaml
kubectl apply -f k8s/stock.yaml
kubectl apply -f k8s/gateway.yaml
kubectl apply -f k8s/chatbot.yaml
```

Pré-requis: Pousser les images dans un registre accessible au cluster et adapter `image:` si besoin.

Sujets d’amélioration:

- Probes liveness/readiness (Actuator)
- Ingress au lieu de NodePort
- ConfigMaps/Secrets pour Keycloak/DB
- Observabilité: Prometheus/Grafana

---

## 9. Démonstrations (exemples via Gateway)

Lister les entreprises:

```bash
curl -s http://localhost:8888/api/companies
```

Créer une entreprise:

```bash
curl -s -X POST http://localhost:8888/api/companies \
  -H 'Content-Type: application/json' \
  -d '{"name":"TestCorp","listingDate":"2024-01-10","currentStockPrice":99.5,"sector":"IT"}'
```

Créer une cotation et mettre à jour le prix courant:

```bash
curl -s -X POST http://localhost:8888/api/stocks \
  -H 'Content-Type: application/json' \
  -d '{"date":"2025-11-17T12:30:00","openValue":120,"highValue":130,"lowValue":118,"closeValue":127,"volume":7000,"companyId":1}'

curl -s -X PUT http://localhost:8888/api/stocks/company/1/update-company-price
```

---

## 10. Captures & snippets (placeholders)

- Schéma global: `docs/architecture.png`
- Eureka Dashboard: `screenshots/eureka-dashboard.png`
- Gateway routes: `screenshots/gateway-routes.png`
- Tests Postman Company: `screenshots/company-postman.png`
- Tests Postman Stock: `screenshots/stock-postman.png`
- Console H2 Company: `screenshots/company-h2.png`
- CI Jenkins: `screenshots/jenkins-pipeline.png`

Snippets clés (références):

- `company-service/web/CompanyRestController.java`
- `stock-service/web/StockRestController.java`
- `stock-service/services/StockServiceImpl.java` (Feign + CircuitBreaker)
- `chatbot-service/tools/McpTools.java` (exposition MCP tools)

---

## 11. Bilan et pistes futures

- Réalisé: Architecture microservices, Discovery, Gateway, Company, Stock (Feign + Resilience4J), Chatbot MCP, Docker/Compose, Jenkins, manifests K8s.
- À finaliser: Sécurité Keycloak/JWT, Frontend React/Angular, Mobile Flutter, Monitoring et traces, Ingress K8s, tests complets (unitaires/intégration/end-to-end).

---

## 12. Annexes

- Versions:
  - Java 17, Spring Boot 3.5.x, Spring Cloud 2025.x
  - Keycloak 23.x (optionnel)
- Rappels: Seeds (Company/Stock) activés pour dev (H2). En prod: DB (MySQL/PostgreSQL), migrations (Flyway/Liquibase), données initiales contrôlées.
