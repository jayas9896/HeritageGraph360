package com.heritagegraph360.profile.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

/**
 * Represents an approval request for high-impact updates.
 * Importance: Enforces admin review for sensitive profile changes.
 * Alternatives: Use workflow engine approvals outside the core service.
 */
@Entity
@Table(name = "approvals")
public class ApprovalEntity {
    @Id
    @Column(name = "approval_id", nullable = false)
    private UUID approvalId;

    @Column(name = "tenant_id", nullable = false, length = 64)
    private String tenantId;

    @Column(name = "profile_id", nullable = false)
    private UUID profileId;

    @Column(name = "requested_by", nullable = false)
    private UUID requestedBy;

    @Column(name = "status", nullable = false, length = 16)
    private String status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    /**
     * Creates an empty approval entity.
     * Importance: Required by JPA for entity instantiation.
     * Alternatives: Use a builder with a protected constructor.
     */
    public ApprovalEntity() {
    }

    /**
     * Returns the approval identifier.
     * Importance: Primary key for approval records.
     * Alternatives: Use a composite key with profile and requester.
     *
     * @return the approval identifier.
     */
    public UUID getApprovalId() {
        return approvalId;
    }

    /**
     * Updates the approval identifier.
     * Importance: Supports approval workflow creation.
     * Alternatives: Generate identifiers server-side only.
     *
     * @param approvalId the approval identifier.
     */
    public void setApprovalId(UUID approvalId) {
        this.approvalId = approvalId;
    }

    /**
     * Returns the tenant identifier.
     * Importance: Enforces tenant isolation for approvals.
     * Alternatives: Use a tenant relation entity.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports tenant-scoped approval workflows.
     * Alternatives: Use a composite key with tenant ID.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the profile identifier.
     * Importance: Links the approval to the affected profile.
     * Alternatives: Use a profile entity association.
     *
     * @return the profile identifier.
     */
    public UUID getProfileId() {
        return profileId;
    }

    /**
     * Updates the profile identifier.
     * Importance: Supports approval workflow creation.
     * Alternatives: Use a profile entity association.
     *
     * @param profileId the profile identifier.
     */
    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }

    /**
     * Returns the requester identifier.
     * Importance: Tracks who requested the approval.
     * Alternatives: Use an approval request entity reference.
     *
     * @return the requester identifier.
     */
    public UUID getRequestedBy() {
        return requestedBy;
    }

    /**
     * Updates the requester identifier.
     * Importance: Supports approval workflow creation.
     * Alternatives: Use a request metadata object.
     *
     * @param requestedBy the requester identifier.
     */
    public void setRequestedBy(UUID requestedBy) {
        this.requestedBy = requestedBy;
    }

    /**
     * Returns the approval status.
     * Importance: Drives workflow transitions for sensitive updates.
     * Alternatives: Use an enum with workflow rules.
     *
     * @return the approval status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the approval status.
     * Importance: Supports approval workflow transitions.
     * Alternatives: Use a workflow engine.
     *
     * @param status the approval status.
     */
    public void setStatus(String status) {
        this.status = status;
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
