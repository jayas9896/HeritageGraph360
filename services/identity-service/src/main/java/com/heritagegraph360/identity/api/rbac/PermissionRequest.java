package com.heritagegraph360.identity.api.rbac;

/**
 * Represents a permission creation request.
 * Importance: Captures permission provisioning input.
 * Alternatives: Use static permission definitions.
 */
public class PermissionRequest {
    private String name;

    /**
     * Returns the permission name.
     * Importance: Supports permission creation.
     * Alternatives: Use a permission code instead of a name.
     *
     * @return the permission name.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the permission name.
     * Importance: Supports payload binding.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param name the permission name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
