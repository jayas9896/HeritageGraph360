# Runbook

## Overview
This runbook covers common operational tasks for HeritageGraph360.

## Service Health
- Check health: `GET /actuator/health` on each service.
- Check metrics: `GET /actuator/prometheus` on each service.

## Common Incidents

### Kafka Consumer Lag
- Symptom: increased latency, backlog in Kafka metrics.
- Actions:
  - Scale insights-service consumers.
  - Verify Kafka broker health and partitions.
  - Reduce ingestion rate via gateway throttling.

### RabbitMQ Queue Backlog
- Symptom: queue depth rising in Grafana.
- Actions:
  - Scale insights-service workers.
  - Inspect dead-letter queues for failures.

### Kinesis Shard Hotspot
- Symptom: shard iterator age rising.
- Actions:
  - Rebalance tenants across shards.
  - Increase shard count temporarily.

## Security Incidents
- Revoke tokens and disable accounts in identity-service.
- Review audit logs for suspicious access.

## Recovery
- Use SQL backups for tenant/account recovery.
- Rehydrate NoSQL event payloads from stream archives.
