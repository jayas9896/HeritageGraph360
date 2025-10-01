package com.heritagegraph360.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Verifies basic behavior for tenant context.
 * Importance: Ensures core shared utilities remain stable for all services.
 * Alternatives: Validate behavior with integration tests across services.
 */
public class TenantContextTest {
    /**
     * Confirms that the tenant context stores the identifiers.
     * Importance: Guards against regressions in shared tenant utilities.
     * Alternatives: Use parameterized tests for multiple tenant cases.
     */
    @Test
    public void shouldStoreTenantIdentifiers() {
        TenantContext context = new TenantContext("org-us-001", "us", "001");
        Assertions.assertEquals("org-us-001", context.getTenantId());
    }
}
