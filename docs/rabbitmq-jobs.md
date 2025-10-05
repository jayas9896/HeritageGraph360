# RabbitMQ Job Processor Design

## Queues
- `insights.scoring`: anomaly detection jobs for profile merges.
- `reports.generation`: lineage and audit report generation.

## Job Lifecycle
1) Producer publishes job with tenant ID and correlation ID.
2) Worker validates tenant scope and rate limits by queue depth.
3) Worker writes job results to NoSQL and updates SQL audit log.
4) Dead-letter queue captures failed jobs for review.

## Reliability
- Prefetch limits avoid worker overload.
- Retry policies with exponential backoff.
- Metrics emitted for queue depth and job latency.
