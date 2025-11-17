# Stock Market Microservices — ENSET ADRIA Test

A Spring Cloud microservices system to manage companies and stock market quotations, with Eureka discovery, API Gateway, and placeholders for Stock and Chatbot services.

## Monorepo layout

```text
enset-adria-stock-market-test/
├── pom.xml                # Maven parent (aggregator)
├── discovery-service/     # Eureka Server (8761)
├── gateway-service/       # Spring Cloud Gateway (8888)
├── company-service/       # Company CRUD (to be implemented) (8081)
├── stock-service/         # Stock quotations (to be implemented) (8082)
└── chatbot-service/       # MCP/Telegram Agent (placeholder) (8090)
```

## Tech stack

- Spring Boot 3.5.x, Java 17
- Spring Cloud 2025.x (Eureka, Gateway, OpenFeign)
- H2/MySQL (later), JPA (later)
- Resilience4J (later)
- Keycloak (later)
- MCP Agent + Telegram (later)

## Getting started

Prerequisites

- Java 17 (JDK)
- Maven 3.9+

Build all modules

```bash
mvn -q clean install
```

Run in dev (separate terminals)

- Discovery (Eureka):

```bash
mvn -q -pl discovery-service -am spring-boot:run
```

- Gateway:

```bash
mvn -q -pl gateway-service -am spring-boot:run
```

- Company:

```bash
mvn -q -pl company-service -am spring-boot:run
```

- Stock (placeholder):

```bash
mvn -q -pl stock-service -am spring-boot:run
```

- Chatbot (placeholder):

```bash
mvn -q -pl chatbot-service -am spring-boot:run
```

Eureka dashboard: <http://localhost:8761>

## Roadmap (steps)

- [x] Multi-module Maven parent & basic services
- [x] Discovery & Gateway wired via Eureka
- [ ] Company service domain (Entity, DTO, Repository, Service, Controller)
- [ ] Stock service domain + OpenFeign to Company + Resilience4J
- [ ] Security with Keycloak (OAuth2/OIDC/JWT) via Gateway
- [ ] Chatbot service (MCP tools + Telegram)
- [ ] React/Angular frontend
- [ ] Flutter mobile app
- [ ] Docker & docker-compose, CI/CD (Jenkins), k8s manifests

## Notes

- Gateway is configured with discovery-based routing. When Company/Stock services register to Eureka, routes like `http://localhost:8888/COMPANY-SERVICE/**` (or lowercase) are available. We will later add explicit route predicates (e.g., `/api/companies/**`).
- Ports: Discovery 8761, Gateway 8888, Company 8081, Stock 8082, Chatbot 8090.

## License

For academic evaluation purposes.
For academic evaluation purposes.
