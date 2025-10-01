package com.heritagegraph360.insights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Boots the insights service for anomaly detection and lineage scoring.
 * Importance: Provides asynchronous insight generation and reporting workflows.
 * Alternatives: Run insights as batch jobs triggered by a scheduler.
 */
@SpringBootApplication
public class InsightsServiceApplication {
    /**
     * Starts the Spring Boot application.
     * Importance: Enables insight processors, queues, and API exposure.
     * Alternatives: Split read-only insight APIs into a separate service.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(InsightsServiceApplication.class, args);
    }
}
