package com.heritagegraph360.insights.api;

import com.heritagegraph360.insights.nosql.InsightSummaryDocument;
import com.heritagegraph360.insights.nosql.InsightSummaryRepository;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes insight summaries for lineage anomalies.
 * Importance: Enables reviewers to access auditable insight explanations.
 * Alternatives: Provide insights only via async reports.
 */
@RestController
@RequestMapping("/api/v1/insights")
public class InsightsController {
    private final InsightSummaryRepository insightSummaryRepository;

    /**
     * Creates a controller with repository dependencies.
     * Importance: Enables insight retrieval from persistent storage.
     * Alternatives: Use a separate query service for insights.
     *
     * @param insightSummaryRepository the repository.
     */
    public InsightsController(InsightSummaryRepository insightSummaryRepository) {
        this.insightSummaryRepository = insightSummaryRepository;
    }

    /**
     * Returns an insight summary for a profile.
     * Importance: Provides actionable insight visibility for reviewers.
     * Alternatives: Require report generation before viewing insights.
     *
     * @param tenantId the tenant identifier.
     * @param profileId the profile identifier.
     * @return the insight summary response.
     */
    @GetMapping("/{profileId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('REVIEWER')")
    public ResponseEntity<InsightSummaryResponse> getInsightSummary(
        @RequestHeader("x-tenant-id") String tenantId,
        @PathVariable String profileId) {
        Optional<InsightSummaryDocument> document =
            insightSummaryRepository.findByTenantIdAndProfileId(tenantId, profileId);
        if (document.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        InsightSummaryDocument summary = document.get();
        InsightSummaryResponse response = new InsightSummaryResponse(
            summary.getProfileId(), summary.getStatus(), summary.getSummary());
        return ResponseEntity.ok(response);
    }
}
