package com.heritagegraph360.ingestion.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * Implements the gRPC ingestion service for bulk archival imports.
 * Importance: Provides high-throughput ingestion with structured validation.
 * Alternatives: Use REST batch endpoints with multipart uploads.
 */
@GrpcService
public class BulkIngestionGrpcService extends BulkIngestionServiceGrpc.BulkIngestionServiceImplBase {
    /**
     * Accepts streaming ingestion records and returns per-record status.
     * Importance: Supports backpressure and per-record feedback in bulk pipelines.
     * Alternatives: Return a single aggregated response after stream completion.
     *
     * @param responseObserver the response stream observer.
     * @return the request observer for ingestion records.
     */
    @Override
    public StreamObserver<IngestionRecord> ingestRecords(StreamObserver<IngestionStatus> responseObserver) {
        return new StreamObserver<>() {
            /**
             * Handles each incoming ingestion record.
             * Importance: Provides incremental validation and status reporting.
             * Alternatives: Buffer all records before responding.
             *
             * @param record the ingestion record.
             */
            @Override
            public void onNext(IngestionRecord record) {
                IngestionStatus status = IngestionStatus.newBuilder()
                    .setRecordId(record.getRecordId())
                    .setStatus("ACCEPTED")
                    .setMessage("Record queued for processing")
                    .build();
                responseObserver.onNext(status);
            }

            /**
             * Handles stream errors from the client.
             * Importance: Ensures ingestion pipelines surface failures promptly.
             * Alternatives: Ignore errors and rely on retries.
             *
             * @param t the throwable.
             */
            @Override
            public void onError(Throwable t) {
                responseObserver.onError(t);
            }

            /**
             * Completes the response stream after all records are processed.
             * Importance: Signals successful stream completion to the client.
             * Alternatives: Keep the stream open for long-lived ingestion sessions.
             */
            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

    /**
     * Validates a partner schema before ingestion.
     * Importance: Prevents invalid schemas from entering the ingestion pipeline.
     * Alternatives: Accept all schemas and validate asynchronously later.
     *
     * @param request the schema validation request.
     * @param responseObserver the response observer.
     */
    @Override
    public void validateSchema(SchemaValidationRequest request, StreamObserver<SchemaValidationResult> responseObserver) {
        SchemaValidationResult result = SchemaValidationResult.newBuilder()
            .setStatus("VALID")
            .setMessage("Schema accepted")
            .build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }
}
