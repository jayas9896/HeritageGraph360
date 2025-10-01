package com.heritagegraph360.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Boots the identity service for authentication, SSO, and RBAC management.
 * Importance: Centralizes security boundaries and tenant-aware identity policies.
 * Alternatives: Split OAuth2 and SAML into separate auth services.
 */
@SpringBootApplication
public class IdentityServiceApplication {
    /**
     * Starts the Spring Boot application.
     * Importance: Initializes the service runtime, security filters, and endpoints.
     * Alternatives: Deploy as a servlet to an external application server.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(IdentityServiceApplication.class, args);
    }
}
