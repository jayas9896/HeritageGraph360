# Demo Scenarios

## Security Scenario
- Use OAuth2 login to access `/api/v1/profiles` via gateway.
- Attempt access to `/api/v1/admin/*` without ADMIN role and verify denial.
- Validate SAML login flow for institutional tenants.

## Failure Scenario
- Stop Kafka or simulate consumer lag and verify gateway throttling metrics.
- Inject invalid ingestion payload and verify audit logging and rejection.
- Force RabbitMQ queue backlog and observe queue depth metrics.

## Scaling Scenario
- Scale ingestion-service replicas and validate Kinesis shard routing by tenant.
- Generate high-volume profile events and observe Kafka partition balance.
- Confirm insights-service consumers scale with queue depth.
