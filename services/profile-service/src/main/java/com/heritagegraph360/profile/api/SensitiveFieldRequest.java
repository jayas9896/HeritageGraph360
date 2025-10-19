package com.heritagegraph360.profile.api;

/**
 * Represents a sensitive field storage request.
 * Importance: Captures sensitive field input for encryption.
 * Alternatives: Use multipart uploads for sensitive data.
 */
public class SensitiveFieldRequest {
    private String fieldType;
    private String plainValue;

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
     * Importance: Supports payload binding for sensitive fields.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param fieldType the field type.
     */
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    /**
     * Returns the plaintext value.
     * Importance: Provides input for encryption.
     * Alternatives: Use client-side encryption and send ciphertext.
     *
     * @return the plaintext value.
     */
    public String getPlainValue() {
        return plainValue;
    }

    /**
     * Updates the plaintext value.
     * Importance: Supports payload binding for sensitive fields.
     * Alternatives: Use client-side encryption and send ciphertext.
     *
     * @param plainValue the plaintext value.
     */
    public void setPlainValue(String plainValue) {
        this.plainValue = plainValue;
    }
}
