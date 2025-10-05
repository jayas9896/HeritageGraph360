package com.heritagegraph360.insights.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures RabbitMQ queues for insights processing.
 * Importance: Enables async workloads for anomaly scoring and report generation.
 * Alternatives: Use a Kafka-based work queue instead of RabbitMQ.
 */
@Configuration
public class RabbitMqConfig {
    /**
     * Declares the queue for insight scoring jobs.
     * Importance: Routes anomaly detection work to dedicated consumers.
     * Alternatives: Use a shared queue with routing keys.
     *
     * @return the insights scoring queue.
     */
    @Bean
    public Queue insightsScoringQueue() {
        return new Queue("insights.scoring", true);
    }

    /**
     * Declares the queue for report generation jobs.
     * Importance: Supports asynchronous report generation workflows.
     * Alternatives: Use a scheduled batch process instead of a queue.
     *
     * @return the report generation queue.
     */
    @Bean
    public Queue reportsGenerationQueue() {
        return new Queue("reports.generation", true);
    }
}
