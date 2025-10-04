package com.heritagegraph360.profile.api;

/**
 * Represents a claim request for a profile.
 * Importance: Captures the reason and evidence for claiming.
 * Alternatives: Use an evidence attachment workflow instead of a reason string.
 */
public class ProfileClaimRequest {
    private String reason;

    /**
     * Returns the claim reason.
     * Importance: Supports claim verification workflows.
     * Alternatives: Use a structured claim payload.
     *
     * @return the claim reason.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Updates the claim reason.
     * Importance: Supports payload binding for claim requests.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param reason the claim reason.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}
