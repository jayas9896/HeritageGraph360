package com.heritagegraph360.identity.rbac;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Composite key for role-permission mappings.
 * Importance: Ensures unique role-permission associations.
 * Alternatives: Use a surrogate key for join tables.
 */
@Embeddable
public class RolePermissionId implements Serializable {
    @Column(name = "role_id", nullable = false)
    private UUID roleId;

    @Column(name = "permission_id", nullable = false)
    private UUID permissionId;

    /**
     * Creates an empty composite key.
     * Importance: Required by JPA for embeddable instantiation.
     * Alternatives: Use a constructor-only immutable key.
     */
    public RolePermissionId() {
    }

    /**
     * Returns the role identifier.
     * Importance: Identifies the role in the mapping.
     * Alternatives: Use a role entity association.
     *
     * @return the role identifier.
     */
    public UUID getRoleId() {
        return roleId;
    }

    /**
     * Updates the role identifier.
     * Importance: Supports mapping creation workflows.
     * Alternatives: Make composite keys immutable.
     *
     * @param roleId the role identifier.
     */
    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    /**
     * Returns the permission identifier.
     * Importance: Identifies the permission in the mapping.
     * Alternatives: Use a permission entity association.
     *
     * @return the permission identifier.
     */
    public UUID getPermissionId() {
        return permissionId;
    }

    /**
     * Updates the permission identifier.
     * Importance: Supports mapping creation workflows.
     * Alternatives: Make composite keys immutable.
     *
     * @param permissionId the permission identifier.
     */
    public void setPermissionId(UUID permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * Compares composite keys for equality.
     * Importance: Required for proper JPA identity behavior.
     * Alternatives: Use a surrogate key entity instead.
     *
     * @param o the other object.
     * @return true if equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RolePermissionId that = (RolePermissionId) o;
        return Objects.equals(roleId, that.roleId) && Objects.equals(permissionId, that.permissionId);
    }

    /**
     * Computes the hash code for the composite key.
     * Importance: Required for correct map/set behavior.
     * Alternatives: Use an immutable record with generated hash.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(roleId, permissionId);
    }
}
