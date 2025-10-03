package com.heritagegraph360.identity.rbac;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manages role persistence operations.
 * Importance: Supports role provisioning and policy enforcement.
 * Alternatives: Use a managed policy service instead of direct persistence.
 */
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
}
