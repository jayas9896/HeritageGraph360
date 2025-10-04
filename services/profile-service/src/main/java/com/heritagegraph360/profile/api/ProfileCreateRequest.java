package com.heritagegraph360.profile.api;

/**
 * Represents the profile creation request payload.
 * Importance: Captures the minimum identifiers required for profile creation.
 * Alternatives: Use separate fields for contact verification channels.
 */
public class ProfileCreateRequest {
    private String primaryEmail;
    private String primaryPhone;

    /**
     * Returns the primary email.
     * Importance: Supports unique identity creation rules.
     * Alternatives: Store email in a nested contact object.
     *
     * @return the primary email.
     */
    public String getPrimaryEmail() {
        return primaryEmail;
    }

    /**
     * Updates the primary email.
     * Importance: Enables client payload binding for identity input.
     * Alternatives: Use a constructor-only immutable request object.
     *
     * @param primaryEmail the primary email.
     */
    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    /**
     * Returns the primary phone.
     * Importance: Supports unique identity creation rules.
     * Alternatives: Store phone in a nested contact object.
     *
     * @return the primary phone.
     */
    public String getPrimaryPhone() {
        return primaryPhone;
    }

    /**
     * Updates the primary phone.
     * Importance: Enables client payload binding for identity input.
     * Alternatives: Use a constructor-only immutable request object.
     *
     * @param primaryPhone the primary phone.
     */
    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }
}
