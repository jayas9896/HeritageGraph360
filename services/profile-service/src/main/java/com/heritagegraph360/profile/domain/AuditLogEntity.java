package com.heritagegraph360.profile.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

/**
 * Represents an audit log entry for profile changes.
 * Importance: Supports compliance and provenance tracking.
 * Alternatives: Store audit events in an external logging system only.
 */
@Entity
@Table(name = "audit_logs")
public class AuditLogEntity {
    @Id
    @Column(name = "audit_id", nullable = false)
    private UUID auditId;

    @Column(name = "tenant_id", nullable = false, length = 64)
    private String tenantId;

    @Column(name = "actor_id", nullable = false)
    private UUID actorId;

    @Column(name = "action", nullable = false, length = 128)
    private String action;

    @Column(name = "entity_id", nullable = false)
    private UUID entityId;

    @Column(name = "details", columnDefinition = "TEXT")
    private String details;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    /**
     * Creates an empty audit log entity.
     * Importance: Required by JPA for entity instantiation.
     * Alternatives: Use a builder with a protected constructor.
     */
    public AuditLogEntity() {
    }

    /**
     * Returns the audit identifier.
     * Importance: Primary key for audit records.
     * Alternatives: Use a composite key with timestamp.
     *
     * @return the audit identifier.
     */
    public UUID getAuditId() {
        return auditId;
    }

    /**
     * Updates the audit identifier.
     * Importance: Supports audit event creation workflows.
     * Alternatives: Generate identifiers server-side only.
     *
     * @param auditId the audit identifier.
     */
    public void setAuditId(UUID auditId) {
        this.auditId = auditId;
    }

    /**
     * Returns the tenant identifier.
     * Importance: Enforces tenant isolation for audit logs.
     * Alternatives: Use a tenant relation entity.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports tenant-scoped audit workflows.
     * Alternatives: Use a composite key with tenant ID.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the actor identifier.
     * Importance: Tracks who performed the audited action.
     * Alternatives: Store actor references in a separate table.
     *
     * @return the actor identifier.
     */
    public UUID getActorId() {
        return actorId;
    }

    /**
     * Updates the actor identifier.
     * Importance: Supports audit event creation workflows.
     * Alternatives: Use a composite key for actor and tenant.
     *
     * @param actorId the actor identifier.
     */
    public void setActorId(UUID actorId) {
        this.actorId = actorId;
    }

    /**
     * Returns the action.
     * Importance: Communicates the action captured by the audit log.
     * Alternatives: Use enumerated audit action codes.
     *
     * @return the action.
     */
    public String getAction() {
        return action;
    }

    /**
     * Updates the action.
     * Importance: Supports audit event creation workflows.
     * Alternatives: Restrict action values to predefined codes.
     *
     * @param action the action.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Returns the entity identifier.
     * Importance: Links audit events to the affected entity.
     * Alternatives: Use a composite entity key object.
     *
     * @return the entity identifier.
     */
    public UUID getEntityId() {
        return entityId;
    }

    /**
     * Updates the entity identifier.
     * Importance: Supports audit event creation workflows.
     * Alternatives: Use a structured entity reference.
     *
     * @param entityId the entity identifier.
     */
    public void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }

    /**
     * Returns the audit details.
     * Importance: Stores provenance and event context.
     * Alternatives: Store details in a separate event store.
     *
     * @return the details.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Updates the audit details.
     * Importance: Supports capturing additional audit metadata.
     * Alternatives: Store structured JSON instead of text.
     *
     * @param details the details.
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Returns the creation timestamp.
     * Importance: Enables audit timeline reconstruction.
     * Alternatives: Use database-generated timestamps only.
     *
     * @return the creation timestamp.
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Updates the creation timestamp.
     * Importance: Supports data migration and backfill workflows.
     * Alternatives: Make timestamps immutable after creation.
     *
     * @param createdAt the creation timestamp.
     */
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
