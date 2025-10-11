# HeritageGraph360

HeritageGraph360 is a secure, multi-tenant genealogy and records verification platform that ingests historical sources, unifies identities, and generates auditable lineage insights.

## High-Level Architecture
- Edge access flows through `gateway-service` for routing and security enforcement.
- Identity flows are centralized in `identity-service` for OAuth2, SAML, and RBAC.
- Profile management lives in `profile-service`, which owns the core domain model and SQL data.
- Bulk ingestion uses `ingestion-service` with gRPC for structured imports and streaming pipelines for events.
- Insight generation and async workloads run in `insights-service` via RabbitMQ and Kafka consumers.

See `docs/uml/architecture.mmd` for the system topology diagram.

## Technology Justification (Non-Negotiable)
- Spring Boot/Spring MVC: primary REST framework and application lifecycle.
- Spring Security: public access, role-based access, OAuth2 login, and SAML federation.
- Hibernate + SQL: relational data for tenants, accounts, RBAC, policies, audit logs.
- NoSQL (MongoDB): event payloads and dynamic schema artifacts.
- REST + Swagger/OpenAPI: external APIs and documentation.
- gRPC: bulk ingestion for partner datasets and archival imports.
- Kafka: high-throughput streaming events for profile changes and lineage updates.
- Kinesis: ingestion of archive partner feeds and partner dataset mirroring.
- RabbitMQ: async workloads such as anomaly scoring and report generation.
- JUnit: service and pipeline tests.
- Git + Maven: version control and builds.
- Docker + local Kubernetes: containerized deployments and local orchestration.
- Prometheus + Grafana: metrics and dashboards.
- UML: architecture, domain, pipeline, and auth flows.
- OpenAI APIs (optional): insight explanation and anomaly summaries.

## Service Ports
- gateway-service: 8080
- identity-service: 8081
- profile-service: 8082
- ingestion-service: 8083
- insights-service: 8084
- reserved: 8085-8086 for future admin/reporting endpoints

## Repository Layout
- `services/`: microservices and shared library
- `infra/`: Docker, Kubernetes, and monitoring assets
- `schemas/`: SQL and NoSQL schema definitions
- `docs/`: architecture, UML, agile, and operational documentation

## Key Docs
- Architecture: `docs/system-architecture.md`
- Domain rules: `docs/domain-rules.md`
- Security: `docs/security.md`
- Security config examples: `docs/security-config-examples.md`
- Maven structure: `docs/maven-structure.md`
- Pipelines: `docs/pipelines.md`
- Schemas: `schemas/sql-schema.sql`, `schemas/nosql-schemas.json`
- UML diagrams: `docs/uml/*.mmd`
- APIs: `docs/api/rest-api.md`, `docs/api/grpc-api.md`
- OpenAPI sample: `docs/api/openapi-example.yaml`
- Observability: `docs/observability.md`
- RabbitMQ jobs: `docs/rabbitmq-jobs.md`
- Testing: `docs/testing.md`
- Agile artifacts: `docs/agile/*.md`
- Demo scenarios: `docs/demo-scenarios.md`
- Runbook: `docs/runbook.md`

## Local Setup (Baseline)
1) Build the project:
   - `mvn -q -DskipTests=false verify`
2) Start local dependencies:
   - `docker compose -f infra/docker/docker-compose.yml up -d`
3) Build a service container:
   - `docker build -f infra/docker/Dockerfile --build-arg JAR_FILE=services/identity-service/target/identity-service-0.1.0-SNAPSHOT.jar -t heritagegraph360/identity-service:local .`
4) Deploy to local Kubernetes:
   - `kubectl apply -f infra/k8s/namespace.yaml`
   - `kubectl apply -f infra/k8s/dependencies.yaml`
   - `kubectl apply -f infra/k8s/identity-service.yaml`

## Demo Scenarios
- Public access: view a public profile summary via gateway endpoints.
- Security: OAuth2 login and SAML federation for enterprise tenants.
- Failure: simulate Kafka consumer lag and observe backpressure metrics.
- Scaling: horizontal scale the ingestion service and verify Kinesis shard routing.
- Detailed scenarios: see `docs/demo-scenarios.md`.

## Recruiter Summary
HeritageGraph360 is a secure, enterprise-grade genealogy and lineage verification platform that unifies identity data from modern users and historical archives, processes high-volume record streams with resilient pipelines, and delivers auditable insights for researchers and institutions. It demonstrates multi-tenant security, OAuth2/SAML federation, streaming and async processing, and a rigorous documentation and testing discipline while remaining practical to deploy locally with Docker and Kubernetes.
