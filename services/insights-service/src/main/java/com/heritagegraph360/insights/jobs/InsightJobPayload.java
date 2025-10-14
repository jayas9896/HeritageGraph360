package com.heritagegraph360.insights.jobs;

/**
 * Represents a queued insight job payload.
 * Importance: Encapsulates tenant and profile data for async processing.
 * Alternatives: Use a raw JSON payload without a typed wrapper.
 */
public class InsightJobPayload {
    private String tenantId;
    private String profileId;
    private String evidenceSummary;

    /**
     * Returns the tenant identifier.
     * Importance: Ensures tenant isolation for job processing.
     * Alternatives: Resolve tenant from headers only.
     *
     * @return the tenant identifier.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Updates the tenant identifier.
     * Importance: Supports payload binding for jobs.
     * Alternatives: Use constructor-only immutable payloads.
     *
     * @param tenantId the tenant identifier.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Returns the profile identifier.
     * Importance: Links job processing to a profile.
     * Alternatives: Use a hashed public profile identifier.
     *
     * @return the profile identifier.
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * Updates the profile identifier.
     * Importance: Supports payload binding for jobs.
     * Alternatives: Use constructor-only immutable payloads.
     *
     * @param profileId the profile identifier.
     */
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    /**
     * Returns the evidence summary.
     * Importance: Provides input for insight summarization.
     * Alternatives: Use structured evidence objects.
     *
     * @return the evidence summary.
     */
    public String getEvidenceSummary() {
        return evidenceSummary;
    }

    /**
     * Updates the evidence summary.
     * Importance: Supports payload binding for jobs.
     * Alternatives: Use constructor-only immutable payloads.
     *
     * @param evidenceSummary the evidence summary.
     */
    public void setEvidenceSummary(String evidenceSummary) {
        this.evidenceSummary = evidenceSummary;
    }
}
