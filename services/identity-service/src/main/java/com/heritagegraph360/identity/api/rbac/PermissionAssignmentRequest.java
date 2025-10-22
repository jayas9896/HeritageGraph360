package com.heritagegraph360.identity.api.rbac;

/**
 * Represents a permission assignment request.
 * Importance: Captures permission assignment input for roles.
 * Alternatives: Use a batch assignment endpoint.
 */
public class PermissionAssignmentRequest {
    private String permissionId;

    /**
     * Returns the permission identifier.
     * Importance: Supports permission assignment.
     * Alternatives: Use a permission name instead of ID.
     *
     * @return the permission identifier.
     */
    public String getPermissionId() {
        return permissionId;
    }

    /**
     * Updates the permission identifier.
     * Importance: Supports payload binding.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param permissionId the permission identifier.
     */
    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}
