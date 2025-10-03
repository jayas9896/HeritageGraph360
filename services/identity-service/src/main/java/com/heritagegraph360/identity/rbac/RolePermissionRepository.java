package com.heritagegraph360.identity.rbac;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manages role-permission mapping persistence.
 * Importance: Supports RBAC grant management.
 * Alternatives: Use an external policy engine.
 */
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, RolePermissionId> {
}
