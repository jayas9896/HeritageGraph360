package com.heritagegraph360.profile.api;

/**
 * Represents the outcome of an approval decision.
 * Importance: Communicates approval results to clients.
 * Alternatives: Use HTTP 202 with a location header only.
 */
public class ApprovalResponse {
    private final String profileId;
    private final String decision;
    private final String status;

    /**
     * Creates an approval response.
     * Importance: Standardizes approval responses.
     * Alternatives: Use a generic response wrapper.
     *
     * @param profileId the profile identifier.
     * @param decision the decision.
     * @param status the approval status.
     */
    public ApprovalResponse(String profileId, String decision, String status) {
        this.profileId = profileId;
        this.decision = decision;
        this.status = status;
    }

    /**
     * Returns the profile identifier.
     * Importance: Correlates approvals with profiles.
     * Alternatives: Use a hashed public profile identifier.
     *
     * @return the profile identifier.
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * Returns the decision.
     * Importance: Indicates the approval decision outcome.
     * Alternatives: Use an enum for decisions.
     *
     * @return the decision.
     */
    public String getDecision() {
        return decision;
    }

    /**
     * Returns the approval status.
     * Importance: Indicates the workflow state after decision.
     * Alternatives: Use an enum for statuses.
     *
     * @return the approval status.
     */
    public String getStatus() {
        return status;
    }
}
