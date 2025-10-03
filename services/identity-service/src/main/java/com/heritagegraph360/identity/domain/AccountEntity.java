package com.heritagegraph360.identity.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

/**
 * Represents a user account within a tenant.
 * Importance: Links identities to authentication and authorization workflows.
 * Alternatives: Store accounts in an external identity provider only.
 */
@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Column(name = "tenant_id", nullable = false, length = 64)
    private String tenantId;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "phone", length = 32)
    private String phone;

    @Column(name = "display_name", nullable = false, length = 128)
    private String displayName;

    @Column(name = "status", nullable = false, length = 32)
    private String status;

    /**
     * Creates an empty account entity.
     * Importance: Required by JPA for entity instantiation.
     * Alternatives: Use a builder and JPA-compatible factory.
     */
    public AccountEntity() {
    }

    /**
     * Returns the account identifier.
     * Importance: Acts as the primary key for accounts.
     * Alternatives: Use a string-based identifier.
     *
     * @return the account identifier.
     */
    public UUID getAccountId() {
        return accountId;
    }

    /**
     * Updates the account identifier.
     * Importance: Supports account provisioning workflows.
     * Alternatives: Generate IDs server-side only.
     *
     * @param accountId the account identifier.
     */
    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    /**
     * Returns the tenant identifier.
     * Importance: Enforces tenant isolation for accounts.
     * Alternatives: Use a tenant relation entity.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports tenant-specific provisioning.
     * Alternatives: Use a tenant entity relationship.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the account email.
     * Importance: Supports OAuth2 and account recovery workflows.
     * Alternatives: Use a separate contact method table.
     *
     * @return the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Updates the account email.
     * Importance: Enables account updates and verification.
     * Alternatives: Restrict to verified email changes only.
     *
     * @param email the email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the account phone.
     * Importance: Supports multi-factor or phone-based login.
     * Alternatives: Store phones in a dedicated contact table.
     *
     * @return the phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Updates the account phone.
     * Importance: Enables account updates and verification.
     * Alternatives: Require verified phone updates only.
     *
     * @param phone the phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the display name.
     * Importance: Provides a human-readable account identifier.
     * Alternatives: Use a profile display name from profile service.
     *
     * @return the display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Updates the display name.
     * Importance: Supports account profile updates.
     * Alternatives: Make display names immutable.
     *
     * @param displayName the display name.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the account status.
     * Importance: Governs access control decisions.
     * Alternatives: Represent status as an enum.
     *
     * @return the account status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the account status.
     * Importance: Allows suspension or activation workflows.
     * Alternatives: Use a separate status history table.
     *
     * @param status the account status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
