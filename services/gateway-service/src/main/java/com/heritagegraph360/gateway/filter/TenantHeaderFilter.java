package com.heritagegraph360.gateway.filter;

import java.util.List;
import java.util.regex.Pattern;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import reactor.core.publisher.Mono;

/**
 * Enforces tenant header requirements for non-public gateway routes.
 * Importance: Ensures multi-tenant isolation by requiring tenant context.
 * Alternatives: Derive tenant context solely from OAuth2 token claims.
 */
@Component
public class TenantHeaderFilter implements GlobalFilter, Ordered {
    private static final String TENANT_HEADER = "x-tenant-id";
    private static final List<String> PUBLIC_PATH_PREFIXES = List.of("/public", "/actuator/health", "/actuator/info");
    private static final Pattern TENANT_PATTERN = Pattern.compile("org-[a-zA-Z0-9]+-[a-zA-Z0-9]+$");

    /**
     * Filters requests to enforce the tenant header.
     * Importance: Blocks requests missing tenant identifiers on protected routes.
     * Alternatives: Accept tenant ID from query parameters.
     *
     * @param exchange the server web exchange.
     * @param chain the gateway filter chain.
     * @return the filter response.
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (isPublicPath(path)) {
            return chain.filter(exchange);
        }
        String tenantId = exchange.getRequest().getHeaders().getFirst(TENANT_HEADER);
        if (tenantId == null || tenantId.isBlank()) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            return exchange.getResponse().setComplete();
        }
        if (!TENANT_PATTERN.matcher(tenantId).matches()) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * Returns the filter order.
     * Importance: Ensures tenant enforcement happens early in the filter chain.
     * Alternatives: Use default order and rely on filter registration order.
     *
     * @return the filter order.
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    /**
     * Determines whether a path is public.
     * Importance: Ensures public endpoints remain accessible without tenant headers.
     * Alternatives: Use a route metadata flag instead of static prefixes.
     *
     * @param path the request path.
     * @return true when the path is public.
     */
    private boolean isPublicPath(String path) {
        for (String prefix : PUBLIC_PATH_PREFIXES) {
            if (path.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }
}
