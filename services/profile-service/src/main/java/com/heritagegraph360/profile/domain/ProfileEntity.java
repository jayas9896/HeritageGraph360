package com.heritagegraph360.profile.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

/**
 * Represents a person profile in the genealogy domain.
 * Importance: Anchors relationships, claims, and lineage evidence.
 * Alternatives: Store profiles in a graph database instead of SQL.
 */
@Entity
@Table(name = "profiles")
public class ProfileEntity {
    @Id
    @Column(name = "profile_id", nullable = false)
    private UUID profileId;

    @Column(name = "tenant_id", nullable = false, length = 64)
    private String tenantId;

    @Column(name = "primary_email", length = 255)
    private String primaryEmail;

    @Column(name = "primary_phone", length = 32)
    private String primaryPhone;

    @Column(name = "display_name", length = 128)
    private String displayName;

    @Column(name = "claimed", nullable = false)
    private boolean claimed;

    @Column(name = "visibility", nullable = false, length = 16)
    private String visibility;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    /**
     * Creates an empty profile entity.
     * Importance: Required by JPA for entity instantiation.
     * Alternatives: Use a builder with a protected constructor.
     */
    public ProfileEntity() {
    }

    /**
     * Returns the profile identifier.
     * Importance: Primary key for profile records.
     * Alternatives: Use a string-based profile identifier.
     *
     * @return the profile identifier.
     */
    public UUID getProfileId() {
        return profileId;
    }

    /**
     * Updates the profile identifier.
     * Importance: Supports profile provisioning workflows.
     * Alternatives: Generate identifiers server-side only.
     *
     * @param profileId the profile identifier.
     */
    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }

    /**
     * Returns the tenant identifier.
     * Importance: Enforces tenant isolation for profiles.
     * Alternatives: Use a tenant relation entity.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports tenant-scoped profile operations.
     * Alternatives: Use a composite key with tenant ID.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the primary email.
     * Importance: Supports uniqueness and contact workflows.
     * Alternatives: Store contact methods in a separate table.
     *
     * @return the primary email.
     */
    public String getPrimaryEmail() {
        return primaryEmail;
    }

    /**
     * Updates the primary email.
     * Importance: Supports profile updates and verification.
     * Alternatives: Restrict updates to verified emails only.
     *
     * @param primaryEmail the primary email.
     */
    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    /**
     * Returns the primary phone.
     * Importance: Supports uniqueness and contact workflows.
     * Alternatives: Store contact methods in a separate table.
     *
     * @return the primary phone.
     */
    public String getPrimaryPhone() {
        return primaryPhone;
    }

    /**
     * Updates the primary phone.
     * Importance: Supports profile updates and verification.
     * Alternatives: Restrict updates to verified phones only.
     *
     * @param primaryPhone the primary phone.
     */
    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    /**
     * Returns the display name.
     * Importance: Provides a human-readable profile identifier.
     * Alternatives: Use structured name fields (given/family).
     *
     * @return the display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Updates the display name.
     * Importance: Supports profile updates and search workflows.
     * Alternatives: Use structured name fields (given/family).
     *
     * @param displayName the display name.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns whether the profile is claimed.
     * Importance: Controls approval workflows for sensitive updates.
     * Alternatives: Use claim status enums.
     *
     * @return true if claimed.
     */
    public boolean isClaimed() {
        return claimed;
    }

    /**
     * Updates the claimed flag.
     * Importance: Supports claim workflows and audit decisions.
     * Alternatives: Use a claim status enum.
     *
     * @param claimed the claimed flag.
     */
    public void setClaimed(boolean claimed) {
        this.claimed = claimed;
    }

    /**
     * Returns the visibility flag.
     * Importance: Controls public exposure of profile data.
     * Alternatives: Use an enum for visibility.
     *
     * @return the visibility flag.
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * Updates the visibility flag.
     * Importance: Supports opt-in public profile workflows.
     * Alternatives: Use a separate visibility policy entity.
     *
     * @param visibility the visibility flag.
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * Returns the creation timestamp.
     * Importance: Enables audit and reporting workflows.
     * Alternatives: Use database-generated timestamps only.
     *
     * @return the creation timestamp.
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Updates the creation timestamp.
     * Importance: Supports data migration and backfill workflows.
     * Alternatives: Make timestamps immutable after creation.
     *
     * @param createdAt the creation timestamp.
     */
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
