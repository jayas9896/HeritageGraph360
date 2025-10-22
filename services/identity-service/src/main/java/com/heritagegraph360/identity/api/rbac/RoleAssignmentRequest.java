package com.heritagegraph360.identity.api.rbac;

/**
 * Represents a role assignment request.
 * Importance: Captures role assignment input for accounts.
 * Alternatives: Use a batch assignment endpoint.
 */
public class RoleAssignmentRequest {
    private String roleId;

    /**
     * Returns the role identifier.
     * Importance: Supports role assignment.
     * Alternatives: Use a role name instead of ID.
     *
     * @return the role identifier.
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * Updates the role identifier.
     * Importance: Supports payload binding.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param roleId the role identifier.
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
