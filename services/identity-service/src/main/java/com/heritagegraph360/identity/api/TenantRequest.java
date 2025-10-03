package com.heritagegraph360.identity.api;

/**
 * Represents the tenant provisioning request payload.
 * Importance: Captures the required tenant metadata for onboarding.
 * Alternatives: Use a separate onboarding workflow entity.
 */
public class TenantRequest {
    private String tenantId;
    private String region;
    private String orgId;
    private String name;

    /**
     * Returns the tenant identifier.
     * Importance: Ensures tenant provisioning aligns with naming rules.
     * Alternatives: Generate tenant identifiers server-side.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports payload binding for tenant provisioning.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the region.
     * Importance: Aligns tenant configuration with regional requirements.
     * Alternatives: Use a lookup table for region metadata.
     *
     * @return the region.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Updates the region.
     * Importance: Supports payload binding for tenant provisioning.
     * Alternatives: Disallow region updates after provisioning.
     *
     * @param region the region.
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Returns the organization identifier.
     * Importance: Links tenant provisioning to the owning organization.
     * Alternatives: Use a numeric organization ID only.
     *
     * @return the organization identifier.
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * Updates the organization identifier.
     * Importance: Supports payload binding for tenant provisioning.
     * Alternatives: Enforce immutable org IDs.
     *
     * @param orgId the organization identifier.
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * Returns the tenant name.
     * Importance: Provides a human-readable tenant label.
     * Alternatives: Store names in a separate tenant profile table.
     *
     * @return the tenant name.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the tenant name.
     * Importance: Supports payload binding for tenant provisioning.
     * Alternatives: Restrict name updates post onboarding.
     *
     * @param name the tenant name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
