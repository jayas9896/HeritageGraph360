package com.heritagegraph360.profile.api;

/**
 * Represents the outcome of a merge decision.
 * Importance: Communicates merge decision results to clients.
 * Alternatives: Use HTTP 202 with a location header only.
 */
public class MergeDecisionResponse {
    private final String mergeId;
    private final String decision;
    private final String status;

    /**
     * Creates a merge decision response.
     * Importance: Standardizes merge decision responses.
     * Alternatives: Use a generic response wrapper.
     *
     * @param mergeId the merge identifier.
     * @param decision the decision.
     * @param status the status.
     */
    public MergeDecisionResponse(String mergeId, String decision, String status) {
        this.mergeId = mergeId;
        this.decision = decision;
        this.status = status;
    }

    /**
     * Returns the merge identifier.
     * Importance: Correlates decisions with merge workflows.
     * Alternatives: Use a hashed public merge identifier.
     *
     * @return the merge identifier.
     */
    public String getMergeId() {
        return mergeId;
    }

    /**
     * Returns the decision.
     * Importance: Indicates the reviewer decision outcome.
     * Alternatives: Use an enum for decisions.
     *
     * @return the decision.
     */
    public String getDecision() {
        return decision;
    }

    /**
     * Returns the status.
     * Importance: Indicates the merge workflow state after decision.
     * Alternatives: Use an enum for statuses.
     *
     * @return the status.
     */
    public String getStatus() {
        return status;
    }
}
