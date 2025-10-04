package com.heritagegraph360.profile.api;

/**
 * Represents the outcome of a merge request.
 * Importance: Communicates merge workflow status to clients.
 * Alternatives: Use HTTP 202 with a location header only.
 */
public class MergeResponse {
    private final String sourceProfileId;
    private final String targetProfileId;
    private final String status;

    /**
     * Creates a merge response.
     * Importance: Standardizes merge workflow responses.
     * Alternatives: Use a generic response wrapper.
     *
     * @param sourceProfileId the source profile identifier.
     * @param targetProfileId the target profile identifier.
     * @param status the merge status.
     */
    public MergeResponse(String sourceProfileId, String targetProfileId, String status) {
        this.sourceProfileId = sourceProfileId;
        this.targetProfileId = targetProfileId;
        this.status = status;
    }

    /**
     * Returns the source profile identifier.
     * Importance: Correlates the merge to the source profile.
     * Alternatives: Use a hashed public profile identifier.
     *
     * @return the source profile identifier.
     */
    public String getSourceProfileId() {
        return sourceProfileId;
    }

    /**
     * Returns the target profile identifier.
     * Importance: Correlates the merge to the target profile.
     * Alternatives: Use a hashed public profile identifier.
     *
     * @return the target profile identifier.
     */
    public String getTargetProfileId() {
        return targetProfileId;
    }

    /**
     * Returns the merge status.
     * Importance: Indicates the current merge workflow state.
     * Alternatives: Use an enum with workflow states.
     *
     * @return the merge status.
     */
    public String getStatus() {
        return status;
    }
}
