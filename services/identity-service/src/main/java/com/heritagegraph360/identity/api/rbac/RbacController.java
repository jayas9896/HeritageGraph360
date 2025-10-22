package com.heritagegraph360.identity.api.rbac;

import com.heritagegraph360.identity.rbac.PermissionEntity;
import com.heritagegraph360.identity.rbac.RoleEntity;
import com.heritagegraph360.identity.service.RbacProvisioningService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes RBAC provisioning endpoints.
 * Importance: Enables admin management of roles and permissions.
 * Alternatives: Delegate RBAC to an external identity provider.
 */
@RestController
@RequestMapping("/api/v1/rbac")
public class RbacController {
    private final RbacProvisioningService provisioningService;

    /**
     * Creates the RBAC controller.
     * Importance: Connects HTTP requests to RBAC provisioning.
     * Alternatives: Use a separate RBAC service.
     *
     * @param provisioningService the provisioning service.
     */
    public RbacController(RbacProvisioningService provisioningService) {
        this.provisioningService = provisioningService;
    }

    /**
     * Creates a role for a tenant.
     * Importance: Supports tenant-scoped role management.
     * Alternatives: Use pre-defined roles only.
     *
     * @param tenantId the tenant identifier.
     * @param request the role request.
     * @return the created role.
     */
    @PostMapping("/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleEntity> createRole(
        @RequestHeader("x-tenant-id") String tenantId,
        @RequestBody RoleRequest request) {
        return ResponseEntity.ok(provisioningService.createRole(tenantId, request.getName()));
    }

    /**
     * Lists roles for a tenant.
     * Importance: Supports tenant role management.
     * Alternatives: Use cached role catalogs.
     *
     * @param tenantId the tenant identifier.
     * @return the roles.
     */
    @GetMapping("/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoleEntity>> listRoles(@RequestHeader("x-tenant-id") String tenantId) {
        return ResponseEntity.ok(provisioningService.listRoles(tenantId));
    }

    /**
     * Creates a permission.
     * Importance: Supports permission management.
     * Alternatives: Use static permission definitions.
     *
     * @param request the permission request.
     * @return the created permission.
     */
    @PostMapping("/permissions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PermissionEntity> createPermission(@RequestBody PermissionRequest request) {
        return ResponseEntity.ok(provisioningService.createPermission(request.getName()));
    }

    /**
     * Lists permissions.
     * Importance: Supports permission management.
     * Alternatives: Use static permission definitions.
     *
     * @return the permissions.
     */
    @GetMapping("/permissions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PermissionEntity>> listPermissions() {
        return ResponseEntity.ok(provisioningService.listPermissions());
    }

    /**
     * Assigns a permission to a role.
     * Importance: Links permissions to roles.
     * Alternatives: Use policy-based rules only.
     *
     * @param roleId the role identifier.
     * @param request the permission assignment request.
     * @return the response.
     */
    @PostMapping("/roles/{roleId}/permissions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> assignPermissionToRole(
        @PathVariable String roleId,
        @RequestBody PermissionAssignmentRequest request) {
        provisioningService.assignPermissionToRole(UUID.fromString(roleId), UUID.fromString(request.getPermissionId()));
        return ResponseEntity.ok().build();
    }

    /**
     * Assigns a role to an account.
     * Importance: Grants role-based access to accounts.
     * Alternatives: Use external identity provider roles.
     *
     * @param accountId the account identifier.
     * @param request the role assignment request.
     * @return the response.
     */
    @PostMapping("/accounts/{accountId}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> assignRoleToAccount(
        @PathVariable String accountId,
        @RequestBody RoleAssignmentRequest request) {
        provisioningService.assignRoleToAccount(UUID.fromString(accountId), UUID.fromString(request.getRoleId()));
        return ResponseEntity.ok().build();
    }
}
