# Pipeline Design

## Kafka
- Topic: `profile.events` for profile state changes.
- Topic: `relationship.events` for relationship updates.
- Partition key: tenant ID.
- Consumers: profile-service (state sync), insights-service (anomaly scoring).

## Kinesis
- Stream: `archive-imports` for partner archival feeds.
- Stream: `partner-datasets` for scheduled dataset mirroring.
- Producers: ingestion-service.
- Consumers: ingestion-service transformers and validators.

## RabbitMQ
- Queue: `insights.scoring` for anomaly detection jobs.
- Queue: `reports.generation` for audit and lineage reports.
- Workers: insights-service job processors.

## gRPC Bulk Ingestion
- Used by large archival imports with strong schemas and low latency.
- Provides backpressure via streaming responses.

## REST Ingestion
- Used for small user-submitted records and admin uploads.
