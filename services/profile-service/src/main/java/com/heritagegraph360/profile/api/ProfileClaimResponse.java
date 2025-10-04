package com.heritagegraph360.profile.api;

/**
 * Represents the outcome of a claim request.
 * Importance: Communicates claim workflow status to clients.
 * Alternatives: Use HTTP 202 with a location header only.
 */
public class ProfileClaimResponse {
    private final String profileId;
    private final String status;
    private final String message;

    /**
     * Creates a claim response.
     * Importance: Standardizes claim workflow responses.
     * Alternatives: Use a generic response wrapper.
     *
     * @param profileId the profile identifier.
     * @param status the claim status.
     * @param message the response message.
     */
    public ProfileClaimResponse(String profileId, String status, String message) {
        this.profileId = profileId;
        this.status = status;
        this.message = message;
    }

    /**
     * Returns the profile identifier.
     * Importance: Correlates the claim to a profile.
     * Alternatives: Use a hashed public profile identifier.
     *
     * @return the profile identifier.
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * Returns the claim status.
     * Importance: Indicates claim workflow progress.
     * Alternatives: Use an enum with workflow states.
     *
     * @return the claim status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the response message.
     * Importance: Provides context for the claim decision.
     * Alternatives: Provide a structured error code.
     *
     * @return the response message.
     */
    public String getMessage() {
        return message;
    }
}
