package com.heritagegraph360.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures gateway security for public and role-restricted endpoints.
 * Importance: Enforces least-privilege access at the edge boundary.
 * Alternatives: Rely on downstream services for all access control.
 */
@Configuration
public class GatewaySecurityConfig {
    /**
     * Builds the gateway security filter chain.
     * Importance: Ensures public endpoints remain limited while protecting APIs.
     * Alternatives: Use a managed API gateway policy engine.
     *
     * @param http the HttpSecurity builder.
     * @return the configured security filter chain.
     * @throws Exception when configuration fails.
     */
    @Bean
    public SecurityFilterChain gatewaySecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/health", "/public/**").permitAll()
                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt());
        return http.build();
    }
}
