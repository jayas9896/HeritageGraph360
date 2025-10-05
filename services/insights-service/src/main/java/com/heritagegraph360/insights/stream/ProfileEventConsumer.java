package com.heritagegraph360.insights.stream;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Consumes profile events for anomaly detection workflows.
 * Importance: Connects streaming updates to insight generation.
 * Alternatives: Poll the database for changes instead of consuming events.
 */
@Component
public class ProfileEventConsumer {
    /**
     * Handles incoming profile events from Kafka.
     * Importance: Triggers downstream anomaly detection and scoring.
     * Alternatives: Buffer events in a queue before processing.
     *
     * @param payload the event payload as JSON.
     */
    @KafkaListener(topics = "profile.events", groupId = "insights-service")
    public void onProfileEvent(String payload) {
        // Placeholder for event parsing and scoring workflows.
    }
}
