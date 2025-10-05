package com.heritagegraph360.insights.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes insight summaries for lineage anomalies.
 * Importance: Enables reviewers to access auditable insight explanations.
 * Alternatives: Provide insights only via async reports.
 */
@RestController
@RequestMapping("/api/v1/insights")
public class InsightsController {
    /**
     * Returns an insight summary for a profile.
     * Importance: Provides actionable insight visibility for reviewers.
     * Alternatives: Require report generation before viewing insights.
     *
     * @param profileId the profile identifier.
     * @return the insight summary response.
     */
    @GetMapping("/{profileId}")
    public ResponseEntity<InsightSummaryResponse> getInsightSummary(@PathVariable String profileId) {
        InsightSummaryResponse response = new InsightSummaryResponse(profileId, "NO_ANOMALY", "No conflicts detected");
        return ResponseEntity.ok(response);
    }
}
