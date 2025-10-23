package com.heritagegraph360.profile.api;

/**
 * Represents a profile view response.
 * Importance: Exposes profile fields based on visibility rules.
 * Alternatives: Use a generic response wrapper.
 */
public class ProfileViewResponse {
    private String profileId;
    private String displayName;
    private String primaryEmail;
    private String primaryPhone;
    private String visibility;

    /**
     * Returns the profile identifier.
     * Importance: Identifies the profile in responses.
     * Alternatives: Use a hashed public profile identifier.
     *
     * @return the profile identifier.
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * Updates the profile identifier.
     * Importance: Supports response construction.
     * Alternatives: Use constructor-only immutable responses.
     *
     * @param profileId the profile identifier.
     */
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    /**
     * Returns the display name.
     * Importance: Provides a human-readable identifier.
     * Alternatives: Use structured name fields.
     *
     * @return the display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Updates the display name.
     * Importance: Supports response construction.
     * Alternatives: Use structured name fields.
     *
     * @param displayName the display name.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the primary email.
     * Importance: Exposes contact data when granted.
     * Alternatives: Use masked values instead of raw email.
     *
     * @return the primary email.
     */
    public String getPrimaryEmail() {
        return primaryEmail;
    }

    /**
     * Updates the primary email.
     * Importance: Supports response construction.
     * Alternatives: Use masked values instead of raw email.
     *
     * @param primaryEmail the primary email.
     */
    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    /**
     * Returns the primary phone.
     * Importance: Exposes contact data when granted.
     * Alternatives: Use masked values instead of raw phone.
     *
     * @return the primary phone.
     */
    public String getPrimaryPhone() {
        return primaryPhone;
    }

    /**
     * Updates the primary phone.
     * Importance: Supports response construction.
     * Alternatives: Use masked values instead of raw phone.
     *
     * @param primaryPhone the primary phone.
     */
    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    /**
     * Returns the visibility.
     * Importance: Indicates profile visibility status.
     * Alternatives: Use an enum for visibility.
     *
     * @return the visibility.
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * Updates the visibility.
     * Importance: Supports response construction.
     * Alternatives: Use an enum for visibility.
     *
     * @param visibility the visibility.
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
