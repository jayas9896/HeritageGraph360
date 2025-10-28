package com.heritagegraph360.identity.policy.api;

/**
 * Represents a policy evaluation response.
 * Importance: Standardizes policy evaluation output.
 * Alternatives: Use a generic response wrapper.
 */
public class PolicyEvaluationResponse {
    private final boolean allowed;

    /**
     * Creates a policy evaluation response.
     * Importance: Provides a consistent response contract.
     * Alternatives: Use a status string instead.
     *
     * @param allowed whether access is allowed.
     */
    public PolicyEvaluationResponse(boolean allowed) {
        this.allowed = allowed;
    }

    /**
     * Returns whether access is allowed.
     * Importance: Communicates policy evaluation decisions.
     * Alternatives: Return a status string.
     *
     * @return true if allowed.
     */
    public boolean isAllowed() {
        return allowed;
    }
}
