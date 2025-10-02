package com.heritagegraph360.ingestion.stream;

import com.heritagegraph360.ingestion.config.KafkaTopics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Publishes ingestion events to Kafka topics.
 * Importance: Provides high-throughput propagation of ingestion outcomes.
 * Alternatives: Write events directly to a database change log.
 */
@Service
public class IngestionEventPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Creates a publisher with the required Kafka template.
     * Importance: Enables Kafka integration for ingestion events.
     * Alternatives: Use a reactive Kafka producer instead.
     *
     * @param kafkaTemplate the Kafka template.
     */
    public IngestionEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Publishes a profile event message.
     * Importance: Notifies downstream services of ingestion outcomes.
     * Alternatives: Use an outbox table with CDC.
     *
     * @param tenantId the tenant identifier.
     * @param payload the event payload.
     */
    public void publishProfileEvent(String tenantId, String payload) {
        kafkaTemplate.send(KafkaTopics.PROFILE_EVENTS, tenantId, payload);
    }
}
