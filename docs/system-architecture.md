# System Architecture

## Overview
HeritageGraph360 is a multi-tenant platform that separates edge access, identity, profile management, ingestion, and insights into dedicated services. Tenant isolation is enforced at the gateway and identity layers, while each service propagates the tenant identifier using `org-{region}-{id}` format.

## Service Responsibilities
- gateway-service: edge routing, tenant header enforcement, rate limiting.
- identity-service: OAuth2 login, SAML federation, RBAC, policy enforcement.
- profile-service: person profiles, relationships, claims, approvals, audit trails.
- ingestion-service: gRPC bulk ingestion, Kafka/Kinesis intake, schema validation.
- insights-service: anomaly detection, merge recommendations, reports, async tasks.

## Data Stores
- SQL (PostgreSQL): tenants, accounts, RBAC, policies, audit logs.
- NoSQL (MongoDB): event payloads, flexible lineage evidence, ingestion artifacts.

## Multi-Tenancy
- Tenant ID format: `org-{region}-{id}`.
- Tenant ID required for all non-public APIs.
- Partition keys align with tenant ID for Kafka topics and Kinesis shards.
- Gateway enforces `x-tenant-id` header for protected routes.

## Failure Modes and Backpressure
- Kafka consumer lag triggers throttling in the gateway.
- Kinesis shard hot spots are rebalanced by tenant partitioning.
- RabbitMQ queue depth informs insight job rate controls.

## Security Boundaries
- Public endpoints limited to health and optional public profile views.
- Authenticated endpoints require OAuth2 tokens or SAML assertions.
- Field-level visibility enforced by policy and per-person grants.
