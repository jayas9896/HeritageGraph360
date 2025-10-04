package com.heritagegraph360.profile.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

/**
 * Represents a relationship between two profiles.
 * Importance: Encodes lineage and relationship status states.
 * Alternatives: Use a graph database edge model.
 */
@Entity
@Table(name = "relationships")
public class RelationshipEntity {
    @Id
    @Column(name = "relationship_id", nullable = false)
    private UUID relationshipId;

    @Column(name = "tenant_id", nullable = false, length = 64)
    private String tenantId;

    @Column(name = "profile_id", nullable = false)
    private UUID profileId;

    @Column(name = "related_profile_id", nullable = false)
    private UUID relatedProfileId;

    @Column(name = "status", nullable = false, length = 16)
    private String status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    /**
     * Creates an empty relationship entity.
     * Importance: Required by JPA for entity instantiation.
     * Alternatives: Use a builder with a protected constructor.
     */
    public RelationshipEntity() {
    }

    /**
     * Returns the relationship identifier.
     * Importance: Primary key for relationship records.
     * Alternatives: Use a composite key based on profile IDs.
     *
     * @return the relationship identifier.
     */
    public UUID getRelationshipId() {
        return relationshipId;
    }

    /**
     * Updates the relationship identifier.
     * Importance: Supports relationship provisioning workflows.
     * Alternatives: Generate identifiers server-side only.
     *
     * @param relationshipId the relationship identifier.
     */
    public void setRelationshipId(UUID relationshipId) {
        this.relationshipId = relationshipId;
    }

    /**
     * Returns the tenant identifier.
     * Importance: Enforces tenant isolation for relationships.
     * Alternatives: Use a tenant relation entity.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports tenant-scoped relationship operations.
     * Alternatives: Use a composite key with tenant ID.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the primary profile identifier.
     * Importance: Identifies the source profile in the relationship.
     * Alternatives: Use a graph edge entity.
     *
     * @return the primary profile identifier.
     */
    public UUID getProfileId() {
        return profileId;
    }

    /**
     * Updates the primary profile identifier.
     * Importance: Supports relationship creation workflows.
     * Alternatives: Use a relationship association entity.
     *
     * @param profileId the profile identifier.
     */
    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }

    /**
     * Returns the related profile identifier.
     * Importance: Identifies the target profile in the relationship.
     * Alternatives: Use a graph edge entity.
     *
     * @return the related profile identifier.
     */
    public UUID getRelatedProfileId() {
        return relatedProfileId;
    }

    /**
     * Updates the related profile identifier.
     * Importance: Supports relationship creation workflows.
     * Alternatives: Use a relationship association entity.
     *
     * @param relatedProfileId the related profile identifier.
     */
    public void setRelatedProfileId(UUID relatedProfileId) {
        this.relatedProfileId = relatedProfileId;
    }

    /**
     * Returns the relationship status.
     * Importance: Supports visible state tracking (green/yellow/orange/red).
     * Alternatives: Use an enum with lifecycle transitions.
     *
     * @return the relationship status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the relationship status.
     * Importance: Supports conflict resolution workflows.
     * Alternatives: Use an enum with lifecycle transitions.
     *
     * @param status the relationship status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the creation timestamp.
     * Importance: Enables audit and reporting workflows.
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
