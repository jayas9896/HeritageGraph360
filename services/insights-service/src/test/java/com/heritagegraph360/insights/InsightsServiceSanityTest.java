package com.heritagegraph360.insights;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Performs a lightweight sanity check for the insights module.
 * Importance: Keeps the insights build pipeline wired for JUnit execution.
 * Alternatives: Replace with Spring Boot context tests in later sprints.
 */
public class InsightsServiceSanityTest {
    /**
     * Confirms the test harness runs for the insights module.
     * Importance: Ensures CI executes tests for insight-critical modules.
     * Alternatives: Use a Spring Boot smoke test.
     */
    @Test
    public void shouldRunSanityTest() {
        Assertions.assertTrue(true);
    }
}
