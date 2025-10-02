package com.heritagegraph360.ingestion.config;

/**
 * Defines Kafka topic names for ingestion and profile events.
 * Importance: Centralizes topic definitions to avoid configuration drift.
 * Alternatives: Load topics from external configuration at runtime.
 */
public class KafkaTopics {
    /**
     * Topic for profile events.
     * Importance: Streams profile lifecycle events to consumers.
     * Alternatives: Use separate topics per event type.
     */
    public static final String PROFILE_EVENTS = "profile.events";

    /**
     * Topic for relationship events.
     * Importance: Streams relationship status changes to consumers.
     * Alternatives: Use a single topic with event type filtering.
     */
    public static final String RELATIONSHIP_EVENTS = "relationship.events";

    /**
     * Prevents instantiation of constants class.
     * Importance: Enforces static access to topic names.
     * Alternatives: Use an enum to represent topics.
     */
    private KafkaTopics() {
    }
}
