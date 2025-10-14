package com.heritagegraph360.ingestion.api;

import com.heritagegraph360.ingestion.service.IngestionProcessingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles REST ingestion for small record submissions.
 * Importance: Supports user-driven or admin-driven ingestion workflows.
 * Alternatives: Require all ingestion through gRPC only.
 */
@RestController
@RequestMapping("/api/v1/ingestion")
public class IngestionController {
    private final IngestionProcessingService processingService;

    /**
     * Creates the ingestion controller.
     * Importance: Connects REST ingestion to processing workflows.
     * Alternatives: Use a separate handler layer for ingestion routing.
     *
     * @param processingService the ingestion processing service.
     */
    public IngestionController(IngestionProcessingService processingService) {
        this.processingService = processingService;
    }

    /**
     * Accepts a single ingestion record for processing.
     * Importance: Enables low-volume ingestion with immediate feedback.
     * Alternatives: Batch records using multipart uploads.
     *
     * @param tenantId the tenant identifier.
     * @param actorId the actor identifier.
     * @param request the ingestion request.
     * @return the ingestion response.
     */
    @PostMapping("/records")
    public ResponseEntity<IngestionResponse> ingestRecord(
        @RequestHeader("x-tenant-id") String tenantId,
        @RequestHeader("x-actor-id") String actorId,
        @RequestBody IngestionRequest request) {
        request.setTenantId(tenantId);
        processingService.processRestRequest(request);
        IngestionResponse response = new IngestionResponse("ACCEPTED", "Record queued by " + actorId);
        return ResponseEntity.ok(response);
    }
}
