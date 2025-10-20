package com.heritagegraph360.profile.api;

import com.heritagegraph360.profile.domain.AuditLogEntity;
import com.heritagegraph360.profile.repo.AuditLogRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes audit log retrieval for profile changes.
 * Importance: Enables compliance reporting and provenance review.
 * Alternatives: Provide audit logs only through offline reports.
 */
@RestController
@RequestMapping("/api/v1/audit")
public class AuditController {
    private final AuditLogRepository auditLogRepository;

    /**
     * Creates the audit controller.
     * Importance: Connects HTTP requests to audit storage.
     * Alternatives: Use a separate audit query service.
     *
     * @param auditLogRepository the audit log repository.
     */
    public AuditController(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    /**
     * Returns the audit trail for a profile.
     * Importance: Provides traceability for profile updates.
     * Alternatives: Require admin privileges for all audit access.
     *
     * @param tenantId the tenant identifier.
     * @param profileId the profile identifier.
     * @return the audit trail response.
     */
    @GetMapping("/{profileId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('REVIEWER')")
    public ResponseEntity<AuditLogResponse> getAuditTrail(
        @RequestHeader("x-tenant-id") String tenantId,
        @PathVariable String profileId) {
        List<AuditLogEntity> entries = auditLogRepository
            .findByTenantIdAndEntityId(tenantId, UUID.fromString(profileId));
        List<AuditLogEntryResponse> responseEntries = entries.stream()
            .map(entry -> new AuditLogEntryResponse(
                entry.getAuditId().toString(),
                entry.getAction(),
                entry.getDetails(),
                entry.getCreatedAt().toString()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(new AuditLogResponse(profileId, responseEntries));
    }
}
