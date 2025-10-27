package com.heritagegraph360.identity.api.accounts;

/**
 * Represents an account creation request.
 * Importance: Captures account provisioning inputs.
 * Alternatives: Use external identity provider provisioning only.
 */
public class AccountRequest {
    private String tenantId;
    private String email;
    private String phone;
    private String displayName;
    private String status;

    /**
     * Returns the tenant identifier.
     * Importance: Ensures account is tenant-scoped.
     * Alternatives: Derive tenant from token claims.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports payload binding.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the email.
     * Importance: Enables contact-based account workflows.
     * Alternatives: Use a separate contact method table.
     *
     * @return the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Updates the email.
     * Importance: Supports payload binding.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param email the email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the phone.
     * Importance: Enables phone-based account workflows.
     * Alternatives: Use a separate contact method table.
     *
     * @return the phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Updates the phone.
     * Importance: Supports payload binding.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param phone the phone.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the display name.
     * Importance: Provides a human-readable account label.
     * Alternatives: Use a profile display name instead.
     *
     * @return the display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Updates the display name.
     * Importance: Supports payload binding.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param displayName the display name.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the status.
     * Importance: Controls account activation state.
     * Alternatives: Use an enum for status.
     *
     * @return the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the status.
     * Importance: Supports payload binding.
     * Alternatives: Use an enum for status.
     *
     * @param status the status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
