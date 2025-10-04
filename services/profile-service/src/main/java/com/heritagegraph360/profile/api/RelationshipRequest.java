package com.heritagegraph360.profile.api;

/**
 * Represents a relationship request payload.
 * Importance: Captures relationship targets and status updates.
 * Alternatives: Use a graph edge request structure.
 */
public class RelationshipRequest {
    private String relatedProfileId;
    private String status;

    /**
     * Returns the related profile identifier.
     * Importance: Links the relationship to the target profile.
     * Alternatives: Use a composite relationship key.
     *
     * @return the related profile identifier.
     */
    public String getRelatedProfileId() {
        return relatedProfileId;
    }

    /**
     * Updates the related profile identifier.
     * Importance: Supports payload binding for relationship requests.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param relatedProfileId the related profile identifier.
     */
    public void setRelatedProfileId(String relatedProfileId) {
        this.relatedProfileId = relatedProfileId;
    }

    /**
     * Returns the relationship status.
     * Importance: Supports visible state updates (green/yellow/orange/red).
     * Alternatives: Use an enum with lifecycle transitions.
     *
     * @return the relationship status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the relationship status.
     * Importance: Supports payload binding for relationship updates.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param status the relationship status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
