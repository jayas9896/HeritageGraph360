package com.heritagegraph360.identity.rbac;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manages permission persistence operations.
 * Importance: Supports permission provisioning and policy enforcement.
 * Alternatives: Use a managed policy service instead of direct persistence.
 */
public interface PermissionRepository extends JpaRepository<PermissionEntity, UUID> {
}
