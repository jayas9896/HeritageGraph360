package com.heritagegraph360.insights.nosql;

import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Stores insight summaries for profiles.
 * Importance: Persists auditable insight outputs for reviewer access.
 * Alternatives: Store summaries in SQL with rigid schemas.
 */
@Document(collection = "insight_summaries")
public class InsightSummaryDocument {
    @Id
    private String id;
    private String tenantId;
    private String profileId;
    private String status;
    private String summary;
    private Instant createdAt;

    /**
     * Returns the document identifier.
     * Importance: Primary key for insight summaries.
     * Alternatives: Use a composite key with tenant and profile ID.
     *
     * @return the document identifier.
     */
    public String getId() {
        return id;
    }

    /**
     * Updates the document identifier.
     * Importance: Supports MongoDB document persistence.
     * Alternatives: Use a UUID-based string.
     *
     * @param id the document identifier.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the tenant identifier.
     * Importance: Ensures tenant isolation for insights.
     * Alternatives: Store tenant data in a separate metadata collection.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports tenant-scoped insight storage.
     * Alternatives: Use a composite partition key.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the profile identifier.
     * Importance: Links summaries to profiles.
     * Alternatives: Use a hashed public profile identifier.
     *
     * @return the profile identifier.
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * Updates the profile identifier.
     * Importance: Supports insight persistence.
     * Alternatives: Use a hashed public profile identifier.
     *
     * @param profileId the profile identifier.
     */
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    /**
     * Returns the insight status.
     * Importance: Indicates anomaly detection outcome.
     * Alternatives: Use an enum for statuses.
     *
     * @return the insight status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the insight status.
     * Importance: Supports insight lifecycle updates.
     * Alternatives: Use an enum for statuses.
     *
     * @param status the insight status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the summary.
     * Importance: Provides human-readable insight results.
     * Alternatives: Store only structured findings.
     *
     * @return the summary.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Updates the summary.
     * Importance: Supports insight storage.
     * Alternatives: Store only structured findings.
     *
     * @param summary the summary.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Returns the creation timestamp.
     * Importance: Supports audit timelines for insights.
     * Alternatives: Use database-generated timestamps only.
     *
     * @return the creation timestamp.
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Updates the creation timestamp.
     * Importance: Supports audit timelines for insights.
     * Alternatives: Use database-generated timestamps only.
     *
     * @param createdAt the creation timestamp.
     */
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
