package com.heritagegraph360.ingestion.service;

import com.heritagegraph360.ingestion.api.IngestionRequest;
import com.heritagegraph360.ingestion.config.IngestionProperties;
import com.heritagegraph360.ingestion.stream.IngestionEventPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.model.PutRecordRequest;
import software.amazon.awssdk.core.SdkBytes;

/**
 * Processes ingestion requests and publishes to streams.
 * Importance: Bridges REST/gRPC ingestion to Kafka and Kinesis pipelines.
 * Alternatives: Use a managed ETL pipeline for ingestion.
 */
@Service
public class IngestionProcessingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IngestionProcessingService.class);
    private final IngestionEventPublisher eventPublisher;
    private final KinesisClient kinesisClient;
    private final IngestionProperties ingestionProperties;
    private final ObjectMapper objectMapper;

    /**
     * Creates the ingestion processing service.
     * Importance: Connects ingestion requests to streaming outputs.
     * Alternatives: Use a separate pipeline service for streaming.
     *
     * @param eventPublisher the Kafka event publisher.
     * @param kinesisClient the Kinesis client.
     * @param ingestionProperties the ingestion properties.
     */
    public IngestionProcessingService(IngestionEventPublisher eventPublisher,
                                      KinesisClient kinesisClient,
                                      IngestionProperties ingestionProperties,
                                      ObjectMapper objectMapper) {
        this.eventPublisher = eventPublisher;
        this.kinesisClient = kinesisClient;
        this.ingestionProperties = ingestionProperties;
        this.objectMapper = objectMapper;
    }

    /**
     * Processes a REST ingestion request.
     * Importance: Provides ingestion for low-volume submissions.
     * Alternatives: Use batch ingestion for all records.
     *
     * @param request the ingestion request.
     */
    public void processRestRequest(IngestionRequest request) {
        String enrichedPayload = enrichPayload(request.getTenantId(), "INGESTION_REST", request.getPayloadJson());
        publishKafkaEvent(request.getTenantId(), enrichedPayload);
        publishKinesisEvent(request.getTenantId(), enrichedPayload);
    }

    /**
     * Processes a gRPC ingestion record.
     * Importance: Provides ingestion for high-volume submissions.
     * Alternatives: Use batch ingestion for all records.
     *
     * @param tenantId the tenant identifier.
     * @param payloadJson the payload JSON.
     */
    public void processGrpcRecord(String tenantId, String payloadJson) {
        String enrichedPayload = enrichPayload(tenantId, "INGESTION_GRPC", payloadJson);
        publishKafkaEvent(tenantId, enrichedPayload);
        publishKinesisEvent(tenantId, enrichedPayload);
    }

    /**
     * Publishes a Kafka event for downstream processing.
     * Importance: Ensures profile events flow into insights pipelines.
     * Alternatives: Use a database outbox for event emission.
     *
     * @param tenantId the tenant identifier.
     * @param payload the payload JSON.
     */
    private void publishKafkaEvent(String tenantId, String payload) {
        eventPublisher.publishProfileEvent(tenantId, payload);
    }

    /**
     * Publishes a record to Kinesis if enabled.
     * Importance: Mirrors archival feeds to Kinesis for partner workflows.
     * Alternatives: Skip Kinesis in favor of Kafka-only pipelines.
     *
     * @param tenantId the tenant identifier.
     * @param payload the payload JSON.
     */
    private void publishKinesisEvent(String tenantId, String payload) {
        if (!ingestionProperties.isEnabled()) {
            LOGGER.info("Kinesis ingestion disabled; skipping publish for tenant {}", tenantId);
            return;
        }
        String streamName = ingestionProperties.getArchiveImports();
        PutRecordRequest request = PutRecordRequest.builder()
            .streamName(streamName)
            .partitionKey(tenantId)
            .data(SdkBytes.fromByteArray(payload.getBytes(StandardCharsets.UTF_8)))
            .build();
        kinesisClient.putRecord(request);
    }

    /**
     * Enriches payloads with tenant and event metadata.
     * Importance: Standardizes event payloads for downstream processing.
     * Alternatives: Use a schema registry and structured event types.
     *
     * @param tenantId the tenant identifier.
     * @param eventType the event type.
     * @param payloadJson the payload JSON.
     * @return the enriched JSON payload.
     */
    private String enrichPayload(String tenantId, String eventType, String payloadJson) {
        try {
            Map<String, Object> wrapper = new HashMap<>();
            wrapper.put("tenantId", tenantId);
            wrapper.put("eventType", eventType);
            wrapper.put("payload", payloadJson);
            return objectMapper.writeValueAsString(wrapper);
        } catch (Exception ex) {
            LOGGER.warn("Failed to enrich payload, using raw payload", ex);
            return payloadJson;
        }
    }
}
