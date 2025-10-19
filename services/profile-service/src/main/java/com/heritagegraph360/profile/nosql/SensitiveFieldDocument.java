package com.heritagegraph360.profile.nosql;

import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Stores encrypted sensitive fields for profiles.
 * Importance: Protects medical and financial data with encryption.
 * Alternatives: Use a dedicated vault or HSM-backed secrets store.
 */
@Document(collection = "sensitive_fields")
public class SensitiveFieldDocument {
    @Id
    private String id;
    private String tenantId;
    private String profileId;
    private String fieldType;
    private String encryptedValue;
    private Instant createdAt;

    /**
     * Returns the document identifier.
     * Importance: Primary key for sensitive field documents.
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
     * Importance: Ensures tenant isolation for sensitive data.
     * Alternatives: Store tenant data in a separate metadata collection.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports tenant-scoped sensitive field storage.
     * Alternatives: Use a composite partition key.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the profile identifier.
     * Importance: Links sensitive data to profiles.
     * Alternatives: Use a hashed public profile identifier.
     *
     * @return the profile identifier.
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * Updates the profile identifier.
     * Importance: Supports sensitive field persistence.
     * Alternatives: Use a hashed public profile identifier.
     *
     * @param profileId the profile identifier.
     */
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    /**
     * Returns the field type.
     * Importance: Identifies the sensitive field category.
     * Alternatives: Use an enum for field types.
     *
     * @return the field type.
     */
    public String getFieldType() {
        return fieldType;
    }

    /**
     * Updates the field type.
     * Importance: Supports sensitive field classification.
     * Alternatives: Use an enum for field types.
     *
     * @param fieldType the field type.
     */
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    /**
     * Returns the encrypted value.
     * Importance: Protects sensitive data at rest.
     * Alternatives: Use a dedicated encryption service.
     *
     * @return the encrypted value.
     */
    public String getEncryptedValue() {
        return encryptedValue;
    }

    /**
     * Updates the encrypted value.
     * Importance: Supports secure storage of sensitive data.
     * Alternatives: Use a dedicated encryption service.
     *
     * @param encryptedValue the encrypted value.
     */
    public void setEncryptedValue(String encryptedValue) {
        this.encryptedValue = encryptedValue;
    }

    /**
     * Returns the creation timestamp.
     * Importance: Supports audit timelines for sensitive data.
     * Alternatives: Use database-generated timestamps only.
     *
     * @return the creation timestamp.
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Updates the creation timestamp.
     * Importance: Supports audit timelines for sensitive data.
     * Alternatives: Use database-generated timestamps only.
     *
     * @param createdAt the creation timestamp.
     */
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
