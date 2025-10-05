package com.heritagegraph360.insights.ai;

import org.springframework.stereotype.Component;

/**
 * Provides OpenAI insight summarization capability.
 * Importance: Enables human-readable anomaly explanations for reviewers.
 * Alternatives: Use a rules-based summarizer only.
 */
@Component
public class OpenAiInsightClient {
    /**
     * Summarizes an anomaly using redacted evidence context.
     * Importance: Produces reviewer-friendly explanations for audit workflows.
     * Alternatives: Generate summaries through template-based rendering.
     *
     * @param tenantId the tenant identifier.
     * @param evidenceSummary the redacted evidence summary.
     * @return the summary text.
     */
    public String summarizeAnomaly(String tenantId, String evidenceSummary) {
        return "Summary for " + tenantId + ": " + evidenceSummary;
    }
}
