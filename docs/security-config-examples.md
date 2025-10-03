# Spring Security Configuration Examples

## Public and Role-Based Access (Gateway)
```java
@Bean
SecurityFilterChain gatewaySecurity(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/actuator/health", "/public/**").permitAll()
            .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        .oauth2ResourceServer(oauth2 -> oauth2.jwt());
    return http.build();
}
```

## OAuth2 Login (Identity Service)
```java
@Bean
SecurityFilterChain oauth2LoginSecurity(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
        .oauth2Login();
    return http.build();
}
```

## SAML Federation (Identity Service)
```java
@Bean
SecurityFilterChain samlSecurity(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
        .saml2Login();
    return http.build();
}
```
