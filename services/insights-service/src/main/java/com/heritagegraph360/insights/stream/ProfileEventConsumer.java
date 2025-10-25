package com.heritagegraph360.insights.stream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heritagegraph360.insights.jobs.InsightJobPayload;
import com.heritagegraph360.insights.jobs.InsightJobPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Consumes profile events for anomaly detection workflows.
 * Importance: Connects streaming updates to insight generation.
 * Alternatives: Poll the database for changes instead of consuming events.
 */
@Component
public class ProfileEventConsumer {
    private final InsightJobPublisher jobPublisher;
    private final ObjectMapper objectMapper;

    /**
     * Creates a consumer with dependencies.
     * Importance: Enables translation of events into insight jobs.
     * Alternatives: Publish jobs directly from the ingestion service.
     *
     * @param jobPublisher the job publisher.
     * @param objectMapper the JSON mapper.
     */
    public ProfileEventConsumer(InsightJobPublisher jobPublisher, ObjectMapper objectMapper) {
        this.jobPublisher = jobPublisher;
        this.objectMapper = objectMapper;
    }

    /**
     * Handles incoming profile events from Kafka.
     * Importance: Triggers downstream anomaly detection and scoring.
     * Alternatives: Buffer events in a queue before processing.
     *
     * @param payload the event payload as JSON.
     */
    @KafkaListener(topics = "profile.events", groupId = "insights-service")
    public void onProfileEvent(String payload) {
        InsightJobPayload jobPayload = new InsightJobPayload();
        jobPayload.setTenantId(extractField(payload, "tenantId", "unknown-tenant"));
        jobPayload.setProfileId(extractProfileId(payload));
        jobPayload.setEvidenceSummary(payload);
        jobPublisher.publish(jobPayload);
    }

    /**
     * Extracts a field from a JSON payload.
     * Importance: Maps event data into job payloads.
     * Alternatives: Use a schema registry with typed events.
     *
     * @param payload the JSON payload.
     * @param field the field name.
     * @param fallback the fallback value.
     * @return the field value or fallback.
     */
    private String extractField(String payload, String field, String fallback) {
        try {
            JsonNode node = objectMapper.readTree(payload);
            if (node.hasNonNull(field)) {
                return node.get(field).asText();
            }
        } catch (Exception ex) {
            return fallback;
        }
        return fallback;
    }

    /**
     * Extracts profileId from top-level or nested payload fields.
     * Importance: Ensures insight jobs identify target profiles.
     * Alternatives: Use typed event payloads with explicit fields.
     *
     * @param payload the JSON payload.
     * @return the profile identifier or fallback.
     */
    private String extractProfileId(String payload) {
        try {
            JsonNode node = objectMapper.readTree(payload);
            if (node.hasNonNull("profileId")) {
                return node.get("profileId").asText();
            }
            if (node.has("payload")) {
                JsonNode nested = node.get("payload");
                if (nested.hasNonNull("profileId")) {
                    return nested.get("profileId").asText();
                }
            }
        } catch (Exception ex) {
            return "unknown-profile";
        }
        return "unknown-profile";
    }
}
