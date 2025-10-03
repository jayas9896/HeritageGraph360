package com.heritagegraph360.identity.rbac;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Joins accounts to roles.
 * Importance: Assigns RBAC roles to authenticated users.
 * Alternatives: Use external identity provider role assignments.
 */
@Entity
@Table(name = "account_roles")
public class AccountRoleEntity {
    @EmbeddedId
    private AccountRoleId id;

    /**
     * Creates an empty mapping entity.
     * Importance: Required by JPA for entity instantiation.
     * Alternatives: Use a builder and immutable mapping.
     */
    public AccountRoleEntity() {
    }

    /**
     * Returns the composite key.
     * Importance: Identifies the account-role mapping.
     * Alternatives: Use a surrogate key.
     *
     * @return the composite key.
     */
    public AccountRoleId getId() {
        return id;
    }

    /**
     * Updates the composite key.
     * Importance: Supports creation of new mappings.
     * Alternatives: Use a constructor-only mapping.
     *
     * @param id the composite key.
     */
    public void setId(AccountRoleId id) {
        this.id = id;
    }
}
