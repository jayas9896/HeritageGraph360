package com.heritagegraph360.profile.api;

/**
 * Represents the outcome of a relationship update.
 * Importance: Provides a consistent response for relationship workflows.
 * Alternatives: Use HTTP 202 with a location header only.
 */
public class RelationshipResponse {
    private final String profileId;
    private final String relatedProfileId;
    private final String status;

    /**
     * Creates a relationship response.
     * Importance: Standardizes relationship responses.
     * Alternatives: Use a generic response wrapper.
     *
     * @param profileId the profile identifier.
     * @param relatedProfileId the related profile identifier.
     * @param status the relationship status.
     */
    public RelationshipResponse(String profileId, String relatedProfileId, String status) {
        this.profileId = profileId;
        this.relatedProfileId = relatedProfileId;
        this.status = status;
    }

    /**
     * Returns the profile identifier.
     * Importance: Correlates the relationship to a profile.
     * Alternatives: Use a hashed public profile identifier.
     *
     * @return the profile identifier.
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * Returns the related profile identifier.
     * Importance: Correlates the relationship to the target profile.
     * Alternatives: Use a hashed public profile identifier.
     *
     * @return the related profile identifier.
     */
    public String getRelatedProfileId() {
        return relatedProfileId;
    }

    /**
     * Returns the relationship status.
     * Importance: Indicates the current state of the relationship.
     * Alternatives: Use an enum with workflow states.
     *
     * @return the relationship status.
     */
    public String getStatus() {
        return status;
    }
}
