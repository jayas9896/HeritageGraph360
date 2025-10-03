package com.heritagegraph360.identity.rbac;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Provides RBAC lookup utilities for authorization checks.
 * Importance: Centralizes role and permission retrieval for policies.
 * Alternatives: Query roles directly in security filters.
 */
@Service
public class RbacService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final AccountRoleRepository accountRoleRepository;

    /**
     * Creates the RBAC service.
     * Importance: Injects repositories needed for RBAC operations.
     * Alternatives: Use a cached policy store instead of repositories.
     *
     * @param roleRepository the role repository.
     * @param permissionRepository the permission repository.
     * @param rolePermissionRepository the role-permission repository.
     * @param accountRoleRepository the account-role repository.
     */
    public RbacService(RoleRepository roleRepository,
                       PermissionRepository permissionRepository,
                       RolePermissionRepository rolePermissionRepository,
                       AccountRoleRepository accountRoleRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.accountRoleRepository = accountRoleRepository;
    }

    /**
     * Returns all roles for a tenant.
     * Importance: Supports admin UI role listings.
     * Alternatives: Filter roles via a separate policy service.
     *
     * @param tenantId the tenant identifier.
     * @return the list of roles.
     */
    public List<RoleEntity> listRoles(String tenantId) {
        return roleRepository.findAll().stream()
            .filter(role -> tenantId.equals(role.getTenantId()))
            .toList();
    }

    /**
     * Returns all permissions.
     * Importance: Provides reference data for role provisioning.
     * Alternatives: Use a static permissions registry.
     *
     * @return the list of permissions.
     */
    public List<PermissionEntity> listPermissions() {
        return permissionRepository.findAll();
    }

    /**
     * Checks if an account has a specific role.
     * Importance: Enables simple role-based authorization checks.
     * Alternatives: Use permission-based checks only.
     *
     * @param accountId the account identifier.
     * @param roleId the role identifier.
     * @return true if the account has the role.
     */
    public boolean hasRole(UUID accountId, UUID roleId) {
        AccountRoleId id = new AccountRoleId();
        id.setAccountId(accountId);
        id.setRoleId(roleId);
        return accountRoleRepository.existsById(id);
    }
}
