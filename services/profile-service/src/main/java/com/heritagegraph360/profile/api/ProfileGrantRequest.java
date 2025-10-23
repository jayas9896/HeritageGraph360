package com.heritagegraph360.profile.api;

/**
 * Represents a profile grant request.
 * Importance: Captures grant inputs for per-person visibility.
 * Alternatives: Use a policy engine instead of explicit grants.
 */
public class ProfileGrantRequest {
    private String granteeAccountId;
    private String fieldName;
    private String visibility;

    /**
     * Returns the grantee account identifier.
     * Importance: Identifies the recipient of the grant.
     * Alternatives: Use a group identifier.
     *
     * @return the grantee account identifier.
     */
    public String getGranteeAccountId() {
        return granteeAccountId;
    }

    /**
     * Updates the grantee account identifier.
     * Importance: Supports payload binding for grant requests.
     * Alternatives: Use constructor-only immutable requests.
     *
     * @param granteeAccountId the grantee account identifier.
     */
    public void setGranteeAccountId(String granteeAccountId) {
        this.granteeAccountId = granteeAccountId;
    }

    /**
     * Returns the field name.
     * Importance: Specifies the field being granted.
     * Alternatives: Use structured field enums.
     *
     * @return the field name.
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Updates the field name.
     * Importance: Supports payload binding for grant requests.
     * Alternatives: Use structured field enums.
     *
     * @param fieldName the field name.
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Returns the visibility level.
     * Importance: Captures the grant visibility.
     * Alternatives: Use a boolean for visibility.
     *
     * @return the visibility level.
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * Updates the visibility level.
     * Importance: Supports payload binding for grant requests.
     * Alternatives: Use a boolean for visibility.
     *
     * @param visibility the visibility level.
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
