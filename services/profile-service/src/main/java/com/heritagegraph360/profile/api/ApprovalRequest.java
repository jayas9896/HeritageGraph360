package com.heritagegraph360.profile.api;

/**
 * Represents an approval decision request.
 * Importance: Captures admin decisions for high-impact updates.
 * Alternatives: Use a workflow engine decision payload.
 */
public class ApprovalRequest {
    private String decision;
    private String reason;

    /**
     * Returns the decision.
     * Importance: Drives approval workflow transitions.
     * Alternatives: Use an enum for decisions.
     *
     * @return the decision.
     */
    public String getDecision() {
        return decision;
    }

    /**
     * Updates the decision.
     * Importance: Supports payload binding for approvals.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param decision the decision.
     */
    public void setDecision(String decision) {
        this.decision = decision;
    }

    /**
     * Returns the decision reason.
     * Importance: Captures rationale for auditability.
     * Alternatives: Store evidence references instead of free text.
     *
     * @return the decision reason.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Updates the decision reason.
     * Importance: Supports payload binding for approvals.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param reason the decision reason.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}
