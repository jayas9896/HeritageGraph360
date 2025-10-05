# OpenAI Insight Feature

## Purpose
Provide narrative summaries of lineage anomalies, duplicates, and conflict resolution suggestions for reviewers.

## Flow
1) insights-service receives anomaly events from Kafka.
2) A RabbitMQ job triggers summarization.
3) The OpenAI API is called with redacted evidence context via `OpenAiInsightClient`.
4) The summary is stored in NoSQL with audit metadata.

## Prompt Template (Example)
"Summarize the lineage anomaly for tenant {tenantId}. Evidence: {evidenceSummary}. Provide a 3-bullet explanation and a confidence score."

## Safeguards
- PII redaction before sending data to the model.
- Store prompts and responses in the audit log.
- Feature flag to disable external calls.
