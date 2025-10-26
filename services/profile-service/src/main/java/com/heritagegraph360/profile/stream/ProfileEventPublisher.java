package com.heritagegraph360.profile.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Publishes profile domain events to Kafka.
 * Importance: Notifies downstream services of profile changes.
 * Alternatives: Use a database outbox and CDC pipeline.
 */
@Component
public class ProfileEventPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    /**
     * Creates the publisher.
     * Importance: Connects profile workflows to Kafka publishing.
     * Alternatives: Use a reactive Kafka producer.
     *
     * @param kafkaTemplate the Kafka template.
     * @param objectMapper the object mapper.
     */
    public ProfileEventPublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * Publishes a profile event.
     * Importance: Emits events for downstream processing.
     * Alternatives: Persist events only in MongoDB.
     *
     * @param tenantId the tenant identifier.
     * @param eventType the event type.
     * @param profileId the profile identifier.
     */
    public void publishProfileEvent(String tenantId, String eventType, String profileId) {
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("tenantId", tenantId);
            payload.put("eventType", eventType);
            payload.put("profileId", profileId);
            String json = objectMapper.writeValueAsString(payload);
            kafkaTemplate.send("profile.events", tenantId, json);
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to publish profile event", ex);
        }
    }
}
