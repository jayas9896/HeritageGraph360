package com.heritagegraph360.profile.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

/**
 * Represents a merge request between two profiles.
 * Importance: Tracks staged merge workflows and audit decisions.
 * Alternatives: Use a workflow engine with external state storage.
 */
@Entity
@Table(name = "merges")
public class MergeEntity {
    @Id
    @Column(name = "merge_id", nullable = false)
    private UUID mergeId;

    @Column(name = "tenant_id", nullable = false, length = 64)
    private String tenantId;

    @Column(name = "source_profile_id", nullable = false)
    private UUID sourceProfileId;

    @Column(name = "target_profile_id", nullable = false)
    private UUID targetProfileId;

    @Column(name = "status", nullable = false, length = 32)
    private String status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    /**
     * Creates an empty merge entity.
     * Importance: Required by JPA for entity instantiation.
     * Alternatives: Use a builder with a protected constructor.
     */
    public MergeEntity() {
    }

    /**
     * Returns the merge identifier.
     * Importance: Primary key for merge records.
     * Alternatives: Use a composite key with source and target IDs.
     *
     * @return the merge identifier.
     */
    public UUID getMergeId() {
        return mergeId;
    }

    /**
     * Updates the merge identifier.
     * Importance: Supports merge workflow creation.
     * Alternatives: Generate identifiers server-side only.
     *
     * @param mergeId the merge identifier.
     */
    public void setMergeId(UUID mergeId) {
        this.mergeId = mergeId;
    }

    /**
     * Returns the tenant identifier.
     * Importance: Enforces tenant isolation for merges.
     * Alternatives: Use a tenant relation entity.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports tenant-scoped merge workflows.
     * Alternatives: Use a composite key with tenant ID.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the source profile identifier.
     * Importance: Tracks the profile initiating the merge.
     * Alternatives: Use a relationship-based merge context.
     *
     * @return the source profile identifier.
     */
    public UUID getSourceProfileId() {
        return sourceProfileId;
    }

    /**
     * Updates the source profile identifier.
     * Importance: Supports merge workflow creation.
     * Alternatives: Use a relationship-based merge context.
     *
     * @param sourceProfileId the source profile identifier.
     */
    public void setSourceProfileId(UUID sourceProfileId) {
        this.sourceProfileId = sourceProfileId;
    }

    /**
     * Returns the target profile identifier.
     * Importance: Tracks the profile to be merged.
     * Alternatives: Use a relationship-based merge context.
     *
     * @return the target profile identifier.
     */
    public UUID getTargetProfileId() {
        return targetProfileId;
    }

    /**
     * Updates the target profile identifier.
     * Importance: Supports merge workflow creation.
     * Alternatives: Use a relationship-based merge context.
     *
     * @param targetProfileId the target profile identifier.
     */
    public void setTargetProfileId(UUID targetProfileId) {
        this.targetProfileId = targetProfileId;
    }

    /**
     * Returns the merge status.
     * Importance: Tracks staged merge decisions.
     * Alternatives: Use an enum with workflow transitions.
     *
     * @return the merge status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the merge status.
     * Importance: Supports decision workflows.
     * Alternatives: Use an enum with workflow transitions.
     *
     * @param status the merge status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the creation timestamp.
     * Importance: Supports audit and timeline reconstruction.
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
