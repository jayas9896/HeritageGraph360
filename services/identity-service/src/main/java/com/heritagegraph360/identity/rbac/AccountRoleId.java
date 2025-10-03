package com.heritagegraph360.identity.rbac;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Composite key for account-role mappings.
 * Importance: Ensures unique account-role assignments.
 * Alternatives: Use a surrogate key for join tables.
 */
@Embeddable
public class AccountRoleId implements Serializable {
    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Column(name = "role_id", nullable = false)
    private UUID roleId;

    /**
     * Creates an empty composite key.
     * Importance: Required by JPA for embeddable instantiation.
     * Alternatives: Use a constructor-only immutable key.
     */
    public AccountRoleId() {
    }

    /**
     * Returns the account identifier.
     * Importance: Identifies the account in the mapping.
     * Alternatives: Use an account entity association.
     *
     * @return the account identifier.
     */
    public UUID getAccountId() {
        return accountId;
    }

    /**
     * Updates the account identifier.
     * Importance: Supports mapping creation workflows.
     * Alternatives: Make composite keys immutable.
     *
     * @param accountId the account identifier.
     */
    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
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
        AccountRoleId that = (AccountRoleId) o;
        return Objects.equals(accountId, that.accountId) && Objects.equals(roleId, that.roleId);
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
        return Objects.hash(accountId, roleId);
    }
}
