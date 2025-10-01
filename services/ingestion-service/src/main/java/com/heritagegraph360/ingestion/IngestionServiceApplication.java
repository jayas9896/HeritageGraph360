package com.heritagegraph360.ingestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Boots the ingestion service for bulk and streaming data intake.
 * Importance: Coordinates gRPC, Kafka, and Kinesis ingestion pipelines.
 * Alternatives: Offload streaming ingestion to a managed ETL service.
 */
@SpringBootApplication
public class IngestionServiceApplication {
    /**
     * Starts the Spring Boot application.
     * Importance: Activates ingestion pipelines and event processing workers.
     * Alternatives: Use separate microservices for gRPC and stream ingestion.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(IngestionServiceApplication.class, args);
    }
}
