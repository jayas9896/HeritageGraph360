package com.heritagegraph360.profile.grant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

/**
 * Represents a field-level visibility grant for a profile.
 * Importance: Enables explicit per-person access to sensitive fields.
 * Alternatives: Use a policy engine with attribute-based access rules.
 */
@Entity
@Table(name = "profile_grants")
public class ProfileGrantEntity {
    @Id
    @Column(name = "grant_id", nullable = false)
    private UUID grantId;

    @Column(name = "tenant_id", nullable = false, length = 64)
    private String tenantId;

    @Column(name = "profile_id", nullable = false)
    private UUID profileId;

    @Column(name = "grantee_account_id", nullable = false)
    private UUID granteeAccountId;

    @Column(name = "field_name", nullable = false, length = 64)
    private String fieldName;

    @Column(name = "visibility", nullable = false, length = 16)
    private String visibility;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    /**
     * Creates an empty grant entity.
     * Importance: Required by JPA for entity instantiation.
     * Alternatives: Use a builder with a protected constructor.
     */
    public ProfileGrantEntity() {
    }

    /**
     * Returns the grant identifier.
     * Importance: Primary key for grant records.
     * Alternatives: Use a composite key of profile and grantee.
     *
     * @return the grant identifier.
     */
    public UUID getGrantId() {
        return grantId;
    }

    /**
     * Updates the grant identifier.
     * Importance: Supports grant provisioning workflows.
     * Alternatives: Generate identifiers server-side only.
     *
     * @param grantId the grant identifier.
     */
    public void setGrantId(UUID grantId) {
        this.grantId = grantId;
    }

    /**
     * Returns the tenant identifier.
     * Importance: Enforces tenant isolation for grants.
     * Alternatives: Use a tenant relation entity.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports tenant-scoped grant management.
     * Alternatives: Use a composite key with tenant ID.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the profile identifier.
     * Importance: Links grants to specific profiles.
     * Alternatives: Use a profile entity association.
     *
     * @return the profile identifier.
     */
    public UUID getProfileId() {
        return profileId;
    }

    /**
     * Updates the profile identifier.
     * Importance: Supports grant provisioning workflows.
     * Alternatives: Use a profile entity association.
     *
     * @param profileId the profile identifier.
     */
    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }

    /**
     * Returns the grantee account identifier.
     * Importance: Identifies the account receiving access.
     * Alternatives: Use a composite key with grantee identity.
     *
     * @return the grantee account identifier.
     */
    public UUID getGranteeAccountId() {
        return granteeAccountId;
    }

    /**
     * Updates the grantee account identifier.
     * Importance: Supports grant provisioning workflows.
     * Alternatives: Use a profile entity association.
     *
     * @param granteeAccountId the grantee account identifier.
     */
    public void setGranteeAccountId(UUID granteeAccountId) {
        this.granteeAccountId = granteeAccountId;
    }

    /**
     * Returns the field name.
     * Importance: Enables field-level visibility enforcement.
     * Alternatives: Use structured field enums.
     *
     * @return the field name.
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Updates the field name.
     * Importance: Supports grant provisioning workflows.
     * Alternatives: Use structured field enums.
     *
     * @param fieldName the field name.
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Returns the visibility.
     * Importance: Encodes the access level granted.
     * Alternatives: Use a boolean flag for visibility.
     *
     * @return the visibility.
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * Updates the visibility.
     * Importance: Supports grant provisioning workflows.
     * Alternatives: Use a boolean flag for visibility.
     *
     * @param visibility the visibility.
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * Returns the creation timestamp.
     * Importance: Supports audit timeline reconstruction.
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
