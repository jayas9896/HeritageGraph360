package com.heritagegraph360.profile.stream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heritagegraph360.profile.nosql.EventPayloadDocument;
import com.heritagegraph360.profile.nosql.EventPayloadRepository;
import java.time.Instant;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Consumes profile events to persist dynamic payloads.
 * Importance: Stores event payloads for audit and analytics.
 * Alternatives: Store events in a separate event store service.
 */
@Component
public class ProfileEventConsumer {
    private final EventPayloadRepository eventPayloadRepository;
    private final ObjectMapper objectMapper;

    /**
     * Creates the event consumer.
     * Importance: Enables persistence of streaming events.
     * Alternatives: Use a stream processor framework.
     *
     * @param eventPayloadRepository the event payload repository.
     * @param objectMapper the object mapper.
     */
    public ProfileEventConsumer(EventPayloadRepository eventPayloadRepository, ObjectMapper objectMapper) {
        this.eventPayloadRepository = eventPayloadRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Handles profile events from Kafka.
     * Importance: Persists event payloads for later inspection.
     * Alternatives: Store only selected events.
     *
     * @param payload the event payload JSON.
     */
    @KafkaListener(topics = "profile.events", groupId = "profile-service")
    public void onProfileEvent(String payload) {
        EventPayloadDocument document = new EventPayloadDocument();
        document.setTenantId(extractField(payload, "tenantId", "unknown-tenant"));
        document.setEventType(extractField(payload, "eventType", "UNKNOWN"));
        document.setPayloadJson(payload);
        document.setIngestedAt(Instant.now());
        eventPayloadRepository.save(document);
    }

    /**
     * Extracts a field from a JSON payload.
     * Importance: Maps event data into storage fields.
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
}
