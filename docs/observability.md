# Observability

## Metrics
- Request latency and throughput per service.
- Kafka consumer lag and processing rate.
- RabbitMQ queue depth and job latency.
- Kinesis shard iterator age and throughput.

## Prometheus
- Each service exposes `/actuator/prometheus`.
- Prometheus scrape config in `infra/monitoring/prometheus.yml`.

## Grafana
- Dashboards stored in `infra/monitoring/grafana-dashboard.json`.
- Panels focus on throughput, latency, queue depth, and error rates.
