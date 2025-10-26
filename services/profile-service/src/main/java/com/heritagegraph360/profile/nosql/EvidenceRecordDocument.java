package com.heritagegraph360.profile.nosql;

import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Stores evidence metadata for lineage records.
 * Importance: Preserves provenance for verification workflows.
 * Alternatives: Store evidence metadata in SQL with rigid schemas.
 */
@Document(collection = "evidence_records")
public class EvidenceRecordDocument {
    @Id
    private String id;
    private String tenantId;
    private String profileId;
    private String evidenceId;
    private String sourceType;
    private String schemaVersion;
    private String contentHash;
    private String metadataJson;
    private Instant ingestedAt;

    /**
     * Returns the document identifier.
     * Importance: Primary key for evidence documents.
     * Alternatives: Use a composite key with tenant and evidence ID.
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
     * Importance: Ensures tenant isolation for evidence.
     * Alternatives: Store tenant data in a separate metadata collection.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports tenant-scoped evidence storage.
     * Alternatives: Use a composite partition key.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the evidence identifier.
     * Importance: Links evidence to lineage workflows.
     * Alternatives: Use a composite evidence key.
     *
     * @return the evidence identifier.
     */
    public String getEvidenceId() {
        return evidenceId;
    }

    /**
     * Updates the evidence identifier.
     * Importance: Supports evidence ingestion workflows.
     * Alternatives: Use a UUID-only identifier.
     *
     * @param evidenceId the evidence identifier.
     */
    public void setEvidenceId(String evidenceId) {
        this.evidenceId = evidenceId;
    }

    /**
     * Returns the profile identifier.
     * Importance: Links evidence to profiles for lineage verification.
     * Alternatives: Store profile references in metadata only.
     *
     * @return the profile identifier.
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * Updates the profile identifier.
     * Importance: Supports evidence ingestion workflows.
     * Alternatives: Store profile references in metadata only.
     *
     * @param profileId the profile identifier.
     */
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    /**
     * Returns the source type.
     * Importance: Captures provenance of evidence documents.
     * Alternatives: Use enumerated source types.
     *
     * @return the source type.
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * Updates the source type.
     * Importance: Supports evidence classification.
     * Alternatives: Use enumerated source types.
     *
     * @param sourceType the source type.
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * Returns the schema version.
     * Importance: Tracks evidence schema evolution.
     * Alternatives: Use a registry-managed schema ID.
     *
     * @return the schema version.
     */
    public String getSchemaVersion() {
        return schemaVersion;
    }

    /**
     * Updates the schema version.
     * Importance: Supports schema validation workflows.
     * Alternatives: Use a registry-managed schema ID.
     *
     * @param schemaVersion the schema version.
     */
    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    /**
     * Returns the content hash.
     * Importance: Enables integrity checks for evidence.
     * Alternatives: Use digital signatures instead of hashes.
     *
     * @return the content hash.
     */
    public String getContentHash() {
        return contentHash;
    }

    /**
     * Updates the content hash.
     * Importance: Supports evidence integrity workflows.
     * Alternatives: Use digital signatures instead of hashes.
     *
     * @param contentHash the content hash.
     */
    public void setContentHash(String contentHash) {
        this.contentHash = contentHash;
    }

    /**
     * Returns the metadata JSON.
     * Importance: Stores flexible metadata for evidence records.
     * Alternatives: Use structured MongoDB documents.
     *
     * @return the metadata JSON.
     */
    public String getMetadataJson() {
        return metadataJson;
    }

    /**
     * Updates the metadata JSON.
     * Importance: Supports evidence metadata persistence.
     * Alternatives: Use structured MongoDB documents.
     *
     * @param metadataJson the metadata JSON.
     */
    public void setMetadataJson(String metadataJson) {
        this.metadataJson = metadataJson;
    }

    /**
     * Returns the ingestion timestamp.
     * Importance: Supports audit timelines and retention policies.
     * Alternatives: Use database-generated timestamps only.
     *
     * @return the ingestion timestamp.
     */
    public Instant getIngestedAt() {
        return ingestedAt;
    }

    /**
     * Updates the ingestion timestamp.
     * Importance: Supports audit timelines and retention policies.
     * Alternatives: Use database-generated timestamps only.
     *
     * @param ingestedAt the ingestion timestamp.
     */
    public void setIngestedAt(Instant ingestedAt) {
        this.ingestedAt = ingestedAt;
    }
}
