package com.heritagegraph360.profile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Performs a lightweight sanity check for the profile module.
 * Importance: Keeps the profile build pipeline wired for JUnit execution.
 * Alternatives: Replace with Spring Boot context tests in later sprints.
 */
public class ProfileServiceSanityTest {
    /**
     * Confirms the test harness runs for the profile module.
     * Importance: Ensures CI executes tests for domain-critical modules.
     * Alternatives: Use a Spring Boot smoke test.
     */
    @Test
    public void shouldRunSanityTest() {
        Assertions.assertTrue(true);
    }
}
