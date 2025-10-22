package com.heritagegraph360.identity.api.rbac;

/**
 * Represents a role creation request.
 * Importance: Captures role provisioning input.
 * Alternatives: Use pre-defined roles only.
 */
public class RoleRequest {
    private String name;

    /**
     * Returns the role name.
     * Importance: Supports role creation.
     * Alternatives: Use a role code instead of a name.
     *
     * @return the role name.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the role name.
     * Importance: Supports payload binding.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param name the role name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
