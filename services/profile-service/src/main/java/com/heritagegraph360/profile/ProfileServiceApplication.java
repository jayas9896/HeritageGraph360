package com.heritagegraph360.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Boots the profile service for genealogy records and relationship graph APIs.
 * Importance: Hosts the core domain operations for profile lifecycle management.
 * Alternatives: Combine profile APIs into the gateway service for simplicity.
 */
@SpringBootApplication
public class ProfileServiceApplication {
    /**
     * Starts the Spring Boot application.
     * Importance: Initializes profile APIs, persistence, and messaging listeners.
     * Alternatives: Run as a serverless function for API-only workloads.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(ProfileServiceApplication.class, args);
    }
}
