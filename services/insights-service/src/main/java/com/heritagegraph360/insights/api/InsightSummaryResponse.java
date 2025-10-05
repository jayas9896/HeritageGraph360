package com.heritagegraph360.insights.api;

/**
 * Represents an insight summary for a profile.
 * Importance: Provides a stable contract for insight consumers.
 * Alternatives: Use a generic insight response wrapper.
 */
public class InsightSummaryResponse {
    private final String profileId;
    private final String status;
    private final String summary;

    /**
     * Creates an insight summary response.
     * Importance: Standardizes insight payloads across clients.
     * Alternatives: Include a list of insight findings instead.
     *
     * @param profileId the profile identifier.
     * @param status the insight status.
     * @param summary the summary message.
     */
    public InsightSummaryResponse(String profileId, String status, String summary) {
        this.profileId = profileId;
        this.status = status;
        this.summary = summary;
    }

    /**
     * Returns the profile identifier.
     * Importance: Correlates the insight with a profile.
     * Alternatives: Use a hashed public profile identifier.
     *
     * @return the profile identifier.
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * Returns the insight status.
     * Importance: Indicates whether anomalies were detected.
     * Alternatives: Use a severity enum.
     *
     * @return the insight status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the insight summary message.
     * Importance: Provides human-readable context for reviewers.
     * Alternatives: Return structured findings only.
     *
     * @return the insight summary.
     */
    public String getSummary() {
        return summary;
    }
}
