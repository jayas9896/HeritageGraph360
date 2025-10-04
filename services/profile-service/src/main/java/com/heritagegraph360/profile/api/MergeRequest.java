package com.heritagegraph360.profile.api;

/**
 * Represents a merge request payload.
 * Importance: Captures target profile identifiers for merge workflows.
 * Alternatives: Use a merge workflow entity with evidence attachments.
 */
public class MergeRequest {
    private String targetProfileId;
    private String reason;

    /**
     * Returns the target profile identifier.
     * Importance: Identifies the profile to merge with.
     * Alternatives: Use a merge candidate list instead.
     *
     * @return the target profile identifier.
     */
    public String getTargetProfileId() {
        return targetProfileId;
    }

    /**
     * Updates the target profile identifier.
     * Importance: Supports payload binding for merge requests.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param targetProfileId the target profile identifier.
     */
    public void setTargetProfileId(String targetProfileId) {
        this.targetProfileId = targetProfileId;
    }

    /**
     * Returns the merge reason.
     * Importance: Provides context for merge review.
     * Alternatives: Use structured evidence references.
     *
     * @return the merge reason.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Updates the merge reason.
     * Importance: Supports payload binding for merge requests.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param reason the merge reason.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}
