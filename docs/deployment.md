# Deployment

## Docker
- Use `infra/docker/Dockerfile` with `--build-arg JAR_FILE=...`.
- Build per service and tag as `heritagegraph360/<service>:local`.
- Start dependencies with `docker compose -f infra/docker/docker-compose.yml up -d`.

## Local Kubernetes
- Apply manifests under `infra/k8s`.
- Apply dependencies first with `infra/k8s/dependencies.yaml`.
- Services are exposed as ClusterIP for internal routing.

## Monitoring
- Prometheus scrape config: `infra/monitoring/prometheus.yml`.
- Grafana dashboard: `infra/monitoring/grafana-dashboard.json`.
