package com.heritagegraph360.identity.rbac;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Joins roles to permissions.
 * Importance: Establishes RBAC grants for authorization decisions.
 * Alternatives: Use an external policy engine for grants.
 */
@Entity
@Table(name = "role_permissions")
public class RolePermissionEntity {
    @EmbeddedId
    private RolePermissionId id;

    /**
     * Creates an empty mapping entity.
     * Importance: Required by JPA for entity instantiation.
     * Alternatives: Use a builder and immutable mapping.
     */
    public RolePermissionEntity() {
    }

    /**
     * Returns the composite key.
     * Importance: Identifies the role-permission mapping.
     * Alternatives: Use a surrogate key.
     *
     * @return the composite key.
     */
    public RolePermissionId getId() {
        return id;
    }

    /**
     * Updates the composite key.
     * Importance: Supports creation of new mappings.
     * Alternatives: Use a constructor-only mapping.
     *
     * @param id the composite key.
     */
    public void setId(RolePermissionId id) {
        this.id = id;
    }
}
