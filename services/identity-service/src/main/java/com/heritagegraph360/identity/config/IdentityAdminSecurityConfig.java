package com.heritagegraph360.identity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures JWT resource server for identity admin APIs.
 * Importance: Ensures RBAC provisioning endpoints are protected.
 * Alternatives: Restrict admin APIs to internal networks only.
 */
@Configuration
@EnableMethodSecurity
public class IdentityAdminSecurityConfig {
    /**
     * Builds the admin security filter chain.
     * Importance: Protects admin APIs with role-based access.
     * Alternatives: Use a dedicated admin gateway.
     *
     * @param http the security builder.
     * @return the security filter chain.
     * @throws Exception when configuration fails.
     */
    @Bean
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthorityPrefix("ROLE_");
        authoritiesConverter.setAuthoritiesClaimName("roles");
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        http
            .securityMatcher("/api/v1/rbac/**", "/api/v1/policies/**", "/api/v1/accounts/**")
            .authorizeHttpRequests(auth -> auth.anyRequest().hasRole("ADMIN"))
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));
        return http.build();
    }
}
