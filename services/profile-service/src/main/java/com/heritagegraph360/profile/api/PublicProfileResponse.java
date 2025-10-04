package com.heritagegraph360.profile.api;

/**
 * Represents a public profile response payload.
 * Importance: Ensures public responses expose only safe, opt-in fields.
 * Alternatives: Use a map-based response with a schema registry.
 */
public class PublicProfileResponse {
    private final String profileId;
    private final String displayName;
    private final String visibility;

    /**
     * Creates a public profile response.
     * Importance: Standardizes the outbound payload for public profile access.
     * Alternatives: Use a builder for optional fields.
     *
     * @param profileId the profile identifier.
     * @param displayName the display name for public view.
     * @param visibility the visibility flag.
     */
    public PublicProfileResponse(String profileId, String displayName, String visibility) {
        this.profileId = profileId;
        this.displayName = displayName;
        this.visibility = visibility;
    }

    /**
     * Returns the profile identifier.
     * Importance: Allows consumers to correlate public profiles with references.
     * Alternatives: Use a hashed public identifier.
     *
     * @return the profile identifier.
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * Returns the display name.
     * Importance: Provides a human-readable identifier for public views.
     * Alternatives: Use initials-only formatting for privacy.
     *
     * @return the display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the visibility marker.
     * Importance: Communicates the visibility state of the profile.
     * Alternatives: Use an enum for visibility states.
     *
     * @return the visibility flag.
     */
    public String getVisibility() {
        return visibility;
    }
}
