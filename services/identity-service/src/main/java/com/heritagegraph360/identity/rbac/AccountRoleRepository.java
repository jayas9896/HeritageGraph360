package com.heritagegraph360.identity.rbac;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manages account-role mapping persistence.
 * Importance: Supports RBAC assignment workflows.
 * Alternatives: Use an external identity provider.
 */
public interface AccountRoleRepository extends JpaRepository<AccountRoleEntity, AccountRoleId> {
}
