package com.heritagegraph360.common;

/**
 * Holds tenant identifiers for multi-tenant request scoping.
 * Importance: Centralizes tenant context so services consistently enforce isolation.
 * Alternatives: Use a ThreadLocal-based tenant resolver instead of a value object.
 */
public class TenantContext {
    private String tenantId;
    private String region;
    private String orgId;

    /**
     * Creates an empty tenant context for later population.
     * Importance: Allows frameworks to instantiate the context for request binding.
     * Alternatives: Use a builder pattern and forbid empty construction.
     */
    public TenantContext() {
        this.tenantId = "";
        this.region = "";
        this.orgId = "";
    }

    /**
     * Creates a tenant context with identifiers.
     * Importance: Captures the tenant keys needed for routing and authorization.
     * Alternatives: Accept a single composite tenant identifier string.
     *
     * @param tenantId the composite tenant identifier.
     * @param region the tenant region code.
     * @param orgId the organization identifier.
     */
    public TenantContext(String tenantId, String region, String orgId) {
        this.tenantId = tenantId;
        this.region = region;
        this.orgId = orgId;
    }

    /**
     * Returns the composite tenant identifier.
     * Importance: Used to bind data partitions and access policies.
     * Alternatives: Use only region and orgId with a derived composite key.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the composite tenant identifier.
     * Importance: Supports request-level overrides in gateway flows.
     * Alternatives: Make the tenant context immutable.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the region component.
     * Importance: Enables regional routing and compliance controls.
     * Alternatives: Store region within a separate locale object.
     *
     * @return the region code.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Updates the region component.
     * Importance: Allows request processing to adjust geographic scope.
     * Alternatives: Use a normalized region enumeration.
     *
     * @param region the region code.
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Returns the organization identifier.
     * Importance: Aligns authorization checks with organizational ownership.
     * Alternatives: Use a numeric surrogate identifier.
     *
     * @return the organization identifier.
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * Updates the organization identifier.
     * Importance: Ensures tenant ownership aligns with request scope.
     * Alternatives: Use immutable value objects with copies.
     *
     * @param orgId the organization identifier.
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
