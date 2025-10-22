package com.heritagegraph360.identity.service;

import com.heritagegraph360.identity.domain.AccountEntity;
import com.heritagegraph360.identity.rbac.AccountRoleEntity;
import com.heritagegraph360.identity.rbac.AccountRoleId;
import com.heritagegraph360.identity.rbac.AccountRoleRepository;
import com.heritagegraph360.identity.rbac.PermissionEntity;
import com.heritagegraph360.identity.rbac.PermissionRepository;
import com.heritagegraph360.identity.rbac.RoleEntity;
import com.heritagegraph360.identity.rbac.RolePermissionEntity;
import com.heritagegraph360.identity.rbac.RolePermissionId;
import com.heritagegraph360.identity.rbac.RolePermissionRepository;
import com.heritagegraph360.identity.rbac.RoleRepository;
import com.heritagegraph360.identity.repo.AccountRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Manages RBAC provisioning and assignments.
 * Importance: Enables tenant-scoped role and permission management.
 * Alternatives: Delegate RBAC to an external identity provider.
 */
@Service
public class RbacProvisioningService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final AccountRepository accountRepository;

    /**
     * Creates the RBAC provisioning service.
     * Importance: Centralizes role and permission provisioning workflows.
     * Alternatives: Use separate services for roles and permissions.
     *
     * @param roleRepository the role repository.
     * @param permissionRepository the permission repository.
     * @param rolePermissionRepository the role permission repository.
     * @param accountRoleRepository the account role repository.
     * @param accountRepository the account repository.
     */
    public RbacProvisioningService(RoleRepository roleRepository,
                                   PermissionRepository permissionRepository,
                                   RolePermissionRepository rolePermissionRepository,
                                   AccountRoleRepository accountRoleRepository,
                                   AccountRepository accountRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.accountRoleRepository = accountRoleRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Creates a new role for a tenant.
     * Importance: Supports RBAC provisioning for tenant admins.
     * Alternatives: Use pre-defined roles only.
     *
     * @param tenantId the tenant identifier.
     * @param roleName the role name.
     * @return the created role.
     */
    public RoleEntity createRole(String tenantId, String roleName) {
        RoleEntity role = new RoleEntity();
        role.setRoleId(UUID.randomUUID());
        role.setTenantId(tenantId);
        role.setName(roleName);
        return roleRepository.save(role);
    }

    /**
     * Creates a new permission.
     * Importance: Enables granular RBAC permissions.
     * Alternatives: Use static permission definitions.
     *
     * @param permissionName the permission name.
     * @return the created permission.
     */
    public PermissionEntity createPermission(String permissionName) {
        PermissionEntity permission = new PermissionEntity();
        permission.setPermissionId(UUID.randomUUID());
        permission.setName(permissionName);
        return permissionRepository.save(permission);
    }

    /**
     * Assigns a permission to a role.
     * Importance: Links permissions to roles for authorization decisions.
     * Alternatives: Use policy-based rules only.
     *
     * @param roleId the role identifier.
     * @param permissionId the permission identifier.
     */
    public void assignPermissionToRole(UUID roleId, UUID permissionId) {
        RolePermissionId id = new RolePermissionId();
        id.setRoleId(roleId);
        id.setPermissionId(permissionId);
        RolePermissionEntity mapping = new RolePermissionEntity();
        mapping.setId(id);
        rolePermissionRepository.save(mapping);
    }

    /**
     * Assigns a role to an account.
     * Importance: Grants role-based access to users.
     * Alternatives: Use external identity provider role assignments.
     *
     * @param accountId the account identifier.
     * @param roleId the role identifier.
     */
    public void assignRoleToAccount(UUID accountId, UUID roleId) {
        AccountEntity account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalStateException("Account not found"));
        AccountRoleId id = new AccountRoleId();
        id.setAccountId(account.getAccountId());
        id.setRoleId(roleId);
        AccountRoleEntity mapping = new AccountRoleEntity();
        mapping.setId(id);
        accountRoleRepository.save(mapping);
    }

    /**
     * Lists roles for a tenant.
     * Importance: Supports admin role management.
     * Alternatives: Use cached role catalogs.
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
     * Lists all permissions.
     * Importance: Supports permission management and role configuration.
     * Alternatives: Use static permission definitions.
     *
     * @return the list of permissions.
     */
    public List<PermissionEntity> listPermissions() {
        return permissionRepository.findAll();
    }
}
