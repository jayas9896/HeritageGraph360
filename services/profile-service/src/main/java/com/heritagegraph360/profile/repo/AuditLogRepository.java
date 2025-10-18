package com.heritagegraph360.profile.repo;

import com.heritagegraph360.profile.domain.AuditLogEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manages audit log persistence operations.
 * Importance: Provides compliance and provenance tracking.
 * Alternatives: Use a separate audit logging service.
 */
public interface AuditLogRepository extends JpaRepository<AuditLogEntity, UUID> {
    /**
     * Finds audit logs for a tenant and entity.
     * Importance: Supports audit trail retrieval for profiles.
     * Alternatives: Use a dedicated audit query service.
     *
     * @param tenantId the tenant identifier.
     * @param entityId the entity identifier.
     * @return audit log entries.
     */
    java.util.List<AuditLogEntity> findByTenantIdAndEntityId(String tenantId, UUID entityId);
}
