package com.heritagegraph360.identity.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a tenant registered in the platform.
 * Importance: Anchors multi-tenant isolation and policy enforcement.
 * Alternatives: Store tenant metadata in a separate configuration service.
 */
@Entity
@Table(name = "tenants")
public class TenantEntity {
    @Id
    @Column(name = "tenant_id", nullable = false, length = 64)
    private String tenantId;

    @Column(name = "region", nullable = false, length = 16)
    private String region;

    @Column(name = "org_id", nullable = false, length = 32)
    private String orgId;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    /**
     * Creates an empty tenant entity.
     * Importance: Required by JPA for entity instantiation.
     * Alternatives: Use a builder and JPA-compatible factory.
     */
    public TenantEntity() {
    }

    /**
     * Returns the tenant identifier.
     * Importance: Used as the primary key for tenant scoping.
     * Alternatives: Use a surrogate UUID key.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports tenant provisioning workflows.
     * Alternatives: Keep tenant identifiers immutable.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the tenant region.
     * Importance: Enables region-specific compliance controls.
     * Alternatives: Use a normalized region lookup table.
     *
     * @return the region code.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Updates the tenant region.
     * Importance: Supports tenant registration and updates.
     * Alternatives: Restrict region updates after provisioning.
     *
     * @param region the region code.
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Returns the organization identifier.
     * Importance: Aligns tenant ownership with account provisioning.
     * Alternatives: Use a numeric organization key.
     *
     * @return the organization identifier.
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * Updates the organization identifier.
     * Importance: Supports tenant metadata management.
     * Alternatives: Keep organization identifiers immutable.
     *
     * @param orgId the organization identifier.
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * Returns the tenant display name.
     * Importance: Provides a human-readable identifier for admins.
     * Alternatives: Store names in a separate profile table.
     *
     * @return the tenant name.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the tenant display name.
     * Importance: Supports tenant onboarding and edits.
     * Alternatives: Make tenant names immutable after provisioning.
     *
     * @param name the tenant name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
