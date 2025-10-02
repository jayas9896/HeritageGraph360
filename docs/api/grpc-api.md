# gRPC API (Bulk Ingestion)

Service: `BulkIngestionService`

## Methods
- `rpc IngestRecords(stream IngestionRecord) returns (stream IngestionStatus)`
  - Accepts streaming records and replies with per-record status.
- `rpc ValidateSchema(SchemaValidationRequest) returns (SchemaValidationResult)`
  - Validates partner schemas before ingest.

## Notes
- gRPC is used for large archival imports and partner datasets.
- Tenant ID is required in the metadata header `x-tenant-id`.
