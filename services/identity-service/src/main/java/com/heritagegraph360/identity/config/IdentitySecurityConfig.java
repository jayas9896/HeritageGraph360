package com.heritagegraph360.identity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures OAuth2 and SAML security flows for the identity service.
 * Importance: Establishes the authentication entry points and SSO federation.
 * Alternatives: Delegate SSO to an external identity provider gateway.
 */
@Configuration
public class IdentitySecurityConfig {
    /**
     * Configures SAML endpoints with highest precedence.
     * Importance: Ensures SAML login flows are handled before OAuth2 rules.
     * Alternatives: Isolate SAML into a separate service or host.
     *
     * @param http the HttpSecurity builder.
     * @return the configured security filter chain.
     * @throws Exception when configuration fails.
     */
    @Bean
    @Order(1)
    public SecurityFilterChain samlSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/saml2/**", "/login/saml2/**")
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .saml2Login();
        return http.build();
    }

    /**
     * Configures OAuth2 login for identity endpoints.
     * Importance: Provides user authentication for standard login flows.
     * Alternatives: Use only JWT bearer validation for all endpoints.
     *
     * @param http the HttpSecurity builder.
     * @return the configured security filter chain.
     * @throws Exception when configuration fails.
     */
    @Bean
    @Order(2)
    public SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .oauth2Login();
        return http.build();
    }
}
