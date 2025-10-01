package com.heritagegraph360.ingestion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Performs a lightweight sanity check for the ingestion module.
 * Importance: Keeps the ingestion build pipeline wired for JUnit execution.
 * Alternatives: Replace with Spring Boot context tests in later sprints.
 */
public class IngestionServiceSanityTest {
    /**
     * Confirms the test harness runs for the ingestion module.
     * Importance: Ensures CI executes tests for pipeline-critical modules.
     * Alternatives: Use a Spring Boot smoke test.
     */
    @Test
    public void shouldRunSanityTest() {
        Assertions.assertTrue(true);
    }
}
