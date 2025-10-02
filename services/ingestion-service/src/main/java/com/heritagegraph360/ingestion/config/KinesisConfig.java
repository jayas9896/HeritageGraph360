package com.heritagegraph360.ingestion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisClient;

/**
 * Configures the Kinesis client for partner ingestion streams.
 * Importance: Enables direct ingestion of archival partner feeds.
 * Alternatives: Use a managed ETL service instead of direct Kinesis access.
 */
@Configuration
public class KinesisConfig {
    /**
     * Builds a Kinesis client using environment-based configuration.
     * Importance: Aligns streaming ingestion with tenant partitioning requirements.
     * Alternatives: Provide a custom credentials provider per tenant.
     *
     * @return the configured Kinesis client.
     */
    @Bean
    public KinesisClient kinesisClient() {
        String region = System.getenv().getOrDefault("AWS_REGION", "us-east-1");
        return KinesisClient.builder()
            .credentialsProvider(DefaultCredentialsProvider.create())
            .region(Region.of(region))
            .build();
    }
}
