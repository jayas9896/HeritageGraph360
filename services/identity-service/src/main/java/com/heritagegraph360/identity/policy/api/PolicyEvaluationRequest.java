package com.heritagegraph360.identity.policy.api;

/**
 * Represents a policy evaluation request.
 * Importance: Captures the evaluation context for policy decisions.
 * Alternatives: Use an external policy engine only.
 */
public class PolicyEvaluationRequest {
    private String resource;
    private String action;

    /**
     * Returns the resource.
     * Importance: Identifies the protected resource.
     * Alternatives: Use resource IDs instead of names.
     *
     * @return the resource.
     */
    public String getResource() {
        return resource;
    }

    /**
     * Updates the resource.
     * Importance: Supports payload binding.
     * Alternatives: Use resource IDs instead of names.
     *
     * @param resource the resource.
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * Returns the action.
     * Importance: Identifies the action being evaluated.
     * Alternatives: Use a list of actions.
     *
     * @return the action.
     */
    public String getAction() {
        return action;
    }

    /**
     * Updates the action.
     * Importance: Supports payload binding.
     * Alternatives: Use a list of actions.
     *
     * @param action the action.
     */
    public void setAction(String action) {
        this.action = action;
    }
}
