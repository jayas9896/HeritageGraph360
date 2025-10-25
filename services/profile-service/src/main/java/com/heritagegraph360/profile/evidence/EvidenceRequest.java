package com.heritagegraph360.profile.evidence;

/**
 * Represents an evidence ingestion request.
 * Importance: Captures metadata for lineage evidence records.
 * Alternatives: Use a multipart upload with metadata headers.
 */
public class EvidenceRequest {
    private String evidenceId;
    private String sourceType;
    private String schemaVersion;
    private String contentHash;
    private String metadataJson;

    /**
     * Returns the evidence identifier.
     * Importance: Links evidence to lineage workflows.
     * Alternatives: Use a generated identifier only.
     *
     * @return the evidence identifier.
     */
    public String getEvidenceId() {
        return evidenceId;
    }

    /**
     * Updates the evidence identifier.
     * Importance: Supports payload binding.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param evidenceId the evidence identifier.
     */
    public void setEvidenceId(String evidenceId) {
        this.evidenceId = evidenceId;
    }

    /**
     * Returns the source type.
     * Importance: Captures evidence provenance.
     * Alternatives: Use an enum for source types.
     *
     * @return the source type.
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * Updates the source type.
     * Importance: Supports payload binding.
     * Alternatives: Use an enum for source types.
     *
     * @param sourceType the source type.
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * Returns the schema version.
     * Importance: Tracks schema evolution.
     * Alternatives: Use a schema registry ID.
     *
     * @return the schema version.
     */
    public String getSchemaVersion() {
        return schemaVersion;
    }

    /**
     * Updates the schema version.
     * Importance: Supports payload binding.
     * Alternatives: Use a schema registry ID.
     *
     * @param schemaVersion the schema version.
     */
    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    /**
     * Returns the content hash.
     * Importance: Supports integrity checks.
     * Alternatives: Use digital signatures.
     *
     * @return the content hash.
     */
    public String getContentHash() {
        return contentHash;
    }

    /**
     * Updates the content hash.
     * Importance: Supports payload binding.
     * Alternatives: Use digital signatures.
     *
     * @param contentHash the content hash.
     */
    public void setContentHash(String contentHash) {
        this.contentHash = contentHash;
    }

    /**
     * Returns the metadata JSON.
     * Importance: Captures flexible evidence metadata.
     * Alternatives: Use structured metadata objects.
     *
     * @return the metadata JSON.
     */
    public String getMetadataJson() {
        return metadataJson;
    }

    /**
     * Updates the metadata JSON.
     * Importance: Supports payload binding.
     * Alternatives: Use structured metadata objects.
     *
     * @param metadataJson the metadata JSON.
     */
    public void setMetadataJson(String metadataJson) {
        this.metadataJson = metadataJson;
    }
}
