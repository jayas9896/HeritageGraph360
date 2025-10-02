package com.heritagegraph360.ingestion.api;

/**
 * Represents a REST ingestion request payload.
 * Importance: Captures a minimal record structure for submission.
 * Alternatives: Use an envelope with metadata and validation hints.
 */
public class IngestionRequest {
    private String tenantId;
    private String recordId;
    private String payloadJson;

    /**
     * Returns the tenant identifier.
     * Importance: Ensures ingestion is scoped to the correct tenant.
     * Alternatives: Resolve tenant from a JWT claim only.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports payload binding for REST ingestion.
     * Alternatives: Use a constructor-only immutable request.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the record identifier.
     * Importance: Enables idempotency checks in ingestion pipelines.
     * Alternatives: Generate IDs server-side.
     *
     * @return the record identifier.
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * Updates the record identifier.
     * Importance: Supports payload binding for REST ingestion.
     * Alternatives: Use a UUID-only field.
     *
     * @param recordId the record identifier.
     */
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    /**
     * Returns the raw payload JSON.
     * Importance: Captures flexible ingestion data without rigid schemas.
     * Alternatives: Use a structured record object with a schema registry.
     *
     * @return the payload JSON.
     */
    public String getPayloadJson() {
        return payloadJson;
    }

    /**
     * Updates the raw payload JSON.
     * Importance: Allows ingestion of dynamic record structures.
     * Alternatives: Store payload fields as typed attributes.
     *
     * @param payloadJson the payload JSON.
     */
    public void setPayloadJson(String payloadJson) {
        this.payloadJson = payloadJson;
    }
}
