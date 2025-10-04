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
}
