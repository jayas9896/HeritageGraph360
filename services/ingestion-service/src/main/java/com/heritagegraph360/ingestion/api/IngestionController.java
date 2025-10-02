package com.heritagegraph360.ingestion.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles REST ingestion for small record submissions.
 * Importance: Supports user-driven or admin-driven ingestion workflows.
 * Alternatives: Require all ingestion through gRPC only.
 */
@RestController
@RequestMapping("/api/v1/ingestion")
public class IngestionController {
    /**
     * Accepts a single ingestion record for processing.
     * Importance: Enables low-volume ingestion with immediate feedback.
     * Alternatives: Batch records using multipart uploads.
     *
     * @param request the ingestion request.
     * @return the ingestion response.
     */
    @PostMapping("/records")
    public ResponseEntity<IngestionResponse> ingestRecord(@RequestBody IngestionRequest request) {
        IngestionResponse response = new IngestionResponse("ACCEPTED", "Record queued");
        return ResponseEntity.ok(response);
    }
}
