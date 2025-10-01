package com.heritagegraph360.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Boots the gateway service for routing, policy enforcement, and edge security.
 * Importance: Centralizes external access, tenant routing, and throttling.
 * Alternatives: Expose each service directly behind a load balancer.
 */
@SpringBootApplication
public class GatewayServiceApplication {
    /**
     * Starts the Spring Boot application.
     * Importance: Initializes gateway routes, security filters, and metrics.
     * Alternatives: Use a managed API gateway in front of services.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
}
