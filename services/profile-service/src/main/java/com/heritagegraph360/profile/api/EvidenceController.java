package com.heritagegraph360.profile.api;

import com.heritagegraph360.profile.evidence.EvidenceRequest;
import com.heritagegraph360.profile.evidence.EvidenceService;
import com.heritagegraph360.profile.nosql.EvidenceRecordDocument;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles evidence ingestion for profiles.
 * Importance: Stores provenance data for lineage verification.
 * Alternatives: Use a dedicated evidence ingestion service.
 */
@RestController
@RequestMapping("/api/v1/profiles")
public class EvidenceController {
    private final EvidenceService evidenceService;

    /**
     * Creates the evidence controller.
     * Importance: Connects HTTP requests to evidence storage.
     * Alternatives: Use a dedicated evidence service.
     *
     * @param evidenceService the evidence service.
     */
    public EvidenceController(EvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }

    /**
     * Stores evidence for a profile.
     * Importance: Persists provenance metadata for verification.
     * Alternatives: Require offline evidence ingestion.
     *
     * @param tenantId the tenant identifier.
     * @param profileId the profile identifier.
     * @param request the evidence request.
     * @return the stored evidence document.
     */
    @PostMapping("/{profileId}/evidence")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('REVIEWER')")
    public ResponseEntity<EvidenceRecordDocument> storeEvidence(
        @RequestHeader("x-tenant-id") String tenantId,
        @PathVariable String profileId,
        @RequestBody EvidenceRequest request) {
        return ResponseEntity.ok(evidenceService.storeEvidence(tenantId, request));
    }
}
