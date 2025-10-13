package com.heritagegraph360.profile.api;

import com.heritagegraph360.profile.service.ProfileWorkflowService;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles merge workflow requests.
 * Importance: Coordinates duplicate resolution with provenance tracking.
 * Alternatives: Use a batch merge pipeline with offline review.
 */
@RestController
@RequestMapping("/api/v1/profiles")
public class MergeController {
    private final ProfileWorkflowService workflowService;

    /**
     * Creates a controller with workflow dependencies.
     * Importance: Connects HTTP requests to profile workflows.
     * Alternatives: Use a separate handler layer for routing.
     *
     * @param workflowService the workflow service.
     */
    public MergeController(ProfileWorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    /**
     * Submits a merge request for a profile.
     * Importance: Initiates staged merge workflows with audit requirements.
     * Alternatives: Perform automatic merges without human review.
     *
     * @param tenantId the tenant identifier.
     * @param actorId the actor identifier.
     * @param profileId the profile identifier.
     * @param request the merge request.
     * @return the merge response.
     */
    @PostMapping("/{profileId}/merge")
    public ResponseEntity<MergeResponse> requestMerge(
        @RequestHeader("x-tenant-id") String tenantId,
        @RequestHeader("x-actor-id") String actorId,
        @PathVariable String profileId,
        @RequestBody MergeRequest request) {
        try {
            MergeResponse response = workflowService.requestMerge(
                tenantId,
                UUID.fromString(actorId),
                UUID.fromString(profileId),
                request);
            return ResponseEntity.ok(response);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
