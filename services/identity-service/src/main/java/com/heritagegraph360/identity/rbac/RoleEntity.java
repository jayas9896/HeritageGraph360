package com.heritagegraph360.identity.rbac;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

/**
 * Represents a role within a tenant.
 * Importance: Encodes RBAC roles for authorization decisions.
 * Alternatives: Use attribute-based access control instead of roles.
 */
@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @Column(name = "role_id", nullable = false)
    private UUID roleId;

    @Column(name = "tenant_id", nullable = false, length = 64)
    private String tenantId;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    /**
     * Creates an empty role entity.
     * Importance: Required by JPA for entity instantiation.
     * Alternatives: Use a builder with a protected constructor.
     */
    public RoleEntity() {
    }

    /**
     * Returns the role identifier.
     * Importance: Primary key for RBAC role records.
     * Alternatives: Use a composite key of tenant and role name.
     *
     * @return the role identifier.
     */
    public UUID getRoleId() {
        return roleId;
    }

    /**
     * Updates the role identifier.
     * Importance: Supports role provisioning workflows.
     * Alternatives: Generate identifiers server-side only.
     *
     * @param roleId the role identifier.
     */
    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    /**
     * Returns the tenant identifier.
     * Importance: Ensures role scope is tenant-bound.
     * Alternatives: Link to a tenant entity relation.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports tenant-scoped role management.
     * Alternatives: Use a composite key with tenant ID.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the role name.
     * Importance: Used in authorization checks and policy mapping.
     * Alternatives: Use numeric role codes.
     *
     * @return the role name.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the role name.
     * Importance: Supports role creation and updates.
     * Alternatives: Make role names immutable.
     *
     * @param name the role name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
