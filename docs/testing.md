# JUnit Testing Strategy

## Scope
- Unit tests for service logic and validation rules.
- Integration tests for REST controllers, security filters, and repositories.
- Pipeline tests for Kafka/Kinesis ingestion and RabbitMQ jobs.

## Critical Coverage
- Profile creation rule enforcing email or phone.
- Duplicate detection threshold and merge workflow.
- RBAC enforcement and tenant isolation.
- Ingestion validation for schemas and payloads.

## Tooling
- JUnit 5 with Spring Boot test starters.
- Testcontainers for PostgreSQL and MongoDB in integration tests.
- Embedded Kafka and RabbitMQ for pipeline tests.
