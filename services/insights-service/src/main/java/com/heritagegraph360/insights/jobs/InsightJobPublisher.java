package com.heritagegraph360.insights.jobs;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Publishes insight jobs to RabbitMQ.
 * Importance: Decouples event consumption from insight processing.
 * Alternatives: Use a Kafka topic for job distribution.
 */
@Component
public class InsightJobPublisher {
    private final RabbitTemplate rabbitTemplate;

    /**
     * Creates the job publisher.
     * Importance: Enables asynchronous job publishing.
     * Alternatives: Use direct REST calls to processing services.
     *
     * @param rabbitTemplate the rabbit template.
     */
    public InsightJobPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Publishes a job payload to the scoring queue.
     * Importance: Ensures insight processing remains asynchronous.
     * Alternatives: Use a work queue per tenant.
     *
     * @param payload the job payload.
     */
    public void publish(InsightJobPayload payload) {
        rabbitTemplate.convertAndSend("insights.scoring", payload);
    }
}
