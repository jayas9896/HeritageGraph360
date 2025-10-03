package com.heritagegraph360.identity.rbac;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

/**
 * Represents an RBAC permission.
 * Importance: Granular permissions underpin role-based authorization.
 * Alternatives: Use attribute-based policy checks only.
 */
@Entity
@Table(name = "permissions")
public class PermissionEntity {
    @Id
    @Column(name = "permission_id", nullable = false)
    private UUID permissionId;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    /**
     * Creates an empty permission entity.
     * Importance: Required by JPA for entity instantiation.
     * Alternatives: Use a builder with a protected constructor.
     */
    public PermissionEntity() {
    }

    /**
     * Returns the permission identifier.
     * Importance: Primary key for permission records.
     * Alternatives: Use a string-based permission key.
     *
     * @return the permission identifier.
     */
    public UUID getPermissionId() {
        return permissionId;
    }

    /**
     * Updates the permission identifier.
     * Importance: Supports permission provisioning workflows.
     * Alternatives: Generate identifiers server-side only.
     *
     * @param permissionId the permission identifier.
     */
    public void setPermissionId(UUID permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * Returns the permission name.
     * Importance: Used in policy mapping and authorization checks.
     * Alternatives: Use numeric permission codes.
     *
     * @return the permission name.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the permission name.
     * Importance: Supports permission management workflows.
     * Alternatives: Make permission names immutable.
     *
     * @param name the permission name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
