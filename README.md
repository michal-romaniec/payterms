# PayTerms

[![CI](https://github.com/michal-romaniec/payterms/actions/workflows/ci.yml/badge.svg)](https://github.com/michal-romaniec/payterms/actions/workflows/ci.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=michal-romaniec_payterms&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=michal-romaniec_payterms)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=michal-romaniec_payterms&metric=coverage)](https://sonarcloud.io/summary/new_code?id=michal-romaniec_payterms)

Contract-based receivables and payables management for SMEs.

PayTerms links contracts with counterparties to what follows from them:
expected payments and late payment interest on the revenue side,
cost invoices from KSeF and budget control on the cost side.

> **Status:** early development — v0.0 (project skeleton) is done. See the roadmap below.

## Tech stack

Java 25 · Spring Boot 4 · Spring Data JPA / Hibernate · PostgreSQL · Flyway ·
Spring Modulith · JUnit 5 · Testcontainers · Docker · GitHub Actions · SonarQube Cloud

## Roadmap

- [x] **v0.0** — project skeleton, CI, code quality analysis
- [ ] **v0.1** — organizational structure, counterparties, contracts with versioned terms
- [ ] **v0.2** — receivables, late payment interest, payment reminders, web UI
- [ ] **v0.3** — cost invoices imported from KSeF, matching to contracts
- [ ] **v0.4** — budget control per department and cost center

## Architecture

Modular monolith: one application, one database, modules with explicit boundaries
verified by Spring Modulith tests.

| Module         | Responsibility                                                        |
|----------------|-----------------------------------------------------------------------|
| `shared`       | Value types used across modules (Money, Period, TaxId)                |
| `organization` | Branches, departments and cost centers assigned to them               |
| `contracts`    | Contracts with counterparties, versioned terms, amendments            |
| `receivables`  | Expected payments, incoming payments, late payment interest           |
| `payables`     | Cost invoices imported from KSeF, matching and approval               |
| `budget`       | Plans per department and cost center, reservations and spending       |

Database schema is versioned with Flyway; Hibernate only validates it against the entities.

## Running locally

Requirements: JDK 25 and Docker.

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

On Windows use `.\mvnw` instead of `./mvnw`.

PostgreSQL starts automatically via Docker Compose. Once the application is up:

- API documentation: http://localhost:8080/swagger-ui.html
- Health check: http://localhost:8080/actuator/health

The `local` profile additionally exposes Actuator endpoints useful during development
(`beans`, `conditions`, `mappings`, `env`).

## Running tests

```bash
./mvnw verify
```

Integration tests use Testcontainers, so Docker must be running.
The coverage report is generated in `target/site/jacoco/index.html`.
